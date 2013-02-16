package info.kyorohiro.helloworld.stress.service;

public class BigEater010Gouki extends KyoroStressService {

	public BigEater010Gouki() {
		super(110);
	}

	@Override
	public String getProperty() {
		return KyoroStressService.ID_10;
	}
}
