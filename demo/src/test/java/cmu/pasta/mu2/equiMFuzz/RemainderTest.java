package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.Remainder;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class RemainderTest {

	@DiffFuzz()
	public double testRemainder(@InRange(minDouble = 1.0, maxDouble = 100.0)Double a, @InRange(minDouble = 1.0, maxDouble = 100.0) Double b){
		Remainder remainder = new Remainder();
		return remainder.divideMod(a ,b);
	}
}
