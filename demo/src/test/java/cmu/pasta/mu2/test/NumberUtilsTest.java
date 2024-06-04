package cmu.pasta.mu2.test;


import org.apache.commons.lang.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

public class NumberUtilsTest {

	/*@Test
	public void testStringToInt() {
		Assert.assertEquals(42, NumberUtils.stringToInt("42"));
		Assert.assertEquals(-123, NumberUtils.stringToInt("-123"));
		Assert.assertEquals(0, NumberUtils.stringToInt("invalid"));
		Assert.assertEquals(999, NumberUtils.stringToInt("invalid", 999));
	}

	@Test
	public void testCreateNumber() {
		Assert.assertEquals(42, NumberUtils.createNumber("42").intValue());
		Assert.assertEquals(123L, NumberUtils.createNumber("123").longValue());
		Assert.assertEquals(123.45f, NumberUtils.createNumber("123.45").floatValue(), 0.01);
		Assert.assertEquals(987.654, NumberUtils.createNumber("987.654").doubleValue(), 0.01);
		Assert.assertNull(NumberUtils.createNumber(null));
	}*/

//	@Test
//	public void testMinimum() {
//		Assert.assertEquals(5, NumberUtils.minimum(5, 10, 20));
//		Assert.assertEquals(-15, NumberUtils.minimum(0, -5, -15));
//		Assert.assertEquals(0, NumberUtils.minimum(0, 0, 0));
//	}
//
//	@Test
//	public void testMaximum() {
//		Assert.assertEquals(20, NumberUtils.maximum(5, 10, 20));
//		Assert.assertEquals(0, NumberUtils.maximum(0, -5, -15));
//		Assert.assertEquals(5, NumberUtils.maximum(0, 5, 2));
//	}

	@Test
	public void testCompare() {
		Assert.assertEquals(0, NumberUtils.compare(10.0, 10.0));
		Assert.assertEquals(-1, NumberUtils.compare(8.0, 10.0));
		/*Assert.assertEquals(1, NumberUtils.compare(15.25, 10.5));
		Assert.assertEquals(0, NumberUtils.compare(5.0f, 5.0f));
		Assert.assertEquals(-1, NumberUtils.compare(4.5f, 5.0f));
		Assert.assertEquals(1, NumberUtils.compare(6.0f, 5.0f));*/
	}

	/*@Test
	public void testIsDigits() {
		Assert.assertTrue(NumberUtils.isDigits("123"));
		Assert.assertFalse(NumberUtils.isDigits("12A"));
		Assert.assertFalse(NumberUtils.isDigits(null));
		Assert.assertFalse(NumberUtils.isDigits(""));
	}

	@Test
	public void testIsNumber() {
		Assert.assertTrue(NumberUtils.isNumber("123"));
		Assert.assertTrue(NumberUtils.isNumber("-12.34"));
		Assert.assertFalse(NumberUtils.isNumber("12A"));
		Assert.assertFalse(NumberUtils.isNumber(null));
		Assert.assertFalse(NumberUtils.isNumber(""));
	}*/
}


