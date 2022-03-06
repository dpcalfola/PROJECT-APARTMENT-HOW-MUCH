package com.folaSmile.apartSearch.databaseClass.userModel;

public class UserModelVO {

    final int userPrimaryKey;
    final String userID;
    final String userPW;
    final boolean isCorrectUserInfo;

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
