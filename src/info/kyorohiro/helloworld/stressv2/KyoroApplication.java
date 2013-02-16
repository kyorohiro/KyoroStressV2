package info.kyorohiro.helloworld.stressv2;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class KyoroApplication extends Application {

	private Handler mHandler = null;
	private static KyoroApplication sInstance = null;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		mHandler = new Handler();
	}

	public static KyoroApplication getKyoroApplication() {
		return sInstance;
	}

	public static void showMessage(String message) {
		try {
			sInstance.mHandler.post(new ToastMessage(sInstance, message));
		} catch(Throwable e){
			e.printStackTrace();
		}
	}

	public Handler getHanler(){
		return sInstance.mHandler;
	}

	private static class ToastMessage implements Runnable {
		private Context mContext = null;
		private String mMessage = null;
		public ToastMessage(Context c, String m) {
			mContext = c;
			mMessage = m;
		}
		public void run(){
			try {
				Toast.makeText(mContext.getApplicationContext(), mMessage, Toast.LENGTH_SHORT).show();
				mContext = null;
				mMessage = null;
			} catch(Throwable e){
				e.printStackTrace();
			} 
		}
	}
}
