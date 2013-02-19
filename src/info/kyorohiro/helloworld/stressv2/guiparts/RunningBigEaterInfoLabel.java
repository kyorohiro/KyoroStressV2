package info.kyorohiro.helloworld.stressv2.guiparts;


import info.kyorohiro.helloworld.display.simple.SimpleDisplayObjectContainer;
import info.kyorohiro.helloworld.display.simple.SimpleGraphicUtil;
import info.kyorohiro.helloworld.display.simple.SimpleGraphics;
import info.kyorohiro.helloworld.stress.task.SurveyBigEaterTask;
import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import info.kyorohiro.helloworld.stressv2.ProcessInfo;
import info.kyorohiro.helloworld.util.SingleTaskRunner;
import info.kyorohiro.helloworld.util.Utility;

public class RunningBigEaterInfoLabel extends SimpleDisplayObjectContainer {

	private SingleTaskRunner mTask = new SingleTaskRunner();
	private int textSize = (int)Utility.inchi2pixel(Utility.mm2inchi(2.5));

	@Override
	public void paint(SimpleGraphics graphics) {
		super.paint(graphics);
		graphics.setTextSize(textSize);
		int textHeight = graphics.getTextSize();
		int drawTextPositionY = textHeight+60;
		ProcessInfo[] infos = BigEaterInfo.getInstance().getWorkerInfo();
		for (ProcessInfo info:infos) {
			graphics.setColor(SimpleGraphicUtil.parseColor("#AA00FF00"));
			graphics.drawLine(10, drawTextPositionY+textHeight, graphics.getWidth()-20, drawTextPositionY+textHeight);
			graphics.setColor(SimpleGraphicUtil.parseColor("#AA000000"));
			graphics.drawText("id:"+info.mID+",pid:"+info.mPID+","+KyoroSetting.getBigEaterState(info.mID), 20, drawTextPositionY);
			graphics.drawText("tpd:"+info.mTPD+"k,pss:"+info.mTPss+"k,tsd:"+info.mTSD+"k", 20, drawTextPositionY+textHeight);
			drawTextPositionY += textHeight*2.3;
		}

		if(!mTask.isAlive()){
			mTask.startTask(new SurveyBigEaterTask());
		}
	}

}
