package com.iceyyy.icework.presence;

import java.util.Map;

public class YearMap {

    public static void main(String[] args) {
        int year = 2001;
        System.out.println(YearMap.yearMap(year));
    }

    public static Map<String, Boolean> yearMap(int year) {
        Year20xx createMap = createMap(year);
        return createMap.getYearMap();
    }

    private static Year20xx createMap(int year) {
        if (year == 2001) {
            return new Year2001();
        } else if (year == 2002) {
            return new Year2002();
        } else if (year == 2003) {
            return new Year2003();
        } else if (year == 2004) {
            return new Year2004();
        } else if (year == 2005) {
            return new Year2005();
        } else if (year == 2006) {
            return new Year2006();
        } else if (year == 2007) {
            return new Year2007();
        } else if (year == 2008) {
            return new Year2008();
        } else if (year == 2009) {
            return new Year2009();
        } else if (year == 2010) {
            return new Year2010();
        } else if (year == 2011) {
            return new Year2011();
        } else if (year == 2012) {
            return new Year2012();
        } else if (year == 2013) {
            return new Year2013();
        } else if (year == 2014) {
            return new Year2014();
        } else if (year == 2015) {
            return new Year2015();
        } else if (year == 2016) {
            return new Year2016();
        } else if (year == 2017) {
            return new Year2017();
        } else if (year == 2018) {
            return new Year2018();
        }
        return null;
    }

}
