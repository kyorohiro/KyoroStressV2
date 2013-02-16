package info.kyorohiro.helloworld.util.arraybuilder;

//
//promitive 型なので テンプレートが使えない？
//
public class ByteArrayBuilder {
	private int mPointer = 0;
	private byte[] mBuffer = new byte[256];

	public void append(byte moji){
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
		byte[] tmp = new byte[length];
		for(int i=0;i<mBuffer.length;i++) {
			tmp[i] = mBuffer[i];
		}
		mBuffer = tmp;
	}

	public void clear() {
		mPointer = 0;
	}

	public byte[] getBuffer(){
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
