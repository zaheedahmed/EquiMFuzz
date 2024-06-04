package cmu.pasta.mu2;

import java.util.Arrays;

public class Median {
	public double median ( Double [] array  ) {
		Arrays.sort(array);
		double median;
		if (array.length % 2 == 0)
			median = (array[array.length/2] + array[array.length/2 - 1])/2;
		else
			median = array[array.length/2];

		return median ;
	}
}
