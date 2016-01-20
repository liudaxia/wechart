package com.ibs.lcp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /** 
     * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss） 
     *  
     * @param createTime 消息创建时间 
     * @return 
     */  
    public static String formatTime(String createTime) {  
        // 将微信传入的CreateTime转换成long类型，再乘以1000  
        long msgCreateTime = Long.parseLong(createTime) * 1000L;  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return format.format(new Date(msgCreateTime));  
    }  
}
