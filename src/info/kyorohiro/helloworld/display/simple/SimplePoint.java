package info.kyorohiro.helloworld.display.simple;

public class SimplePoint {
	private boolean mVisible = true;
	private int mX = 0;
	private int mY = 0;
	
	public SimplePoint(int x, int y, boolean visible) {
		mX = x;
		mY = y;
		mVisible = visible;
	}

	public void isVisible(boolean visible){
		mVisible = visible;
	}

	public boolean isVisible() {
		return mVisible;
	}

	public void setPoint(int x, int y) {
		mX = x;
		mY = y;
	}

	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}
}
