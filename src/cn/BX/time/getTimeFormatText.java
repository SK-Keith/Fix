package cn.BX.time;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import cn.BX.time.*;

/**
 * 计算时间差
 * 类：拿到date数据
 * 返回与当前时间相比较之后的差值
 * 以几天前，几个小时前等形式返回
 * @author Administrator
 *
 */
public class getTimeFormatText {
	private final static long minute = 60 * 1000;// 1分钟
	private final static long hour = 60 * minute;// 1小时
	private final static long day = 24 * hour;// 1天
	private final static long month = 31 * day;// 月
	private final static long year = 12 * month;// 年

	/**
	* 返回文字描述的日期
	*
	* @param date
	* @return
	*/
	public static String getTimeFormatText(Date date) {
		if(date == null){
			return null;
		}
		long diff = new Date().getTime() - date.getTime();
//		Date currentTime = new Date();
//		System.out.println("当前时间是："+ currentTime);
		long r = 0;
		if(diff > year){
			r = (diff/year);
			return r+ "年前";
		}
		if(diff > month){
			r = (diff / month);
			return r+"个月前";
		}
		if(diff > day){
			r = (diff / day);
			return r + "天前";
		}
		if(diff > hour){
			r = (diff / hour);
			return r + "个小时前";
		}
		if(diff > minute){
			r = (diff / minute);
			return r + "分钟前";
		}
		return "刚刚";
	}

	public static void main(String[] args) {
		String date = "2017-04-10 10:57:13";
//		Date currentTime = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateString = formatter.format(currentTime);//时间转为字符串
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateString = formatter.format(currentTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(date, pos);
		
//		String back = getTimeFormatText(strtodate);
		System.out.println(strtodate);
	}
}
