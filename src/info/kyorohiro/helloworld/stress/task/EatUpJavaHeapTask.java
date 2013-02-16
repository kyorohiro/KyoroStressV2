package info.kyorohiro.helloworld.stress.task;


import info.kyorohiro.helloworld.stress.service.KyoroStressService;
import info.kyorohiro.helloworld.stressv2.KyoroSetting;

import java.util.LinkedList;

import android.test.IsolatedContext;

public class EatUpJavaHeapTask implements Runnable {
	private LinkedList<byte[]> mBuffer = new LinkedList<byte[]>();
	public static int mEatUpSize = 10*1024*1024;
	public static int mAtomSize = 1024*4;

	public EatUpJavaHeapTask(LinkedList<byte[]> buffer) {
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
				StressUtility.eatUpJavaHeap(mBuffer, mEatUpSize, mAtomSize);
				if(KyoroSetting.RETRY_ON.equals(retryValue)&& mBuffer.size()*mAtomSize < mEatUpSize){
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
