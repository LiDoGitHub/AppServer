package com.gjyl.appserver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	private static final SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String getNowDateStr() {
		String nowDate = format.format(new Date());
		return nowDate;
	}

	/**
	 * 获取时间差
	 * 
	 * @param reminddate
	 *            :开始时间
	 * @param enddate
	 *            :截止时间
	 * @return
	 * @throws ParseException
	 */
	public static int getMinusDate(Date reminddate, Date enddate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		reminddate = sdf.parse(sdf.format(reminddate));
		enddate = sdf.parse(sdf.format(enddate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(reminddate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(enddate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static long dateDiff(Date startTime, Date endTime) {
		//生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
		long nh = 1000 * 60 * 60;//一小时的毫秒数
		long nm = 1000 * 60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数long diff;try {
		//获得两个时间的毫秒时间差异
		long diff = startTime.getTime()-endTime.getTime();
		long day = diff / nd;//计算差多少天
		long hour = diff % nd / nh;//计算差多少小时
		long min = diff % nd % nh / nm;//计算差多少分钟
		long sec = diff % nd % nh % nm / ns;//计算差多少秒//输出结果
//		System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
		//总共多少分钟
		long totalMin=day*24*60+hour*60+min;
		return totalMin;
	}

	/**
	 * 获取后一天
	 * 
	 * @param remindDate
	 * @return
	 */
	public static Date getNextDate(Date remindDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(remindDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date next = new Date(calendar.getTimeInMillis());
		return next;
	}

	/**
	 * 获取当前星期几
	 */
    public static String getWeekDay() {
		String weekday;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week_index=calendar.get(Calendar.DAY_OF_WEEK)-1;
		switch (week_index){
			case 0:{
				weekday="sunday";
				break;
			}
			case 1:{
				weekday="monday";
				break;
			}
			case 2:{
				weekday="tuesday";
				break;
			}
			case 3:{
				weekday="wensday";
				break;
			}
			case 4:{
				weekday="thursday";
				break;
			}
			case 5:{
				weekday="friday";
				break;
			}
			case 6:{
				weekday="saturday";
				break;
			}
			default:{
				weekday="monday";
				break;
			}
		}
		return weekday;
	}

	/**
	 * 获取指定的的日期格式
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getDateStr(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
		String time = sdf.format(date);
		return time;
	}
}
