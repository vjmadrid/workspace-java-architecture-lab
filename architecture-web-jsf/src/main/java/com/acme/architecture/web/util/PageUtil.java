package com.acme.architecture.web.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PageUtil {

    public static void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch(Exception e) {}
    }

    public static Date getActualDate() {
        GregorianCalendar gc = new GregorianCalendar();
        Date date = gc.getTime();
        return date;
    }

    public static int getDay (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
