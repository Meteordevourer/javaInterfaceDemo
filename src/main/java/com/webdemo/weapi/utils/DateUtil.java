package com.webdemo.weapi.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @Description: 日期工具类
 * @author: 许智皓
 * @date: 2017年6月3日 上午10:08:55
 * @version: V1.0
 * @updateDate:
 * @update:
 */
public class DateUtil {
	// 日期常量
	static enum DateConstants {
		TODAY(0), NEARLYWEEK(1), MONTH(2), NEARLYMONTH(3);
		public int value;

		DateConstants(int value) {
			this.value = value;
		}
	}

	/**
	 * 显示日期的格式,dd
	 */
	public static final String DATE_FORMAT_DAY = "dd";

	/**
	 * 显示日期的格式,yyyy-MM-dd
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 显示日期的格式,yyyy-MM-dd
	 */
	public static final String DATE_HOUR_FORMAT = "yyyy-MM-dd HH:mm";

	/**
	 * 显示日期的格式,yyyy-MM
	 */
	public static final String DATE_YEAE_MONTH = "yyyy-MM";

	/**
	 * 显示日期的格式,yyyy
	 */
	public static final String DATE_YEAR = "yyyy";

	/**
	 * 显示日期的格式,yyyy-MM-dd HH:mm:ss
	 */
	public static final String TIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 显示日期的格式, HH:mm:ss
	 */
	public static final String HMS_FORMAT = "HH:mm:ss";

	/**
	 * 显示日期的格式, HH:mm
	 */
	public static final String HM_FORMAT = "HH:mm";

	/**
	 * 显示日期的格式,yyyy年MM月dd日HH时mm分ss秒
	 */
	public static final String ZHCN_TIME_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
	/**
	 * 显示日期的格式,yyyy年MM月dd日HH时mm分
	 */
	public static final String ZHCN_TIME_FORMAT1 = "yyyy年MM月dd日HH时mm分";

	/**
	 * 显示日期的格式,yyyyMMddHHmmss
	 */
	public static final String TIME_STR_FORMAT = "yyyyMMddHHmmss";
	/**
	 * 显示日期的格式,yyyyMMddHHmmssSSS
	 */
	public static final String TIMESSS_STR_FORMAT = "yyyyMMddHHmmssSSS";
	/**
	 * 显示日期的格式,yyyyMMdd
	 */
	public static final String DATE_STR_FORMAT = "yyyyMMdd";

	/**
	 * DateFormat,格式:yyyy-MM-dd
	 */
	private static FastDateFormat dateFormat;

	/**
	 * DateFormat,格式:yyyy-MM-dd HH:mm:ss
	 */
	private static FastDateFormat dateTimeFormat;

	/**
	 * DateFormat,格式:yyyyMMddHHmmss
	 */
	private static FastDateFormat dateTimeStrFormat;

	/**
	 * DateFormat,格式:yyyy年MM月dd日HH时mm分ss秒
	 */
	private static FastDateFormat zhcnDateTimeStrFormat;

	static {
        dateFormat = FastDateFormat.getInstance(DATE_FORMAT);
        dateTimeFormat = FastDateFormat.getInstance(TIMEF_FORMAT);
        dateTimeStrFormat = FastDateFormat.getInstance(TIME_STR_FORMAT);
        zhcnDateTimeStrFormat = FastDateFormat.getInstance(ZHCN_TIME_FORMAT);
	}

	/**
	 * 获取当前时间在＋－n分钟后的字符串时间
	 * 
	 * 
	 * @param n
	 *            int
	 * @return String
	 */
	public static String getTimebyMinAfter(int n) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MINUTE, n);
		return (dateTimeFormat.format(now.getTime()));
	}

	/**
	 * 获取当前时间在＋－n分钟后的字符串时间
	 * 
	 * 
	 * @param n
	 *            int
	 * @return String
	 */
	public static Long getTimebyMinAfterLong(int n) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MINUTE, n);
		return now.getTimeInMillis();
	}

	/**
	 * 获取当前时间在＋－n秒后的字符串时间
	 * 
	 * @param n
	 *            int
	 * @return String
	 */
	public static String getTimebySecAfter(int n) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.SECOND, n);
		return (dateTimeFormat.format(now.getTime()));
	}

	/**
	 * 获取当前时间在＋－n分钟后的时间(java.util.Date)
	 * 
	 * 
	 * @param n
	 *            int
	 * @return String
	 */
	public static String getTimebyMinAfterDate(int n) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MINUTE, n);
		return dateToDateString(now.getTime());
	}

	/**
	 * 获取定义的DateFormat格式
	 * 
	 * @param formatStr
	 * @return
	 */
	private static FastDateFormat getDateFormat(String formatStr) {
		if (formatStr.equalsIgnoreCase(DATE_FORMAT)) {
			return dateFormat;
		} else if (formatStr.equalsIgnoreCase(TIMEF_FORMAT)) {
			return dateTimeFormat;
		} else {
			return FastDateFormat.getInstance(formatStr);
		}
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 * 
	 * 
	 * @param date
	 *            日期
	 * @return String 字符串
	 */
	public static String dateToDateString(Date date) {
		return dateToDateString(date, TIMEF_FORMAT);
	}
	
	public static String ds(Date date) {
		return dateToDateString(date, TIMEF_FORMAT);
	}

	/**
	 * 将Date转换成formatStr格式的字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToDateString(Date date, String formatStr) {
		if (null != date) {
            FastDateFormat df = getDateFormat(formatStr);
			return df.format(date);
		}

		return null;
	}

	/**
	 * 将Date转换成yyyy-MM-dd的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
        FastDateFormat df = getDateFormat(DATE_FORMAT);
		return df.format(date);
	}

	/**
	 * 将小时数换算成返回以毫秒为单位的时间
	 * 
	 * @param hours
	 * @return
	 */
	public static long getMicroSec(Double hours) {
		BigDecimal bd = new BigDecimal(hours).multiply(new BigDecimal(3600 * 1000));
		return bd.longValue();
	}

	/**
	 * 获取今天的日期，格式自定
	 * 
	 * @param pattern
	 *            - 设定显示格式
	 * @return String - 返回今天的日期
	 */
	public static String getToday(String pattern) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
        FastDateFormat sdf = getDateFormat(pattern);
		return (sdf.format(now.getTime()));
	}

	// 得到系统当前的分钟数,如当前时间是上午12:00,系统当前的分钟数就是12*60
	public static int getCurrentMinutes() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		int iMin = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);
		return iMin;
	}

	/**
	 * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
	 * 
	 * @return 当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
	 */
	public static String getCurZhCNDateTime() {
		return dateToDateString(new Date(), ZHCN_TIME_FORMAT);
	}

	/**
	 * @return 得到本月月份 如09
	 */
	public static String getMonth() {
		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH) + 1;
		String monStr = String.valueOf(month);

		// 对于10月份以下的月份,加"0"在前面

		if (month < 10) {
            monStr = "0" + monStr;
        }
		return monStr;
	}

	/**
	 * @return 得到本月第几天
	 */
	public static String getDay() {
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		String dayStr = String.valueOf(day);

		// 对于10月份以下的月份,加"0"在前面
		if (day < 10) {
            dayStr = "0" + dayStr;
        }
		return dayStr;
	}

	/**
	 * @return 获取指定日期月份 如09
	 */
	public static String getMonth(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int month = now.get(Calendar.MONTH) + 1;
		String monStr = String.valueOf(month);
		// 对于10月份以下的月份,加"0"在前面
		if (month < 10) {
            monStr = "0" + monStr;
        }
		return monStr;
	}

	/**
	 * @return 获取指定日期天数
	 */
	public static String getDay(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int day = now.get(Calendar.DAY_OF_MONTH);
		String dayStr = String.valueOf(day);
		// 对于10月份以下的月份,加"0"在前面
		if (day < 10) {
            dayStr = "0" + dayStr;
        }
		return dayStr;
	}

	/**
	 * 根据失效时间判断是否依然有效
	 * 
	 * @param expireTime
	 *            失效时间的字符串形式,格式要求:yyMMddHHmmss
	 * @return true:失效 false:没有失效
	 * @throws ParseException
	 */
	public static boolean isTimeExpired(String expireTime) throws ParseException {
		boolean ret = false;

		// SimpleDateFormat不是线程安全的,所以每次调用new一个新的对象

		Date date = new SimpleDateFormat(TIME_STR_FORMAT).parse(expireTime);
		Calendar expire = Calendar.getInstance();
		expire.setTime(date);
		Calendar now = Calendar.getInstance();
		// 将毫秒字段设为0,保持精度一致性

		now.set(Calendar.MILLISECOND, 0);
		if (now.after(expire)) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 根据开始时间和可用时间计算出失效时间
	 * 
	 * 
	 * @param startTime
	 *            开始时间
	 * 
	 * @param availableTime
	 *            有效时长（单位：秒）
	 * @return 失效时间
	 * @throws ParseException
	 */
	public static String getExpireTimeByCalculate(Date startTime, int availableTime) {

		Calendar expire = Calendar.getInstance();

		// 设置为开始时间

		expire.setTime(startTime);

		// 开始时间向后有效时长秒得到失效时间
		expire.add(Calendar.SECOND, availableTime);

		// 返回失效时间字符串

		// SimpleDateFormat不是线程安全的,所以每次调用new一个新的对象

		return new SimpleDateFormat(TIME_STR_FORMAT).format(expire.getTime());

	}

	/**
	 * 将Date转换为yyyyMMddHHmmss的形式
	 * 
	 * 
	 * @param date
	 * @return 日期的字符串形式,格式：yyyyMMddHHmmss
	 */
	public static String convertDateToString(Date date) {
		return new SimpleDateFormat(TIME_STR_FORMAT).format(date);
	}

	/**
	 * 将Date转换为yyMMddHHmmss的形式
	 * 
	 * 
	 * @param date
	 * @return 日期的字符串形式,格式：yyMMddHHmmss
	 */
	public static String convertDateToString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 将yyMMddHHmmss形式的字符串转换成Date的形式
	 * 
	 * 
	 * @param date
	 *            yyMMddHHmmss形式的日期字符串
	 * @return Date对象
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String date) throws ParseException {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return new SimpleDateFormat(TIME_STR_FORMAT).parse(date);
	}

	/**
	 * 字符串转化为日期
	 * 
	 * @param date
	 *            日期字符串
	 * @param formatString
	 *            格式化字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String date, String formatString) throws Exception {
		if (StringUtils.isBlank(date) || StringUtils.isBlank(formatString)) {
			return null;
		}
		return new SimpleDateFormat(formatString).parse(date);
	}

	/**
	 * 日期转化为格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param formatString
	 *            格式化字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date convertDateToDate(Date date, String formatString) {
		if (null == date || StringUtils.isBlank(formatString)){
			return null;
		}
		try {
			return new SimpleDateFormat(formatString).parse(convertDateToString(date, formatString));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转化为格式化字符串格式
	 * 
	 * @param date
	 *            日期
	 * @param formatString
	 *            格式化字符串
	 * @return
	 * @throws ParseException
	 */
	public static String convertStringToString(String date, String formatString) throws ParseException {
		if (StringUtils.isBlank(date) || StringUtils.isBlank(formatString)) {
			return null;
		}
		return new SimpleDateFormat(formatString).format(convertSimpleStringToDateTime(date));
	}

	/**
	 * 将yy-MM-dd形式的字符串转换成Date的形式
	 * 
	 * 
	 * 
	 * @param date
	 *            yy-MM-dd形式的日期字符串
	 * @return Date对象
	 * @throws ParseException
	 */
	public static Date convertSimpleStringToDate(String date) throws ParseException {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return new SimpleDateFormat(DATE_FORMAT).parse(date);
	}

	/**
	 * @param date
	 *            yyyyMMddHHmmss格式的日期字符转换为yyyy年MM月dd日HH时mm分ss秒格式的字符串
	 * 
	 * @return yyyy年MM月dd日HH时mm分ss秒格式的日期字符串
	 * 
	 * @throws ParseException
	 */
	public static String convertStringToZhCN(String date) throws ParseException {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return zhcnDateTimeStrFormat.format(dateTimeStrFormat.parse(date));
	}

	/**
	 * 时间字符串转换成日期时间的形式
	 * 
	 * @param date
	 *            yy-MM-dd HH:mm:ss形式的日期字符串
	 * @return Date对象
	 * @throws ParseException
	 */
	public static Date convertSimpleStringToDateTime(String date) throws ParseException {
		if (StringUtils.isBlank(date)){
			return null;
		}
		return new SimpleDateFormat(TIMEF_FORMAT).parse(date);
	}

	/**
	 * 时间字符串转换成日期时间的形式
	 * 
	 * @param date
	 *            yy-MM-dd HH:mm:ss形式的日期字符串
	 * @return Date对象
	 * @throws ParseException
	 */
	public static Long convertSimpleStringToLong(String date) {
        Long time = null;
		if (StringUtils.isBlank(date)) {
			return time;
		}

        try {
            time = new SimpleDateFormat(TIMEF_FORMAT).parse(date).getTime();
        } catch (Exception e) {

        }
		return time;
	}
	
	

	/**
	 * 获取当天日期 返回格式：yyyy-MM-dd
	 */
	public static Date getTodayDate() {
		// 获取昨日的日期
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		return today;
	}

	/**
	 * 获取昨日日期 返回格式：yyyy-MM-dd
	 */
	public static String getYesterdayDateStr() {
		// 获取昨日的日期
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = cal.getTime();
		return new SimpleDateFormat(DATE_FORMAT).format(yesterday);
	}

	/**
	 * 获取昨日日期 返回格式：yyyy-MM-dd
	 */
	public static Date getYesterdayDate() {
		// 获取昨日的日期
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = cal.getTime();
		return yesterday;
	}

	/**
	 * 获取明天日期 返回格式：yyyy-MM-dd
	 */
	public static String getTomorrowDateStr() {
		// 获取昨日的日期
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = cal.getTime();
		return new SimpleDateFormat(DATE_FORMAT).format(tomorrow);
	}

	/**
	 * 获取明天日期 返回格式：yyyy-MM-dd
	 */
	public static Date getTomorrowDate() {
		// 获取昨日的日期

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = cal.getTime();
		return tomorrow;
	}

	/**
	 * 根据Calendar对象、字符串日期类型获得字符串日期
	 * 
	 * @param calendar
	 *            Calendar对象
	 * @param strDateType
	 *            字符串日期类型（1：XXXX年XX月XX日，2：XX月XX日，3，XXXX年，4：XXXX-XX-XX，5：XX-XX，6：
	 *            XXXX）
	 * 
	 * @return
	 */
	public static String getStrDate(Calendar calendar, int strDateType) {
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = (calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1)
				: String.valueOf

				((calendar.get(Calendar.MONTH) + 1));
		String day = calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + calendar.get(Calendar.DAY_OF_MONTH)
				: String.valueOf

				(calendar.get(Calendar.DAY_OF_MONTH));
		String strDate = "";

		switch (strDateType) {
		case 1:
			strDate = year + "年" + month + "月" + day + "日";
			break;
		case 2:
			strDate = month + "月" + day + "日";
			break;
		case 3:
			strDate = year + "年";
			break;
		case 4:
			strDate = year + "-" + month + "-" + day;
			break;
		case 5:
			strDate = month + "-" + day;
			break;
		case 6:
			strDate = year;
			break;
		default:
			strDate = year + "-" + month + "-" + day;
			break;
		}

		return strDate;
	}

	/**
	 * 根据原来的时间（Date）获得相对偏移 N 月的时间（Date）
	 * 
	 * @param protoDate
	 *            原来的时间（java.util.Date）
	 * 
	 * @param monthOffset
	 *            （向前移正数，向后移负数）
	 * 
	 * @return 时间（java.util.Date）
	 */
	public static Date getOffsetMonthDate(Date protoDate, int monthOffset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		// cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - monthOffset);错误写法
		cal.add(Calendar.MONTH, -monthOffset);
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(getNowDateToString());
	}

	/**
	 * 根据原来的时间（Date）获得相对偏移 N 天的时间（Date）
	 * 
	 * @param protoDate
	 *            原来的时间（java.util.Date）
	 * 
	 * @param dateOffset
	 *            （向前移正数，向后移负数）
	 * 
	 */
	public static Date getOffsetDayDate(Date protoDate, int dateOffset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - dateOffset);
		return cal.getTime();
	}

	/**
	 * 根据原来的时间（Date）获得相对偏移 N 小时的时间（Date）
	 * 
	 * @param protoDate
	 *            原来的时间（java.util.Date）
	 * 
	 * @param offsetHour
	 *            （向前移正数，向后移负数）
	 * 
	 * @return 时间（java.util.Date）
	 */
	public static Date getOffsetHourDate(Date protoDate, int offsetHour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - offsetHour);
		return cal.getTime();
	}

	/**
	 * 获取指定月份和日子的日期(未做日期效验)
	 * 
	 * @param currentDate
	 *            当前日期
	 * @param assignYear
	 *            指定年份,-1代表年不做修改
	 * @param assignMonth
	 *            指定月份,从0开始,超过月最大值自动往后加一个月年,-1代表月不做修改
	 * @param assignDay
	 *            指定日,从1开始,超过日最大值往后加一个月,-1代表日不做修改
	 * @return 修改后的日期
	 */
	public static Date getAssignDate(Date currentDate, int assignYear, int assignMonth, int assignDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		if (assignYear > -1) {
			cal.set(Calendar.YEAR, assignYear);
		}
		if (assignMonth > -1) {
			cal.set(Calendar.MONTH, assignMonth);
		}
		if (assignDay > -1) {
			cal.set(Calendar.DAY_OF_MONTH, assignDay);
		}
		return cal.getTime();
	}

	/**
	 * 获得两个日前之间相差的天数,有时分秒的影响
	 * 
	 * @param startDate
	 *            开始日期
	 * 
	 * @param endDate
	 *            结束日期
	 * @return 天数
	 */
	public static int getDayCountBetweenDate(Date startDate, Date endDate) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		int i = 0;
		while (endCalendar.compareTo(startCalendar) >= 0) {
			startCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.get(Calendar.DAY_OF_MONTH) + 1);
			i++;
		}
		return i;
	}

    /**
     * 两个日期相减，返回秒数
     * @param startDate
     * @param endDate
     * @return
     */
	public static Long getDayBetween(String startDate, String endDate) {
		if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
			long rs = convertSimpleStringToLong(startDate) - convertSimpleStringToLong(endDate);
			if (0 != rs) {
				return rs / 1000;
			} else {
				return (long) 0;
			}
		}
		return (long) 0;
	}
	
	/**
     * 两个日期相减，返回秒数
     * @param startDate
     * @param endDate
     * @return
     */
	public static Long getDayBetween(Date startDate, Date endDate) {
		if (null != startDate && null != endDate) {
			long rs = startDate.getTime() - endDate.getTime();
			if (0 != rs) {
				return rs / 1000;
			} else {
				return (long) 0;
			}
		}
		return (long) 0;
	}

	/**
	 * 获得两个日前之间相差的月份
	 * 
	 * @param startDate
	 *            开始日期
	 * 
	 * @param endDate
	 *            结束日期
	 * @return 月数
	 */
	public static int getMonthCountBetweenDate(Date startDate, Date endDate) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		int i = 0;
		while (endCalendar.compareTo(startCalendar) >= 0) {
			startCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH) + 1);
			i++;
		}
		return i;
	}

	/**
	 * 根据原来的时间（Date）获得相对偏移 N 天的时间（Date）
	 * 
	 * 
	 * @param protoDate
	 *            原来的时间（java.util.Date）
	 * 
	 * 
	 * @param dateOffset
	 *            （向前移正数，向后移负数）
	 * 
	 * @param type
	 *            指定不同的格式（1：年月日，2：年月日时，3：年月日时分）
	 * 
	 * @return 时间（java.util.Date），没有时分秒
	 */
	public static Date getOffsetSimpleDate(Date protoDate, int dateOffset, int type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - dateOffset);
		if (type == 1) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
		}
		if (type == 2) {
			cal.set(Calendar.MINUTE, 0);
		}
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 时间转为化为字符串
	 * 
	 * 格式为：yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public static String getDateToString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESSS_STR_FORMAT);
		Date date = new Date();
		String str = dateFormat.format(date);
		return str;
	}

	/**
	 * 时间转为化为字符串
	 * 
	 * 格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTodayTimeString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIMEF_FORMAT);
		Date date = new Date();
		String str = dateFormat.format(date);
		return str;
	}

	/**
	 * 增加N天
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static String addDay(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 减N天
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static String reductionDay(String s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.set(Calendar.DATE, cd.get(Calendar.DATE) - n);// 减N天
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 减N天
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static String reductionDay(Date s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(s);
			cd.set(Calendar.DATE, cd.get(Calendar.DATE) - n);// 减N天
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 增加一天
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static String addDay(Date s, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(s);
			cd.add(Calendar.DATE, n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 增加一个月
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static String addMonth(Date m, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(m);
			cd.add(Calendar.MONTH, n);// 增加一个月
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 增加一个月
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static String addMonth(Date m, int n, String formatstring) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatstring);
			Calendar cd = Calendar.getInstance();
			cd.setTime(m);
			cd.add(Calendar.MONTH, n);// 增加一个月
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 减个月
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static String reductionMonth(Date m, int n, String formatstring) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatstring);
			Calendar cd = Calendar.getInstance();
			cd.setTime(m);
			cd.set(Calendar.MONTH, cd.get(Calendar.MONTH) - n);// 减N月
			return sdf.format(cd.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 减N秒
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static Date reductionS(Date s, int n) {
		try {

			Calendar cd = Calendar.getInstance();
			cd.setTime(s);
			cd.set(Calendar.SECOND, cd.get(Calendar.SECOND) - n);

			return cd.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 增N秒
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static Date addS(Date s, int n) {
		try {

			Calendar cd = Calendar.getInstance();
			cd.setTime(s);
			cd.add(Calendar.SECOND, n);

			return cd.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取需要执行的统计的日期数组
	 * 
	 * @return 格式['2011-01-01',2011-01-02']
	 */
	public static String[] getExecDay(Date lastDate) {
		String[] day = null;
		// 获取昨天日期
		Date yesdate = null;
		yesdate = DateUtil.convertDateToDate(DateUtil.getYesterdayDate(), DateUtil.DATE_FORMAT);

		// 获取上次最后执行日期与昨天相隔天数
		int dayCount = DateUtil.getDayCountBetweenDate(lastDate, yesdate);
		if (dayCount <= 0) {
			return day;
		}
		if (dayCount == 1) {
			return new String[] { DateUtil.getYesterdayDateStr() };
		} else {
			day = new String[dayCount];
			for (int i = 0; i < dayCount; i++) {
				String date = DateUtil.addDay(lastDate, i);
				day[i] = date;
			}
		}
		return day;
	}

	/**
	 * 获取这个月第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		return firstDate;
	}

	/**
	 * 获取指定月份第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		return ca.getTime();
	}

	/**
	 * 获这个月的最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		return lastDate;
	}

	/**
	 * 获指定月份的最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		return lastDate;
	}

	/**
	 * 获查询日期区间 今天(0), 近一周(1), 本月(2),近一月(3) ;
	 * 
	 * @return Date[0] 开始时间 Date[1] 结束时间
	 */
	public static Date[] getDateSection(int dateType) {
		Date[] dateSection = new Date[2];
		if (DateConstants.TODAY.value == dateType) {
			dateSection[0] = getTodayDate();
			dateSection[1] = dateSection[0];
		} else if (DateConstants.NEARLYWEEK.value == dateType) {
			dateSection[0] = getOffsetDayDate(getYesterdayDate(), 6);
			dateSection[1] = getYesterdayDate();
		} else if (DateConstants.NEARLYMONTH.value == dateType) {
			dateSection[0] = getOffsetMonthDate(getYesterdayDate(), 1);
			dateSection[1] = getYesterdayDate();
		} else if (DateConstants.MONTH.value == dateType) {
			dateSection[0] = getFirstDayOfMonth();
			dateSection[1] = getLastDayOfMonth();
		} else {
			dateSection = null;
		}
		return dateSection;
	};

	/**
	 * 将date转为GMT格式 Date date, 时间 String format,格式化,如 yyyy-MM-dd String to
	 * 日期格式,如 GMT
	 */
	public static String dateToFormat(Date date, String format, String to) {

		try {
			if (null == date) {
				date = new Date();
			}

			DateFormat gmtFormat = new SimpleDateFormat(format);
			TimeZone gmtTime = TimeZone.getTimeZone(to);
			gmtFormat.setTimeZone(gmtTime);
			return gmtFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据日期获得星期几
	 *
	 * @return
	 */
	public static int getWeekOfNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 根据日期获得星期几
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getWeekOfDate(String strDate) {

		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		Date date = new Date();

		try {
			date = sdfInput.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0) {
            dayOfWeek = 0;
        }
		return dayNames[dayOfWeek];
	}

	/**
	 * 根据中文星期几转化为对应的数字
	 * 
	 * @param strDate
	 */
	public static int getWeekZnToNum(String strDate) {
		int i = 0;
		switch (strDate) {
		case "星期一":
			i = 1;
			break;
		case "星期二":
			i = 2;
			break;
		case "星期三":
			i = 3;
			break;
		case "星期四":
			i = 4;
			break;
		case "星期五":
			i = 5;
			break;
		case "星期六":
			i = 6;
			break;
		case "星期日":
			i = 7;
			break;
		default:
			break;
		}
		return i;
	}

	/**
	 * 根据日期获得一周的日期，周日至周6
	 * 
	 * @param strDate
	 * @return
	 */
	public static String[] getDateForWeekList(String strDate) {

		String week = getWeekOfDate(strDate);
		int weekNum = getWeekZnToNum(week);

		String show = "";
		String[] weekDate = new String[7];

		int n = 0;
		if (7 != weekNum) {
			n = weekNum;
		}

		String nowDate = reductionDay(strDate, n);

		for (int i = 1; i <= 7; i++) {

			if (i != 1) {
				show = addDay(show, 1);
			} else {
				show = nowDate;
			}
			weekDate[i - 1] = show;
		}

		return weekDate;
	}

	/**
	 * 获查询日期区间 输入一个时间间段
	 * 
	 * @return Date[0] 开始时间 Date[1] 结束时间
	 */
	@SuppressWarnings("deprecation")
	public static Date[] getDateSection2(int dateType) {
		Date[] dateSection = new Date[2];

		dateSection[0] = getOffsetDayDate(getYesterdayDate(), dateType - 1);
		dateSection[1] = getYesterdayDate();

		dateSection[0].setHours(0);
		dateSection[0].setMinutes(0);
		dateSection[0].setSeconds(0);

		dateSection[1].setHours(23);
		dateSection[1].setMinutes(59);
		dateSection[1].setSeconds(59);

		return dateSection;
	}

	/**
	 * 获查询日期区间 输入一个时间间段
	 * 
	 * @return Date[0] 开始时间 Date[1] 结束时间
	 */
	public static List<Date> getDateSection3(int dateType) {

		List<Date> dateList = new ArrayList<Date>();

		for (int i = 1; i <= dateType; i++) {

			dateList.add(getOffsetDayDate(getYesterdayDate(), dateType - i));
		}

		return dateList;
	}

	/**
	 * 
	 * @Title: getAllTheDateOftheMonth
	 * @Description: 根据当前日期获得本月的所有日期
	 * @author: 许智皓
	 * @date 2015-11-27 上午9:31:48
	 * @param date
	 * @return List<Date>
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 * 
	 * @Title: getAllTheDateOftheMonth2
	 * @Description: 根据当前日期获得本月的从当日至月底日期
	 * @author: 许智皓
	 * @date 2015-11-27 上午9:33:10
	 * @param date
	 * @return List<Date>
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static List<Date> getAllTheDateOftheMonth2(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 * 
	 * @Title: getAllTheDateOftheMonth3
	 * @Description: 根据当前日期获得本月的从1号到当日
	 * @author: 许智皓
	 * @date 2015-11-27 上午9:46:56
	 * @param date
	 * @return List<Date>
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static List<Date> getAllTheDateOftheMonth3(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		while (cal.get(Calendar.DAY_OF_MONTH) != nowDay) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 * 
	 * @Title: cal
	 * @Description: 一个以秒为单位的数转换成时分秒
	 * @author: 许智皓
	 * @date 2015-11-30 下午3:56:00
	 * @param second
	 * @return String
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static String cal(int second) {
		int h = 0;
		int d = 0;
		int s = 0;
		int temp = second % 3600;
		if (second > 3600) {
			h = second / 3600;
			if (temp != 0) {
				if (temp > 60) {
					d = temp / 60;
					if (temp % 60 != 0) {
						s = temp % 60;
					}
				} else {
					s = temp;
				}
			}
		} else {
			d = second / 60;
			if (second % 60 != 0) {
				s = second % 60;
			}
		}

		return h + "时" + d + "分" + s + "秒";
	}

	/**
	 * 
	 * @Title: getWeeksName
	 * @Description: 查询当前日期属于一年中的第几个星期
	 * @author: 许智皓
	 * @date 2015-12-1 下午5:34:49
	 * @param date
	 * @return int
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static int getWeeksName(String date) {

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(DateUtil.convertSimpleStringToDate(date));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		// 取当前日期的年份里面的周数
		return cal.get(Calendar.WEEK_OF_YEAR);

	}

	/**
	 * 时间戳转换成日期格式字符串 yyyy-MM-dd HH:mm:ss
	 * @param timeStamp
	 * @return
	 */
	public static String timeStampToDateStr(long timeStamp) {

		SimpleDateFormat sdf = new SimpleDateFormat(TIMEF_FORMAT);
		return sdf.format(new Date(timeStamp));
	}

	/**
	 * 
	 * @Title: timeStampToDate
	 * @Description: TODO
	 * @author: 许智皓
	 * @date 2016-1-12 上午11:40:51
	 * @param timeStamp
	 * @return Date
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static Date timeStampToDate(long timeStamp) {
		return convertDateToDate(new Date(timeStamp), TIMEF_FORMAT);
	}

	/**
	 * 
	 * @Title: isEqual
	 * @Description: 两个日期是否相等
	 * @author: 许智皓
	 * @date 2016年1月6日 下午6:32:10
	 * @param date1
	 * @param date2
	 * @return Boolean
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static Boolean isEqual(Date date1, Date date2) {

		String dateStr1 = DateUtil.convertDateToString(date1, DateUtil.DATE_FORMAT);
		String dateStr2 = DateUtil.convertDateToString(date2, DateUtil.DATE_FORMAT);

		if (dateStr1.equals(dateStr2)) {

			return true;
		}

		return false;
	}

	/**
	 * 
	 * @Title: getTwoDay
	 * @Description: 两日期相减，返回天数
	 * @author: 许智皓
	 * @date 2016年1月7日 上午9:34:58
	 * @param begin_date
	 * @param end_date
	 * @return long
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static long getTwoDay(Date begin_date, Date end_date) {
		long day = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String sdate = format.format(Calendar.getInstance().getTime());

			if (begin_date == null) {
				begin_date = format.parse(sdate);
			}
			if (end_date == null) {
				end_date = format.parse(sdate);
			}
			day = (end_date.getTime() - begin_date.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return -1;
		}
		return day;
	}

	// 获取时间截
	public static long getTimeMillis() {

		return System.currentTimeMillis();
	}

	/**
	 * 
	 * @Description: 获取现在时间 返回短时间格式 yyyy-MM-dd
	 * @author: 李山秋
	 * @date 2017年8月16日 下午6:09:11
	 * @version: V1.0
	 * 
	 * @updateDate:
	 * @update:
	 */
	public static String getNowDateShort() {
		Date date = new Date();
		return convertDateToString(date, DATE_FORMAT);
	}

	public static String getNowDateToString() {
		Date date = new Date();
		return convertDateToString(date, TIMEF_FORMAT);
	}

	public static boolean isInTime(String sourceTime, String curTime) {
		if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
		if (curTime == null || !curTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
		}
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();
			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00")) {
				args[1] = "24:00";
			}
			if (end < start) {
				if (now >= end && now < start) {
					return false;
				} else {
					return true;
				}
			}
			else {
				if (now >= start && now < end) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
	}
	
	/**
	 * @param n 第几天的日期，可以为负，即为前n天
	 * 获取昨日日期 返回格式：yyyy-MM-dd
	 */
	public static String getNdayDateStr(int n) {
		// 获取昨日的日期
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, n);
		Date yesterday = cal.getTime();
		return new SimpleDateFormat(DATE_FORMAT).format(yesterday);
	}

	/**
	 * 
	 * @Description: 判断日期格式是否正确
	 *
	 */
	public static boolean isValidDate(String str) {
	      boolean convertSuccess=true;
	       SimpleDateFormat format = new SimpleDateFormat(TIMEF_FORMAT);
	       try {
	          format.setLenient(false);
	          format.parse(str);
	       } catch (ParseException e) {
	           convertSuccess=false;
	       } 
	       return convertSuccess;
	}
	
	
	 public static Long localToUTC_Time(String localTime) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date localDate= null;
	        try {
	            localDate = sdf.parse(localTime);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	   
	        return localDate.getTime();
	    }
	 
	 
	 public static Long localToUTC_Time(Long localTime) {
	      
	        return localTime;
	    }
	
	/**
     * 
     * <p>Description: 本地时间转化为UTC时间</p>
     * @param localTime
     * @return
     * @author wgs 
     * @date  2018年10月19日 下午2:23:43
     *
     */
    public static Date localToUTC_YES(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate= null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());
        return utcDate;
    }

    /**
     *
     * <p>Description: 本地时间转化为UTC时间</p>
     * @param localTime
     * @return
     * @author wgs
     * @date  2018年10月19日 下午2:23:43
     *
     */
    public static String localToUTC_Str_YES(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate= null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());

        return dateToDateString(utcDate);
    }

    /**
     *
     * <p>Description: 本地时间转化为UTC时间</p>
     * @param localTime
     * @return
     * @author wgs
     * @date  2018年10月19日 下午2:23:43
     *
     */
    public static String localToUTC_Time_Str_YES(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate= null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());

        return String.valueOf(utcDate.getTime());
    }



    public static Long localToUTC_Time_YES(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate= null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());

        return utcDate.getTime();
    }

    public static Long localToUTC_Time_YES(Long localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate= null;
        try {

            localDate = sdf.parse(ds(new Date(localTime)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());
        
        return utcDate.getTime();
    }
    
    /**
     * 
     * <p>Description:UTC时间转化为本地时间 </p>
     * @param utcTime
     * @return
     * @author wgs 
     * @date  2018年10月19日 下午2:23:24
     *
     */
    public static Date utcToLocal(String utcTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        Date locatlDate = null;
        String localTime = sdf.format(utcDate.getTime());
        try {
            locatlDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locatlDate;
    }
}
