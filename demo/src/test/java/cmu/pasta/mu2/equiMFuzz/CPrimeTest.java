package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.CPrime;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class CPrimeTest {
	@DiffFuzz()
	public boolean testCPrime(@InRange(minInt = 0, maxInt = 100) Integer n){
		return CPrime.isPrime(1);
	}
}
