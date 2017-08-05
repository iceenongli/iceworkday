package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2002 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20020101", true); // 休
        map.put("20020102", true); // 休
        map.put("20020103", true); // 休
        map.put("20020209", false); // 班
        map.put("20020210", false); // 班
        map.put("20020212", true); // 休
        map.put("20020213", true); // 休
        map.put("20020214", true); // 休
        map.put("20020215", true); // 休
        map.put("20020218", true); // 休
        map.put("20020427", false); // 班
        map.put("20020428", false); // 班
        map.put("20020501", true); // 休
        map.put("20020502", true); // 休
        map.put("20020503", true); // 休
        map.put("20020506", true); // 休
        map.put("20020507", true); // 休
        return map;
    }
}
