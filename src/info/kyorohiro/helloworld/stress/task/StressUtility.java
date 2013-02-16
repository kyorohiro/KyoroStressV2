package info.kyorohiro.helloworld.stress.task;

import java.util.LinkedList;

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
}