package info.kyorohiro.helloworld.display.simple;

public abstract class SimpleGraphics {
	public final static int STYLE_STROKE = 1;
	public final static int STYLE_FILL = 2;
	public final static int STYLE_LINE = 3;


	public abstract int getGlobalX();
	public abstract int getGlobalY();
	public abstract void saveSetting();
	public abstract void restoreSetting();	
	public abstract void setGlobalPoint(SimpleGraphics graphics, int x,int y);
	public abstract SimpleGraphics getChildGraphics(SimpleGraphics graphics, int globalX, int globalY);
	public abstract void drawCircle(int x, int y, int radius);	
	public abstract void drawLine(int startX, int startY, int stopX, int stopY);	
	public abstract void drawBackGround(int color);
	public abstract void drawText(char[] text, int start, int end, int x, int y);
	public abstract void drawText(CharSequence text, int x, int y);
	public abstract void drawPosText(char[] text, float[] widths, float zoom, int start, int end, int x, int y);
	public abstract int getTextSize();
	public abstract void startPath();
	public abstract void moveTo(int x, int y);
	public abstract void lineTo(int x, int y);
	public abstract void endPath();
	public abstract int getWidth();	
	public abstract int getHeight();
	public abstract int getColor();
	public abstract void setColor(int color);
	public abstract void setTextSize(int size);
	public abstract void setStyle(int style);
	public abstract int getStyle();
	public abstract void setStrokeWidth(int w);
//	public abstract int getTextWidth(String line);
	public abstract SimpleDisplayObject createImage(byte[] data, int offset, int length);
	public abstract void drawImageAsTile(SimpleImage image, int x, int y, int w, int h);
	public abstract void clipRect(int left, int top, int right, int bottom); 


}