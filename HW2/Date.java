package HW2;

/* Date.java */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Date {

	/* Put your private data fields here. */
	private int month;
	private int day;
	private int year;
	
	
	/** Constructs a date with the given month, day and year.   If the date is
	 *  not valid, the entire program will halt with an error message.
	 *  @param month is a month, numbered in the range 1...12.
	 *  @param day is between 1 and the number of days in the given month.
	 *  @param year is the year in question, with no digits omitted.
	 */
	public Date(int month, int day, int year) {
		if ( month>0 && month<13 && day>0 && day<32 && Date.isValidDate(month, day, year) == true) {
			this.month = month;
			this.day = day;
			this.year = year;
		} else {
			System.out.println("Error: the date is not valid");
			System.exit(0);
		}

	}

	/** Constructs a Date object corresponding to the given string.
	 *  @param s should be a string of the form "month/day/year" where month must
	 *  be one or two digits, day must be one or two digits, and year must be
	 *  between 1 and 4 digits.  If s does not match these requirements or is not
	 *  a valid date, the program halts with an error message.
	 */
	public Date(String s) {
		int mm=0,yy=0,dd=0;
		// check pattern
		String pattern = "^([0-9]{1,2})/([0-9]{1,2})/([0-9]{1,4})$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(s);
	    if (m.find()) {
			mm = Integer.valueOf(m.group(1));
			dd = Integer.valueOf(m.group(2));
			yy = Integer.valueOf(m.group(3));
	    } else {
			System.out.println("Error: input format is wrong");
			System.exit(0);
	    }
	    
	    if (mm>0 && mm<13 && dd>0 && dd<32 && Date.isValidDate(mm, dd, yy) == true) {
	    	this.month = mm;
	    	this.day = dd;
	    	this.year = yy;
	    } else {
			System.out.println("Error: the date is not valid");
			System.exit(0);
	    }
	}

	/** Checks whether the given year is a leap year.
	 *  @return true if and only if the input year is a leap year.
	 */
	public static boolean isLeapYear(int year) {
		boolean ans; 
		//A leap year is any year divisible by 4, 
		//except that a year divisible by 100 is not a leap year, 
		//except that a year divisible by 400 is a leap year after all.
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					ans = true;
				} else {
					ans = false;
				}
			} else {
				ans = true;
			}
		} else {
			ans = false;
		}
		return ans;
	}

	/** Returns the number of days in a given month.
	 *  @param month is a month, numbered in the range 1...12.
	 *  @param year is the year in question, with no digits omitted.
	 *  @return the number of days in the given month.
	 */
	public static int daysInMonth(int month, int year) {
		//February contains 28 days most years, but 29 days during a leap year. 
		int day = 0;
		switch (month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				day = 30;
				break;
			case 2:
				if (Date.isLeapYear(year)==true) {
					day = 29;
				} else {
					day = 28;
				}
				break;
		}
		return day;
	}

	/** Checks whether the given date is valid.
	 *  @return true if and only if month/day/year constitute a valid date.
	 *
	 *  Years prior to A.D. 1 are NOT valid.
	 */
	public static boolean isValidDate(int month, int day, int year) {
		return (month < Date.daysInMonth(month, year));
	}

	/** Returns a string representation of this date in the form month/day/year.
	 *  The month, day, and year are expressed in full as integers; for example,
	 *  12/7/2006 or 3/21/407.
	 *  @return a String representation of this date.
	 */
	public String toString() {
		return String.format("%d/%d/%d",this.month,this.day,this.year);
	}

	/** Determines whether this Date is before the Date d.
	 *  @return true if and only if this Date is before d. 
	 *  Important Thing: 
	 *  all the methods in the Date class can read all the private fields in any Date object (not just "this" Date object).
	 */
	public boolean isBefore(Date d) {
		boolean ans = false;
		if (this.year < d.year) {
			ans = true;
		} else if (this.year==d.year) {
			if (this.month < d.month) {
				ans = true;
			} else if (this.month==d.month) {
				if (this.day < d.day) {
					ans= true;
				} else {
					ans =false;
				}
			} else {
				ans = false;
			}
		} else {
			ans = false;
		}
		return ans;
	}

	/** Determines whether this Date is after the Date d.
	 *  @return true if and only if this Date is after d. 
	 */
	public boolean isAfter(Date d) {
		return d.isBefore(this);
	}

	/** Returns the number of this Date in the year.
	 *  @return a number n in the range 1...366, inclusive, such that this Date
	 *  is the nth day of its year.  (366 is used only for December 31 in a leap
	 *  year.)
	 */
	public int dayInYear() {
		if (Date.isLeapYear(this.year)) {
			return 366;
		} else {
			return 365;
		}
	}

	/** Determines the difference in days between d and this Date.  For example,
	 *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
	 *  If this Date occurs before d, the result is negative.
	 *  @return the difference in days between d and this date.
   	*/
	public int difference(Date d) {
		// this - 0/0/0
		int this_DaysFromZero = 0;
		for (int yy=0; yy<this.year ;yy+=1){
			if (Date.isLeapYear(yy)) {
				this_DaysFromZero += 366;
			} else {
				this_DaysFromZero += 365;
			}
		}
		for (int mm=1; mm<this.month ;mm+=1){
			this_DaysFromZero += Date.daysInMonth(mm,this.year);
		}
		this_DaysFromZero += this.day;

		// d - 0/0/0		
		int d_DaysFromZero = 0;
		for (int yy=0; yy<d.year ;yy+=1){
			if (Date.isLeapYear(yy)) {
				d_DaysFromZero += 366;
			} else {
				d_DaysFromZero += 365;
			}
		}
		for (int mm=1; mm<d.month ;mm+=1){			
			d_DaysFromZero += Date.daysInMonth(mm,d.year);
		}
		d_DaysFromZero += d.day;	

		return (this_DaysFromZero - d_DaysFromZero);
	}

	public static void main(String[] argv) {
		System.out.println("\nTesting constructors.");
		Date d1 = new Date(1, 1, 1);
		System.out.println("Date should be 1/1/1: " + d1);
		d1 = new Date("2/4/2");
		System.out.println("Date should be 2/4/2: " + d1);
		d1 = new Date("2/29/2000");
		System.out.println("Date should be 2/29/2000: " + d1);
		d1 = new Date("2/29/1904");
		System.out.println("Date should be 2/29/1904: " + d1);

		d1 = new Date(12, 31, 1975);
		System.out.println("Date should be 12/31/1975: " + d1);
		Date d2 = new Date("1/1/1976");
		System.out.println("Date should be 1/1/1976: " + d2);
		Date d3 = new Date("1/2/1976");
		System.out.println("Date should be 1/2/1976: " + d3);

		Date d4 = new Date("2/27/1977");
		Date d5 = new Date("8/31/2110");

		/* I recommend you write code to test the isLeapYear function! */

		System.out.println("\nTesting before and after.");
		System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
		System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
		System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
		System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
		System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

		System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
		System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
		System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
		System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
		System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

		System.out.println("\nTesting difference.");
		System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
		System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
		System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
		System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
		System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
	}
}
