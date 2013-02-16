package info.kyorohiro.helloworld.display.simple;


public abstract class SimpleDisplayObject implements SimpleDisplayObjectSpec {
	private int mX=0;
	private int mY=0;
	private int mW=100;
	private int mH=100;
	private Object mParent = null;

	public static SimpleStage getStage(SimpleDisplayObjectSpec object) {
		SimpleDisplayObjectSpec t = object;
		while(true) {
			Object o = t.getParent();
			if(o != null&& o instanceof SimpleDisplayObjectSpec ) {
				t = (SimpleDisplayObjectSpec)o;
			}
			else if(o != null && o instanceof SimpleStage) {
				return (SimpleStage) o;
			}
			else {
				break;
			}
		}
		return null;
	}

	public int[] getGlobalXY(int[] xy) {
		if(getParent() != null && getParent() instanceof SimpleDisplayObject){
			SimpleDisplayObject dobject = (SimpleDisplayObject)getParent();
			dobject.getGlobalXY(xy);
			xy[0] += getX();
			xy[1] += getY();
		} else {
			xy[0] = getX();
			xy[1] = getY();
		}
		return xy;
	}


	private boolean mIsDisposed = false;

	public boolean isDispose() {
//		android.util.Log.v("kiyo","isDisposed="+mIsDisposed);
		return mIsDisposed;
	}

	public void dispose() {
//		android.util.Log.v("kiyo","disposed");
		mIsDisposed = true;
	};

	public boolean includeParentRect() {
		return true;
	}

	public int getX() {
		return mX;
	}
	
	public int getY() {
		return mY;
	}
	
	public Object getParent() {
		return mParent;
	}

	public void setParent(Object parent) {
		mParent = parent;
	}

	public void setPoint(int x, int y){
		mX = x;
		mY = y;
	}

	public void setRect(int w, int h) {
		mW = w;
		mH = h;
	}

	public int getWidth() {
		return mW;
	}

	public int getHeight() {
		return mH;
	}
	private boolean mIsVisible = true;
	public boolean isVisible() {
		return mIsVisible;
	}
	public void isVisible(boolean on) {
		mIsVisible = on;
	}
	public abstract void paint(SimpleGraphics graphics);

	/**
	 * todo rewrite and look at touch event framework  
	 * @param x
	 * @param y
	 * @param action
	 * @return touch point is in a DisplayObject then true, else false. 
	 */
//	@Deprecated
	public boolean onTouchTest(int x, int y, int action) {
		return false;
	}

	/**
	 * @return touch point is in a DisplayObject then true, else false. 
	 */
	public boolean onKeyUp(int keycode) {
		return false;
	}
	/**
	 * @return touch point is in a DisplayObject then true, else false. 
	 */
	public boolean onKeyDown(int keycode) {
		return false;
	}
	
	public void start() {
		
	}

	public void stop() {
		
	}
}
