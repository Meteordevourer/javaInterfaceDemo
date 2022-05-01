package com.webdemo.weapi.utils.lang;



import com.webdemo.weapi.action.domain.entity.WlVehicle;
import com.webdemo.weapi.utils.DateUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @Description: 字符串共用方法类
 * @author: 许智皓
 * @date: 2017年6月3日 上午10:12:06
 * 
 * @version: V1.0
 *
 * @updateDate:
 * @update:
 */
public class StringBase {

	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	
	
	public static String StrCheckToStr(String str) {
		
		if(null != str) {
			return str;
		}
		return "";
	}
	
	//L转Us gul  fuelType油耗单位类型：（默认2）1.升 2.加仑
	public static Double ltoUsGul(Double l, String fuelType) {
		
		if("2".equals(fuelType)) {
			BigDecimal bg = new BigDecimal(l * 0.2641721);
			return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}else {
			return l;
		}
	}
	
	
	
	
	//公里转米 小时 	 mileageType里程单位类型：（默认2）1.公里/小时 2.米/小时
	public static Double kmToM(Double l, String mileageType) {
		
		if("2".equals(mileageType)) {
			
			BigDecimal bg = new BigDecimal(l * 1000);
			return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
		}else {
			return l;
		}
	}
	
	//公里转米 小时 	speedType速度单位类型：（默认2）1.公里/小时  2.米/小时
	public static Double kmToM_Speed(Double l, String speedType) {

		if ("2".equals(speedType)) {

			BigDecimal bg = new BigDecimal(l * 1000);
			return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		} else {
			return l;
		}
	}
	
	public static Float kmToM_Speed(Float l, String speedType) {

		if ("2".equals(speedType)) {

			BigDecimal bg = new BigDecimal(l * 1000);
			return bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

		} else {
			return l;
		}
	}
	
	public static String arrayToString(String [] strs) {
		
		if(null != strs && 0 < strs.length) {
			
			String str = "";
			
			for (int i = 0; i < strs.length; i++) {
				
				if(checkStr(strs[i])) {
					str += strs[i] + ",";
				}
			}
			
			if(checkStr(str)) {
				return str.substring(0, str.length() - 1);
			}
		}
		
		return null;
	} 

	public static Integer strToInt(String str) {
		Integer in = 0;

		try {
			if (StringBase.checkStr(str)) {
				in = Integer.parseInt(str);
			}
		} catch (Exception e) {
			return in;
		}

		return in;
	}

	/**
	 *
	 * @Title: CheckStr
	 * @Description: 判断变量是否为NULL或空
	 * @author: 许智皓
	 * @param str
	 * @return boolean
	 */
	public static boolean checkStr(String str) {
		if (null != str) {
			if (!"".equals(str.trim()) && !"null".equals(str.trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkObjStr(Object str) {
		if (null != str) {
			if (!"".equals(str.toString().trim()) && !"null".equals(str.toString().trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @Title: CheckStr
	 * @Description: 判断变量是否为NULL及
	 * @author: 许智皓
	 * @param str
	 * @param value
	 * @return boolean
	 */
	public static boolean checkStr(String str, String value) {
		if (null != str) {
			if (value.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @Title: CheckInt
	 * @Description: 判断数字是否为NULL及是否大于0
	 * @author: 许智皓
	 * @param ints
	 * @return boolean
	 */
	public static boolean checkInt(Integer ints) {
		if (null != ints) {
			if (0 < ints) {
				return true;
			}
		}
		return false;
	}

	public static Long checkLong(Long obj) {

		if (null != obj) {
			return obj;
		}
		return (long) 0;
	}

	/**
	 *
	 * @Title: CheckArray
	 * @Description: 判断变量是否为NULL或空
	 * @author: 许智皓
	 * @param array
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkArray(List array) {
		if (null != array && 0 < array.size()) {
			return true;
		}
		return false;
	}

	public static boolean checkStrArray(Object[] str) {
		if (null != str && 0 < str.length) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @Title: 根据查询标识返回一组开始，结束的时间日期，格式为：xxxx-xx-xx
	 * @Description: TODO
	 * @author: 许智皓
	 * @param selValue
	 * @return String[]
	 */
	@SuppressWarnings("static-access")
	public static String[] getCombinationDate(String selValue) {
		// toDay 今天
		// yesterDay 昨天
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strdate;
		String[] strdates = new String[2];

		try {
			if (StringBase.checkStr(selValue)) {

				if ("toDay".equals(selValue)) {// 当天时间
					calendar.setTime(date);
					calendar.add(Calendar.DATE, 0);// 把日期往后增加一天.整数往后推,负数往前移动
					date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
					strdate = formatter.format(date);

					strdates[0] = strdate;
					strdates[1] = strdate;
				} else if ("yesterDay".equals(selValue)) {// 昨天时间
					calendar.setTime(date);
					calendar.add(Calendar.DATE, -1);
					date = calendar.getTime();
					strdate = formatter.format(date);

					strdates[0] = strdate;
					strdates[1] = strdate;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return strdates;
		}

		return strdates;
	}

	/**
	 *
	 * @Title: combinationDate
	 * @Description: 根据查询标识组合查询时间段
	 * @author: 许智皓
	 * @param field
	 * @param selValue
	 * @return String
	 */
	@SuppressWarnings("static-access")
	public static String combinationDate(String field, String selValue) {
		// toDay 今天
		// yesterDay 昨天
		// sevenDays 最近7天
		// aMonth 最近一个月
		// month 本月

		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strdate;

		try {
			if (StringBase.checkStr(selValue)) {// 当天时间
				if ("toDay".equals(selValue)) {
					calendar.setTime(date);
					calendar.add(Calendar.DATE, 0);// 把日期往后增加一天.整数往后推,负数往前移动
					date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
					strdate = formatter.format(date);
					return field + " BETWEEN '" + strdate + " 00:00:00 ' AND '" + strdate + " 23:59:59' ";

				} else if ("yesterDay".equals(selValue)) {// 昨天时间
					calendar.setTime(date);
					calendar.add(Calendar.DATE, -1);
					date = calendar.getTime();
					strdate = formatter.format(date);
					return field + " BETWEEN '" + strdate + " 00:00:00 ' AND '" + strdate + " 23:59:59' ";
				} else if ("sevenDays".equals(selValue)) {// 最近7天
					calendar.setTime(date);
					calendar.add(Calendar.DATE, -6);
					date = calendar.getTime();
					strdate = formatter.format(date);

					date = new Date();
					Calendar bincalendar = new GregorianCalendar();
					bincalendar.setTime(date);
					bincalendar.add(Calendar.DATE, 0);
					date = bincalendar.getTime();
					String endstrdate = formatter.format(date);

					return field + " BETWEEN '" + strdate + " 00:00:00 ' AND '" + endstrdate + " 23:59:59' ";
				} else if ("twoWeek".equals(selValue)) {// 最近两周
					calendar.setTime(date);
					calendar.add(Calendar.DATE, -13);
					date = calendar.getTime();
					strdate = formatter.format(date);

					date = new Date();
					Calendar bincalendar = new GregorianCalendar();
					bincalendar.setTime(date);
					bincalendar.add(Calendar.DATE, 0);
					date = bincalendar.getTime();
					String endstrdate = formatter.format(date);

					return field + " BETWEEN '" + strdate + " 00:00:00 ' AND '" + endstrdate + " 23:59:59' ";
				} else if ("aMonth".equals(selValue)) {// 最近一个月
					calendar.setTime(date);
					calendar.add(Calendar.DATE, -29);
					date = calendar.getTime();
					strdate = formatter.format(date);

					date = new Date();
					Calendar bincalendar = new GregorianCalendar();
					bincalendar.setTime(date);
					bincalendar.add(Calendar.DATE, 0);
					date = bincalendar.getTime();
					String endstrdate = formatter.format(date);

					return field + " BETWEEN '" + strdate + " 00:00:00 ' AND '" + endstrdate + " 23:59:59' ";
				} else if ("month".equals(selValue)) {// 本月
					calendar.setTime(date);
					calendar.add(Calendar.DATE, 0);
					date = calendar.getTime();
					strdate = formatter.format(date);
					String binstrdate = strdate.substring(0, 8);
					binstrdate = binstrdate + "01";

					return field + " BETWEEN '" + binstrdate + " 00:00:00 ' AND '" + strdate + " 23:59:59' ";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return "";
	}

	/**
	 *
	 * @Title: kbToMbRoGb
	 * @Description: KB单位转为MB或G
	 * @author: 许智皓
	 * @param mapcounts
	 * @return String
	 */
	public static String kbToMbRoGb(long mapcounts) {

		DecimalFormat fnum = new DecimalFormat("##0.00 "); // 两位小数
		DecimalFormat fnum2 = new DecimalFormat("##0 "); // 没有小数

		float mapcount = mapcounts;
		if (0 < mapcount) {
			// 先转为MB
			mapcount = mapcount / 1024;
			if (mapcount > 1024) {
				// 转为GB
				mapcount = mapcount / 1024;
				if (mapcount > 1024) {
					if (mapcount > 100) {
						return fnum2.format(mapcount) + "GB";
					}
					return fnum.format(mapcount) + "GB";
				} else {
					if (mapcount > 100) {
						return fnum2.format(mapcount) + "GB";
					}
					return fnum.format(mapcount) + "GB";
				}
			} else {
				if (mapcount > 100) {
					return fnum2.format(mapcount) + "MB";
				}
				return fnum.format(mapcount) + "MB";
			}
		}

		return "0KB";

	}

	/**
	 *
	 * @Title: CreateOrderCode
	 * @Description: 生成支付订单号
	 * @author: 许智皓
	 * @return String
	 */
	public static String createOrderCode() {

		Date currTime = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.US);
		return sf.format(currTime) + randomString(3); // 推荐订单号构成格式为
		// 年月日-商户号-小时分钟秒
	}

	/**
	 *
	 * @Title: randomString
	 * @Description: 生成随机字符
	 * @author: 许智皓
	 * @param length
	 * @return String
	 */
	public static final String randomString(int length) {

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
					.toCharArray();
			// numbersAndLetters =
			// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
			// randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);
	}

	/**
	 *
	 * @Title: randomInt
	 * @Description: 生成随机数-纯数字
	 * @author: 许智皓
	 * @param length
	 * @return String
	 */
	public static final String randomInt(int length) {

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);
	}

	// 拼接字符
	public static final String strSpell(String str, String str2) {

		if (StringBase.checkStr(str)) {
			str = str + str2;
		}
		return str;

	}

	public static BigDecimal checkMoeny(BigDecimal money) {

		if (null != money) {
			return money;
		} else {
			return new BigDecimal(0);
		}

	}

	// HM1 对 HM2 赋值
	@SuppressWarnings("rawtypes")
	public static Map mashMapFz(Map<String, Object> map1, Map<String, Object> map2) {

		Iterator<Map.Entry<String, Object>> mapIt = map1.entrySet().iterator();

		while (mapIt.hasNext()) {

			Map.Entry<String, Object> mapItem = (Map.Entry<String, Object>) mapIt.next();

			map2.put(mapItem.getKey(), mapItem.getValue());
		}

		return map2;
	}

	/**
	 *
	 * @Title: paseDateStr
	 * @Description: 裁剪日期 从2015-11-04 00:00:00 裁为2015-11-04
	 * @author: 许智皓
	 * @date 2015-11-17 上午10:39:25
	 * @param date
	 * @return String
	 *
	 * @updateDate:
	 * @update:
	 */
	public static String paseDateStr(String date) {

		if (StringBase.checkStr(date)) {

			if (18 < date.length()) {
				return date.substring(0, 10);
			}
		}

		return date;
	}

	/**
	 * 计算百分比-保留1位小数
	 *
	 * @param dividendObj 被除数
	 * @param divisorObj  除数
	 * @return 如果除数为0则返回0.0
	 */
	public static String calculatedPercent(Object dividendObj, Object divisorObj) {
		if (dividendObj == null || divisorObj == null)
			return "0.0";
		double dividend = Double.parseDouble(dividendObj.toString());
		double divisor = Double.parseDouble(divisorObj.toString());
		double result = 0.0;
		if (divisor != 0) {
			result = dividend / divisor * 100;
			DecimalFormat df = new DecimalFormat("0.0");
			return df.format(result);
		} else {
			return "0.0";
		}
	}

	/**
	 *
	 * @Title: getUUID
	 * @Description: 生成自增ID
	 * @author: 许智皓
	 * @date 2016年1月11日 上午10:08:24
	 * @return String
	 *
	 * @updateDate:
	 * @update:
	 */
	public static String getUUID() {

		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 *
	 * @Title: getPLatformDeptId
	 * @Description: 根据子部门ID截取其最顶级部门的ID
	 * @author: 许智皓
	 * @date 2016-1-13 下午5:55:26
	 * @param deptId
	 * @return String
	 *
	 * @updateDate:
	 * @update:
	 */
	public static String getPLatformDeptId(String deptId) {

		int num = deptId.indexOf("_", 4);
		if (num > 0) {
			return deptId.substring(0, num);
		} else {
			return deptId;
		}
	}

	public static boolean checkIs(BigDecimal val) {

		if (2 == initBigDecimal(val).intValue()) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @Title: initBigDecimal
	 * @Description: 初始化BigDecimal
	 * @author: 许智皓
	 * @date 2016-1-14 上午10:24:47
	 * @param val
	 * @return BigDecimal
	 *
	 * @updateDate:
	 * @update:
	 */
	public static BigDecimal initBigDecimal(BigDecimal val) {

		if (null != val) {
			return val;
		}

		return new BigDecimal(0);
	}

	/**
	 *
	 * @Title: formatLonLat
	 * @Description: 格式化经玮度
	 * @author: 许智皓
	 * @date 2016-4-18 下午2:52:36
	 * @param val
	 * @return BigDecimal
	 *
	 * @updateDate:
	 * @update:
	 */
	public static BigDecimal formatLonLat(BigDecimal val) {

		DecimalFormat df = new DecimalFormat("0.000000000");

		if (null != val && StringBase.checkStr(df.format(val))) {
			try {

				return BigDecimal.valueOf(Double.parseDouble(df.format(val)));

			} catch (Exception e) {
				return BigDecimal.valueOf(Double.parseDouble(df.format(0.000)));
			}
		} else {

			return BigDecimal.valueOf(Double.parseDouble(df.format(0.000)));
		}
	}

	/**
	 *
	 * @Title: formatLonLat
	 * @Description: 格式化经玮度
	 * @author: 许智皓
	 * @date 2016-4-18 下午2:52:36
	 * @param val
	 * @return BigDecimal
	 *
	 * @updateDate:
	 * @update:
	 */
	public static String formatLonLatStr(BigDecimal val) {

		DecimalFormat df = new DecimalFormat("0.000000000");

		if (null != val) {
			return df.format(val);
		}

		return null;
	}

	/**
	 * 将字符串转成unicode
	 *
	 * @param str 待转字符串
	 * @return unicode字符串
	 */
	public static String unicodeConvert(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);

		}
		return (new String(sb));
	}

	/**
	 * 将unicode 字符串
	 *
	 * @param str 待转字符串
	 * @return 普通字符串
	 */
	public static String unicodeRevert(String str) {
		str = (str == null ? "" : str);
		if (str.indexOf("\\u") == -1)// 如果不是unicode码则原样返回
			return str;

		StringBuffer sb = new StringBuffer(1000);

		for (int i = 0; i < str.length() - 6;) {
			String strTemp = str.substring(i, i + 6);
			String value = strTemp.substring(2);
			int c = 0;
			for (int j = 0; j < value.length(); j++) {
				char tempChar = value.charAt(j);
				int t = 0;
				switch (tempChar) {
				case 'a':
					t = 10;
					break;
				case 'b':
					t = 11;
					break;
				case 'c':
					t = 12;
					break;
				case 'd':
					t = 13;
					break;
				case 'e':
					t = 14;
					break;
				case 'f':
					t = 15;
					break;
				default:
					t = tempChar - 48;
					break;
				}

				c += t * ((int) Math.pow(16, (value.length() - j - 1)));
			}
			sb.append((char) c);
			i = i + 6;
		}
		return sb.toString();

	}

	/**
	 * 去除字符串前后空格
	 *
	 * @param str {@link Object} 对象或字符串
	 * @return {@link boolean} 是否为空对象boolean值
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	/**
	 * 去除字符串前后空格
	 *
	 * @param str {@link String} 待去除空格的字符串
	 * @return {@link String} 去除空格后的字符串
	 */
	public static String trim(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return str.trim();
	}

	/**
	 * Replace all occurences of a substring within a string with another string.
	 *
	 * @param inString   String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replaceAll(String inString, String oldPattern, String newPattern) {
		if (null == inString || oldPattern == null || newPattern == null) {
			return inString;
		}
		String rlt = inString;
		StringBuffer sb = new StringBuffer();
		while (true) {
			int idx = rlt.indexOf(oldPattern);
			if (idx < 0) {
				break;
			}
			sb.delete(0, sb.length());
			if (idx > 0) {
				sb.append(rlt.substring(0, idx));
			}
			sb.append(newPattern);
			sb.append(rlt.substring(idx + oldPattern.length()));
			rlt = sb.toString();
		}
		return rlt;
	}

	/**
	 * 根据用","隔开的字符Id转换成list
	 *
	 * @param id {@link String}
	 * @return {@link List}
	 */
	public static List<Integer> getListId(String id) {
		String str[] = id.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < str.length; i++) {
			list.add(Integer.parseInt(str[i]));
		}
		return list;
	}

	/**
	 * 判断两个字符串是否相等
	 *
	 * @param arg1 {@link String}
	 * @param arg2 {@link String}
	 * @return {@link Boolean}
	 */
	public static Boolean isEqualString(String arg1, String arg2) {
		return arg1.equals(arg2);
	}

	/**
	 * 格式化字符串
	 *
	 * @param arg     {@link String}
	 * @param objects {@link Object}
	 * @return {@link String}
	 */
	public static String formatterString(String arg, Object... objects) {
		return MessageFormat.format(arg, objects);
	}

	/**
	 *
	 * String字符串转整形数组
	 *
	 * @param ids  {@link String} 字符串
	 * @param sign {@link String} 符号
	 * @return
	 */
	public static int[] stringToArray(String ids, String sign) {
		String[] t = ids.split(sign);
		int[] arrays = new int[t.length];
		for (int i = 0; i < t.length; i++) {
			arrays[i] = Integer.parseInt(t[i]);
		}
		return arrays;
	}

	/**
	 * uncode 转中文
	 *
	 * @param dataStr {@link String} 目标字符串
	 * @return
	 */
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(Character.valueOf(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	/**
	 * 中文 转 uncode
	 *
	 * @param dataStr {@link String} 目标字符串
	 * @return
	 */
	public static String encodeUnicode(final String dataStr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dataStr.length(); i++) {
			sb.append("\\u");
			sb.append(Integer.toHexString(dataStr.charAt(i) & 0xffff));
		}
		return sb.toString();
	}

	/**
	 * 查询数字是否存在数组中
	 *
	 * @param array  目标数组
	 * @param number 目标数字
	 * @return
	 */
	public static boolean numberInArray(int[] array, int number) {
		int start = 0, end, middle, count = 0;
		int N = array.length;
		end = N;
		middle = (start + end) / 2;
		while (number != array[middle]) {
			if (number > array[middle]) {
				start = middle;
			} else if (number < array[middle]) {
				end = middle;
			}
			middle = (start + end) / 2;
			count++;
			if (count > N / 2) {
				break;
			}
		}
		if (count > N / 2) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @Description: 生成行程名称
	 * @author: 许智皓
	 * @date 2017年6月9日 下午6:53:26
	 * 
	 * @version: V1.0
	 *
	 * @updateDate:
	 * @update:
	 */
	public static String crateTravelName(WlVehicle vehicle) {

		String num = "";
		if (StringBase.checkStr(vehicle.getVehicleCode())) {
			num = vehicle.getVehicleCode();
		} else {
			if (StringBase.checkStr(vehicle.getVin())) {
				num = vehicle.getVin();
			}
		}

		// vehicleNumber+时间截
		return num + "_" + DateUtil.getTimeMillis();
	}

}
