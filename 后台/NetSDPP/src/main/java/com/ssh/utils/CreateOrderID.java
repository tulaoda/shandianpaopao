package com.ssh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateOrderID {
    public static String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Long getCurrentTimeWithoutSpace() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Long dateString = Long.parseLong(formatter.format(currentTime) + getRandom());
        return dateString;
    }

    public static String getOrderId() {
        String orderId = "";
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        orderId = date + getRandom();
        return orderId;
    }

    /**
     * 产生随机数
     *
     * @return
     */
    public static String getRandom() {
        Random rad = new Random();
        int temp = rad.nextInt(90) + 10;
        String result = String.valueOf(temp);
        return result;
    }
}
