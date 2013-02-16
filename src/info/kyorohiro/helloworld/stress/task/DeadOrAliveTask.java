package info.kyorohiro.helloworld.stress.task;

import java.lang.ref.WeakReference;
import java.util.List;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import info.kyorohiro.helloworld.stress.service.KyoroStressService;
import info.kyorohiro.helloworld.util.KyoroMemoryInfo;

public class DeadOrAliveTask  implements Runnable {

	private WeakReference<Context> mRef = null;

	public DeadOrAliveTask(Context context) {
		mRef = new WeakReference<Context>(context);
	}

	public void run() {
		try {
			int len = KyoroStressService.JavaHeapEater.length;
			for (int i=0;i<len;i++) {
				task(KyoroStressService.JavaHeapEater[i],KyoroStressService.ServiceProcessName[i]);
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
		KyoroMemoryInfo infos = new KyoroMemoryInfo();
		List<RunningAppProcessInfo> list = null;
		list = infos.getRunningAppList(KyoroApplication.getKyoroApplication());
		String c = KyoroApplication.getKyoroApplication().getApplicationContext().getPackageName()+":"+processName;

		for(RunningAppProcessInfo i : list) {
			String p = i.processName;
			if(p.equals(c)){
				if(!KyoroStressService.START_SERVICE.equals(KyoroSetting.getBigEaterState(processName))){
					KyoroStressService.stopService(clazz, con);
				}
				return;
			}
		}
/*
		if(KyoroStressService.START_SERVICE.equals(KyoroSetting.getBigEaterState(processName))){
			KyoroStressService.startService(clazz, KyoroApplication.getKyoroApplication(), "restart");
		}
*/
	}
}