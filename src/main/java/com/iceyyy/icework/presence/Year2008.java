package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2008 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20080101", true); // 休
        map.put("20080203", false); // 班
        map.put("20080206", true); // 休
        map.put("20080207", true); // 休
        map.put("20080208", true); // 休
        map.put("20080211", true); // 休
        map.put("20080212", true); // 休
        map.put("20080404", true); // 休
        map.put("20080501", true); // 休
        map.put("20080502", true); // 休
        map.put("20080504", false); // 班
        map.put("20080609", true); // 休
        map.put("20080915", true); // 休
        map.put("20080927", false); // 班
        map.put("20080928", false); // 班
        map.put("20080929", true); // 休
        map.put("20080930", true); // 休
        return map;
    }
}
