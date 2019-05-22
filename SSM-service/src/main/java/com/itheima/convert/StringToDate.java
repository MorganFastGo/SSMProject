package com.itheima.convert;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate implements Converter<String,Date> {

    @Override
    public Date convert(String source) {
        if (source == null) {
            throw new RuntimeException("请输入日期");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date= sdf.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式不正确");
        }
        return date;
    }
}
