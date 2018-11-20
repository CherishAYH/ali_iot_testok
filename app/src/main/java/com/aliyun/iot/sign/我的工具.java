package com.aliyun.iot.sign;
//wy的工具包

import java.util.Date;
import java.util.SimpleTimeZone;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class 我的工具 {
    private final static String TIME_ZONE = "GMT";
    private final static String FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	//取ISO8601格式时间戳（	调用方法：XXXXXX.getISO8601Time(null);		）
    public static String getISO8601Time(Date date) {
        Date nowDate = date;
        if (null == date) {
            nowDate = new Date();//取出现行时间并赋值
        }
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_ISO8601);//取标准时间
        df.setTimeZone(new SimpleTimeZone(0, TIME_ZONE));//设置时区
        return df.format(nowDate);//处理时间并返回Timestamp
    }
    //生成UUID随机码（	调用方法：XXXXXX.getUniqueNonce()	）
    public static String getUniqueNonce() {
        UUID uuid = UUID.randomUUID();//生成随机码：singnatureonce
        return uuid.toString();//返回随机码
    }
}