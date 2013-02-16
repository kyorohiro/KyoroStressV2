package info.kyorohiro.helloworld.display.simple;

import java.util.HashMap;

public class CrossCuttingProperty {
	public static CrossCuttingProperty sInstance  = new CrossCuttingProperty();
	public static CrossCuttingProperty getInstance() {
		return sInstance;
	}

	private CrossCuttingProperty() {
	}

	private HashMap<String, String> mStrProperty = new HashMap<String, String>();
	private HashMap<String, Integer> mIntProperty = new HashMap<String, Integer>();
	public String getProperty(String key, String def) {
		String ret = mStrProperty.get(key);
		if(ret == null) {
			return def;
		} else {
			return ret;
		}
	}

	public void setProperty(String key, String value) {
		mStrProperty.put(key, value);
	}

	public void setProperty(String key, Integer value) {
		mIntProperty.put(key, value);
	}

	public Integer getProperty(String key, Integer def) {
		Integer ret = mIntProperty.get(key);
		if(ret == null) {
			return def;
		} else {
			return ret;
		}
	}
}
