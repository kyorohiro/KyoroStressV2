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
		Application app = KyoroApplication.getKyoroApplication();
		BigEaterInfo eaterInfo = BigEaterInfo.getInstance();
		graphics.setColor(SimpleGraphicUtil.parseColor("#FFFF3300"));
		graphics.drawText("lowMemory:"+KyoroMemoryInfo.getMemoryInfo(app).lowMemory, 50, 20);
		graphics.drawText("availMem:"+KyoroMemoryInfo.getMemoryInfo(app).availMem, 50, 50);
		graphics.drawText("threshold:"+KyoroMemoryInfo.getMemoryInfo(app).threshold, 50, 80);
		graphics.drawText("dalvik.vm.heapsize:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapsize, "none"), 50, 110);
		graphics.drawText("dalvik.vm.heapgrowthlimit:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapgrowthlimit, "none"), 50, 140);
		graphics.drawText("dalvik.vm.heapstartsize:"
		+eaterInfo.getProperty(BigEaterInfo.KEY_dalvik_vm_heapstartsize, "none"), 50, 170);

		//
		if(!mRunner.isAlive()&&!GetDalvikHeapInfoTask.sIsRun){
			mRunner.startTask(new GetDalvikHeapInfoTask());
		}

	}

}
