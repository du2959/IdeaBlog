package ideablog.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyTime {

    public static String getMyTime() {
        long l = System.currentTimeMillis();
        Date time = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
}
