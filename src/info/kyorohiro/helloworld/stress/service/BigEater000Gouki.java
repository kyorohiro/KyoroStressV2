package info.kyorohiro.helloworld.stress.service;

public class BigEater000Gouki extends KyoroStressService {

	public BigEater000Gouki() {
		super(100);
		android.util.Log.v("kiyo","new BigEater000Gouki()");
	}
	@Override
	public String getProperty() {
		return KyoroStressService.ID_00;
	}

}
