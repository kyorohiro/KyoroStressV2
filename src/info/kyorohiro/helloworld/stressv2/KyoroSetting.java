 package info.kyorohiro.helloworld.stressv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class KyoroSetting {

	private static Object lock = new Object();
	public static final String TAG_MEMSIZE = "eadup_size_kb";
	public static final String TAG_NUM_OF_BIGEATER = "NumOfBigEater";
	public static final int NUM_OF_BIGEATER_DEFAULT_VALUE = 12;
	public static final int MEMSIZE_DEFAULT_VALUE = 1024 * 10;
	public static final String TAG_RETRY = "retry";
	public static final String RETRY_ON = "on";
	public static final String RETRY_OFF = "off";
	public static final String RETRY_DEFAULT = RETRY_ON;

	public static String getRetry() {
		String retry = RETRY_DEFAULT;
		try {
			String t = getData(TAG_RETRY);
			if (t != null && !t.equals("none")) {
				retry = t;
			}
		} catch (Throwable t) {
		}
		return retry;
	}

	public static void setRetry(String value) {
		try {
			setData(TAG_RETRY, value);
		} catch (Throwable t) {
		}
	}

	public static void setBigEaterState(String id, String value) {
		setData(KyoroApplication.getKyoroApplication(), id, value, id);
	}

	public static String getBigEaterState(String id) {
		String value = getData(KyoroApplication.getKyoroApplication(), id, id);
		return value;
	}

	public static void setNumOfBigEater(String num) {
		setData(TAG_NUM_OF_BIGEATER, num);
	}

	public static int getNumOfBigEater() {
		int ret = NUM_OF_BIGEATER_DEFAULT_VALUE;
		try {
			String valueAsString = getData(TAG_NUM_OF_BIGEATER);
			if(valueAsString != null && !valueAsString.equals("none")) {
				ret = Integer.parseInt(valueAsString);
				if(ret<3){
					ret = NUM_OF_BIGEATER_DEFAULT_VALUE;
				}
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
		return ret;
	}
	
	public static int getEatupHeapSize() {
		int ret = MEMSIZE_DEFAULT_VALUE;

		try {
			String memsizeAsString = getData(TAG_MEMSIZE);
			if (memsizeAsString != null && !memsizeAsString.equals("none")) {
				int t = Integer.parseInt(memsizeAsString);
				if (t < 100) {
					ret = MEMSIZE_DEFAULT_VALUE;
				} else {
					ret = t;
				}
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}

		return ret;
	}

	public static void setEatupHeadSize(String size) {
		setData(TAG_MEMSIZE, size);
	}

	public static void setData(String property, String value) {
		setData(KyoroApplication.getKyoroApplication(), property, value);
	}

	public static void setData(Context context, String property, String value) {
		setData(context, property, value, "default");//null);
	}

	public static void setData(Context context, String property, String value, String tag) {
		SharedPreferences pref = null; 
		synchronized (lock) {
			if (tag == null) {
				pref = PreferenceManager.getDefaultSharedPreferences(context);
			} else {
				pref = context.getSharedPreferences(tag, Context.MODE_MULTI_PROCESS|Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
			}
			pref.edit().putString(property, value).commit();
		}
	}

	public static String getData(String property) {
		return getData(KyoroApplication.getKyoroApplication(), property);
	}

	public static String getData(Context context, String property) {
		return getData(context, property, "default");//null);
	}

	public static String getData(Context context, String property, String tag) {
		SharedPreferences pref = null; 
		synchronized (lock) {
			if (tag == null) {
				pref = PreferenceManager.getDefaultSharedPreferences(context);
			} else {
				pref = context.getSharedPreferences(tag, Context.MODE_MULTI_PROCESS|Context.MODE_WORLD_READABLE);
			}
			return pref.getString(property, "none");
		}
	}

}
