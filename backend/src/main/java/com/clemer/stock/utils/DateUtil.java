package com.clemer.stock.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final ZoneId zoneId = ZoneId.systemDefault();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LOCAL_DATETIME_FORMATTER);

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    public static LocalDateTime getLocalDateTimeWithZeroMillis(LocalDateTime localDateTime) {
        return LocalDateTime.parse(localDateTime.format(formatter), formatter);
    }
}
