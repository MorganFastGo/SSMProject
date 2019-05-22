package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String DateToString(Date date,String patten){
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        String newDate = sdf.format(date);
        return newDate;
    }
    public static Date StringToDate(String date,String patten) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        Date newDate = sdf.parse(date);
        return newDate;
    }
}
