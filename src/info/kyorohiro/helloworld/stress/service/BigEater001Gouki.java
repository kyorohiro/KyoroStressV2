package info.kyorohiro.helloworld.stress.service;

public class BigEater001Gouki extends KyoroStressService {

	public BigEater001Gouki() {
		super(101);
	}

	@Override
	public String getProperty() {
		return KyoroStressService.ID_01;
	}

}
