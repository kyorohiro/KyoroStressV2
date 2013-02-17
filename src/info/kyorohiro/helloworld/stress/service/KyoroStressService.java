package info.kyorohiro.helloworld.stress.service;


import java.util.LinkedList;
import java.util.List;

import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import info.kyorohiro.helloworld.stressv2.MainActivity;
import info.kyorohiro.helloworld.stressv2.R;
import info.kyorohiro.helloworld.stress.task.DeadOrAliveTask;
import info.kyorohiro.helloworld.stress.task.EatUpJavaHeapTask;
import info.kyorohiro.helloworld.stress.task.EatUpMemoryFileTask;
import info.kyorohiro.helloworld.stress.task.StressUtility;
import info.kyorohiro.helloworld.util.KyoroMemoryInfo;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.IBinder;

public abstract class KyoroStressService extends ForegroundService {

	private LinkedList<byte[]> mBuffer = new LinkedList<byte[]>();
	private Thread mTask = null;
	private long startTime = System.currentTimeMillis();

	public static final String START_SERVICE = "start";
	public static final String STOP_SERVICE = "stop";

	public static String ID_00 = "no00";
	public static String ID_01 = "no01";
	public static String ID_02 = "no02";
	public static String ID_03 = "no03";
	public static String ID_04 = "no04";
	public static String ID_05 = "no05";
	public static String ID_06 = "no06";
	public static String ID_07 = "no07";
	public static String ID_08 = "no08";
	public static String ID_09 = "no09";
	public static String ID_10 = "no10";
	public static String ID_11 = "no11";
	public static String ID_12 = "no12";
	public static String ID_13 = "no13";
	public static String ID_14 = "no14";
	public static String ID_15 = "no15";
	public static String ID_16 = "no16";
	public static String ID_17 = "no17";
	public static String ID_18 = "no18";
	public static String ID_19 = "no19";
	public static String ID_20 = "no20";
	public static String ID_21 = "no21";
	public static String ID_22 = "no22";
	public static String ID_23 = "no23";
	public static String ID_24 = "no24";
	public static String ID_25 = "no25";
	public static String ID_26 = "no26";
	public static String ID_27 = "no27";
	public static String ID_28 = "no28";
	public static String ID_29 = "no29";
	public static String ID_30 = "no30";
	public static String ID_31 = "no31";
	public static String ID_32 = "no32";
	
	private String mCurrentDisplayMessage = "";

	public final static String ServiceProcessName[] = new String[]{
		ID_00,ID_01, ID_02, ID_03,
		ID_04,ID_05, ID_06, ID_07,
		ID_08,ID_09, ID_10, ID_11,
		ID_12,ID_13, ID_14, ID_15,
		ID_16,ID_17, ID_18, ID_19,
		ID_20,ID_21, ID_22, ID_23,
		ID_24,ID_25, ID_26, ID_27,
		ID_28,ID_29, ID_30, ID_31,
		ID_32,
	};

	public final static Class JavaHeapEater[] = new Class[] {
		BigEater000Gouki.class,
		BigEater001Gouki.class, 
		BigEater002Gouki.class,
		BigEater003Gouki.class,
		BigEater004Gouki.class,
		BigEater005Gouki.class,
		BigEater006Gouki.class,
		BigEater007Gouki.class,
		BigEater008Gouki.class,
		BigEater009Gouki.class,
		BigEater010Gouki.class,
		BigEater011Gouki.class, 
		BigEater012Gouki.class,
		BigEater013Gouki.class,
		BigEater014Gouki.class,
		BigEater015Gouki.class,
		BigEater016Gouki.class,
		BigEater017Gouki.class,
		BigEater018Gouki.class, 
		BigEater019Gouki.class,
		BigEater020Gouki.class,
		BigEater021Gouki.class,
		BigEater022Gouki.class,
		BigEater023Gouki.class,
		BigEater024Gouki.class,
		BigEater025Gouki.class,
		BigEater026Gouki.class,
		BigEater027Gouki.class,
		BigEater028Gouki.class, 
		BigEater029Gouki.class,
		BigEater030Gouki.class,
		BigEater031Gouki.class,
		BigEater032Gouki.class,
	};

	public static String getID(int number) {
			return ServiceProcessName[number];
	}

	public static Class getClassFromID(String id) {
		int len = ServiceProcessName.length;
		for(int i=0;i<len;i++) {
			if(ServiceProcessName[i].equals(id)) {;
				return JavaHeapEater[i];
			}
		}
		return null;
	}

	public static List<RunningAppProcessInfo> getRunningBigEater(Context context) {
		Application app = KyoroApplication.getKyoroApplication();
		List<RunningAppProcessInfo>  infos = KyoroMemoryInfo.getRunningAppList(app);

		List<RunningAppProcessInfo> runningBegEater = new LinkedList<ActivityManager.RunningAppProcessInfo>();
		for(RunningAppProcessInfo processinfo:infos) {
			if(processinfo.processName.startsWith(app.getPackageName())) {
				runningBegEater.add(processinfo);
			}
		}

		return runningBegEater;
	}

	public static Intent startService(Context context, String id, String message) {
		Class clazz = getClassFromID(id);
//		android.util.Log.v("kiyo","kick startService:"+clazz.toString());
		Intent startIntent = new Intent(context, clazz);
	    if(message != null){
	    	startIntent.putExtra("message", message);
	    }
	    context.startService(startIntent);
	    return startIntent;
	}

	public static String getProcessName(String processName) {
		Context con =  KyoroApplication.getKyoroApplication().getApplicationContext();
		return con.getPackageName()+":"+ processName;
	}

	public static Intent stopService(Class clazz, Context context) {
		Intent startIntent = new Intent(context, clazz);
	    context.stopService(startIntent);
	    return startIntent;
	}

	public abstract String getProperty();

	public KyoroStressService(int IdOfStartForeground) {
		super(IdOfStartForeground);
	}	
	
	public String getLabel() {
		return "KyoroStress";
	}

	public String getMessage() {
		return "eatup java heap";
	}
	

	@Override
	public void onCreate() {
		android.util.Log.v("kiyo","onCreate()"+getProperty());
		super.onCreate();
		stopForegroundCompat();
		mBuffer = new LinkedList<byte[]>(); 
		if (START_SERVICE.equals(""+KyoroSetting.getBigEaterState(getProperty()))) {
			startForground("restart");
		}
		onStartHandle(null);
	}

	@Override
	public void onStartHandle(Intent intent) {
		String message = "";
		
		if(intent !=null && null != intent.getExtras()){
		    message = intent.getExtras().getString("message");
		}
/*
		if(message != null && message.equals("end")){
			// when still cant not showed notificartion
			long endTime = System.currentTimeMillis();
			long waitTime = (endTime-startTime);
			Handler h = new Handler();
			if(waitTime<2000){
				waitTime = 2000;
			}
			h.postDelayed(new DelayAndStop(),waitTime);
			return;
		}

		if (!START_SERVICE.equals(""+KyoroSetting.getBigEaterState(getProperty()))) {
			Handler h = new Handler();
			h.postDelayed(new DelayAndStop(),100);
			return;
		}*/

		if(startTask()){
			startForground(message);
		}
	}

	private class DelayAndStop implements Runnable{
		@Override
		public void run() {
			stopForegroundCompat();
			stopSelf();	
		}
	}


	public void startForground(String messagePlus) {
			mCurrentDisplayMessage = messagePlus;
			startTime = System.currentTimeMillis();
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
			int resId = R.drawable.ic_launcher;
			String title = getLabel()+":"+ getProperty();
			String message = getMessage()+":"+messagePlus;
			startForgroundAtOnGoing(resId, title, message, contentIntent);
	}

	@Override
	public void onDestroy() {
		android.util.Log.v("kiyohiro","onDestry()"+getClass().getName());
		super.onDestroy();
		if(mTask != null){
			mTask.interrupt();
			mTask = null;
		}
		endTask();
	}

	@Override
	public IBinder onBind(Intent intent) {return null;}

	
	//
	// Task
	//
	private Thread mTh = null;
	public Object mHeap = null;

	public boolean startTask() {
		if(mTh == null || mHeap == null /*|| !mTh.isAlive()*/) {
			if (KyoroSetting.MEMORYFILE_ON.equals(KyoroSetting.getIsMomoryFile())) {
				mTh = new MyStarter((EatUpMemoryFileTask)(mHeap = new EatUpMemoryFileTask(null)));
			} else {
				mTh = new MyStarter((EatUpJavaHeapTask)(mHeap = new EatUpJavaHeapTask(null)));
			}
			mTh.start();
//			android.util.Log.v("kiyo",""+mHeap);
			return true;
		}
		else {
			return false;
		}
	}

	public void endTask() {
		if(mTh !=null && mTh.isAlive()) {
			mTh.interrupt();
			mTh = null;
		}
	}

	public class MyStarter extends Thread {
		public MyStarter(Runnable runnable) {
			super(runnable);
		}

		@Override
		public void run() {
			try {
				startForground("lahalito pf");
				super.run();
				startForground("kadorto to party");
				DeadOrAliveTask task = new DeadOrAliveTask(KyoroStressService.this);
				try {
					while(mTh==Thread.currentThread()){
						task.run();
						String retryValue = KyoroSetting.getRetry();
						if(!KyoroSetting.RETRY_ON.equals(retryValue)){
							break;
						}
						Thread.sleep(3000);
					}
				}catch(InterruptedException e) {

				}
				startForground("done all task");
			} finally {

			}
		}
	}
}
