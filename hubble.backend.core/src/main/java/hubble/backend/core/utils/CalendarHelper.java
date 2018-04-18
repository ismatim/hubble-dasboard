package hubble.backend.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class CalendarHelper {

    public static final int TEN_MINUTES = 10;
    public static final int ONE_HOUR = 60;
    public static final int ONE_DAY = 1440;
    public static final int ONE_WEEK = 10080;
    public static final int ONE_MONTH = 1;

    public static Calendar getNow() {

        Calendar nowCalendar = Calendar.getInstance();
        Date now = new Date();
        nowCalendar.setTime(now);

        return nowCalendar;
    }

    public static Date getDateTimezone(String timezone, int duration) {

        Calendar from = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        from.add(Calendar.MINUTE, -duration);

        int hour = from.get(Calendar.HOUR_OF_DAY);
        int min = from.get(Calendar.MINUTE);
        int sec = from.get(Calendar.SECOND);
        int day = from.get(Calendar.DAY_OF_MONTH);
        int month = from.get(Calendar.MONTH);
        int year = from.get(Calendar.YEAR);

        return new Date(year, month, day, hour, min, sec);
    }

    public static int getDifferenceDays(Date dateFrom, Date dateTo) {

        DateTime dtFrom = new DateTime(dateFrom);
        DateTime dtTo = new DateTime(dateTo);

        return Days.daysBetween(dtFrom, dtTo).getDays();

    }

}
