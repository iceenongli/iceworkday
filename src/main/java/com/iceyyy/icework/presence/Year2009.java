package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2009 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20090101", true); // 休
        map.put("20090102", true); // 休
        map.put("20090104", false); // 班
        map.put("20090124", false); // 班
        map.put("20090126", true); // 休
        map.put("20090127", true); // 休
        map.put("20090128", true); // 休
        map.put("20090129", true); // 休
        map.put("20090130", true); // 休
        map.put("20090201", false); // 班
        map.put("20090406", true); // 休
        map.put("20090501", true); // 休
        map.put("20090528", true); // 休
        map.put("20090529", true); // 休
        map.put("20090531", false); // 班
        map.put("20090927", false); // 班
        map.put("20091001", true); // 休
        return map;
    }
}
