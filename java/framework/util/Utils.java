package framework.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Date;
import java.util.Random;

public class Utils {
    public static Date convertLocalDateToDate(LocalDate localDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }
    public static String convertDateToString(Date indate)
    {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");

        try{
            dateString = sdfr.format( indate );
        }catch (Exception ex ){
            System.out.println(ex);
        }
        return dateString;
    }
    public static final String COMPANY = "company";
    public static final String PERSONAL = "personal";

    public static int randInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
