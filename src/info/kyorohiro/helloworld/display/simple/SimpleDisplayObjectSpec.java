package info.kyorohiro.helloworld.display.simple;

public interface SimpleDisplayObjectSpec {
	public int getX();
	public int getY();
	public void setPoint(int x, int y);
	public abstract void paint(SimpleGraphics graphics);
	public boolean onTouchTest(int x, int y, int action);
	public boolean onKeyUp(int keycode);
	public boolean onKeyDown(int keycode);
	public Object getParent();
}
