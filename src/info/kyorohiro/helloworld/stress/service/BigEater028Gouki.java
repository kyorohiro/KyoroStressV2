package info.kyorohiro.helloworld.stress.service;

public class BigEater028Gouki extends KyoroStressService {

	public BigEater028Gouki() {
		super(128);
	}

	@Override
	public String getProperty() {
		return KyoroStressService.ID_28;
	}

}
