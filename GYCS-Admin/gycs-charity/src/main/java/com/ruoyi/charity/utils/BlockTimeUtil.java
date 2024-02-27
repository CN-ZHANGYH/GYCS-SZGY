package com.ruoyi.charity.utils;

import com.alibaba.fastjson.JSONArray;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class BlockTimeUtil {

    /**
     * 字符串转毫秒
     *
     * @param dateString 日期字符串
     * @return 毫秒
     * @throws ParseException
     */
    public static long stringToMillis(String dateString,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateString).getTime();
    }

    /**
     * 将毫秒级别的时间戳进行转换
     * @param timestamp 时间戳
     * @return 返回格式化好的时间
     */
    public static String convertToDate(Long timestamp){
        long timestampInSecond = timestamp / 1000L;
        Date date = new Date(timestampInSecond * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * 将毫秒级别的时间戳进行转换
     * @param timestamp 时间戳
     * @return 返回格式化好的时间
     */
    public static String convertToDateTime(Long timestamp){
        long timestampInSecond = timestamp / 1000L;
        Date date = new Date(timestampInSecond * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 将毫秒级别的时间戳进行转换
     * @param timestamp 时间戳
     * @return 返回格式化好的时间
     */
    public static String convertToTime(Long timestamp){
        long timestampInSecond = timestamp / 1000L;
        Date date = new Date(timestampInSecond * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return dateFormat.format(date);
    }

    /**
     * 将毫秒级别的时间戳进行转换
     * @param timestamp 时间戳
     * @return 返回格式化好的时间
     */
    public static String convertToLocalTime(Long timestamp){
        long timestampInSecond = timestamp / 1000L;
        Date date = new Date(timestampInSecond * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return dateFormat.format(date);
    }

}
