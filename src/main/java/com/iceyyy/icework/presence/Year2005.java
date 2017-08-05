package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2005 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20050103", true); // 休
        map.put("20050205", false); // 班
        map.put("20050206", false); // 班
        map.put("20050209", true); // 休
        map.put("20050210", true); // 休
        map.put("20050211", true); // 休
        map.put("20050214", true); // 休
        map.put("20050215", true); // 休
        map.put("20050430", false); // 班
        map.put("20050502", true); // 休
        map.put("20050503", true); // 休
        map.put("20050504", true); // 休
        map.put("20050505", true); // 休
        map.put("20050506", true); // 休
        map.put("20050508", false); // 班
        map.put("20051003", true); // 休
        map.put("20051004", true); // 休
        return map;
    }
}
