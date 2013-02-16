package info.kyorohiro.helloworld.pfdep.android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Utility {

	public static boolean sIsDebug = false;
	public static boolean isDebuggingFromCash() {
		return sIsDebug;
	}
	public static boolean isDebugging(Context ctx){
		PackageManager manager = ctx.getPackageManager();
		ApplicationInfo appInfo = null;
		try {
			appInfo = manager.getApplicationInfo(ctx.getPackageName(), 0);
			if((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE) {
				sIsDebug = true;
			}
		} catch (NameNotFoundException e) {
			sIsDebug = false;
		}
		return isDebuggingFromCash();
	}

	
	public static double pixel2inchi(double from) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		return from/((metrics.xdpi+metrics.ydpi)/2.0);
	}

	public static double inchi2pixel(double from) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		return from*((metrics.xdpi+metrics.ydpi)/2.0);
	}

	public static double inchi2mm(double from){
		return from*25.4;
	}


	public static double mm2inchi(double from){
		return (from/25.4);
	}
}
