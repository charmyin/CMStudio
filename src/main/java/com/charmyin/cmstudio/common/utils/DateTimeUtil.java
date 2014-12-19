package com.charmyin.cmstudio.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateTimeUtil {

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param year
	 *            yyyy
	 * @param month
	 *            MM
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}

	/**
	 * 获取一个月的第一天
	 * 
	 * @param year
	 *            yyyy
	 * @param month
	 *            MM
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}

	/**
	 * 获取某年中某一个月的天数
	 * 
	 * @param dyear
	 *            yyyy
	 * @param dmouth
	 *            MM
	 * @return
	 */
	public static int calDayByYearAndMonth(String dyear, String dmouth) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(simpleDate.parse(dyear + "/" + dmouth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);// 根据年月 获取月份天数
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));*/
		//System.out.println("smdate:"+sdf.format(smdate)+"-----bdate:"+sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 * 
	 * @param smdate
	 *            yyyy-MM-dd
	 * @param bdate
	 *            yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取当前日期的季度 1. 
	 * 第一季度：1月1号，3月31号 2. 
	 * 第二季度：4月1号，6月30号 3. 
	 * 第三季度：7月1号，9月30号 4.
	 * 第四季度：10月1号，12月31号
	 */
	public static int getCurrentQuarter() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		if (daysBetween(currentYear + "-4-1", nowDate) < 0) {
			return 1;
		} else if (daysBetween(currentYear + "-7-1", nowDate) < 0) {
			return 2;
		} else if (daysBetween(currentYear + "-10-1", nowDate) < 0) {
			return 3;
		} else {
			return 4;
		}
	}

	/**
	 * 获取当前时间在当前季度中所属天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static int getCurrentDaysCountInCurrentQuarter() throws ParseException{
    	int i = DateTimeUtil.getCurrentQuarter();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
   	 	String nowDate = sdf.format(new Date());
	   	Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
    	switch(i){
    		case 1:
    			return daysBetween(currentYear + "-1-1", nowDate)+1;
    		case 2:
    			return daysBetween(currentYear + "-4-1", nowDate)+1;
    		case 3:
    			return daysBetween(currentYear + "-7-1", nowDate);
    		case 4:
    			return daysBetween(currentYear + "-10-1", nowDate);
    		default:
    			return 0;
    	}
    }
	
	/**
	 * 获取日期的季度
	 */
	public static int getQuarterByDateString(String dateStr) {
		return 1;
	}
	
	
	public static String dateToString(Date date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
	}

}
