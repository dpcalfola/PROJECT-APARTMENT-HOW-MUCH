package com.folaSmile.apartSearch.databaseClass.databaseTest;

public class Test1VO {

    private int userPrimaryKey;
    private String userID;
    private String userPW;


    public Test1VO(int userPrimaryKey, String userID, String userPW) {
        this.userPrimaryKey = userPrimaryKey;
        this.userID = userID;
        this.userPW = userPW;
    }

    public int getUserPrimaryKey() {
        return userPrimaryKey;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPW() {
        return userPW;
    }
}
