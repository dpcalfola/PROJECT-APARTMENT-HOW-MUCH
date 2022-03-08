package com.folaSmile.apartSearch.databaseClass.userModel;

public class UserModelVO {

    final private int userPrimaryKey;
    final private String userID;
    final private String userPW;
    final private boolean isCorrectUserInfo;

    public UserModelVO(int userPrimaryKey, String userID, String userPW, boolean isCorrectUserInfo) {
        this.userPrimaryKey = userPrimaryKey;
        this.userID = userID;
        this.userPW = userPW;
        this.isCorrectUserInfo = isCorrectUserInfo;
    }

    public boolean isCorrectUserInfo() {
        return isCorrectUserInfo;
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
