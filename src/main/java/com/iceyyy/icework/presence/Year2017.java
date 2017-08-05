package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2017 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20170102", true); // 休
        map.put("20170122", false); // 班
        map.put("20170127", true); // 休
        map.put("20170130", true); // 休
        map.put("20170131", true); // 休
        map.put("20170201", true); // 休
        map.put("20170202", true); // 休
        map.put("20170204", false); // 班
        map.put("20170401", false); // 班
        map.put("20170403", true); // 休
        map.put("20170404", true); // 休
        map.put("20170501", true); // 休
        map.put("20170527", false); // 班
        map.put("20170529", true); // 休
        map.put("20170530", true); // 休
        map.put("20170930", false); // 班
        map.put("20171002", true); // 休
        return map;
    }
}
