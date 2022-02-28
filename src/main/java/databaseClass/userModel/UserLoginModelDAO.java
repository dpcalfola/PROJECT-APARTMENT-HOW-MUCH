package databaseClass.userModel;

import databaseClass.ConnDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginModelDAO extends ConnDB {

    public UserModelVO loginUser(String userID, String userPW) {
        ResultSet resultSet = null;
        PreparedStatement checkUpUser = null;


        UserModelVO userInfo = new UserModelVO(0, "initial value", "initial value", false);

        try {

            connDB();

            checkUpUser = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            checkUpUser.setString(1, userID);

            System.out.println(checkUpUser.toString());

            resultSet = checkUpUser.executeQuery();

            // if user_id exist in database
            if (resultSet.next()) {
                String getUserIdFromDB = resultSet.getString("user_id");
                String getUserPwFromDB = resultSet.getString("user_pw");
                int getUserPrimaryKey = resultSet.getInt("user_primaryKey");

                // return userInfo through UserVO constructor
                if (getUserIdFromDB.equals(userID) && getUserPwFromDB.equals(userPW)) {
                    userInfo = new UserModelVO(getUserPrimaryKey, getUserIdFromDB, getUserPwFromDB, true);
                    return userInfo;
                } else {
                    userInfo = new UserModelVO(getUserPrimaryKey, getUserIdFromDB, "WRONGPASSWORD", false);
                    return userInfo;
                }
            } else {
                // if user ID doesn't exist, return userPrimaryKey as -1
                // Use this value when SignUp situation
                userInfo = new UserModelVO(-1, "Doesn't exist userID", "Doesn't exist user ID", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (checkUpUser != null) {
                try {
                    checkUpUser.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return userInfo;
    }
}
