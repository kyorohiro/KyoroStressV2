package info.kyorohiro.helloworld.stress.task;

import info.kyorohiro.helloworld.stress.service.KyoroStressService;
import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import info.kyorohiro.helloworld.stressv2.ProcessInfo;

public class StartBigEaterTask implements Runnable {

	@Override
	public void run() {
		startAll();
	}

	private void startAll() {
		int numOfWorker = KyoroSetting.getNumOfBigEater();
		BigEaterInfo info = BigEaterInfo.getInstance();
		info.clear();
		for(int i=0;i<numOfWorker;i++) {
			ProcessInfo processInfo = new ProcessInfo();
			processInfo.mID = KyoroStressService.getID(i);
			info.append(processInfo);
		}

		try {
			ProcessInfo[] processInfos = info.getWorkerInfo();
			for (ProcessInfo processInfo:processInfos) {
					startService(processInfo.mID);
					Thread.sleep(100);
					Thread.yield();
			}

		} catch (Exception e) {

		}
	}

	public void startService(String id) {
		android.util.Log.v("kiyo","#kick startService:"+id);
		KyoroSetting.setBigEaterState(id, KyoroStressService.START_SERVICE);
		KyoroStressService.startService(KyoroApplication.getKyoroApplication(), id, "start");
	}
}
