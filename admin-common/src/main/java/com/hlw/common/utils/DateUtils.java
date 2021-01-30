package com.hlw.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @author mqz
 * @description
 * @since 2020/5/27
 */
public class DateUtils {

         /**禁止定义成 static 变量或者全局共享变量，因为它是线程不安全的*/
//        private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        public static void main(String[] args) {
//
//            System.out.println(getDays("2019-06-28", dateFormat.format(new Date())));
//        }

        /**
         * 获取两个日期之间的所有日期
         * @param startTime 开始日期
         * @param endTime 结束日期
         * @return
         */
        public static List<String> getDays(String startTime, String endTime) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<String> days = new ArrayList<>();
            try {
                Date start = dateFormat.parse(startTime);
                Date end = dateFormat.parse(endTime);
                Calendar tempStart = Calendar.getInstance();
                tempStart.setTime(start);
                Calendar tempEnd = Calendar.getInstance();
                tempEnd.setTime(end);
                /**日期加1(包含结束)*/
                tempEnd.add(Calendar.DATE, +1);
                while (tempStart.before(tempEnd)) {
                    days.add(dateFormat.format(tempStart.getTime()));
                    tempStart.add(Calendar.DAY_OF_YEAR, 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();

            }
            return days;
        }

}
