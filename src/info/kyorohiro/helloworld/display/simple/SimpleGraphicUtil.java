package info.kyorohiro.helloworld.display.simple;



public class SimpleGraphicUtil {

	public static final int BLACK = parseColor("#FF000000");
	public static final int WHITE = parseColor("#FFFFFFFF");
	public static final int GREEN = parseColor("#FF00FF00");
	public static final int YELLOW = parseColor("#FFFFFF00");
	public static final int RED = parseColor("#FFFF0000");

	public static void fillRect(SimpleGraphics graphics, int x, int y, int w, int h) {
		drawRect(graphics, SimpleGraphics.STYLE_FILL, x, y, w, h);
	}

	public static void drawRect(SimpleGraphics graphics, int x, int y, int w, int h) {
		drawRect(graphics, SimpleGraphics.STYLE_STROKE, x, y, w, h);
	}
	
	public static void drawRect(SimpleGraphics graphics,int style, int x, int y, int w, int h) {
		int s = graphics.getStyle();
		graphics.setStrokeWidth(1);
		graphics.setStyle(style);
		graphics.startPath();
		graphics.moveTo(x, y);
		graphics.lineTo(x, h+y);
		graphics.lineTo(x+w, h+y);
		graphics.lineTo(x+w, y);
		graphics.lineTo(x, y);
		graphics.endPath();
		graphics.setStyle(s);
	}

	public static void drawControlCodeRect(SimpleGraphics graphics,int style, int x, int y, int w, int h) {
		graphics.drawLine(x+w, h/2+y, x+w, y);
		graphics.drawLine(x+w, y, x, y);
	}

	public static int parseColor(String colorSource) {
		String c = colorSource.replaceAll(" ","").replaceAll("#","");
		return (int)Long.parseLong(c, 16);
	}

	public static int argb(int a, int r, int g, int b) {
		int ret = ((a&0xff)<<(3*8))|((r&0xff)<<(2*8))|((g&0xff)<<(1*8))|((b&0xff)<<(0*8));
		return ret;
	}
	public static int colorA(int c) {
		int ret = 0xff&(c>>(3*8));
		return ret;		
	}
	public static int colorR(int c) {
		int ret = 0xff&(c>>(2*8));
		return ret;		
	}
	public static int colorG(int c) {
		int ret = 0xff&(c>>(1*8));
		return ret;		
	}
	public static int colorB(int c) {
		int ret = 0xff&(c>>(0*8));
		return ret;		
	}

}
