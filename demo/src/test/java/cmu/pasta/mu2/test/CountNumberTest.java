package cmu.pasta.mu2.test;

import cmu.pasta.mu2.CountNumber;
import org.junit.Assert;
import org.junit.Test;

public class CountNumberTest {

	@Test
	public void testCountNumber () {
		int [] array = {};
		int a = 1;
		Assert.assertEquals(CountNumber.countNumber(array, a), 0); ;
	}

}
