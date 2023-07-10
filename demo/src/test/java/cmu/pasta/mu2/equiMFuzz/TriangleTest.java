package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.Triangle;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.Comparison;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class TriangleTest {

	@DiffFuzz(cmp = "compareIntegers")
	public Integer testTriangle(@InRange(minInt = -100, maxInt = 100) Integer a, @InRange(minInt = -100, maxInt = 100) Integer b, @InRange(minInt = -100, maxInt = 100) Integer c){
		return Triangle.classify(a,b,c);
	}

	@Comparison
	public static Boolean compareIntegers(Integer a, Integer b){
		return a-b == 0;
}

}
