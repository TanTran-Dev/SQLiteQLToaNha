package com.trantan.sqliteqltoanha.model;

public class Resident{
    private int mID;
    private String mNameOfHouseHold;
    private String mRoomNumber;
    private String mNameOfBuilding;
    private int mPhoneNumber;

    public Resident(int mID, String mNameOfHouseHold, String mRoomNumber, String mNameOfBuilding, int mPhoneNumber) {
        this.mID = mID;
        this.mNameOfHouseHold = mNameOfHouseHold;
        this.mRoomNumber = mRoomNumber;
        this.mNameOfBuilding = mNameOfBuilding;
        this.mPhoneNumber = mPhoneNumber;
    }

    public Resident(String mNameOfHouseHold, String mRoomNumber, String mNameOfBuilding, int mPhoneNumber) {
        this.mNameOfHouseHold = mNameOfHouseHold;
        this.mRoomNumber = mRoomNumber;
        this.mNameOfBuilding = mNameOfBuilding;
        this.mPhoneNumber = mPhoneNumber;
    }

    public Resident() {
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmNameOfBuilding() {
        return mNameOfBuilding;
    }

    public void setmNameOfBuilding(String mNameOfBuilding) {
        this.mNameOfBuilding = mNameOfBuilding;
    }

    public String getmNameOfHouseHold() {
        return mNameOfHouseHold;
    }

    public void setmNameOfHouseHold(String mNameOfHouseHold) {
        this.mNameOfHouseHold = mNameOfHouseHold;
    }

    public String getmRoomNumber() {
        return mRoomNumber;
    }

    public void setmRoomNumber(String mRoomNumber) {
        this.mRoomNumber = mRoomNumber;
    }

    public int getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(int mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}
