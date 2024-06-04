package cmu.pasta.mu2;

public class Remainder
{


	double remainder;

	public Remainder()
	{
	}


	public double divideMod( double dividend, double divisor )
	{
		double r = dividend;
		r = dividend % divisor;
		remainder = r;
		return r;
	}

}