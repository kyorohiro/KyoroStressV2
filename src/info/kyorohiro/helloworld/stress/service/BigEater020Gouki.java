package info.kyorohiro.helloworld.stress.service;

public class BigEater020Gouki extends KyoroStressService {

	public BigEater020Gouki() {
		super(120);
	}

	@Override
	public String getProperty() {
		return KyoroStressService.ID_20;
	}

}
