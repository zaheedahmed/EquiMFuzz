package cmu.pasta.mu2.test;

import cmu.pasta.mu2.Maximum;
import org.junit.Assert;
import org.junit.Test;

public class MaximumTest {
	@Test
	public void testMaximum(){
		Maximum maximum = new Maximum();
		Integer[] numbers = {1, -2, 3, -4, -5};
		Assert.assertEquals(maximum.max(numbers), 3,0.01);
	}
}
