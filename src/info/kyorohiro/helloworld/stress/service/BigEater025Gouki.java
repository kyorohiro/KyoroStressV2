package info.kyorohiro.helloworld.stress.service;

public class BigEater025Gouki extends KyoroStressService {

	public BigEater025Gouki() {
		super(125);
	}

	@Override
	public String getProperty() {
		return KyoroStressService.ID_25;
	}

}
