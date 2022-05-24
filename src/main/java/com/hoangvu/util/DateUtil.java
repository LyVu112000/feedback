package com.hoangvu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    private static final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public static Date format(String date) throws ParseException {
        return format(date, "yyyy-MM-dd");
    }

    public static Date format(String date, String pattern) throws ParseException {
        if (Objects.isNull(date)) {
            throw new IllegalArgumentException("date cannot be null");
        }
        formater.applyPattern(pattern);
        return formater.parse(date);
    }

    public static String parse(Date date) {
        return formater.format(date);
    }
}