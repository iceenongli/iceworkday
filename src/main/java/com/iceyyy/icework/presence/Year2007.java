package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2007 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20070101", true); // 休
        map.put("20070102", true); // 休
        map.put("20070103", true); // 休
        map.put("20070217", false); // 班
        map.put("20070219", true); // 休
        map.put("20070220", true); // 休
        map.put("20070221", true); // 休
        map.put("20070222", true); // 休
        map.put("20070223", true); // 休
        map.put("20070225", false); // 班
        map.put("20070428", false); // 班
        map.put("20070429", false); // 班
        map.put("20070501", true); // 休
        map.put("20070502", true); // 休
        map.put("20070503", true); // 休
        map.put("20070504", true); // 休
        map.put("20070507", true); // 休
        return map;
    }
}
