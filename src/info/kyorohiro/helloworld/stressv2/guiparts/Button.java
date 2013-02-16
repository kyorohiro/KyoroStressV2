package info.kyorohiro.helloworld.stressv2.guiparts;

import android.graphics.Color;
import android.view.MotionEvent;
import info.kyorohiro.helloworld.display.simple.SimpleDisplayObject;
import info.kyorohiro.helloworld.display.simple.SimpleDisplayObjectContainer;
import info.kyorohiro.helloworld.display.simple.SimpleGraphics;
import info.kyorohiro.helloworld.pfdep.android.util.Utility;

public class Button extends SimpleDisplayObjectContainer {

	private int mRadius = (int)Utility.inchi2pixel(Utility.mm2inchi(20))/2;
	private String mLabel = "";
	private CircleButtonListener mListener = null;

	public Button(String label) {
		mLabel = label;
		addChild(new BG());
		setRect(mRadius, mRadius);
	}

	public void setCircleButtonListener(CircleButtonListener listener) {
		mListener = listener;
	}
	
	public void setRadius(int radius) {
		mRadius = radius;
	}
	
	public int getWidth(){
		return mRadius*2;
	}

	public int getHeight(){
		return mRadius*2;
	}
	
	class BG extends SimpleDisplayObject {
		@Override
		public void paint(SimpleGraphics graphics) {
			if(mIsTouched){
				graphics.setColor(Color.parseColor("#AAAAAAFF"));
			} else {
				graphics.setColor(Color.parseColor("#AAFFAAAA"));
			}
			graphics.setStrokeWidth(10);
			graphics.drawCircle(0, 0, mRadius / 2);
			graphics.drawCircle(0, 0, mRadius / 3);
			graphics.drawCircle(0, 0, mRadius / 4);
			graphics.drawCircle(0, 0, mRadius / 5);	
			graphics.setColor(Color.BLACK);
			graphics.drawText(":"+mLabel+":", 0, 0);
		}

		private boolean mIsTouched = false;
		@Override
		public boolean onTouchTest(int x, int y, int action) {
			if((x*x+y*y)<mRadius*mRadius){
				mIsTouched = true;
				//return true;
			} else {
				//return false;
			}
			switch(action) {
			case MotionEvent.ACTION_DOWN:
				if((x*x+y*y)<mRadius*mRadius){
					mIsTouched = true;
					return true;
				} else {
					return false;
				}
			case MotionEvent.ACTION_UP:
				if(mIsTouched&&mListener!=null){
					mListener.clicked(Button.this);
				}
				mIsTouched = false;
				return false;
			case MotionEvent.ACTION_MOVE:
				if((x*x+y*y)<mRadius*mRadius){
					mIsTouched = true;
					return true;
				} else {
					return false;
				}
			}
			return false;
		}
	}
	
	public static interface CircleButtonListener {
		public void clicked(Button btn);
	}
}
