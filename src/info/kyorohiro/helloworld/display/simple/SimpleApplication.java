package info.kyorohiro.helloworld.display.simple;

import java.io.File;

public interface SimpleApplication {
	File getApplicationDirectory();
	void showMessage(CharSequence message);
}
