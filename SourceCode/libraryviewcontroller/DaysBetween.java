/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Silpa
 */
public class DaysBetween {
        
    
    public static int daysBetween(Calendar startDate, Calendar endDate) {  
            Calendar date = (Calendar)startDate.clone();  
            int daysBetween = 0;  
            while (date.before(endDate)) {  
            date.add(Calendar.DAY_OF_MONTH, 1);  
            daysBetween++;  
            }  
           
            return daysBetween;  
        }
        
        public static Calendar dateToCalendar(Date date){ 
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return cal;
        }
        
        public static int calculateDaysBetween(Date startDate, Date endDate){
            Calendar sD = dateToCalendar(startDate);
            Calendar eD = dateToCalendar(endDate);
            int days = daysBetween(sD, eD);
            return days;
        }
        /*
        public static void main(String[] args)
        {
            Transaction transaction = new Transaction();
            int tranNo = transaction.selectTransaction("SELECT * FROM Transaction WHERE itemNumber like '" + "V30001"+"' AND (MemberID like '" + "00007"+"' )");
            Date todayDate = new Date();
            System.out.println(todayDate);
            Date dueDate = transaction.getEndDate();
            System.out.println(dueDate);
            int k = calculateDaysBetween(dueDate, todayDate);
            System.out.println(k);
        }*/
}
