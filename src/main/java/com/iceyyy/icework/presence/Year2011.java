package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2011 implements Year20xx {
    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20110103", true); // 休
        map.put("20110130", false); // 班
        map.put("20110202", true); // 休
        map.put("20110203", true); // 休
        map.put("20110204", true); // 休
        map.put("20110207", true); // 休
        map.put("20110208", true); // 休
        map.put("20110212", false); // 班
        map.put("20110402", false); // 班
        map.put("20110404", true); // 休
        map.put("20110405", true); // 休
        map.put("20110502", true); // 休
        map.put("20110606", true); // 休
        map.put("20110912", true); // 休
        map.put("20111003", true); // 休
        map.put("20111004", true); // 休
        map.put("20111005", true); // 休
        return map;
    }
}
