package info.kyorohiro.helloworld.stress.task;


import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import java.util.LinkedList;

import android.os.MemoryFile;


public class EatUpMemoryFileTask implements Runnable {
	private LinkedList<MemoryFile> mBuffer = new LinkedList<MemoryFile>();
	public static int mEatUpSize = 10*1024*1024;
	public static int mAtomSize = 1024*4;

	public EatUpMemoryFileTask(LinkedList<MemoryFile> buffer) {
		if(buffer == null) {
			buffer = new LinkedList<MemoryFile>();
		}
		try {
			mEatUpSize = KyoroSetting.getEatupHeapSize() * 1024;
		} catch(Throwable t) {
			t.printStackTrace();
		}
		mBuffer = buffer;
	}

	@Override
	public void run() {
		String retryValue = null;
		try {
			while(true) {
				retryValue = KyoroSetting.getRetry();
				long size = StressUtility.eatUpHeapFromMemoryFile(mBuffer, mEatUpSize, mAtomSize);
				if(KyoroSetting.RETRY_ON.equals(retryValue)&& size < mEatUpSize) {
					Thread.sleep(500);
				} else {
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
