package com.bookmanagement.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * This is DateUtil class
 * 
 * @author Aung Ba
 *
 */
@Component
public class DateUtil {

	/**
	 * This method is used to get current date
	 * 
	 * @return currentDate
	 */
	public Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();

	}

	/**
	 * This method is used to get date from calendar
	 * 
	 * @param day number of day to increase current day
	 * @return date
	 */
	public Date getDate(int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
		return cal.getTime();
	}

	/**
	 * This method is used to format the date
	 * 
	 * @param date date
	 * @return formatted date
	 */
	public String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

}
