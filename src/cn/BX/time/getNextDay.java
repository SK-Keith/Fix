package cn.BX.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获得第二天的时间
 * @author Administrator
 *
 */
public class getNextDay {
	
	
	public static String getNextDay(Date date){
		/*
		 * 自动增加一天的，然后把时间放到数据库中
		 */
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date dd = df.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//添加一天
//		System.out.println("增加一天后"+df.format(calendar.getTime()));
//		record.setDate2(date2);
		
		return df.format(calendar.getTime());
	}
	
	
//	public static void main(String[] args) {
//		Date date = new Date();
//		String date2 = getNextDay(date);
//		System.out.println(date);
//		System.out.println(date2);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		ParsePosition pos = new ParsePosition(0);
//		Date date3 = formatter.parse(date2, pos);
//		int a = compareDate(date,date3);
//		System.out.println(a);
//	}
	

	public static int compareDate(Date d1,Date d2){
	    if (d1.getTime() > d2.getTime()) {
	        System.out.println("现在 在dt2前");
	        return 1;
	    } else if (d1.getTime() < d2.getTime()) {
	        System.out.println("现在在dt2后");
	        return -1;
	    } else {//相等
	        return 0;
	    }
	}
}
