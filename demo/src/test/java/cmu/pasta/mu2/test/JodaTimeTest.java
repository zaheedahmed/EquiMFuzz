package cmu.pasta.mu2.test;

import cmu.pasta.mu2.JodaTime;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class JodaTimeTest {

	@Test
	public void testIsAfterPayDay() {
		JodaTime jodaTime = new JodaTime();

		// Test for a date after pay day
		DateTime afterPayDay = new DateTime(2024, 2, 28, 12, 0, 0);
		Assert.assertTrue(jodaTime.isAfterPayDay(afterPayDay));

		// Test for a date on pay day
		DateTime onPayDay = new DateTime(2024, 2, 26, 12, 0, 0);
		Assert.assertFalse(jodaTime.isAfterPayDay(onPayDay));

		// Test for a date before pay day
		DateTime beforePayDay = new DateTime(2024, 2, 25, 12, 0, 0);
		Assert.assertFalse(jodaTime.isAfterPayDay(beforePayDay));
	}
	@Test
	public void testDaysToNewYear(){
		JodaTime jodaTime = new JodaTime();
		LocalDate currentDate = LocalDate.parse("2024-02-16");
		Days result = jodaTime.daysToNewYear(currentDate);
        Assert.assertEquals(320, (result.getDays()));
	}

	@Test
	public void testIsRentalOverdue() {
		JodaTime jodaTime = new JodaTime();

		// Test for an overdue rental
		DateTime overdueRental = new DateTime(2024, 2, 10, 12, 0, 0);
		Assert.assertTrue(jodaTime.isRentalOverdue(overdueRental));

		// Test for a rental not overdue
		DateTime notOverdueRental = new DateTime(2024, 2, 12, 12, 0, 0);
		Assert.assertTrue(jodaTime.isRentalOverdue(notOverdueRental));
	}

	@Test
	public void testGetBirthMonthText() {
		JodaTime jodaTime = new JodaTime();

		// Test for a birth month
		LocalDate birthDate = new LocalDate(1990, 5, 15);
		Assert.assertEquals("May", jodaTime.getBirthMonthText(birthDate));
	}
}
