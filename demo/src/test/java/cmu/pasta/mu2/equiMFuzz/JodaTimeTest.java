package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.JodaTime;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.generator.InRange;

@RunWith(JQF.class)
public class JodaTimeTest {

	@DiffFuzz
	public void testIsAfterPayDay(@InRange(minInt = 1, maxInt = 12) Integer month,
								  @InRange(minInt = 1, maxInt = 31) Integer day,
								  @InRange(minInt = 2000, maxInt = 2100) Integer year) {
		JodaTime jodaTime = new JodaTime();
		DateTime dateTime = new DateTime(year, month, day, 0, 0);
		jodaTime.isAfterPayDay(dateTime);
	}

	@DiffFuzz
	public void testDaysToNewYear(@InRange(minInt = 1, maxInt = 12) Integer month,
								  @InRange(minInt = 1, maxInt = 31) Integer day,
								  @InRange(minInt = 2000, maxInt = 2100) Integer year) {
		JodaTime jodaTime = new JodaTime();
		LocalDate fromDate = new LocalDate(year, month, day);
		jodaTime.daysToNewYear(fromDate);
	}

	@DiffFuzz
	public void testIsRentalOverdue(@InRange(minInt = 1, maxInt = 12) Integer month,
									@InRange(minInt = 1, maxInt = 31) Integer day,
									@InRange(minInt = 2000, maxInt = 2100) Integer year) {
		JodaTime jodaTime = new JodaTime();
		DateTime dateTimeRented = new DateTime(year, month, day, 0, 0);
		jodaTime.isRentalOverdue(dateTimeRented);
	}

	@DiffFuzz
	public void testGetBirthMonthText(@InRange(minInt = 1, maxInt = 12) Integer month,
									  @InRange(minInt = 1, maxInt = 31) Integer day,
									  @InRange(minInt = 2000, maxInt = 2100) Integer year) {
		JodaTime jodaTime = new JodaTime();
		LocalDate birthDate = new LocalDate(year, month, day);
		jodaTime.getBirthMonthText(birthDate);
	}
}
