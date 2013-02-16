package info.kyorohiro.helloworld.util;


import info.kyorohiro.helloworld.stress.service.KyoroStressService;
import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.stressv2.ProcessInfo;

import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.util.Log;


public class KyoroMemoryInfo {

	public static List<RunningAppProcessInfo>  getRunningAppList(Context context) {
		ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningApp = manager.getRunningAppProcesses();
		return runningApp;
	}

	public static ActivityManager.MemoryInfo getMemoryInfo(Context context) {
	    ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
	    activityManager.getMemoryInfo(memoryInfo);
		return memoryInfo;
	}
	public static void updateProcessInfo(Context context, ProcessInfo[] output) {
		List<RunningAppProcessInfo> runningWorker = KyoroStressService.getRunningBigEater(context);

	    for(int i=0;i<output.length;i++) {
	    	String workerProcessName = KyoroStressService.getProcessName(output[i].mID);
	    	boolean update = false;
	    	for(RunningAppProcessInfo info:runningWorker) {
	    		String runningProcessName = info.processName;
	    		if(workerProcessName.equals(runningProcessName)){
	    			output[i].mPID = info.pid;
	    			update = true;
	    			break;
	    		}
	    	}
	    	if(!update) {
	    		output[i].mPID = -1;
	    	}
	    }
	}
	public static void updateMemoryInfo(Context context, ProcessInfo[] output) {
	    ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
	    activityManager.getMemoryInfo(memoryInfo);
	    
	    int[] pids = new int[output.length];
	    for(int i=0;i<pids.length;i++) {
	    	pids[i] = output[i].mPID;
	    }

	    android.os.Debug.MemoryInfo[] infos= getMemInfoData(context, pids);
	    for(int i=0;i<pids.length;i++) {
	    	output[i].mTPD = infos[i].getTotalPrivateDirty();
	    	output[i].mTSD = infos[i].getTotalSharedDirty();
	    	output[i].mTPss= infos[i].getTotalPss();
	    }
	}

	public static android.os.Debug.MemoryInfo[] getMemInfoData(Context context, int[] pids) {
		ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		android.os.Debug.MemoryInfo[] memoryInfoArray = manager.getProcessMemoryInfo(pids);
		return memoryInfoArray;
	}
	
	public void log(android.os.Debug.MemoryInfo[] memoryInfoArray) {
		for(android.os.Debug.MemoryInfo pidMemoryInfo: memoryInfoArray) {
			Log.v("kyorohiro", "===================================================");
			Log.v("kyorohiro", "getTotalPrivateDirty: " + pidMemoryInfo.getTotalPrivateDirty());
			Log.v("kyorohiro", "getTotalSharedDirty: " + pidMemoryInfo.getTotalSharedDirty());
			Log.v("kyorohiro", "getTotalPss: " + pidMemoryInfo.getTotalPss());
			Log.v("kyorohiro", "---------------------------------------------------");
			Log.v("kyorohiro", "dalvikPrivateDirty: " + pidMemoryInfo.dalvikPrivateDirty);
			Log.v("kyorohiro", "dalvikPss: " + pidMemoryInfo.dalvikPss);
			Log.v("kyorohiro", "dalvikSharedDirty: " + pidMemoryInfo.dalvikSharedDirty);
			Log.v("kyorohiro", "nativePrivateDirty: " + pidMemoryInfo.nativePrivateDirty);
			Log.v("kyorohiro", "nativePss: " + pidMemoryInfo.nativePss);
			Log.v("kyorohiro", "nativeSharedDirty: " + pidMemoryInfo.nativeSharedDirty);
			Log.v("kyorohiro", "otherPrivateDirty: " + pidMemoryInfo.otherPrivateDirty);
			Log.v("kyorohiro", "otherPss: " + pidMemoryInfo.otherPss);
			Log.v("kyorohiro", "otherSharedDirty: " + pidMemoryInfo.otherSharedDirty);
			Log.v("kyorohiro", "===================================================");
		}
	}
}