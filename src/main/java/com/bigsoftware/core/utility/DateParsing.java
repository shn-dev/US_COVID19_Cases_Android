package com.bigsoftware.core.utility;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * The CSV file provided by Johns Hopkins does not have a consistent date format. For example,
 * January 1, 2020, is represented as "1/1/2020" instead of "01/01/2020". This class converts
 * mal-formed dates into MM/dd/yyyy representation.
 */

public class DateParsing {

    /**
     * Converts a date of type MM/dd/yyyy, where MM and dd may both either be one or two digits.
     * @param date
     * @return
     */
    public static Date convertSpecialDate(String date) throws ParseException{
        String[] segments = date.split("/");
        List<String> newSegments = new ArrayList<>();
        for (String s :
                segments) {
            if (s.length() == 1) {
                StringBuilder sb = new StringBuilder(s);
                sb.insert(0, "0");
                newSegments.add(sb.toString());
            }
            else{
                newSegments.add(s);
            }
        }
        String newDate = TextUtils.join("/", newSegments);

        return new SimpleDateFormat("MM/dd/yyyy").parse(newDate);
    }

}
