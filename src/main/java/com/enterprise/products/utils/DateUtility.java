package com.enterprise.products.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public final class DateUtility {
   private static String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
   private static String TRANSACTION_DATE_FORMATE = "yyyy-MM-dd";
   private static String TIMESTAMP_FORMAT = "yyyy-MM-dd HH-mm-ss a";
   private static String MONTH_FORMAT = "MM";
   private static String YEAR_FORMAT = "yyyy";
   private static final DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

   /**
    * Format the given date.
    */
   public static String formatDate(Date date) {
      return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
   }

   /**
    * Format the given date for Advantage Gen proxy calls.
    * 
    */
   public static String transactionDate(String stDate) throws Exception {
	   String result = null;
	   try {
		   Date date = dateFormat.parse(stDate);
		   result = new SimpleDateFormat(TRANSACTION_DATE_FORMATE).format(date);
	   } catch (Exception exp) {
		   throw new Exception("Error occured in transaction formate date:"+exp.toString());
	   }
	   return result;
   }

   /**
    * Parse the given string as a date in the format of MMDDYYYY
    * 
    */
   public static Date parseAsMMDDYYYY(String dateString) throws Exception {
      Date date = null;
      try {
         date = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dateString);
      } catch (Exception exp) {
    	  throw exp;
      }
      return date;
   }

   /**
    * @return Returns current date.   
    */
   public static Date getTodayDate() {
      return Calendar.getInstance().getTime();
   }

   /**
    * @return Convert string date to a <code>Date</code> object.
    */
   public static Date getDate(String formatedDate) {
      Date date = null;
      try {
         date = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(formatedDate);
      } catch (Exception exp) {
      }
      return date;
   }

   /**
    * @param date
     */
   public static int getQuarter(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return (calendar.get(Calendar.MONTH) + 3) / 3;
   }

   /**
    * @return Begin date by current quarter
    */
   public static Date getBeginDateByCurrentQuarter() {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, 0 - (cal.get(Calendar.MONTH) % 3));
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return cal.getTime();
   }

   /**
    * @return End date by current quarter
    */
   public static Date getEndDateByCurrentQuarter() {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, 2 - (cal.get(Calendar.MONTH) % 3));
      cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
      return cal.getTime();
   }

   /**
    * @param quarter
    * @return quarter begin date as String
    */
   public static String getBeginDateByQuarter(int quarter, String year) {
      if (quarter == 1) {
         return "01/01/" + year;
      }
      if (quarter == 2) {
         return "04/01/" + year;
      }
      if (quarter == 3) {
         return "07/01/" + year;
      }
      if (quarter == 4) {
         return "10/01/" + year;
      }
      return null;
   }

   /**
    * @param quarter
    * @return quarter end date as String
    */
   public static String getEndDateByQuarter(int quarter, String year) {
      //Calendar cal = Calendar.getInstance();
      if (quarter == 1) {
         return "03/31/" + year;
      }
      if (quarter == 2) {
         return "06/30/" + year;
      }
      if (quarter == 3) {
         return "09/30/" + year;
      }
      if (quarter == 4) {
         return "12/31/" + year;
      }
      return null;
   }

   /**
    * @param quarter
    * @return quarter begin date as Date
    */
   public static Date getBeginDateByQuarterYear(int quarter, String year) {
      return getDate(getBeginDateByQuarter(quarter, year));
   }

   /**
    * @param quarter
    * @return quarter end date as Date
     */
   public static Date getEndDateByQuarterYear(int quarter, String year) {
      return getDate(getEndDateByQuarter(quarter, year));
   }

 

   /**
    * Convert the given <code>Date</code> object to a <code>Calendar</code>
    * object, <code>null</code> if none.
    * 
    */
   public static Calendar toCalendar(Date date) {
      if (date == null) {
         return null;
      }
      Calendar cal = Calendar.getInstance();

      cal.setTime(date);
      return cal;
   }

   /**
    * Convert the given <code>Calendar</code> object to a <code>Date</code>
    * object, <code>null</code> if none given.
    * 
    */
   public static Date toDate(Calendar cal) {
      return cal != null ? cal.getTime() : null;
   }

   /**
    * This method format a date to yyyy-MM-DD HH:MM:SS
    * 
    */
   public static String getFormatTimestamp(Timestamp timestamp) {
      return new SimpleDateFormat(TIMESTAMP_FORMAT).format(timestamp);
   }

   public static Timestamp getCurrentTimestamp() {
      return new Timestamp(getTodayDate().getTime());
   }

   /**
    * Get the month from the date
     */
   public static int getMonthsBetween(Date currentDate, Date filingDueDate) {
      SimpleDateFormat yearFormatter = new SimpleDateFormat(YEAR_FORMAT);
      SimpleDateFormat monthFormatter = new SimpleDateFormat(MONTH_FORMAT);
      int currentYear = Integer.parseInt(yearFormatter.format(currentDate));
      int filingDueYear = Integer.parseInt(yearFormatter.format(filingDueDate));
      int yearMonths = 0;
      int monthMonths = 0;
      if (currentYear > filingDueYear) {
         yearMonths = (currentYear - filingDueYear) * 12;
      }
      int currentMonth = Integer.parseInt(monthFormatter.format(currentDate));
      int filingDueMonth = Integer.parseInt(monthFormatter.format(filingDueDate));
      monthMonths = (currentMonth - filingDueMonth);
      return yearMonths + monthMonths;
   }

   /**
    * Number of days to add into input date
    * 
     */
   public static String addDaysToDate(Date inputDate, int numberOfDays) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(inputDate);
      calendar.add(Calendar.DATE, numberOfDays);
      return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(calendar.getTime());
   }

   /**
    * Default constructor - disabled.
    */
   private DateUtility() {
   }

   

   /**
    * Clear all of the time related fields from this calendar. This method is
    * useful if you are attempting to compare two calendars on the basis of
    * their dates.
   
    */
   public static void clearTimeFields(Calendar cal) {
      cal.clear(Calendar.HOUR);
      cal.clear(Calendar.AM_PM);
      cal.clear(Calendar.HOUR_OF_DAY);
      cal.clear(Calendar.MINUTE);
      cal.clear(Calendar.SECOND);
      cal.clear(Calendar.MILLISECOND);
   }

   /**
    * Compare two calendars on the basis of their dates. Return -1 if the date
    * of cal1 is less than the date of cal2, 0 if the date of cal1 is the same
    * as the date of cal2 and 1 if the date of cal1 is greater than the date of
    * cal2.
    * 
   
    */
   public static int dateCompare(Calendar cal1, Calendar cal2) {
      Calendar date1 = (Calendar) cal1.clone(), date2 = (Calendar) cal2.clone();
      clearTimeFields(date1);
      clearTimeFields(date2);
      if (date1.before(date2))
         return -1;
      else if (date1.after(date2))
         return 1;
      else
         return 0;
   }

}