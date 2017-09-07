package hubble.backend.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHelper {   

    public static long getDateDiff(Date dateFrom, Date dateTo, TimeUnit timeUnit) {
        long diffInMillies = dateTo.getTime() - dateFrom.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    
    public static Date addDaysToDate(Date date, int daysQty){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, daysQty);
        return calendar.getTime();
    }
    
}
