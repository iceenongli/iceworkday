package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2006 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20060102", true); // 休
        map.put("20060103", true); // 休
        map.put("20060128", false); // 班
        map.put("20060130", true); // 休
        map.put("20060131", true); // 休
        map.put("20060201", true); // 休
        map.put("20060202", true); // 休
        map.put("20060203", true); // 休
        map.put("20060205", false); // 班
        map.put("20060429", false); // 班
        map.put("20060430", false); // 班
        map.put("20060501", true); // 休
        map.put("20060502", true); // 休
        map.put("20060503", true); // 休
        map.put("20060504", true); // 休
        map.put("20060505", true); // 休
        map.put("20060930", false); // 班
        return map;
    }
}
