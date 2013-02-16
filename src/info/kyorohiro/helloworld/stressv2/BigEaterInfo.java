package info.kyorohiro.helloworld.stressv2;

import java.util.LinkedList;


public class BigEaterInfo {
	private LinkedList<ProcessInfo> mInfo = new LinkedList<ProcessInfo>(); 

	private static BigEaterInfo sInstance = null;
	public static BigEaterInfo getInstance() {
		if(sInstance == null) {
			sInstance = new BigEaterInfo();
		}
		return sInstance;
	}
	private BigEaterInfo(){}
	
	public synchronized int numOfWorker() {
		return mInfo.size();
	}

	public synchronized ProcessInfo[] getWorkerInfo() {
		ProcessInfo[] infos = new ProcessInfo[numOfWorker()];
		mInfo.toArray(infos);
		return infos;
	}

	public void append(ProcessInfo info) {
		mInfo.add(info);
	}

	public ProcessInfo get(int location) {
		return mInfo.get(location);
	}

	public void append(int index, ProcessInfo info) {
		mInfo.add(index, info);
	}
	
	public void clear() {
		mInfo.clear();
	}
}
