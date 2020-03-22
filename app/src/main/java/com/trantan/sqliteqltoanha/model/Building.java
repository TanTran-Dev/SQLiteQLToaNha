package com.trantan.sqliteqltoanha.model;

public class Building {
    private int mID;
    private String mCode;
    private String mName;

    public Building(int mID, String mCode, String mName) {
        this.mID = mID;
        this.mCode = mCode;
        this.mName = mName;
    }

    public Building(String mCode, String mName) {
        this.mCode = mCode;
        this.mName = mName;
    }

    public Building() {
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
