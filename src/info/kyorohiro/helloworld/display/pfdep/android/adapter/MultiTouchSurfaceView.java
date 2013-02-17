package info.kyorohiro.helloworld.display.pfdep.android.adapter;

import info.kyorohiro.helloworld.display.simple.SimplePoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

/**
 * todo Android�Ɉˑ����Ă��镔���͕�������
 */
public class MultiTouchSurfaceView extends SurfaceView {

	final static int MULTI_TOUCH_MAX = 5;
	private SimplePoint[] mMultiTouchPoints = new SimplePoint[MULTI_TOUCH_MAX];

	private static final Class<?>[] mGetXSignature = new Class[] {int.class};
	private static final Class<?>[] mGetYSignature = new Class[] {int.class};
	private static final Class<?>[] mGetPointerIdSignature = new Class[] {int.class};
	private static final Class<?>[] mGetPointerCountSignature = null;//new Class[0];

	private Method mGetPointerCount;
	private Method mGetPointerId;
	private Method mGetX;
	private Method mGetY;
	private boolean support = false;

	private Object[] mGetPointerIdArgs = new Object[1];
	private Object[] mGetXArgs = new Object[1];
	private Object[] mGetYArgs = new Object[1];	

	private Object invokeMethod(Object o, Method method, Object[] args) {
		try {
			return  method.invoke(o, args);
		} catch (InvocationTargetException e) {
			// Should not happen.
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// Should not happen.
			e.printStackTrace();
		}
		return null;
	}

	private int getPointerCount(MotionEvent e) {
		Object ret = invokeMethod(e, mGetPointerCount, null);
		if (ret != null) {
			return (Integer)ret;
		} else {
			return 0;
		}
	}

	private int getPointerId(MotionEvent e, int pointerIndex) {
		mGetPointerIdArgs[0] = pointerIndex;
		Object ret = invokeMethod(e, mGetPointerId, mGetPointerIdArgs);
		if(ret != null) {
			return (Integer)ret;
		} else {
			return 0;
		}
	}

	private float getX(MotionEvent e, int pointerIndex) {
		mGetXArgs[0] = pointerIndex;
		Object ret = invokeMethod(e, mGetX, mGetXArgs);
		if(ret != null) {
			return (Float)ret;
		} else {
			return 0;
		}
	}

	private float getY(MotionEvent e, int pointerIndex) {
		mGetYArgs[0] = pointerIndex;
		Object ret = invokeMethod(e, mGetY, mGetYArgs);
		if(ret != null) {
			return (Float)ret;
		} else {
			return 0;
		}
	}

	public MultiTouchSurfaceView(Context context) {
		super(context);
		setMoultiTouch();
	}

	private void setMoultiTouch() {
		for(int i=0;i<mMultiTouchPoints.length;i++){
			mMultiTouchPoints[i] = new SimplePoint(0, 0, false);
		}
		try {
			mGetPointerCount = MotionEvent.class.getMethod("getPointerCount", mGetPointerCountSignature);
			mGetPointerId = MotionEvent.class.getMethod("getPointerId", mGetPointerIdSignature);
			mGetX = MotionEvent.class.getMethod("getX", mGetXSignature);
			mGetY = MotionEvent.class.getMethod("getY", mGetYSignature);
			if (mGetPointerCount != null && mGetPointerId != null && 
			   mGetX != null && mGetY != null) {
				support = true;
			}
		} catch (SecurityException e) {
		//	e.printStackTrace();
		} catch (NoSuchMethodException e) {
		//	e.printStackTrace();
		}
	}

	public boolean isSupportMultiTouch() {
		if(Build.VERSION.SDK_INT >= 7&& support == true) {
			return true;
		} else {
			return false;
		}
	}

	public SimplePoint [] getMultiTouchEvent() {
		return mMultiTouchPoints;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(isSupportMultiTouch()) {
			int count = getPointerCount(event);//event.getPointerCount();
			for(int j=0;j<mMultiTouchPoints.length;j++){
				mMultiTouchPoints[j].isVisible(false);
			}
			for(int i=0; i<count; i++) {
				int id = getPointerId(event, i);
				if(id<mMultiTouchPoints.length) {
					mMultiTouchPoints[id].setPoint((int)getX(event, i), (int)getY(event,i));
					mMultiTouchPoints[id].isVisible(true);
					//android.util.Log.v("kiyohiro","["+id+"]="+event.getX(i)+","+event.getY());
				}
			}
		}
		return super.onTouchEvent(event);
	}
}
