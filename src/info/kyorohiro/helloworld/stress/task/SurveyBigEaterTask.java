package info.kyorohiro.helloworld.stress.task;

import java.util.List;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import info.kyorohiro.helloworld.stress.service.KyoroStressService;
import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.stressv2.ProcessInfo;
import info.kyorohiro.helloworld.util.KyoroMemoryInfo;

public class SurveyBigEaterTask implements Runnable {
	@Override
	public void run() {
		Application app = KyoroApplication.getKyoroApplication();
		BigEaterInfo workerInfo = BigEaterInfo.getInstance();

		//
		ProcessInfo[] worker = workerInfo.getWorkerInfo();
		KyoroMemoryInfo.updateProcessInfo(app, worker);
		KyoroMemoryInfo.updateMemoryInfo(app, worker);
	}

}
