package info.kyorohiro.helloworld.stress.task;

import java.lang.ref.WeakReference;
import java.util.List;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import info.kyorohiro.helloworld.stress.service.KyoroStressService;

//
//
//
public class DeadOrAliveTask  implements Runnable {

	private WeakReference<Context> mRef = null;

	public DeadOrAliveTask(Context context) {
		mRef = new WeakReference<Context>(context);
	}

	public void run() {
		try {
			int len = KyoroStressService.JavaHeapEater.length;
			for (int i=0;i<len;i++) {
				task(KyoroStressService.JavaHeapEater[i], KyoroStressService.ServiceProcessName[i]);
				Thread.sleep(10);
				Thread.yield();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private void task(Class clazz, String processName) {
		Context con = mRef.get();
		if(con==null) {
			return;
		}

		if (KyoroStressService.START_SERVICE.equals(KyoroSetting.getBigEaterState(processName))) {
			KyoroStressService.startService(KyoroApplication.getKyoroApplication(), processName, "restart");
		}
		
		//
		Application application = KyoroApplication.getKyoroApplication();
		String processUrl = application.getApplicationContext().getPackageName()+":"+processName;
		List<RunningAppProcessInfo> list = null;
		list = KyoroStressService.getRunningBigEater(application);
		for (RunningAppProcessInfo i : list) {
			String p = i.processName;
			if (p.equals(processUrl)) {
				if (!KyoroStressService.START_SERVICE.equals(KyoroSetting.getBigEaterState(processName))) {
					KyoroStressService.stopService(clazz, con);
				}
				return;
			}
		}
	}
}