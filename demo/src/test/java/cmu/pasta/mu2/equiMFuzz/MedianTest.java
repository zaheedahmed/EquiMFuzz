package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.Median;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class MedianTest {
	@DiffFuzz()
	public Double testMedian(@InRange(minDouble = 1.0, maxDouble = 10.0) Double[] array){
		if (array.length != 0) {
			Median median = new Median();
			return median.median(array);
		}
        return 0.0;
    }
}
