package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2013 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20130101", true); // 休
        map.put("20130102", true); // 休
        map.put("20130103", true); // 休
        map.put("20130105", false); // 班
        map.put("20130106", false); // 班
        map.put("20130211", true); // 休
        map.put("20130212", true); // 休
        map.put("20130213", true); // 休
        map.put("20130214", true); // 休
        map.put("20130215", true); // 休
        map.put("20130216", false); // 班
        map.put("20130217", false); // 班
        map.put("20130404", true); // 休
        map.put("20130405", true); // 休
        map.put("20130407", false); // 班
        map.put("20130427", false); // 班
        map.put("20130428", false); // 班
        return map;
    }
}
