package info.kyorohiro.helloworld.display.simple;

//
//todo don't fix
public class CommitText {
	    // todo commit text keycode 
		private CharSequence mText = null;
		private int mNewCursorPosition = 0;
		private boolean mIsKeyCode = false;
		private int mKeycode = 0;

		// todo controlCode
		private boolean mPushingCtrl = false;
		private boolean mPushingEsc = false;

		public void pushingAlt(boolean pushing) {
			mPushingEsc = pushing;
		}

		public boolean pushingAlt() {
			return mPushingEsc;
		}

		public void pushingCtrl(boolean pushing) {
			mPushingCtrl = pushing;
		}

		public boolean pushingCtrl() {
			return mPushingCtrl;
		}

		@Deprecated
		public CommitText(int keycode) {
			mIsKeyCode = true;
			mKeycode = keycode;
		}

		public CommitText(CharSequence text, int newCursorPosition) {
			mText = text;
			mNewCursorPosition = newCursorPosition;
		}

		public CharSequence getText() {
			return mText;
		}

		public int getNewCursorPosition() {
			return mNewCursorPosition;
		}
		public boolean isKeyCode() {
			return mIsKeyCode;
		}
		public int getKeyCode() {
			return mKeycode;
		}
	}
