package databaseClass.testConnection;

public class TestConnectionVO {

    private int userPrimaryKey;
    private String userID;
    private String userPW;


    public TestConnectionVO(int userPrimaryKey, String userID, String userPW) {
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
