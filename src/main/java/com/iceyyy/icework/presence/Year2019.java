package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2019 implements Year20xx {

    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20190101", true); // 休
        map.put("20190202", false); // 班
        map.put("20190203", false); // 班
        map.put("20190204", true); // 休
        map.put("20190205", true); // 休
        map.put("20190206", true); // 休
        map.put("20190207", true); // 休
        map.put("20190208", true); // 休
        map.put("20190405", true); // 休
        map.put("20190501", true); // 休
        map.put("20190607", true); // 休
        map.put("20190913", true); // 休
        map.put("20190929", false); // 班
        map.put("20191001", true); // 休
        map.put("20191002", true); // 休
        map.put("20191003", true); // 休
        map.put("20191004", true); // 休
        map.put("20191007", true); // 休
        map.put("20191012", false); // 班
        return map;
    }

}
