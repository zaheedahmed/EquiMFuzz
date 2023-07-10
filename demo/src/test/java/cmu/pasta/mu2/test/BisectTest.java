package cmu.pasta.mu2.test;

import cmu.pasta.mu2.Bisect;
import org.junit.Assert;
import org.junit.Test;

public class BisectTest {
	@Test
	public void testBisect(){
		Bisect bisect = new Bisect();
		bisect.setEpsilon(0.001);
		Assert.assertEquals(bisect.sqrt(2.0), 1.4142,0.01);
	}
}
