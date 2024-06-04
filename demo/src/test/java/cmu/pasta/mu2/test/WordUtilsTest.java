package cmu.pasta.mu2.test;


import org.apache.commons.lang.WordUtils;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;




public class WordUtilsTest {

		@Test
		public void testWrap() {
			String input = "I am living in Chrito with Awais, Mohib also lives in Christo but Adnan lives in Roko.";
			int wrapLength = 20;

			String wrapped = WordUtils.wrap(input, wrapLength);
			String expected = "I am living in\r\n" +
					"Chrito with Awais,\r\n" +
					"Mohib also lives in\r\n" +
					"Christo but Adnan\r\n" +
					"lives in Roko.";

			assertEquals(expected, wrapped);
		}

//		@Test
//		public void testCapitalize() {
//			String input = "hello world";
//
//			String capitalized = WordUtils.capitalize(input);
//			String expected = "Hello World";
//
//			assertEquals(expected, capitalized);
//		}
//
//		@Test
//		public void testCapitalizeFully() {
//			String input = "john doe";
//
//			String capitalizedFully = WordUtils.capitalizeFully(input);
//			String expected = "John Doe";
//
//			assertEquals(expected, capitalizedFully);
//		}
//
//		@Test
//		public void testUncapitalize() {
//			String input = "Hello World";
//
//			String uncapitalized = WordUtils.uncapitalize(input);
//			String expected = "hello world";
//
//			assertEquals(expected, uncapitalized);
//		}
//
//		@Test
//		public void testSwapCase() {
//			String input = "Hello World";
//
//			String swappedCase = WordUtils.swapCase(input);
//			String expected = "hELLO wORLD";
//
//			assertEquals(expected, swappedCase);
//		}
//
//		@Test
//		public void testInitials() {
//			String input = "John Doe";
//
//			String initials = WordUtils.initials(input);
//			String expected = "JD";
//
//			assertEquals(expected, initials);
//		}
//
//		@Test
//		public void testAbbreviate() {
//			String input = "Lorem ipsum dolor sit amet";
//			int lower = 5;
//			int upper = 10;
//			String appendToEnd = "...";
//
//			String abbreviated = WordUtils.abbreviate(input, lower, upper, appendToEnd);
//			String expected = "Lorem...";
//
//			assertEquals(expected, abbreviated);
//		}
}




