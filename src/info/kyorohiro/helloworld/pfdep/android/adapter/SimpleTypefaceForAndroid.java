package info.kyorohiro.helloworld.pfdep.android.adapter;

import java.io.File;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import info.kyorohiro.helloworld.display.simple.SimpleTypeface;

public class SimpleTypefaceForAndroid extends SimpleTypeface {

	private Typeface mTypeface = null;

	public SimpleTypefaceForAndroid(Typeface f) {
		mTypeface = f;
	}	
	public SimpleTypefaceForAndroid(File f) {
		mTypeface = Typeface.createFromFile(f);
	}

	public Typeface getTypeface() {
		return mTypeface;
	}
}
