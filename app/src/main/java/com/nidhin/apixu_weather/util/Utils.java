package com.nidhin.apixu_weather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    static String apostrophe = String.valueOf("'");

    public static Date formatDate(String time, String format) {
        try {
            java.text.DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(time);

        } catch (Exception e) {
            return null;
        }
    }
    public static String getFormattedDate(String value) throws ParseException {
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date= dateFormat.parse(value);
        String dateString = getFormattedDay(date.getDate());
        dateString = getDayStringFromDayNumeric(date.getDay()) + ", " + dateString ;
        return dateString;
    }

    static String getFormattedDay(int day) {
        if (day < 10)
            return "0" + day;
        else
            return String.valueOf(day);
    }

    public static String getFormattedHour(int hour, int min) {
        String value;
        if (hour < 10)
            value = "0" + hour;
        else if (hour > 12) {
            if (hour - 12 < 10)
                value = "0" + String.valueOf(hour - 12);
            else
                value = String.valueOf(hour - 12);
        } else
            value = String.valueOf(hour);
        value = value + ":";
        if (min < 10)
            value = value + "0" + min;
        else
            value = value + min;
        if (hour > 11)
            value = value + " pm";
        else
            value = value + " am";
        return value;
    }

    static String getDayOfMonthSuffix(final int n) {
//        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static String getDayStringFromDayNumeric(int paramInt) {
        switch (paramInt) {
            default:
                return "Sunday";
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
        }

    }
}
