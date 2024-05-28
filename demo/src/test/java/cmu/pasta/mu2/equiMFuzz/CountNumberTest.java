package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.CountNumber;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class CountNumberTest {

	@DiffFuzz
	public void testCountNumber(@InRange(minInt = -100, maxInt = 100) int[] array, @InRange(minInt = -100, maxInt = 100) int a) {
		// Call the method under test
		CountNumber.countNumber(array, a);
	}
}
