package com.rmp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期格式转换，处理工具
 */
public class DateUtil {
	
	private DateUtil() {
	}
	
	public static final String yyyy = "yyyy";
	public static final String MM = "MM";
	public static final String dd = "dd";
	public static final String HH = "HH";
	public static final String mm = "mm";
	public static final String ss = "ss";
	public static final String sss = "sss";
	
	public static final String YYYY_MM = yyyy + "-" + MM;
	public static final String YYYY_MM_DD = YYYY_MM + "-" + dd;
	public static final String YYYY_MM_DD_HH = YYYY_MM_DD + " " + HH;
	public static final String YYYY_MM_DD_HH_MM = YYYY_MM_DD_HH + ":" + mm;
	public static final String YYYY_MM_DD_HH_MM_SS = YYYY_MM_DD_HH_MM + ":" + ss;
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = YYYY_MM_DD_HH_MM_SS + ":" + sss;
	
	public static final String sssss = ss + sss;
	
	public static final String mmss = mm + ss;
	public static final String mmsssss = mm + sssss;

	public static final String HHmm = HH + mm;
	public static final String HHmmss = HH + mmss;
	public static final String HHmmsssss = HH + mmsssss;
	
	public static final String ddHH = dd + HH;
	public static final String ddHHMM = dd + HHmm;
	public static final String ddHHMMSS = dd + HHmmss;
	public static final String ddHHMMSSSSS = dd + HHmmsssss;
	
	public static final String MMdd = MM + dd;
	public static final String MMddHH = MM + ddHH;
	public static final String MMddHHMM = MM + ddHHMM;
	public static final String MMddHHMMSS = MM + ddHHMMSS;
	public static final String MMddHHMMSSSSS = MM + ddHHMMSSSSS;
	
	public static final String yyyyMM = yyyy + MM;
	public static final String yyyyMMdd = yyyy + MMdd;
	public static final String yyyyMMddHH = yyyy + MMddHH;
	public static final String yyyyMMddHHMM = yyyy + MMddHHMM;
	public static final String yyyyMMddHHMMSS = yyyy + MMddHHMMSS;
	public static final String yyyyMMddHHMMSSSSS = yyyy + MMddHHMMSSSSS;
	
	
	
	
	/**
	 * date 格式化 str
	 * @param date
	 * @param pattern 格式
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		return formater.format(date);
	}
	
	/**
	 * date 格式化 str（yyyy-MM-dd）
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat(YYYY_MM_DD);
		return formater.format(date);
	}
	
	/**
	 * date 格式化 str（yyyy-MM-dd HH:mm:ss）
	 * @param date
	 * @return
	 */
	public static String formatDate2(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return formater.format(date);
	}
	
	/**
	 * date 格式化 str（yyyyMMdd）
	 * @param date
	 * @return
	 */
	public static Long formatDateLong(Date date) {
		return Long.valueOf(formatDate(date, yyyyMMdd));
	}
	
	/**
	 * date 格式化 str（yyyyMMddHHmmss）
	 * @param date
	 * @return
	 */
	public static Long formatDate2Long(Date date) {
		return Long.valueOf(formatDate(date, yyyyMMdd));
	}
	
	/**
	 * str 格式化 date
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateStr, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		try {
			return formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * str 格式化 date（yyyy-MM-dd）
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr){
		return parseDate(dateStr, YYYY_MM_DD);
	}
	
	/**
	 * str 格式化 date（yyyy-MM-dd HH:mm:ss）
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate2(String dateStr){
		return parseDate(dateStr, YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	/**
	 * 获取当前月份
	 * @return
	 */
	public static int getCurrentMonth(){
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	
	/**
	 * 获取 周
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int num = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (num == 0) {
			num = 7;
		}
		return num;
	}
	
/*	*//**
	 * 比较两个日期相差的天数
	 * @param d1 
	 * @param d2 
	 * @param hour 相差的小时 
	 * @return
	 *//*
	public static long getDayDiffer(Date d1,Date d2,int hour){
	    long rel =d2.getTime()-d1.getTime();
	    return Math.abs(rel/(1000*60*60*hour));
	}*/
	
	/**
	 * 比较两个日期相差的秒
	 * @param d1 
	 * @param d2 
	 * @param hour 相差的秒
	 * @return
	 */
	public static long getSencondDiffer(Date d1,Date d2){
	    long rel =d2.getTime()-d1.getTime();
	    return Math.abs(rel/(1000));
	}
	
	/**
     * 时间比较, 比较到日
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 0表示相同, 正数是date1 > date2, 负数是date1 < date2
     */
    public static int compareToDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date1).compareTo(sdf.format(date2));
    }
    
    /**
     * 时间比较, 比较到时
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 0表示相同, 正数是date1 > date2, 负数是date1 < date2
     */
    public static int compareToHour(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        return sdf.format(date1).compareTo(sdf.format(date2));
    }

    /**
     * 时间比较, 比较到秒
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 0表示相同, 正数是date1 > date2, 负数是date1 < date2
     */
    public static int compareToSecond(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date1).compareTo(sdf.format(date2));
    }

    /**
     * 取得所给的日期的日开始时间(00:00:00,000)
     *
     * @param day 要转换的日期
     * @return 日开始时间
     */
    public static Date getStartDateOfDay(Date day) {
        if (day == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * 取得所给的日期的日结束时间(23:59:59,000)
     *
     * @param day 要转换的日期
     * @return 日结束时间
     */
    public static Date getEndDateOfDay(Date day) {
        if (day == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return cal.getTime();
    }

    /**
     * 取得所给的日期的月开始时间(00:00:00,000)
     *
     * @param day 要转换的日期
     * @return 月开始时间
     */
    public static Date getStartDateOfMonth(Date day) {
        if (day == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 取得所给的日期的月结束时间(23:59:59,000)
     *
     * @param day 要转换的日期
     * @return 月结束时间
     */
    public static Date getEndDateOfMonth(Date day) {
        if (day == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 取得所给的日期的年开始时间(00:00:00,000)
     *
     * @param day 要转换的日期
     * @return 年开始时间
     */
    public static Date getStartDateOfYear(Date day) {
        if (day == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        return cal.getTime();
    }

    /**
     * 取得所给的日期的年结束时间(23:59:59,000)
     *
     * @param day 要转换的日期
     * @return 年结束时间
     */
    public static Date getEndDateOfYear(Date day) {
        if (day == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return cal.getTime();
    }

    /**
     * 根据年月日创建的日期
     *
     * @param year  年 如2006年为2006
     * @param month 月 如12月为 11
     * @param day   日 如15日为15
     * @return 日期
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 根据年月日创建的日期
     *
     * @param year   年 如2006年为2006
     * @param month  月 如12月为 11
     * @param day    日 如15日为15
     * @param hour   小时 24时格式 如下午2点为 14
     * @param minute 分钟 如25分为25
     * @param second 秒 如30秒位30
     * @return 日期
     */
    public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 取得年数字
     *
     * @param date 日期
     * @return 年数字
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 取得月数字,一月为 0
     *
     * @param date 日期
     * @return 月数字
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    /**
     * 取得日数字
     *
     * @param date 日期
     * @return 日数字
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算开始日期和结束日期中间相差几个月
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 相差几个月
     */
    public static int calculateMonthDistance(Date start, Date end) {
        int year1 = getYear(start);
        int year2 = getYear(end);
        int month1 = getMonth(start);
        int month2 = getMonth(end);
        return 12 * (year1 - year2) + (month1 - month2);
    }

    /**
     * 计算开始日期和结束日期中间相差多少天
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 相差多少天
     */
    public static int calculateDayDistance(Date start, Date end) {
        long startTimeInMillis = start.getTime();
        long endTimeInMillis = end.getTime();
        return (int) ((startTimeInMillis - endTimeInMillis) / (1000 * 60 * 60 * 24));
    }
    

    /**
     * 得到现在的时间
     *
     * @return Date
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }
    
    /**
     * 得到现在的时间 毫秒
     *
     * @return Long
     */
    public static Long nowMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }
    
    /**
     * 得到现在的时间 秒
     *
     * @return Long
     */
    public static Long nowSecond() {
    	String nowLong = nowMillis().toString();
        return Long.valueOf(nowLong.substring(0, nowLong.length()-3));
    }
    
    /**
     * 得到现在的时间 秒 yyyyMMddHHmmss
     *
     * @return Long
     */
    public static Long nowSecondFormat() {
        return formatDate2Long(now());
    }
    
    /**
     * 时间戳 毫秒 转 Date
     * @param millis
     * @return
     */
    public static Date millisConversionDate(Long millis) {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(millis);
        return c.getTime();
    }
    
    /**
     * 时间戳 秒 转 Date
     * @param millis
     * @return
     */
    public static Date secondConversionDate(Long second) {
        return millisConversionDate(second * 1000);
    }
    
    /**
     * Date 转 时间戳 毫秒
     * @param millis
     * @return
     */
    public static Long dateConversionMillis(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
        return c.getTimeInMillis();
    }
    
    /**
     * 时间戳 秒 转 Date
     * @param millis
     * @return
     */
    public static Long dateConversionSecond(Date date) {
    	String millis = dateConversionMillis(date).toString();
        return Long.valueOf(millis.substring(0, millis.length()-3));
    }
    
    /**
     * 得到现在的时间 秒
     *
     * @return Long
     */
    public static Long nowLong2(Date date) {
        return dateConversionSecond(date);
    }

    
    /**
     * 当前时间以指定格式传换成文字.
     *
     * @param pattern 格式
     * @return 文字
     */
    public static String nowInFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(Calendar.getInstance().getTime());
    }
    
    /**
     * 获取传入日期的后一个月的日期
     * @param dateStr 日期
     * @param pattern 格式 例yyyy-MM-dd yyyy-MM
     */
    @SuppressWarnings("deprecation")
	public static String nowAfterMouth(String dateStr, String pattern){
		Date date = DateUtil.parseDate(dateStr,pattern);
		date.setMonth(date.getMonth() + 1);
        String datestr2 = DateUtil.formatDate(date, pattern);
        return datestr2;
	}
    
    /**
     * 取当前日期的前一个月的日期
     */
	public static String nowBeforeMouth(){
		//String dateStr = DateUtils.nowInFormat("yyyy-MM-dd");
		//Date now =DateUtils.parseDate(dateStr);
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());   //设置当前日期
        c.add(Calendar.MONTH, -1); //日期加1
        Date date2 = c.getTime(); //结果
        String datestr2=DateUtil.formatDate(date2, "yyyy-MM-dd");
        return datestr2;
	}
	
	/**
	 * 传入一个日期和间隔天数，取到想要的日期
	 */
	public static Date changeDays(Date date,int count)
	{
		Date date1=(Date)date;
		//String dateStr = DateUtils.nowInFormat("yyyy-MM-dd");
		//Date now =DateUtils.parseDate(dateStr);
		Calendar c = Calendar.getInstance();
        c.setTime(date1);   //设置当前日期
        c.add(Calendar.DATE, count); //日期加1
        Date date2 = c.getTime(); //结果
        return date2;
	}
	/**
	 * 传入一个日期和间隔天数，取到想要的日期
	 */
	public static Date changeDays(String date,int count)
	{
		Date date1=(Date)parseDate(date);
		//String dateStr = DateUtils.nowInFormat("yyyy-MM-dd");
		//Date now =DateUtils.parseDate(dateStr);
		Calendar c = Calendar.getInstance();
        c.setTime(date1);   //设置当前日期
        c.add(Calendar.DATE, count); //日期加1
        Date date2 = c.getTime(); //结果
        return date2;
	}
	
	/**
	 * 传入一个日期和间隔天数，取到想要的日期
	 */
	public static Date changeHours(Date date,int count)
	{
		Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.HOUR, count); //小时加1
        Date date2 = c.getTime(); //结果
		return date2; 
	}
	
	/**
	 * 传入一个日期和间隔分钟数，取到想要的日期时间
	 */
	public static Date changeMinute(Date date,int count)
	{
		Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.MINUTE, count); //加上间隔时间的分钟数
        Date date2 = c.getTime(); //结果
		return date2; 
	}
	/**
	 * 传入一个日期和间隔天数，取到想要的日期
	 */
	public static Date changeMonth(Date date,int count)
	{
		Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.MONTH, count); //月加1
        Date date2 = c.getTime(); //结果
		return date2; 
	}
	
	/**
	 * 传入一个日期和间隔天数，取到想要的日期
	 */
	public static Date changeYear(Date date,int count)
	{
		Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.YEAR, count); //年加1
        Date date2 = c.getTime(); //结果
		return date2; 
	}
	
	/**
	 * 传入一个日期和  秒数,取到想要的日期,如果是负数是减，正数是增加
	 * @param date
	 * @return
	 */
	public static Date changeSecond(Date date, int count) {    
	    Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(date);    
	    calendar.add(Calendar.SECOND, count);    
	    return calendar.getTime();    
	} 
	/**
	 * 传入一个日期和  秒数,取到想要的日期,如果是负数是减，正数是增加
	 * @param date
	 * @return
	 */
	public static Date changeSecond(String date, int count) {  
		Date date1=(Date)parseDate(date);
	    Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(date1);    
	    calendar.add(Calendar.SECOND, count);    
	    return calendar.getTime();    
	}
	
	/**
	 * 检查当前时间是否在配置时间之内
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkDate(Date startDate, Date endDate) {
		boolean result = false;
		if(startDate != null && endDate != null) {
			Date nowDate = DateUtil.now();
			if(DateUtil.compareToSecond(nowDate, startDate) > 0
					&& DateUtil.compareToSecond(endDate, nowDate) > 0) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 检查当前时间是否在配置时间之内(2个时间可以都为null)
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkDate2(Date startDate, Date endDate) {
		boolean result = false;
		if(startDate != null && endDate != null) {
			Date nowDate = DateUtil.now();
			if(DateUtil.compareToSecond(nowDate, startDate) > 0
					&& DateUtil.compareToSecond(endDate, nowDate) > 0) {
				result = true;
			}
		} else if(startDate == null && endDate != null) {
			Date nowDate = DateUtil.now();
			if(DateUtil.compareToSecond(endDate, nowDate) > 0) {
				result = true;
			}
		} else if(startDate != null && endDate == null) {
			Date nowDate = DateUtil.now();
			if(DateUtil.compareToSecond(nowDate, startDate) > 0) {
				result = true;
			}
		} else {
			result = true;
		}
		return result;
	}
	
	
	
	/**
	 * 获取倒计时字符串  (date2 - date1)
	 * @param date1		
	 * @param date2	
	 * @return
	 */
	public static String getCountdownStr(Date date1,Date date2) {
		long nd = 24*60*60;//一天的秒数
		long nh = 60*60;//一小时的秒数
		long nm = 60;//一分钟的秒数
		long ns = 1;//一秒钟的毫秒数long diff;try {
		//获得两个时间的毫秒时间差异
		long diff = getSencondDiffer(date2, date1);
		long day = diff/nd;//计算差多少天
		long hour = diff%nd/nh;//计算差多少小时
		long min = diff%nd%nh/nm;//计算差多少分钟
		long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
		StringBuffer strBuf = new StringBuffer();
		if(day>0)
			strBuf.append(day).append("天");
		if(hour>=0)
			strBuf.append(hour).append("小时");
		if(min>=0)
			strBuf.append(min).append("分钟");
		if(sec>=0)
			strBuf.append(sec).append("秒");
		return strBuf.toString();
	}
	
	/**
	 * 获取本周一
	 * @param date
	 * @return
	 */
	public static Date getNowWeekMonday(Date date) {    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(date);    
       cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况    
       cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
       return cal.getTime();    
    }
	
	
	/**
	 * 获取上周一
	 * @param date
	 * @return
	 */
	public static Date getLastWeekMonday(Date date) {    
       Date a = DateUtil.changeDays(date, -1);    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(a);    
       cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周    
       cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
       return cal.getTime();    
   }
	/**
	 * 获取上周日
	 * @param date
	 * @return
	 */
	public static Date getLastWeekSunday(Date date) {    
       Date a = DateUtil.changeDays(date, -1);    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(a);    
       cal.set(Calendar.DAY_OF_WEEK, 1);    
       return cal.getTime();    
    }
	
	/**
	 * 将特定的字符串日期转化成日期类型 <br />
	 * 1.需要格式化的字符型日期str <br />
	 * 2.可判断的格式为：yyyy-MM-dd和yyyy-MM-dd HH:mm:ss; <br />
	 * 得到日期型数据
	 * 
	 * @param str
	 *            需要处理的日期型字符串
	 * @return 返回一个日期类型的对象
	 * @see Date
	 * @see SimpleDateFormat
	 * @see StringUtil
	 * @see ParseException
	 * 
	 */
	public static Date str2Date(String str) {
		str=str.replace(".0", "");
		String formatStr = "";
		String reg1 = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
		String reg2 = "^\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}$";
		String reg3 = "^\\d{4}-\\d{1,2}";
		if(str.matches(reg1)){
			formatStr = YYYY_MM_DD;
		}
		if(str.matches(reg2)){
			formatStr = YYYY_MM_DD_HH_MM_SS;
		}
		if(str.matches(reg3)){
			formatStr = YYYY_MM;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		
		try {
			str = StringUtils.trim(str);
			if (!StringUtils.isEmpty(str)) {
				Date date = sdf.parse(str);
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			sdf = null;
		}
		return null;
	}
	
	/**
     * 将秒数转换为日时分秒，
     * @param second
     * @return
     */
    public static String secondToTime(long second){
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second /60;            //转换分钟
        second = second % 60;                //剩余秒数
        
    	String result = "";
    	if (second < 9) {
    		result = "0" + second + "";
    	} else {
    		result = second + "";
    	}
    	
    	if (minutes == 0) {
    		result = "00:" + result;
    	} else if (minutes < 9) {
    		result = "0" + minutes + ":" + result;
    	} else {
    		result = minutes + ":" + result;
    	}
    	
    	if (hours == 0) {
    		
    	} else if (hours < 9) {
    		result = "0" + hours + ":" + result;
    	} else {
    		result = hours + ":" + result;
    	}
    	
    	if (days > 0) {
    		result = days + ":" + result;
    	}
        return result;
    }
    
    /**
     * 将秒数转换为日时分秒，
     * @param second
     * @return
     */
    public static String secondToTime2(long second){
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second /60;            //转换分钟
        second = second % 60;                //剩余秒数
        
    	String result = "";
    	if (second < 9) {
    		result = "0" + second + "";
    	} else {
    		result = second + "";
    	}
    	
    	if (minutes == 0) {
    		result = "00:" + result;
    	} else if (minutes < 9) {
    		result = "0" + minutes + ":" + result;
    	} else {
    		result = minutes + ":" + result;
    	}
    	
    	if (hours == 0) {
    		result = "00:" + result;
    	} else if (hours < 9) {
    		result = "0" + hours + ":" + result;
    	} else {
    		result = hours + ":" + result;
    	}
    	
    	if (days > 0) {
    		result = days + ":" + result;
    	}
        return result;
    }
    
    public static void main(String[] args) {
    	System.out.println(secondToTime2(1111L));
	}
}