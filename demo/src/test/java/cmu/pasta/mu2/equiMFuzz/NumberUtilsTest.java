package cmu.pasta.mu2.equiMFuzz;

import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.apache.commons.lang.NumberUtils;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class NumberUtilsTest {

	@DiffFuzz
	public Integer testCompare(@InRange(minDouble = 1.0, maxDouble = 100.0) Double a, @InRange(minDouble = 1.0, maxDouble = 100.0) Double b){
		return NumberUtils.compare(a, b);
	}


}
