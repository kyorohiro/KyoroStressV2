package info.kyorohiro.helloworld.stressv2.guiparts;

import android.app.Application;
import info.kyorohiro.helloworld.display.simple.SimpleDisplayObject;
import info.kyorohiro.helloworld.display.simple.SimpleGraphicUtil;
import info.kyorohiro.helloworld.display.simple.SimpleGraphics;
import info.kyorohiro.helloworld.stress.task.GetDalvikHeapInfoTask;
import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.stressv2.KyoroApplication;
import info.kyorohiro.helloworld.util.KyoroMemoryInfo;
import info.kyorohiro.helloworld.util.SingleTaskRunner;
import info.kyorohiro.helloworld.util.Utility;

public class PFMemoryInfoLabel extends SimpleDisplayObject {
	private SingleTaskRunner mRunner = new SingleTaskRunner();
	private int textSize = (int)Utility.inchi2pixel(Utility.mm2inchi(2.5));
	@Override
	public void paint(SimpleGraphics graphics) {
		int drawText_x = 50;
		int drawText_y = 20;

		graphics.setTextSize(textSize);
		
		Application app = KyoroApplication.getKyoroApplication();
		BigEaterInfo eaterInfo = BigEaterInfo.getInstance();
		graphics.setColor(SimpleGraphicUtil.parseColor("#FFFF3300"));
		
		graphics.drawText("lowMemory:"+KyoroMemoryInfo.getMemoryInfo(app).lowMemory, drawText_x, drawText_y);
		drawText_y+=textSize*1.2;
		long availMem = KyoroMemoryInfo.getMemoryInfo(app).availMem;
		graphics.drawText("availMem:"+(availMem/1000/1000)+"m:"+availMem, drawText_x, drawText_y);
		drawText_y+=textSize*1.2;
		long threshold = KyoroMemoryInfo.getMemoryInfo(app).threshold;		
		graphics.drawText("threshold:"+(threshold/1000/1000)+"m:"+threshold, drawText_x, drawText_y);
		drawText_y+=textSize*1.2;
		graphics.drawText("dalvik.vm.heapsize:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapsize, "none"), drawText_x, drawText_y);
		drawText_y+=textSize*1.2;
		graphics.drawText("dalvik.vm.heapgrowthlimit:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapgrowthlimit, "none"), drawText_x, drawText_y);
		drawText_y+=textSize*1.2;
		graphics.drawText("dalvik.vm.heapstartsize:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapstartsize, "none"), drawText_x, drawText_y);
		drawText_y+=textSize*1.2;

		//
		//
		if (!mRunner.isAlive()&&!GetDalvikHeapInfoTask.sIsRun) {
			mRunner.startTask(new GetDalvikHeapInfoTask());
		}

	}

}
