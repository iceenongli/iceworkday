package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2012 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20120102", true); // 休
        map.put("20120103", true); // 休
        map.put("20120121", false); // 班
        map.put("20120123", true); // 休
        map.put("20120124", true); // 休
        map.put("20120125", true); // 休
        map.put("20120126", true); // 休
        map.put("20120127", true); // 休
        map.put("20120129", false); // 班
        map.put("20120331", false); // 班
        map.put("20120401", false); // 班
        map.put("20120402", true); // 休
        map.put("20120403", true); // 休
        map.put("20120404", true); // 休
        map.put("20120428", false); // 班
        map.put("20120430", true); // 休
        map.put("20120501", true); // 休
        return map;
    }
}
