package info.kyorohiro.helloworld.util;


/**
 * 
 * Management Task. Current running thread is end. and nextTask is run.
 *
 */
public class SingleTaskRunner {

	private Thread mTaskRunner = null;
	private Runnable mNextTask = null;
	private Thread mTaskUpdater = null;
	
	// todo following field have memory leak potential
	private Runnable mCurrentTask = null;

	private void log(String message) {
//		System.out.println("#SingleTaskRunner#"+message);
//		android.util.Log.v("kiyo","#SingleTaskRunner#"+message);
	}

	public Runnable getCurrentTask() {
		return mCurrentTask;
	}

	public boolean isAliveUpdater() {
		if(mTaskUpdater == null || !mTaskUpdater.isAlive()) {
			return false;
		} else {
			return true;
		}
	}
	public boolean isAlive() {
		if(mTaskRunner == null || !mTaskRunner.isAlive()) {
			return false;
		} else {
			return true;
		}
	}

	//
	// current running thread is end. and nextTask is run.
	//
	public synchronized void startTask(Runnable nextTask) {
		log("startTask");
		// 
		// this method don't call updateTask method.
		// if you call updateTask. depend on this method caller.
		// 
		mNextTask = nextTask;
		if(mTaskUpdater == null || !mTaskUpdater.isAlive()) {
			log("--2--");
			mTaskUpdater = new UpdateTaskThread();
			mTaskUpdater.start();
		}
		log("--3--");
	}

	public synchronized void endTask() {
		log("endTask");
		if(mTaskRunner !=null && mTaskRunner.isAlive()) {
			log("endTask -1-");
			mTaskRunner.interrupt();
			mTaskRunner = null;
		}
		mTaskRunner = null;
	}


	public synchronized void updateTask() {
		log("done 1");
		if(mNextTask != null) {
			updateTask(mNextTask);
			mNextTask = null;
		}
	}
	public synchronized void updateTask(Runnable task) {
		log("done 2");
		if(task == null) {
			log("done 2-1");
			endTask();
			return;
		}
		if(mTaskRunner !=null && mTaskRunner.isAlive()) {
			log("done 2-2");
			Thread tmp = mTaskRunner;
			mTaskRunner = null;
			try {
				if(tmp != null&&tmp.isAlive()) {
					tmp.interrupt();
					tmp.join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log("done 2-3");
		mTaskRunner = new Thread(mCurrentTask = task);
		mTaskRunner.start();
	}

	public class UpdateTaskThread extends Thread {
		@Override
		public void run() {
			updateTask();
		}
	}
}
