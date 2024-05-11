package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.PersonalDetail;
import cmu.pasta.mu2.dto.Person;
import cmu.pasta.mu2.dto.PhoneNumber;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class PersonalDetailTest {

	@DiffFuzz
	public void testPersonalDetailWithFuzzing(@InRange(minInt = 100, maxInt = 999) int code,
											  @InRange(minInt = 1000000, maxInt = 9999999) int number) {
		// Generate random strings for first and last names if needed
		String firstName = "Umair"; // Consider using a random string generator
		String lastName = "Bin Yousaf";   // Consider using a random string generator

		PhoneNumber phone = new PhoneNumber(code, String.valueOf(number));
		PhoneNumber fax = new PhoneNumber(code, String.valueOf(number)); // Simplify for the test case

		Person person = new Person(firstName, lastName);
		person.setPhone(phone);
		person.setFax(fax);

		PersonalDetail detail = new PersonalDetail();
		String xml = detail.toXml(person);

		// Your assertion here, adjusted for the actual content you expect to test
		assert xml != null && !xml.isEmpty();
	}
}
