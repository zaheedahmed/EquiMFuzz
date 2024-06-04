package cmu.pasta.mu2.test;

import cmu.pasta.mu2.CalDay;
import org.junit.Assert;
import org.junit.Test;

public class CalDayTest {

	@Test
	public void testToJulian() {
		CalDay calDay = new CalDay();

		// Test for a date in the Gregorian calendar
		Assert.assertEquals(2459991.0, calDay.toJulian(15, 2, 2023), 0.0001);

		// Test for a date before the switch to the Gregorian calendar
		Assert.assertEquals(2299161.0, calDay.toJulian(15, 10, 1582), 0.0001);

		// Test for a negative year
		Assert.assertEquals(982568.0, calDay.toJulian(15, 2, -2023), 0.0001);

		// Add more test cases as needed to cover different scenarios
	}
}
