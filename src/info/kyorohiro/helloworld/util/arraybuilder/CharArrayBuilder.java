package info.kyorohiro.helloworld.util.arraybuilder;

//
//promitive 型なので テンプレートが使えない？
//
public class CharArrayBuilder {
	private int mPointer = 0;
	private char[] mBuffer = new char[256];

	public void append(char moji){
		if(mPointer >= mBuffer.length){
			updateBuffer();
		}
		mBuffer[mPointer] = moji;
		mPointer++;
	}

	public void setBufferLength(int length) {
		if(mBuffer.length < length) {
			updateBuffer(length>mBuffer.length*2?length:mBuffer.length*2);
		}
	}

	private void updateBuffer() {
		updateBuffer(mBuffer.length*2);
	}

	private void updateBuffer(int length) {
		char[] tmp = new char[length];
		for(int i=0;i<mBuffer.length;i++) {
			tmp[i] = mBuffer[i];
		}
		mBuffer = tmp;
	}
	public void clear() {
		mPointer = 0;
	}

	public char[] getBuffer(){
		return mBuffer;
	}

	public int length(){
		return mPointer;
	}

	public void removeLast(){
		if(0<mPointer) {
			mPointer--;
		}
	}

}
