package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2015 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20150101", true); // 休
        map.put("20150102", true); // 休
        map.put("20150104", false); // 班
        map.put("20150215", false); // 班
        map.put("20150218", true); // 休
        map.put("20150219", true); // 休
        map.put("20150220", true); // 休
        map.put("20150223", true); // 休
        map.put("20150224", true); // 休
        map.put("20150228", false); // 班
        map.put("20150406", true); // 休
        map.put("20150501", true); // 休
        map.put("20150622", true); // 休
        map.put("20150903", true); // 休
        map.put("20150904", true); // 休
        map.put("20150906", false); // 班
        map.put("20151001", true); // 休
        return map;
    }
}
