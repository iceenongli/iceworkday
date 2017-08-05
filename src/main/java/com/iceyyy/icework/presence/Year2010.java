package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2010 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20100101", true); // 休
        map.put("20100215", true); // 休
        map.put("20100216", true); // 休
        map.put("20100217", true); // 休
        map.put("20100218", true); // 休
        map.put("20100219", true); // 休
        map.put("20100220", false); // 班
        map.put("20100221", false); // 班
        map.put("20100405", true); // 休
        map.put("20100503", true); // 休
        map.put("20100612", false); // 班
        map.put("20100613", false); // 班
        map.put("20100614", true); // 休
        map.put("20100615", true); // 休
        map.put("20100616", true); // 休
        map.put("20100919", false); // 班
        map.put("20100922", true); // 休
        return map;
    }
}
