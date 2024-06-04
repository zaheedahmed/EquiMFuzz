package cmu.pasta.mu2;
// This is mutant program.
// Author : ysma

import cmu.pasta.mu2.dto.Person;
import cmu.pasta.mu2.dto.PhoneNumber;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;

public class PersonalDetail
{
	XStream xstream = new XStream();

	public String toXml( Person person )
	{
		xstream.alias("person", Person.class);
		xstream.alias("phonenumber", PhoneNumber.class);
        return xstream.toXML(person);
	}

}