package info.kyorohiro.helloworld.stressv2;

import java.util.List;

import info.kyorohiro.helloworld.display.pfdep.android.adapter.SimpleStageForAndroid;
import info.kyorohiro.helloworld.display.simple.SimpleDisplayObject;
import info.kyorohiro.helloworld.display.simple.SimpleGraphics;
import info.kyorohiro.helloworld.stress.service.KyoroStressService;
import info.kyorohiro.helloworld.stress.task.StartBigEaterTask;
import info.kyorohiro.helloworld.stress.task.StopBigEaterTask;
import info.kyorohiro.helloworld.stressv2.guiparts.Button;
import info.kyorohiro.helloworld.stressv2.guiparts.Button.CircleButtonListener;
import info.kyorohiro.helloworld.stressv2.guiparts.Label;
import info.kyorohiro.helloworld.stressv2.guiparts.ListView;
import info.kyorohiro.helloworld.stressv2.menuparts.HeapSizeOfBigEaterDialog;
import info.kyorohiro.helloworld.stressv2.menuparts.NumOfBigEaterDialog;
import info.kyorohiro.helloworld.stressv2.menuparts.RetryOfBigEaterDialog;
import info.kyorohiro.helloworld.stressv2.menuparts.ShowNotificationOfBigEaterDialog;
import info.kyorohiro.helloworld.util.SingleTaskRunner;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String MENU_STOP = "stop all";
	public final static String MENU_START = "start all";
	public final static String MENU_SETTING = "setting";
	public final static String MENU_SETTING_EATUP_JAVA_HEAP_SIZE = "eatup java heap size";
	public final static String MENU_SETTING_BIGEATER_NUM = "num of bigeater";
	public final static String MENU_SETTING_IS_RETRY = "is retry";
	public final static String MENU_SETTING_IS_NOTIFICATION = "show notification";

	private SimpleStageForAndroid mStage = null;
	private Button mStartButton = new Button("start");
	private Button mStopButton = new Button("stop");
	private Button mUpButton = new Button("up");
	private Button mDownButton = new Button("down");
	private ListView mBigEaterInfos = new ListView();
	private Label mLabel = new Label();

	private SingleTaskRunner mRunner = new SingleTaskRunner();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mStage = new SimpleStageForAndroid(this);
		mStage.getRoot().addChild(new Layout());
		mStage.getRoot().addChild(mStartButton);
		mStage.getRoot().addChild(mStopButton);
	//	mStage.getRoot().addChild(mUpButton);
	//	mStage.getRoot().addChild(mDownButton);
		mStage.getRoot().addChild(mBigEaterInfos);
		mStage.getRoot().addChild(mLabel);
		setContentView(mStage);
		init();
	}

	private void init() {
		mStartButton.setCircleButtonListener(new CircleButtonListener(){
			@Override
			public void clicked(Button btn) {
				List<RunningAppProcessInfo> runningBegEater = KyoroStressService.getRunningBigEater(KyoroApplication.getKyoroApplication());
				for(RunningAppProcessInfo info: runningBegEater) {
					android.util.Log.v("kiyo", "xxx"+info.pid +","+ info.processName);
				}
				StartBigEaterTask task = new StartBigEaterTask();
				task.run();
			}
		});
		mStopButton.setCircleButtonListener(new CircleButtonListener(){
			@Override
			public void clicked(Button btn) {
				List<RunningAppProcessInfo> runningBegEater = KyoroStressService.getRunningBigEater(KyoroApplication.getKyoroApplication());
				for(RunningAppProcessInfo info: runningBegEater) {
					android.util.Log.v("kiyo", "xxx"+info.pid +","+ info.processName);
				}
				StopBigEaterTask task = new StopBigEaterTask();
				task.run();
			}
		});
	
	}

	public class Layout extends SimpleDisplayObject {
		@Override
		public void paint(SimpleGraphics graphics) {
			int w = graphics.getWidth();
			int h = graphics.getHeight();
			int bw = mStartButton.getWidth();
			int bh = mStartButton.getHeight();
			mStartButton.setPoint(bw, h-bh);
			mStopButton.setPoint(bw+bw, h-bh);
			mUpButton.setPoint(bw, h-bh*2);
			mDownButton.setPoint(bw+bw, h-bh*2);
			mStage.resetTimer();
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		mStage.start();
	}

	@Override
	protected void onPause() {
		mStage.stop();
		super.onPause();
	}

	//
	//
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.clear();
		menu.add(MainActivity.MENU_START);
		menu.add(MainActivity.MENU_STOP);
		{
			SubMenu s = menu.addSubMenu(MainActivity.MENU_SETTING);
			s.add(MENU_SETTING_BIGEATER_NUM);
			s.add(MENU_SETTING_EATUP_JAVA_HEAP_SIZE);
			s.add(MENU_SETTING_IS_RETRY);
			s.add(MENU_SETTING_IS_NOTIFICATION);
		}
		Toast.makeText(MainActivity.this, "now working..",
				Toast.LENGTH_LONG);
		return super.onPrepareOptionsMenu(menu);
	}

	public void stopAllTask() {
		mRunner.startTask(new StopBigEaterTask());
	}

	public void startAllTask() {
		mRunner.startTask(new StartBigEaterTask());
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item == null) {
			return super.onMenuItemSelected(featureId, item);			
		}
		if (MainActivity.MENU_STOP.equals(item.getTitle())) {
			stopAllTask();
			return true;
		} else if (MainActivity.MENU_START.equals(item.getTitle())) {
			startAllTask();
			return true;
		} else if (MainActivity.MENU_SETTING_EATUP_JAVA_HEAP_SIZE.equals(item.getTitle())) {
			stopAllTask();
			HeapSizeOfBigEaterDialog.createDialog(MainActivity.this).show();
		} else if (MainActivity.MENU_SETTING_BIGEATER_NUM.equals(item.getTitle())) {
			stopAllTask();
			NumOfBigEaterDialog.createDialog(MainActivity.this).show();
		} else if(MainActivity.MENU_SETTING_IS_RETRY.equals(item.getTitle())) {
			stopAllTask();
			RetryOfBigEaterDialog.createDialog(MainActivity.this).show();
		} else if(MainActivity.MENU_SETTING_IS_NOTIFICATION.equals(item.getTitle())) {
			stopAllTask();
			ShowNotificationOfBigEaterDialog.createDialog(MainActivity.this).show();
		}

		return super.onMenuItemSelected(featureId, item);
	}

}
