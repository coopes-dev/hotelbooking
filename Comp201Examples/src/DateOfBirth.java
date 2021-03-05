import java.util.Calendar;
import java.util.Date;

public class DateOfBirth extends Date {
	
	public DateOfBirth(int day, int month, int year) {
		super();
		
		Calendar dob=Calendar.getInstance();		// used to calculate date from components
		dob.set(Calendar.DAY_OF_MONTH,day);
		dob.set(Calendar.MONTH,month);
		dob.set(Calendar.YEAR,year);
		Calendar now=Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());  // set the currrent time
		now.set(Calendar.HOUR,0);now.set(Calendar.MINUTE,0);now.set(Calendar.SECOND,0);now.set(Calendar.MILLISECOND,0);
		if (now.getTimeInMillis()<dob.getTimeInMillis()) {
			throw new IllegalArgumentException("Bad Date of Birth, date in the future");
		}
		
		
		
	}


	private int day;
	private int month;
	private int year;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
