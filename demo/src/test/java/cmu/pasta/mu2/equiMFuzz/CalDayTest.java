package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.CalDay;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class CalDayTest {

	@DiffFuzz
	public void testToJulian(@InRange(minInt = 1, maxInt = 31) Integer day,
							 @InRange(minInt = 1, maxInt = 12) Integer month,
							 @InRange(minInt = 1900, maxInt = 2025) Integer year) {
		CalDay calDay = new CalDay();
		calDay.toJulian(day, month, year);
		// You can add assertions or additional logic based on your testing requirements
	}
}
