package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2003 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20030101", true); // 休
        map.put("20030203", true); // 休
        map.put("20030204", true); // 休
        map.put("20030205", true); // 休
        map.put("20030206", true); // 休
        map.put("20030207", true); // 休
        map.put("20030208", false); // 班
        map.put("20030209", false); // 班
        map.put("20030426", false); // 班
        map.put("20030427", false); // 班
        map.put("20030501", true); // 休
        map.put("20030502", true); // 休
        map.put("20030505", true); // 休
        map.put("20030506", true); // 休
        map.put("20030507", true); // 休
        map.put("20030927", false); // 班
        map.put("20030928", false); // 班
        return map;
    }
}
