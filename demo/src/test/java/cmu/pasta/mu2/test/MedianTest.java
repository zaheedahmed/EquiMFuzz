package cmu.pasta.mu2.test;

import cmu.pasta.mu2.Median;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedianTest {

	@Test
	public void testMedian () {
		Median median = new Median();
		Double [] array = {1.0, 7.0, 3.0, 9.0, 5.0};
		assertEquals("Median", median.median(array), 5.0, 0.01); ;
	}

}
