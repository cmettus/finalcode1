package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {


	RateDomainModel rate;

	public RateException(RateDomainModel r)
	{
		this.rate = r;
	}

	public RateDomainModel getRateDomainModel()
	{
		return this.rate;
	}
}
