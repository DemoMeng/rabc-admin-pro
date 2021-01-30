package com.hlw.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author mqz
 * @description
 * @since 2020/7/15
 */
public class OrderNoUtils {


    private static String getDateTime(){
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    public static String orderNo(){
        String date = getDateTime();
        long min = 1,max = 9;
        for (int i = 1; i < 4; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min ;
        return date+rangeLong;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            System.out.println(orderNo());
        }
    }




}
