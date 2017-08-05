package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2001 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20010120", false); // 班
        map.put("20010121", false); // 班
        map.put("20010124", true); // 休
        map.put("20010125", true); // 休
        map.put("20010126", true); // 休
        map.put("20010129", true); // 休
        map.put("20010130", true); // 休
        map.put("20010428", false); // 班
        map.put("20010429", false); // 班
        map.put("20010501", true); // 休
        map.put("20010502", true); // 休
        map.put("20010503", true); // 休
        map.put("20010504", true); // 休
        map.put("20010507", true); // 休
        map.put("20010929", false); // 班
        map.put("20010930", false); // 班
        map.put("20011001", true); // 休
        return map;
    }
}
