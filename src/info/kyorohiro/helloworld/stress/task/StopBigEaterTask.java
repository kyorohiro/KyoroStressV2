package info.kyorohiro.helloworld.stress.task;


import info.kyorohiro.helloworld.stress.service.KyoroStressService;
import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import info.kyorohiro.helloworld.stressv2.ProcessInfo;

public class StopBigEaterTask implements Runnable {

	@Override
	public void run() {
		stopAll();
	}

	private static void stopAll() {
		try {
			int len = KyoroStressService.numOfBigEater();
			for (int i=0;i<len;i++) {
				String id = KyoroStressService.getID(i);
				stopService(id);
				Thread.sleep(10);
				Thread.yield();
			}
		} catch (Exception e) {
		}
	}

	private static void stopFromBigEaterInfo() {
		BigEaterInfo info = BigEaterInfo.getInstance();
		try {
			ProcessInfo[] processInfos = info.getWorkerInfo();
			for (ProcessInfo processInfo:processInfos) {
					stopService(processInfo.mID);
					Thread.sleep(100);
					Thread.yield();
			}
		} catch (Exception e) {

		}
	}

	public static void stopService(String id) {
		android.util.Log.v("kiyo","#kick stopService:"+id);
		Class clazz = KyoroStressService.getClassFromID(id);
		KyoroSetting.setBigEaterState(id, KyoroStressService.STOP_SERVICE);
		KyoroStressService.stopService(clazz, KyoroApplication.getKyoroApplication()); 
	}
}
