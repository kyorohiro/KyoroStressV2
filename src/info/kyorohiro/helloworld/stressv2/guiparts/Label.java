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

public class Label extends SimpleDisplayObject {
	private SingleTaskRunner mRunner = new SingleTaskRunner();

	@Override
	public void paint(SimpleGraphics graphics) {
		int drawText_x = 50;
		int drawText_y = 20;

		Application app = KyoroApplication.getKyoroApplication();
		BigEaterInfo eaterInfo = BigEaterInfo.getInstance();
		graphics.setColor(SimpleGraphicUtil.parseColor("#FFFF3300"));
		graphics.drawText("lowMemory:"+KyoroMemoryInfo.getMemoryInfo(app).lowMemory, drawText_x, drawText_y);
		drawText_y+=30;
		graphics.drawText("availMem:"+KyoroMemoryInfo.getMemoryInfo(app).availMem, drawText_x, drawText_y);
		drawText_y+=30;
		graphics.drawText("threshold:"+KyoroMemoryInfo.getMemoryInfo(app).threshold, drawText_x, drawText_y);
		drawText_y+=30;
		graphics.drawText("dalvik.vm.heapsize:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapsize, "none"), drawText_x, drawText_y);
		drawText_y+=30;
		graphics.drawText("dalvik.vm.heapgrowthlimit:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapgrowthlimit, "none"), drawText_x, drawText_y);
		drawText_y+=30;
		graphics.drawText("dalvik.vm.heapstartsize:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapstartsize, "none"), drawText_x, drawText_y);
		drawText_y+=30;

		//
		//
		if (!mRunner.isAlive()&&!GetDalvikHeapInfoTask.sIsRun) {
			mRunner.startTask(new GetDalvikHeapInfoTask());
		}

	}

}
