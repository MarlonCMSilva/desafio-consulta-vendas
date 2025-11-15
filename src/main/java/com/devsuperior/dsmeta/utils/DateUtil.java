package com.devsuperior.dsmeta.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {

    public static LocalDate[] parseMinAndMaxDate(String minDateStr, String maxDateStr) {
        LocalDate maxDate = (maxDateStr == null || maxDateStr.isEmpty())
                ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
                : LocalDate.parse(maxDateStr);

        LocalDate minDate = (minDateStr == null || minDateStr.isEmpty())
                ? maxDate.minusYears(1L)
                : LocalDate.parse(minDateStr);

        return new LocalDate[] { minDate, maxDate };
    }
}
