package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2004 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20040101", true); // 休
        map.put("20040117", false); // 班
        map.put("20040118", false); // 班
        map.put("20040122", true); // 休
        map.put("20040123", true); // 休
        map.put("20040126", true); // 休
        map.put("20040127", true); // 休
        map.put("20040128", true); // 休
        map.put("20040503", true); // 休
        map.put("20040504", true); // 休
        map.put("20040505", true); // 休
        map.put("20040506", true); // 休
        map.put("20040507", true); // 休
        map.put("20040508", false); // 班
        map.put("20040509", false); // 班
        map.put("20041001", true); // 休
        map.put("20041003", true); // 休
        return map;
    }
}
