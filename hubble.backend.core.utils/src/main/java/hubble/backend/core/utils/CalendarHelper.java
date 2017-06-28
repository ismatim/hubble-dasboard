package hubble.backend.core.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarHelper {

    public static Calendar getNow() {

        Date now = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(now);

        return nowCalendar;
    }
}
