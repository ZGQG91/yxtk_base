package com.yidu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/31.
 */
public class ModuleTools {
    public static String formatDate(Date date, String format){
        try{
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        }catch(Exception e){
            return null;
        }
    }

    public static long formatDate(String date,String format){
        try {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            Date time = simpleDateFormat .parse(date);
            long timeStemp = time.getTime();
            System.out.println(timeStemp );
            return timeStemp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
