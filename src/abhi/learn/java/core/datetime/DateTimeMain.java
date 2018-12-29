package abhi.learn.java.core.datetime;

import java.time.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Abhishek on 10/7/2018.
 */
public class DateTimeMain {

    public static void main(String[] args) throws Exception {
        System.out.println("START");
        testInstant();
        System.out.println("END");
    }

    private static void testInstant(){
        Instant instant = Instant.now();
        long secs = instant.getEpochSecond();
        System.out.println("secs = " + secs);
        int nano = instant.getNano();
        System.out.println("nano = " + nano);

    }

    private static void testDate(){
        TimeZone timeZone1 = TimeZone.getTimeZone("America/Los_Angeles");
        TimeZone timeZone2 = TimeZone.getTimeZone("Europe/Copenhagen");

        Calendar calendar = new GregorianCalendar();

        long timeCPH = calendar.getTimeInMillis();
        System.out.println("timeCPH  = " + timeCPH);
        System.out.println("hour     = " + calendar.get(Calendar.HOUR_OF_DAY));

        calendar.setTimeZone(timeZone1);

        long timeLA = calendar.getTimeInMillis();
        System.out.println("timeLA   = " + timeLA);
        System.out.println("hour     = " + calendar.get(Calendar.HOUR_OF_DAY));
    }

    private static void testLocalTime(){
        LocalDate date = LocalDate.of(2018, 02,9);
        System.out.println(date);
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        LocalTime onlyTime = LocalTime.now();
        System.out.println(onlyTime);

        ZonedDateTime zd = ZonedDateTime.now();
        System.out.println(zd);
        ZonedDateTime zdInd = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(zdInd);
        ZonedDateTime zdInd2 = ZonedDateTime.now(ZoneId.ofOffset("GMT", ZoneOffset.ofHours(5)));
        System.out.println(zdInd2);
//        DateTimeFormatter formatter = new DateTimeFormatter()

    }
}
