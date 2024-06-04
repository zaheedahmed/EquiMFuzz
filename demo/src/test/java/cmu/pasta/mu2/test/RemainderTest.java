package cmu.pasta.mu2.test;

import cmu.pasta.mu2.Remainder;
import org.junit.Assert;
import org.junit.Test;

public class RemainderTest {
	@Test
	public void testRemainder(){
		Remainder remainder = new Remainder();
		Assert.assertEquals(remainder.divideMod(139, 7), 6.0,0.01);
	}
}
