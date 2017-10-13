package hubble.backend.core.utils.tests;

import hubble.backend.core.utils.DateHelper;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateHelperUnitTest {
        
    @Test
    public void date_helper_should_return_difference_in_days(){
        //Assign
        final int ADAYINMILLIS = 86400*1000; 
        Date toNow = new Date();
        //Act
        Date fromDate = new Date(toNow.getTime()- ADAYINMILLIS*3);
        long diff = DateHelper.getDateDiff(fromDate, toNow, TimeUnit.DAYS);
        //Assert
        assertEquals(3, diff);     
    }
    
    @Test
    public void date_helper_should_add_four_days_to_date(){
        //Assign
        final int ADAYINMILLIS = 86400*1000; 
        Date now = new Date();
        
        //Act
        Date fourDaysBefore =  new Date(now.getTime()- ADAYINMILLIS*4);
        
        //Assert
        assertEquals(now, DateHelper.addDaysToDate(fourDaysBefore, 4));
    }

}
