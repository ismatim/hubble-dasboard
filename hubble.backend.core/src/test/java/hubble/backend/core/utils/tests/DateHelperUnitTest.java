package hubble.backend.core.utils.tests;

import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.DateHelper;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import static org.junit.Assert.*;
import org.junit.Test;

public class DateHelperUnitTest {

    @Test
    public void date_helper_should_return_difference_in_days() {
        //Assign
        final int ADAYINMILLIS = 86400 * 1000;
        Date toNow = new Date();
        //Act
        Date fromDate = new Date(toNow.getTime() - ADAYINMILLIS * 3);
        long diff = DateHelper.getDateDiff(fromDate, toNow, TimeUnit.DAYS);
        //Assert
        assertEquals(3, diff);
    }

    @Test
    public void date_helper_should_add_four_days_to_date() {
        //Assign
        final int ADAYINMILLIS = 86400 * 1000;
        Date now = new Date();

        //Act
        Date fourDaysBefore = new Date(now.getTime() - ADAYINMILLIS * 4);

        //Assert
        assertEquals(now, DateHelper.addDaysToDate(fourDaysBefore, 4));
    }

    @Test
    public void calendarhelper_should_get_diff_from_dates() {

        //Assign
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTime dateFrom = formatter.parseDateTime("2017-09-19T15:44:23.256Z");
        DateTime dateTo = formatter.parseDateTime("2017-09-21T15:44:23.256Z");

        //Act
        int days = CalendarHelper.getDifferenceDays(dateFrom.toDate(), dateTo.toDate());

        //Assert
        assertEquals(days, 2);
    }
}
