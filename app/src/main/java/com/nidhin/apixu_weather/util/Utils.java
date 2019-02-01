package com.nidhin.apixu_weather.util;

import android.app.Activity;
import android.util.DisplayMetrics;

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
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date= dateFormat.parse(value);
        String dateString = String.valueOf((date.getDate()));
        dateString = getDayStringFromDayNumeric(date.getDay()) + ", " + dateString+getDayOfMonthSuffix(date.getDate()) ;
        return dateString;
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


    public static float getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static float getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
