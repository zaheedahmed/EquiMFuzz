package cmu.pasta.mu2.test;

import cmu.pasta.mu2.PersonalDetail;
import cmu.pasta.mu2.dto.Person;
import cmu.pasta.mu2.dto.PhoneNumber;
import org.junit.Assert;
import org.junit.Test;

public class PersonalDetailTest {
	@Test
	public void testPersonalDetail(){
		Person person = new Person("Umair", "Bin Yousaf");
		person.setPhone(new PhoneNumber(123, "1234-456"));
		person.setFax(new PhoneNumber(123, "9999-999"));
		PersonalDetail personalDetail = new PersonalDetail();
		String result = personalDetail.toXml(person);
		Assert.assertTrue(result.contains("3"));
	}
}
