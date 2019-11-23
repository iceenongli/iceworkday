package com.iceyyy.workday;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.iceyyy.icework.presence.YearMap;
import com.iceyyy.nongli.NongLi;
/**
 * iceworkday工作日算法
 * @author IceWater zxcyhn@126.com
 */
public class WorkUtils {

    public static Map<String, Boolean> weekendMap(String year) {
        if (year == null || "".equals(year.trim())) {
            throw new RuntimeException("传入的参数为空");
        }
        if (year.length() != 4) {
            throw new RuntimeException("传入的参数格式错误，应传入yyyyMMdd格式的日期");
        }
        int yearNum = Integer.parseInt(year.substring(0, 4));
        if (yearNum < 2001) {
            throw new RuntimeException("工作日计算年份需要大于等于2001");
        } else if (yearNum > 2017) {
            Map<String, Boolean> mapFromFile = weekMapFromFile();
            mapFromFile = filterMap(mapFromFile, yearNum);
            Map<String, Boolean> map = complexCalculate(yearNum);
            if (mapFromFile.size() != 0) {
                map.putAll(mapFromFile);
            }
            return map;
        } else {
            Map<String, Boolean> map = YearMap.yearMap(yearNum);
            return map;
        }
    }

    public static boolean isWorkendDay(String ymd) {
        if (ymd == null || "".equals(ymd)) {
            throw new RuntimeException("输入的日期不能为空");
        }
        ymd = convertDate(ymd);
        if("19991230".equals(ymd)) {
            return true;
        }
        if("19991231".equals(ymd)) {
            return true;
        }
        String yearStr = ymd.substring(0, 4);
        Map<String, Boolean> weekendMap = weekendMap(yearStr);
        Boolean isWeekend = weekendMap.get(ymd);
        if (isWeekend == null) {
            int day = getWeekDay(ymd);
            if (day == 0 || day == 6) {
                return true;
            } else {
                return false;
            }
        } else {
            return isWeekend;
        }
    }

	private static String convertDate(String dateStr) {
		String[] array = null;
		if (dateStr.contains("-")) {
			array = dateStr.split("-");
		} else if (dateStr.contains(".")) {
			array = dateStr.split("\\.");
		} else if (dateStr.contains("/")) {
			array = dateStr.split("/");
		} else if (dateStr.contains("_")) {
			array = dateStr.split("_");
		} else if (dateStr.contains("年")) {
			if (dateStr.endsWith("日")) {
				dateStr = dateStr.substring(0, dateStr.length() - 1);
			}
			array = dateStr.split("年|月");
		} else {
			if (dateStr.length() == 8) {
				return dateStr;
			}
		}
		String yearStr = array[0];
		String monthStr = array[1];
		String dayStr = array[2];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4 - yearStr.length(); i++) {
			sb.append("0");
		}
		sb.append(yearStr);
		if (monthStr.length() == 1) {
			sb.append("0");
		}
		sb.append(monthStr);
		if (dayStr.length() == 1) {
			sb.append("0");
		}
		sb.append(dayStr);
		return sb.toString();
	}

    private static Map<String, Boolean> complexCalculate(int yearNum) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        String newYear = "0101";
        String chingming = "0405";
        String labor = "0501";
        String dragon = "0505L";
        String autumn = "0815L";
        Map<String, Boolean> newYearMap = gregorianNewYearMap(yearNum, newYear);
        Map<String, Boolean> nextYearMap = gregorianNewYearMap(yearNum + 1, newYear);
        map.putAll(currentYearMap(newYearMap, nextYearMap, yearNum));
        map.putAll(festivalMap(yearNum, chingming));
        map.putAll(newYearLunarMap(yearNum));
        map.putAll(festivalMap(yearNum, labor));
        map.putAll(festivalMap(yearNum, dragon));
        map.putAll(nationalAutumnMap(yearNum, autumn));
        return map;
    }

    private static Map<String, Boolean> nationalAutumnMap(int yearNum, String autumn) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        String ymd = yearNum + "0815";// 中秋节
        String ymdNational = yearNum + "1001";// 国庆节
        String ymdAutumn = convertGregorian(ymd);
        int day = getWeekDay(ymdNational);
        Map<String, Boolean> mapHistory = getHistoryMap();
        String key = ymdAutumn.substring(4, 8) + "_" + day;
        if (mapHistory.get(key) != null) {
            // TODO 此处希望从近几年相似的月份中进行类比，是一种优化算法，暂未实现，但不影响算法使用。
        }
        String monthAutumn = ymdAutumn.substring(4, 6);
        String dateAutumn = ymdAutumn.substring(6, 8);
        int monthAutumnNum = Integer.parseInt(monthAutumn);
        int dateAutumnNum = Integer.parseInt(dateAutumn);
        if (dateAutumnNum < 25 && monthAutumnNum == 9) {// 中秋在9.25以前
            // 中秋最早9.7 最晚10.8
            map.putAll(festivalMap(yearNum, "0815L"));// 中秋
            map.putAll(nationalMap(yearNum));// 国庆
        } else if (monthAutumnNum == 10) {// 中秋节在10月份
            map.putAll(nationalOctMap(yearNum));// 国庆
        } else if (dateAutumnNum >= 25 && monthAutumnNum == 9) {// 中秋节在9.25 9.26
                                                                // 9.27 9.28
                                                                // 9.29 9.30
            // 1. 中秋 25 全部两段 周一~周日
            // 2. 中秋 26 全部两段 周二~周一
            // 3. 中秋 27 最复杂 周三~周二
            // 4. 中秋 28 全部休8天 周四~周三
            // 5. 中秋 29 全部休8天 周五~周四
            // 6. 中秋 30 全部休8天 周六~周五
            // 补班 最早 9.22 最迟 10.12
            int dayAutumn = getWeekDay(ymdAutumn);
            map.putAll(complexMap(yearNum, dateAutumnNum, dayAutumn));// 国庆
        } else {
            throw new RuntimeException("中秋日期格式错误");
        }
        return map;
    }

    private static Map<String, Boolean> complexMap(int yearNum, int dateAutumnNum, int dayAutumn) {
        Data[][] table = getAutumnTable(dateAutumnNum);
        Data[] row = getRow(table, dateAutumnNum, dayAutumn);
        return row2map(row, yearNum);
    }

    private static Map<String, Boolean> row2map(Data[] row, int yearNum) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        for (int i = 0; i < row.length; i++) {
            Data data = row[i];
            int date = data.getDate();
            boolean repose = data.getRepose();
            String monthStr = "10";// 10月份
            if (date > 15) {
                monthStr = "09";// 9月份
            }
            String dateStr = date + "";
            if (date < 10) {
                dateStr = "0" + date;
            }
            String key = yearNum + monthStr + dateStr;
            map.put(key, repose);
        }
        return map;
    }

    /**
     * 根据中秋的星期获取具体的某一行的数据
     * 
     * @param table
     * @param dayAutumn
     * @return
     */
    private static Data[] getRow(Data[][] table, int dateAutumnNum, int dayAutumn) {
        // 25 1 [0]
        // 25 2 [1]
        // 25 3 [2]

        // 26 1 [6]
        // 26 2 [0]
        // 26 3 [1]
        // 26 4 [2]
        // date+index-23=day
        // index=day+23-date
        int index = (dayAutumn + 31 - dateAutumnNum) % 7;
        return table[index];
    }

    private static Data[][] getAutumnTable(int dateAutumnNum) {
        Data[][] days1 = {
                { new Data(25, true), new Data(30, false), new Data(2, true), new Data(3, true), new Data(4, true),
                        new Data(5, true), new Data(6, true), new Data(8, false) },
                { new Data(22, false), new Data(24, true), new Data(25, true), new Data(29, false), new Data(30, false),
                        new Data(1, true), new Data(2, true), new Data(3, true), new Data(4, true), new Data(5, true) },
                { new Data(25, true), new Data(26, true), new Data(27, true), new Data(28, false), new Data(29, false),
                        new Data(1, true), new Data(2, true), new Data(3, true), new Data(4, true), new Data(7, true),
                        new Data(12, false) },
                { new Data(21, false), new Data(25, true), new Data(26, true), new Data(28, false), new Data(1, true),
                        new Data(2, true), new Data(3, true), new Data(6, true), new Data(7, true),
                        new Data(11, false) },
                { new Data(20, false), new Data(25, true), new Data(1, true), new Data(2, true), new Data(5, true),
                        new Data(6, true), new Data(7, true), new Data(10, false) },
                { new Data(27, true), new Data(1, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(7, true), new Data(9, false), new Data(10, false) },
                { new Data(26, true), new Data(3, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(7, true), new Data(8, false), new Data(9, false) } };
        Data[][] days2 = {
                { new Data(23, false), new Data(25, true), new Data(26, true), new Data(30, false), new Data(2, true),
                        new Data(3, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(8, false) },
                { new Data(23, false), new Data(26, true), new Data(27, true), new Data(28, true), new Data(29, false),
                        new Data(30, false), new Data(1, true), new Data(2, true), new Data(3, true), new Data(4, true),
                        new Data(5, true), new Data(13, false) },
                { new Data(22, false), new Data(26, true), new Data(27, true), new Data(29, false), new Data(1, true),
                        new Data(2, true), new Data(3, true), new Data(4, true), new Data(7, true) },
                { new Data(21, false), new Data(26, true), new Data(1, true), new Data(2, true), new Data(3, true),
                        new Data(6, true), new Data(7, true) },
                { new Data(1, true), new Data(2, true), new Data(5, true), new Data(6, true), new Data(7, true),
                        new Data(10, false) },
                { new Data(1, true), new Data(4, true), new Data(5, true), new Data(6, true), new Data(7, true),
                        new Data(9, false) },
                { new Data(26, true), new Data(3, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(7, true), new Data(8, false), new Data(9, false) } };
        Data[][] days3 = {
                { new Data(23, false), new Data(24, false), new Data(25, true), new Data(26, true), new Data(27, true),
                        new Data(30, false), new Data(2, true), new Data(3, true), new Data(4, true), new Data(5, true),
                        new Data(6, true), new Data(8, false) },
                { new Data(27, true), new Data(28, true), new Data(1, true), new Data(2, true), new Data(3, true),
                        new Data(4, true), new Data(6, false), new Data(7, false) },
                { new Data(27, true), new Data(30, true), new Data(1, true), new Data(2, true), new Data(3, true),
                        new Data(4, true), new Data(5, false), new Data(6, false) },
                { new Data(1, true), new Data(2, true), new Data(3, true), new Data(6, true), new Data(7, true),
                        new Data(11, false) },
                { new Data(1, true), new Data(2, true), new Data(5, true), new Data(6, true), new Data(7, true),
                        new Data(10, false) },
                { new Data(27, true), new Data(1, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(7, true), new Data(9, false), new Data(10, false) },
                { new Data(24, false), new Data(26, true), new Data(27, true), new Data(3, true), new Data(4, true),
                        new Data(5, true), new Data(6, true), new Data(7, true), new Data(8, false),
                        new Data(9, false) } };
        Data[][] days4 = {
                { new Data(28, true), new Data(29, true), new Data(2, true), new Data(3, true), new Data(4, true),
                        new Data(5, true), new Data(7, false), new Data(8, false) },
                { new Data(28, true), new Data(1, true), new Data(2, true), new Data(3, true), new Data(4, true),
                        new Data(5, true), new Data(6, false), new Data(7, false) },
                { new Data(30, true), new Data(1, true), new Data(2, true), new Data(3, true), new Data(4, true),
                        new Data(6, false) },
                { new Data(27, false), new Data(29, true), new Data(30, true), new Data(1, true), new Data(2, true),
                        new Data(3, true) },
                { new Data(26, false), new Data(27, false), new Data(28, true), new Data(29, true), new Data(30, true),
                        new Data(1, true), new Data(2, true), new Data(5, true) },
                { new Data(25, false), new Data(26, false), new Data(28, true), new Data(29, true), new Data(30, true),
                        new Data(1, true), new Data(4, true), new Data(5, true) },
                { new Data(25, false), new Data(28, true), new Data(29, true), new Data(30, true), new Data(3, true),
                        new Data(4, true), new Data(5, true), new Data(8, false) } };
        Data[][] days5 = {
                { new Data(29, true), new Data(2, true), new Data(3, true), new Data(4, true), new Data(5, true),
                        new Data(6, true), new Data(7, false), new Data(8, false) },
                { new Data(1, true), new Data(2, true), new Data(3, true), new Data(4, true), new Data(5, true),
                        new Data(7, false) },
                { new Data(28, false), new Data(30, true), new Data(1, true), new Data(2, true), new Data(3, true),
                        new Data(4, true) },
                { new Data(27, false), new Data(28, false), new Data(29, true), new Data(30, true), new Data(1, true),
                        new Data(2, true), new Data(3, true), new Data(6, true) },
                { new Data(26, false), new Data(27, false), new Data(29, true), new Data(30, true), new Data(1, true),
                        new Data(2, true), new Data(5, true), new Data(6, true) },
                { new Data(26, false), new Data(29, true), new Data(30, true), new Data(1, true), new Data(4, true),
                        new Data(5, true), new Data(6, true), new Data(9, false) },
                { new Data(29, true), new Data(30, true), new Data(3, true), new Data(4, true), new Data(5, true),
                        new Data(6, true), new Data(8, false), new Data(9, false) } };
        Data[][] days6 = {
                { new Data(2, true), new Data(3, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(8, false) },
                { new Data(29, false), new Data(1, true), new Data(2, true), new Data(3, true), new Data(4, true),
                        new Data(5, true) },
                { new Data(28, false), new Data(29, false), new Data(30, true), new Data(1, true), new Data(2, true),
                        new Data(3, true), new Data(4, true), new Data(7, true) },
                { new Data(27, false), new Data(28, false), new Data(30, true), new Data(1, true), new Data(2, true),
                        new Data(3, true), new Data(6, true), new Data(7, true) },
                { new Data(27, false), new Data(30, true), new Data(1, true), new Data(2, true), new Data(5, true),
                        new Data(6, true), new Data(7, true), new Data(10, false) },
                { new Data(30, true), new Data(1, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(7, true), new Data(9, false), new Data(10, false) },
                { new Data(30, true), new Data(3, true), new Data(4, true), new Data(5, true), new Data(6, true),
                        new Data(7, true), new Data(8, false), new Data(9, false) } };
        if (dateAutumnNum == 25) {
            return days1;
        } else if (dateAutumnNum == 26) {
            return days2;
        } else if (dateAutumnNum == 27) {
            return days3;
        } else if (dateAutumnNum == 28) {
            return days4;
        } else if (dateAutumnNum == 29) {
            return days5;
        } else if (dateAutumnNum == 30) {
            return days6;
        }
        throw new RuntimeException("中秋日期格式错误");
    }

    private static Map<String, Boolean> nationalOctMap(int yearNum) {
        String ymd = yearNum + "1001";
        int day = getWeekDay(ymd);
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        int[][] correction = { { 30 }, { 29, 30 }, { 28, 29 }, { 28, 11 }, { 27, 10 }, { 9, 10 }, { 9 } };
        for (int i = 0; i < correction[day].length; i++) {
            int corr = correction[day][i];
            if (corr <= 15) {
                String key = yearNum + "10" + convertNum(corr);
                map.put(key, false);
            } else {
                String key = yearNum + "09" + convertNum(corr);
                map.put(key, false);
            }
        }
        int start = 0;
        if (day == 0 || day == 1) {
            start = -1;
        }
        int end = 0;
        if (day == 5) {
            end = 1;
        }
        for (int i = 0 + start; i < 8 + end; i++) {// 国庆节一般为7天
            String curDate = addDay(ymd, i);
            int curDay = getWeekDay(curDate);
            if (curDay > 0 && curDay < 6) {
                map.put(curDate, true);
            }
        }
        return map;
    }

    private static Map<String, Boolean> nationalMap(int yearNum) {
        String ymd = yearNum + "1001";
        int day = getWeekDay(ymd);
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        int[][] correction = { { 30, 8 }, { 29, 14 }, { 29, 12 }, { 28, 11 }, { 10 }, { 26, 9 }, { 8, 9 } };
        if (day > 0 && day < 6) {
            map.put(ymd, true);
        }
        for (int i = 0; i < correction[day].length; i++) {
            int corr = correction[day][i];
            if (corr <= 15) {
                String key = yearNum + "10" + convertNum(corr);
                map.put(key, false);
            } else {
                String key = yearNum + "09" + convertNum(corr);
                map.put(key, false);
            }
        }
        for (int i = 0; i < 7; i++) {// 国庆节一般为7天
            String curDate = addDay(ymd, i);
            int curDay = getWeekDay(curDate);
            if (curDay > 0 && curDay < 6) {
                map.put(curDate, true);
            }
        }
        return map;
    }

    private static String convertNum(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return num + "";
        }
    }

    private static Map<String, Boolean> getHistoryMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return map;
    }

    private static Map<String, Boolean> newYearLunarMap(int yearNum) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        String ymd = yearNum + "0101";
        ymd = convertGregorian(ymd);
        int day = getWeekDay(ymd);
        int[][] correction = { { 6, 7 }, { -2, 6 }, { -3, 12 }, { -3, 10 }, { -4, 9 }, { -5, 8 }, { -6, 7 } };
        int correct = -1;
        if (yearNum == 2014 || yearNum <= 2007) {
            correction = new int[][] { { 6, 7 }, { -2, 6 }, { -3, 12 }, { -3, 10 }, { -4, 9 }, { -5, 8 }, { -6, 7 } };
            correct = 0;
        }
        for (int i = 0; i < correction[day].length; i++) {
            map.put(addDay(ymd, correction[day][i]), false);
        }
        for (int i = 0; i < 7; i++) {
            String curYmd = addDay(ymd, i + correct);
            int dayCur = getWeekDay(curYmd);
            if (dayCur > 0 && dayCur < 6) {
                map.put(curYmd, true);
            }
        }
        return map;
    }

    private static Map<String, Boolean> currentYearMap(Map<String, Boolean> newYearMap,
            Map<String, Boolean> nextYearMap, int yearNum) {
        String yearStr = yearNum + "";
        Set<String> setCur = newYearMap.keySet();
        Set<String> setNext = nextYearMap.keySet();
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        for (String key : setCur) {
            if (key.startsWith(yearStr)) {
                map.put(key, newYearMap.get(key));
            }
        }
        for (String key : setNext) {
            if (key.startsWith(yearStr)) {
                map.put(key, nextYearMap.get(key));
            }
        }
        return map;
    }

    private static Map<String, Boolean> festivalMap(int yearNum, String festival) {
        String ymd = yearNum + festival;
        if (festival.endsWith("L")) {
            festival = festival.substring(0, festival.length() - 1);
            ymd = convertGregorian(ymd);
        } else if ("0405".equals(festival)) {
            ymd = chingming(yearNum);
        }
        return small(ymd);
    }

    private static Map<String, Boolean> gregorianNewYearMap(int yearNum, String festival) {
        String ymd = yearNum + festival;
        return gregorianNewYearSmall(ymd);
    }

    private static String chingming(int yearNum) {
        return yearNum + "040" + qing(yearNum);
    }

    /**
     * 计算清明节的日期(可计算范围: 1700-3100)
     * 
     * @param year
     *            需要计算的年份
     * @return 清明节在公历中的日期
     */
    private static int qing(int year) {
        if (year == 2232) {
            return 4;
        }
        if (year < 1700) {
            throw new RuntimeException("1700年以前暂时不支持");
        }
        if (year >= 3100) {
            throw new RuntimeException("3100年以后暂时不支持");
        }
        double[] coefficient = { 5.15, 5.37, 5.59, 4.82, 5.02, 5.26, 5.48, 4.70, 4.92, 5.135, 5.36, 4.60, 4.81, 5.04,
                5.26 };
        int mod = year % 100;
        return (int) (mod * 0.2422 + coefficient[year / 100 - 17] - mod / 4);
    }

    private static Map<String, Boolean> small(String ymd) {
        int day = getWeekDay(ymd);
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        int[][] correction = { { 1 }, {}, { -1, -3 }, { -1, -2, -3, -4 }, { 1, 3 }, {}, { 2 } };
        // 6.取-1 2014年清明 2015年端午 6. 取2 2012年端午
        if (day > 0 && day < 6) {
            map.put(ymd, true);
        }
        if (day == 3) {
            map.put(addDay(ymd, -1), true);
            map.put(addDay(ymd, -2), true);
            map.put(addDay(ymd, -3), false);
            map.put(addDay(ymd, -4), false);
        } else {
            boolean flag = true;
            for (int i = 0; i < correction[day].length; i++) {
                map.put(addDay(ymd, correction[day][i]), flag);
                flag = false;
            }
        }
        return map;
    }

    private static Map<String, Boolean> gregorianNewYearSmall(String ymd) {
        int day = getWeekDay(ymd);
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        int[][] correction = { { 1 }, {}, { 1, 2, 4, 5 }, {}, { 1, 2, 3 }, {}, { 2 } };
        // 6.取-1 2014年清明 2015年端午 6. 取2 2012年端午 0{+1}1{} 2{+1+2 +4b+5b} 3{}
        // 4{+1,+2,+3b}5{}6{+2}
        if (day > 0 && day < 6) {
            map.put(ymd, true);
        }
        if (day == 2) {
            map.put(addDay(ymd, 1), true);
            map.put(addDay(ymd, 2), true);
            map.put(addDay(ymd, 4), false);
            map.put(addDay(ymd, 5), false);
        } else if (day == 3) {

        } else if (day == 4) {
            map.put(addDay(ymd, 1), true);
            map.put(addDay(ymd, 3), false);
        } else {
            boolean flag = true;
            for (int i = 0; i < correction[day].length; i++) {
                map.put(addDay(ymd, correction[day][i]), flag);
                flag = false;
            }
        }
        return map;
    }

    private static int getWeekDay(String ymd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(ymd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return day;
    }

    private static String convertGregorian(String ymd) {// private
        String cnYmd = convertCnYmd(ymd);
        int start = 18;
        int len = 60;// 公历农历按最多相差60天计算。
        for (int i = start; i < start + len; i++) {
            String gregorianDate = addDay(ymd, i);
            String lunarDate = NongLi.getDate(gregorianDate);
            if (cnYmd.equals(lunarDate)) {
                return gregorianDate;
            }
        }
        return null;
    }

    private static String convertCnYmd(String ymd) {// private
        String numArray = "零一二三四五六七八九十";
        String m = ymd.substring(4, 6);
        String d = ymd.substring(6, 8);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        String yearStr = "";
        for (int i = 0; i < 4; i++) {
            yearStr += numArray.charAt(ymd.charAt(i) - '0') + "";
        }
        yearStr += "年";
        String monthStr = "";
        if ("11".equals(m)) {
            monthStr = "冬";
        } else if ("12".equals(m)) {
            monthStr = "腊";
        } else if ("01".equals(m)) {
            monthStr = "正";
        } else {
            monthStr = numArray.charAt(month) + "";
        }
        monthStr += "月";
        String dayStr = "";
        if (day <= 10) {
            dayStr = "初" + numArray.charAt(day);
        } else if (day < 20) {
            dayStr = "十" + numArray.charAt(day - 10);
        } else if (day == 20) {
            dayStr = "二十";
        } else if (day == 30) {
            dayStr = "三十";
        } else {
            dayStr = "廿" + numArray.charAt(day - 20);
        }
        return yearStr + monthStr + dayStr;
    }

    private static String addDay(String date, int dayLength) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateSrc = null;
        try {
            dateSrc = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateSrc);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + dayLength);
        return sdf.format(cal.getTime());
    }

    private static String[] read(String fileName) {
        BufferedReader br = null;
        ArrayList<String> list = new ArrayList<String>();

        try {
            br = new BufferedReader(
                    new InputStreamReader(WorkUtils.class.getClassLoader().getResourceAsStream(fileName)));
            String every = null;
            while ((every = br.readLine()) != null) {
                if (!every.startsWith("#")) {
                    list.add(every);
                }
            }
            String[] datas = new String[list.size()];
            for (int i = 0; i < datas.length; i++) {
                datas[i] = list.get(i);
            }
            return datas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Map<String, Boolean> weekMapFromFile() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        String fileName = "iceweek.txt";
        String[] lines = WorkUtils.read(fileName);
        for (String line : lines) {
            if (line == null || line.length() != 9) {
                continue;
            }
            String key = line.substring(0, 8);
            Boolean value = convertWork(line.substring(8));
            map.put(key, value);
        }
        return map;
    }

    private static Boolean convertWork(String str) {
        if ("b".equals(str)) {
            return false;
        } else if ("x".equals(str)) {
            return true;
        }
        return null;
    }

    private static Map<String, Boolean> filterMap(Map<String, Boolean> map, int year) {
        String yearStr = year + "";
        Map<String, Boolean> newMap = new HashMap<String, Boolean>();
        Set<Entry<String, Boolean>> entrySet = map.entrySet();
        for (Entry<String, Boolean> entry : entrySet) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            if (key != null && key.startsWith(yearStr)) {
                newMap.put(key, value);
            }
        }
        return newMap;
    }

    /**
     * 提示语
     * @return 提示语
     */
    protected static String warn() {
        return "注意：该算法对于将来的时间仅是预测，并不能完全精确。";
    }
}
