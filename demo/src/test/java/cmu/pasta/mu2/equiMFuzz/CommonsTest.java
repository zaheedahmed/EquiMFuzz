package cmu.pasta.mu2.equiMFuzz;

import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.apache.commons.lang.WordUtils;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class CommonsTest {

	@DiffFuzz
	public String testCapitalize(String str){
		return WordUtils.capitalize(str);
	}

	@DiffFuzz
	public String testWrap(String input, @InRange(minInt = 1, maxInt = 100) int wrapLength) {
		return WordUtils.wrap(input, wrapLength);
	}

	@DiffFuzz
	public String testCapitalizeFully(String input) {
		return WordUtils.capitalizeFully(input);
	}

	@DiffFuzz
	public String testUncapitalize(String input) {
		return WordUtils.uncapitalize(input);
	}

	@DiffFuzz
	public String testSwapCase(String input) {
		return WordUtils.swapCase(input);
	}

	@DiffFuzz
	public String testInitials(String input) {
		return WordUtils.initials(input);
	}

	@DiffFuzz
	public String testAbbreviate(String input, @InRange(minInt = 1, maxInt = 100) int lower,
								 @InRange(minInt = 1, maxInt = 100) int upper, String appendToEnd) {
		return WordUtils.abbreviate(input, lower, upper, appendToEnd);
	}


}
