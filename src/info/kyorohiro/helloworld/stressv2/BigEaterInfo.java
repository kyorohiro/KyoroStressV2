package info.kyorohiro.helloworld.stressv2;

import java.util.HashMap;
import java.util.LinkedList;


public class BigEaterInfo {

	public static final String KEY_dalvik_vm_heapsize = "dalvik.vm.heapsize";
	public static final String KEY_dalvik_vm_heapgrowthlimit = "dalvik.vm.heapgrowthlimit";
	public static final String KEY_dalvik_vm_heapstartsize = "dalvik.vm.heapstartsize";

	private LinkedList<ProcessInfo> mInfo = new LinkedList<ProcessInfo>(); 
	private static BigEaterInfo sInstance = null;
	public static BigEaterInfo getInstance() {
		if(sInstance == null) {
			sInstance = new BigEaterInfo();
		}
		return sInstance;
	}
	private BigEaterInfo(){}
	
	//
	//
	// worker info
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
	
	//
	// worker memory restriction info
	//
	private HashMap<String, String> mProperty = new HashMap<String, String>();
	public String getProperty(String key, String def) {
		if(!mProperty.containsKey(key)) {
			return def;
		}
		return mProperty.get(key);
	}

	public void setProperty(String key, String value) {
		android.util.Log.v("kiyo","key="+key+",value="+value);
		mProperty.put(key,value);
	}

}
