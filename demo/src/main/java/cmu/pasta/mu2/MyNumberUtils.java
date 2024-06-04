package cmu.pasta.mu2;

import org.apache.commons.lang.NumberUtils;

public class MyNumberUtils {

	public static int compare(int num1, int num2) {

		double number1 = ((double) num1);
		double number2 = ((double) num2);
		return NumberUtils.compare(number1, number2);

	}

	public static int minimum(int num1, int num2, int num3) {

		return NumberUtils.minimum(num1, num2, num3);

	}
}
