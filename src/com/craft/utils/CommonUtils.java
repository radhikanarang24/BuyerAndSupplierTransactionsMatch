package com.craft.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static Date getDateFromString(String strDate) {
        if (strDate != null && !strDate.isEmpty()) {
            SimpleDateFormat[] formats =
                    new SimpleDateFormat[]{new SimpleDateFormat("dd-MM-yyyy"), new SimpleDateFormat("yyyyMMdd"),
                            new SimpleDateFormat("MM/dd/yyyy")};

            Date parsedDate = null;

            for (SimpleDateFormat format : formats) {
                try {
                    parsedDate = format.parse(strDate);
                    return parsedDate;
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        return null;
    }
}
