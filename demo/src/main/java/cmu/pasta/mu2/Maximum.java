package cmu.pasta.mu2;

public class Maximum
{


	public Maximum()
	{
	}

	public int max(Integer[] numbers)
	{
		int maxValue = Integer.MIN_VALUE;

		// iterating over array and updating maxValue
        for (int number : numbers) {
            maxValue = Math.max(maxValue, number);
        }
		return maxValue;
	}

}