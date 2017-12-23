package com.iceyyy.icework.presence;

import java.util.HashMap;
import java.util.Map;

public class Year2018 implements Year20xx {

    @Override
    public Map<String, Boolean> getYearMap() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("20180101", true); // 休
        map.put("20180211", false); // 班
        map.put("20180215", true); // 休
        map.put("20180216", true); // 休
        map.put("20180219", true); // 休
        map.put("20180220", true); // 休
        map.put("20180221", true); // 休
        map.put("20180224", false); // 班
        map.put("20180405", true); // 休
        map.put("20180406", true); // 休
        map.put("20170408", false); // 班
        map.put("20170428", false); // 班
        map.put("20180430", true); // 休
        map.put("20180501", true); // 休
        map.put("20180618", true); // 休
        map.put("20180924", true); // 休
        map.put("20170929", false); // 班
        map.put("20170930", false); // 班
        map.put("20181001", true); // 休
        map.put("20181002", true); // 休
        map.put("20181003", true); // 休
        map.put("20181004", true); // 休
        map.put("20181005", true); // 休
        return map;
    }

}
