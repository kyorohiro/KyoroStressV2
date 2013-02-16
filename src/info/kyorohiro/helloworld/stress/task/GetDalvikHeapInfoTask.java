package info.kyorohiro.helloworld.stress.task;

import java.io.InputStream;
import java.nio.ByteBuffer;

import info.kyorohiro.helloworld.stressv2.BigEaterInfo;
import info.kyorohiro.helloworld.util.CLIAppKicker;
import info.kyorohiro.helloworld.util.CLIAppKicker.CLIAppKickerException;
import info.kyorohiro.helloworld.util.arraybuilder.ByteArrayBuilder;

public class GetDalvikHeapInfoTask implements Runnable {

	public static boolean sIsRun = false;
	@Override
	public void run() {
		android.util.Log.v("kiyo","START");
		sIsRun = true;
		setValue(BigEaterInfo.KEY_dalvik_vm_heapsize);
		setValue(BigEaterInfo.KEY_dalvik_vm_heapgrowthlimit);
		setValue(BigEaterInfo.KEY_dalvik_vm_heapstartsize);
		android.util.Log.v("kiyo","END");
	}

	public void setValue(String key) {
		BigEaterInfo eaterInfo = BigEaterInfo.getInstance();
		CLIAppKicker kicker = new CLIAppKicker();
		kicker.start("getprop "+key);
//		kicker.start("pwd");
		try {
			android.util.Log.v("kiyo","--001---");
			ByteArrayBuilder builder = new ByteArrayBuilder();
			while(true) {
				android.util.Log.v("kiyo","--002---");

				InputStream stream = kicker.getInputStream();
				InputStream error = kicker.getErrorStream();
				if(stream.available()>0) {
					byte[] buffer = new byte[stream.available()];
					stream.read(buffer);
					builder.append(buffer);
				}
				android.util.Log.v("kiyo","--003---");
				if(error.available()>0) {
					byte[] buffer = new byte[error.available()];
					error.read(buffer);
					builder.append(buffer);
				}
				android.util.Log.v("kiyo","--004---");
				if(!kicker.isAlive() && stream.available() <=0&& error.available()<=0) {
					break;
				}
				Thread.sleep(10);
			}
			String value  = new String(builder.getBuffer());
			eaterInfo.setProperty(key, value);
		} catch (InterruptedException e) {e.printStackTrace();
		} catch (CLIAppKickerException e) {e.printStackTrace();
		} catch (Throwable e) {e.printStackTrace();
		}
	}
}
