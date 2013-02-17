package info.kyorohiro.helloworld.stress.task;

import java.util.LinkedList;

import android.os.MemoryFile;

public class StressUtility {

	public static LinkedList<byte[]> eatUpJavaHeap(int eatUpSize, int atomSize) {
		LinkedList<byte[]> list = new LinkedList<byte[]>();
		return eatUpJavaHeap(list, eatUpSize, atomSize);
	}

	public static LinkedList<byte[]> eatUpJavaHeap(LinkedList<byte[]> list, int eatUpSize, int atomSize) {
		try {
			while(list.size()*atomSize < eatUpSize) {
				list.add(new byte[atomSize]);
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
		finally { 
		}
		return list;
	}

	public static long eatUpHeapFromMemoryFile(LinkedList<MemoryFile> list, int eatUpSize, int atomSize) {
		long ret = 0;
		try {
			byte[] buffer = new byte[atomSize];
			do{
				MemoryFile mmap = new MemoryFile("name"+list.size(), eatUpSize/10);
				int s = 0;
				int e = 0;
				while(true) {
					e = s+buffer.length;
					if(e>eatUpSize/10) {
						break;
					}
					if(s!=e) {
						mmap.writeBytes(buffer, 0, s, buffer.length);
					}
					s = e;
				}
				list.add(mmap);
				ret += eatUpSize/10;
			} while(ret < eatUpSize);
		} catch(Throwable t) {
			android.util.Log.v("kiyo","###eeee_d"+t.getMessage());
			t.printStackTrace();
		}
		finally { 
		}
		return ret;
	}

}