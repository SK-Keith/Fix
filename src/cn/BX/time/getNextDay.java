package cn.BX.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��õڶ����ʱ��
 * @author Administrator
 *
 */
public class getNextDay {
	
	
	public static String getNextDay(Date date){
		/*
		 * �Զ�����һ��ģ�Ȼ���ʱ��ŵ����ݿ���
		 */
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date dd = df.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//���һ��
//		System.out.println("����һ���"+df.format(calendar.getTime()));
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
	        System.out.println("���� ��dt2ǰ");
	        return 1;
	    } else if (d1.getTime() < d2.getTime()) {
	        System.out.println("������dt2��");
	        return -1;
	    } else {//���
	        return 0;
	    }
	}
}
