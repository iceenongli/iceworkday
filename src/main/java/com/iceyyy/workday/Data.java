package com.iceyyy.workday;

public class Data {
    private int date;
    private boolean repose;

    public Data(int date, boolean repose) {
        this.date = date;
        this.repose = repose;
    }

    public int getDate() {
        return date;
    }

    public boolean getRepose() {
        return repose;
    }
}
