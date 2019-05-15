package com.expensetracker.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UtcDate {
	String datetime;
	String utcdatetime;
	String date;
	String month;
	String year;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public UtcDate() {
		Calendar c = Calendar.getInstance();
		TimeZone z = c.getTimeZone();
		int offset = z.getRawOffset();
		if (z.inDaylightTime(new Date())) {
			offset = offset + z.getDSTSavings();
		}
		int offsetHrs = offset / 1000 / 60 / 60;
		int offsetMins = offset / 1000 / 60 % 60;
		c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
		c.add(Calendar.MINUTE, (-offsetMins) - 3);
		SimpleDateFormat yyy = new SimpleDateFormat("yyyy");
		this.year = yyy.format(c.getTime());
		SimpleDateFormat MM = new SimpleDateFormat("MM");
		this.month = MM.format(c.getTime());
		SimpleDateFormat DD = new SimpleDateFormat("dd");
		this.date = DD.format(c.getTime());
	}
}