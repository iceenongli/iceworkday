package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2016 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20160101", true); // 休
        map.put("20160206", false); // 班
        map.put("20160208", true); // 休
        map.put("20160209", true); // 休
        map.put("20160210", true); // 休
        map.put("20160211", true); // 休
        map.put("20160212", true); // 休
        map.put("20160214", false); // 班
        map.put("20160404", true); // 休
        map.put("20160502", true); // 休
        map.put("20160609", true); // 休
        map.put("20160610", true); // 休
        map.put("20160612", false); // 班
        map.put("20160915", true); // 休
        map.put("20160916", true); // 休
        map.put("20160918", false); // 班
        map.put("20161003", true); // 休
        return map;
    }
}
