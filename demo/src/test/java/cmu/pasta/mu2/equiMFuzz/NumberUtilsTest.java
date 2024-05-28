package cmu.pasta.mu2.equiMFuzz;

import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.apache.commons.lang.NumberUtils;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class NumberUtilsTest {

	@DiffFuzz
	public Integer testCompare(@InRange(minDouble = 1.0, maxDouble = 10.0) Double a, @InRange(minDouble = 1.0, maxDouble = 10.0) Double b){
		return NumberUtils.compare(a, b);
	}

	@DiffFuzz
	public Integer testMinimum(@InRange(minInt = 1, maxInt = 10) Integer a, @InRange(minInt = 1, maxInt = 10) Integer b, @InRange(minInt = 1, maxInt = 10) Integer c){
		return NumberUtils.minimum(a, b, c);
	}
	@DiffFuzz
	public Integer testMaximum(@InRange(minInt = 1, maxInt = 10) Integer a, @InRange(minInt = 1, maxInt = 10) Integer b, @InRange(minInt = 1, maxInt = 10) Integer c){
		return NumberUtils.maximum(a, b, c);
	}

}
