package com.iceyyy.workday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.iceyyy.workday.WorkUtils;

public class WorkdayGen {

    public static void main(String[] args) {
        int year = 2021;
        String code = genYearCode(year);
        System.out.println(code);
    }

    protected static String gen(int year) {
        String yearStr = String.valueOf(year);
        Map<String, Boolean> weekendMap = WorkUtils.weekendMap(yearStr);
        Set<String> keySet = weekendMap.keySet();
        List<String> list = new ArrayList<>(keySet);
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String ymd : list) {
            boolean weekendDay = WorkUtils.isWorkendDay(ymd);
            String itemCode = genItemCode(ymd, weekendDay);
            sb.append(itemCode);
        }
        return sb.toString();
    }

    private static String genItemCode(String ymd, boolean weekendDay) {
        StringBuilder sb = new StringBuilder();
        String tab = "        ";
        String nl = "\r\n";
        sb.append(tab);
        sb.append("map.put(\"");
        sb.append(ymd);
        sb.append("\", ");
        sb.append(weekendDay);
        sb.append("); // ");
        sb.append(wordCode(weekendDay));
        sb.append(nl);
        return sb.toString();
    }

    private static String wordCode(boolean weekday) {
        if (weekday) {
            return "休";
        } else {
            return "班";
        }
    }

    private static String genYearCode(int year) {
        StringBuilder sb = new StringBuilder();
        String nl = "\r\n";
        sb.append("package com.iceyyy.icework.presence;" + nl);
        sb.append("" + nl);
        sb.append("import java.util.HashMap;" + nl);
        sb.append("import java.util.Map;" + nl);
        sb.append("");
        sb.append("public class Year" + year + " implements Year20xx {" + nl);
        sb.append("" + nl);
        sb.append("    @Override" + nl);
        sb.append("    public Map<String, Boolean> getYearMap() {" + nl);
        sb.append("        Map<String, Boolean> map = new HashMap<String, Boolean>();" + nl);
        String gen = gen(year);
        sb.append(gen);
        sb.append("        return map;" + nl);
        sb.append("    }" + nl);
        sb.append("" + nl);
        sb.append("}" + nl);
        return sb.toString();
    }
}
