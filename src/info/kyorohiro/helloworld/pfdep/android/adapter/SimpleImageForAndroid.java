package info.kyorohiro.helloworld.pfdep.android.adapter;

import info.kyorohiro.helloworld.display.simple.SimpleImage;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SimpleImageForAndroid extends SimpleImage {
	private Bitmap mImage = null;
	public SimpleImageForAndroid(InputStream is) {
		mImage = BitmapFactory.decodeStream(is);
	}
	
	public Bitmap getImage() {
		return mImage;
	}
}
