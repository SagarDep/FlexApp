package jp.co.flexapp.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yumitsuhori on 2017/09/17.
 */

public class DateUtils {

    public static String convert2String(Date date) {
        return convert2String(date, "yyyy/MM/dd HH:mm:ss");
    }

    public static String convert2String(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date convert2Date() {
        return null;
    }
}
