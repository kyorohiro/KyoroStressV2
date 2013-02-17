package info.kyorohiro.helloworld.stress.task;


import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import java.util.LinkedList;


public class EatUpJavaHeapTask implements Runnable {
	private LinkedList<byte[]> mBuffer = new LinkedList<byte[]>();
	public static int mEatUpSize = 10*1024*1024;
	public static int mAtomSize = 1024*4;

	public EatUpJavaHeapTask(LinkedList<byte[]> buffer) {
		if(buffer == null) {
			buffer = new LinkedList<byte[]>();
		}
		try {
			mEatUpSize = KyoroSetting.getEatupHeapSize() * 1024;
//			android.util.Log.v("kiyo","---"+KyoroSetting.getEatupHeapSize());
		} catch(Throwable t) {
			t.printStackTrace();
		}
		mBuffer = buffer;
	}

	@Override
	public void run() {
		String retryValue = null;
		long eatup = 0;
		try {
			while(true) {
				retryValue = KyoroSetting.getRetry();
				eatup += StressUtility.eatUpJavaHeap(mBuffer, mEatUpSize, mAtomSize);
				if(KyoroSetting.RETRY_ON.equals(retryValue)&& eatup < mEatUpSize){
					Thread.sleep(500);
				} else {
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
