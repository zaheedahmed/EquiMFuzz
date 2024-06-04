package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.RecursionPower;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class RecursionPowerTest {
	@DiffFuzz()
	public Integer testPower(@InRange(minInt = 1, maxInt = 100) Integer a, @InRange(minInt = 1, maxInt = 100) Integer b){
		return RecursionPower.power(a,b);
	}
}
