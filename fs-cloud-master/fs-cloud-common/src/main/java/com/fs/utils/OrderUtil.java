package com.fs.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***
 * 订单处理类
 * @ClassName OrderUtil
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月17日
 */
public class OrderUtil {

	private static Calendar today = Calendar.getInstance();
	private static int orderIndex = 0;
	
	private static String getIndex() {
		Calendar nowday = Calendar.getInstance();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currTime = outFormat.format(new Date());
		if (orderIndex > 0) {
			if (nowday.get(Calendar.YEAR) == today.get(Calendar.YEAR) && nowday.get(Calendar.MONTH) == nowday.get(Calendar.MONTH) && nowday.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {
				orderIndex += 1;
			} else {
				today = nowday;
				orderIndex = 1;
			}
		} else {
			today = nowday;
			orderIndex = 1;
		}
		if (orderIndex > 999999) {
			orderIndex = 1;
		}
		String indexString = String.format("%s%06d", currTime, orderIndex);
		return indexString;
	}

	/**
	 * 生成订单号
	 * 
	 * @param preFixString
	 * @return
	 */
	public static String GetOrderNumber(String preFixString) {
		String orderNumberString = preFixString + getIndex();
		return orderNumberString;
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String GetTimestamp() {
		return Long.toString(new Date().getTime() / 1000);
	}

}
