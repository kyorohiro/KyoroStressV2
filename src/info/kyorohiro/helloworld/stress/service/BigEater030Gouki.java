package info.kyorohiro.helloworld.stress.service;

public class BigEater030Gouki extends KyoroStressService {

	public BigEater030Gouki() {
		super(130);
	}

	@Override
	public String getProperty() {
		return KyoroStressService.ID_30;
	}

}
