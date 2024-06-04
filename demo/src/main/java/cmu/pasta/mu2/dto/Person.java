package cmu.pasta.mu2.dto;

import javax.enterprise.inject.Default;

// This is mutant program.
// Author : ysma

public class Person {
	private String firstname;
	private String lastname;

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	private PhoneNumber phone;

	public void setFax(PhoneNumber fax) {
		this.fax = fax;
	}

	private PhoneNumber fax;

	public Person(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	// ... constructors and methods
}
