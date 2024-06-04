package cmu.pasta.mu2.test;

import cmu.pasta.mu2.RecursionPower;
import org.junit.Assert;
import org.junit.Test;

public class RecursionPowerTest {
	@Test
	public void testRecursionPower(){
		RecursionPower recursionPower = new RecursionPower();
		int result = RecursionPower.power(2,2);
		Assert.assertEquals(result, 4);
	}
}
