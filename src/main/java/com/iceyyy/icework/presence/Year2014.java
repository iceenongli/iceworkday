package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2014 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20140101", true); // 休
        map.put("20140126", false); // 班
        map.put("20140131", true); // 休
        map.put("20140203", true); // 休
        map.put("20140204", true); // 休
        map.put("20140205", true); // 休
        map.put("20140206", true); // 休
        map.put("20140208", false); // 班
        map.put("20140407", true); // 休
        map.put("20140501", true); // 休
        map.put("20140502", true); // 休
        map.put("20140504", false); // 班
        map.put("20140602", true); // 休
        map.put("20140908", true); // 休
        map.put("20140928", false); // 班
        map.put("20141001", true); // 休
        map.put("20141002", true); // 休
        return map;
    }
}
