package cn.BX.time;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import cn.BX.time.*;

/**
 * ����ʱ���
 * �ࣺ�õ�date����
 * �����뵱ǰʱ����Ƚ�֮��Ĳ�ֵ
 * �Լ���ǰ������Сʱǰ����ʽ����
 * @author Administrator
 *
 */
public class getTimeFormatText {
	private final static long minute = 60 * 1000;// 1����
	private final static long hour = 60 * minute;// 1Сʱ
	private final static long day = 24 * hour;// 1��
	private final static long month = 31 * day;// ��
	private final static long year = 12 * month;// ��

	/**
	* ������������������
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
//		System.out.println("��ǰʱ���ǣ�"+ currentTime);
		long r = 0;
		if(diff > year){
			r = (diff/year);
			return r+ "��ǰ";
		}
		if(diff > month){
			r = (diff / month);
			return r+"����ǰ";
		}
		if(diff > day){
			r = (diff / day);
			return r + "��ǰ";
		}
		if(diff > hour){
			r = (diff / hour);
			return r + "��Сʱǰ";
		}
		if(diff > minute){
			r = (diff / minute);
			return r + "����ǰ";
		}
		return "�ո�";
	}

	public static void main(String[] args) {
		String date = "2017-04-10 10:57:13";
//		Date currentTime = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateString = formatter.format(currentTime);//ʱ��תΪ�ַ���
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateString = formatter.format(currentTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(date, pos);
		
//		String back = getTimeFormatText(strtodate);
		System.out.println(strtodate);
	}
}
