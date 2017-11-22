package com.syzible.boop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ed on 22/11/2017.
 */

public class TimeUtils {
    public static String getDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault());
        Date date = new Date(time);
        return format.format(date);
    }
}
