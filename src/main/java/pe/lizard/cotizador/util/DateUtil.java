package pe.lizard.cotizador.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getDate(Date date) {
        return addDays(date, 0);
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.DAY_OF_YEAR, days);

        return calendar.getTime();
    }

    public static String toString(String pattern, Date fecha) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(fecha);
    }

    public static Date toDate(String pattern, String fecha) {
        Date date = null;

        try {
            DateFormat df = new SimpleDateFormat(pattern);
            date = df.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
