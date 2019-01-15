package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static String dataBaseTime2String(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (sdf.format(date));
    }

    public static String dataBaseDate2String(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return (sdf.format(date));
    }

}
