package info.kyorohiro.helloworld.stress.service;


import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

public abstract class ForegroundService extends TestService {

	public ForegroundService(int IdOfStartForeground) {
		super(IdOfStartForeground);
	}

	public void startForgroundAtOnGoing(int resId, String title, String message, Class clazz) {
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, clazz), 0);
		startForgroundAtOnGoing(resId, title, message, contentIntent);
	}
	
	// you call this function in onStartHandle and onCreate
	public void startForgroundAtOnGoing(int resId, String title, String message, PendingIntent contentIntent) {
		// forground
		Notification n = null;//new Notification(resId, title, System.currentTimeMillis());
//		Notification n = new Notification();
		if (KyoroSetting.NOTIFICATION_ON.equals(KyoroSetting.getNotification())) {
			n = new Notification(resId, title, System.currentTimeMillis());
		} else {
			n = new Notification();
		}

		n.setLatestEventInfo(this, title, message, contentIntent);
		n.flags = Notification.FLAG_ONGOING_EVENT;
		startForegroundCompat(n);
	}
}
