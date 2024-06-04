package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.Maximum;
import cmu.pasta.mu2.Median;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class MaximumTest {
	@DiffFuzz()
	public int testMaximum(@InRange(minInt = 1, maxInt = 200) Integer[] array){
		Maximum maximum = new Maximum();
		return maximum.max(array);
	}
}
