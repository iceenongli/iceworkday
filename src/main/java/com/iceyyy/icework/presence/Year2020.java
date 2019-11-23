package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2020 implements Year20xx {

    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20200101", true); // 休
        map.put("20200119", false); // 班
        map.put("20200124", true); // 休
        map.put("20200127", true); // 休
        map.put("20200128", true); // 休
        map.put("20200129", true); // 休
        map.put("20200130", true); // 休
        map.put("20200201", false); // 班
        map.put("20200406", true); // 休
        map.put("20200426", false); // 班
        map.put("20200501", true); // 休
        map.put("20200504", true); // 休
        map.put("20200505", true); // 休
        map.put("20200509", false); // 班
        map.put("20200625", true); // 休
        map.put("20200626", true); // 休
        map.put("20200628", false); // 班
        map.put("20200927", false); // 班
        map.put("20201001", true); // 休
        map.put("20201002", true); // 休
        map.put("20201005", true); // 休
        map.put("20201006", true); // 休
        map.put("20201007", true); // 休
        map.put("20201008", true); // 休
        map.put("20201010", false); // 班
        return map;
    }

}
