package info.kyorohiro.helloworld.stressv2.guiparts;

import java.util.LinkedList;

import android.app.Application;

import info.kyorohiro.helloworld.display.simple.SimpleDisplayObjectContainer;
import info.kyorohiro.helloworld.display.simple.SimpleGraphicUtil;
import info.kyorohiro.helloworld.display.simple.SimpleGraphics;
import info.kyorohiro.helloworld.stress.task.SurveyBigEaterTask;
import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import info.kyorohiro.helloworld.stressv2.ProcessInfo;
import info.kyorohiro.helloworld.util.KyoroMemoryInfo;
import info.kyorohiro.helloworld.util.SingleTaskRunner;

public class ListView extends SimpleDisplayObjectContainer {

	private int mIndex = 0;
	private SingleTaskRunner mTask = new SingleTaskRunner();

	@Override
	public void paint(SimpleGraphics graphics) {
		super.paint(graphics);
		int ya = graphics.getTextSize();
		int y = ya+60;
		ProcessInfo[] infos = BigEaterInfo.getInstance().getWorkerInfo();
		graphics.setColor(SimpleGraphicUtil.GREEN);
		graphics.drawLine(10, 10, graphics.getWidth()-20, graphics.getHeight()-20);
		for(ProcessInfo info : infos) {
			graphics.setColor(SimpleGraphicUtil.parseColor("#AA00FF00"));
			graphics.drawLine(10, y+ya, graphics.getWidth()-20, y+ya);
			graphics.setColor(SimpleGraphicUtil.parseColor("#77000000"));
			graphics.drawText("id:"+info.mID+",pid:"+info.mPID+","+KyoroSetting.getBigEaterState(info.mID), 0, y);
			graphics.drawText("tpd:"+info.mTPD+"byte,pss:"+info.mTPss+",tsd:"+info.mTSD, 0, y+ya);
			y += ya*2;
		}
		
		//
		if(!mTask.isAlive()){
			mTask.startTask(new SurveyBigEaterTask());
		}
	}

	public void up() {
		mIndex++;
	}

	public void down() {
		mIndex--;
	}

}
