package com.neusoft.hr.sys.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

//自定义参数绑定   String ->转  Date

public class DateConverter implements Converter<String,Date> {

    public Date convert(String s) {
        try {
            return DateUtils.parseDate(s,"yyyy-mm-dd HH:mm:ss","yyyy-mm-dd");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
