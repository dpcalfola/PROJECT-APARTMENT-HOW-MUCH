package databaseClass.user;

import databaseClass.ConnDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDAO {

    public UserVO loginUser(String userID, String userPW) {
        ResultSet resultSet = null;
        PreparedStatement checkUpUser = null;


        UserVO userInfo = new UserVO(0, "initial value", "initial value", false);

        try {
            ConnDB loginConn = new ConnDB();
            loginConn.connDB();

            checkUpUser = loginConn.conn.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            checkUpUser.setString(1, userID);

            resultSet = checkUpUser.executeQuery();

            // if user_id exist in database
            if (resultSet.next()) {
                String getUserIdFromDB = resultSet.getString("user_id");
                String getUserPwFromDB = resultSet.getString("user_pw");
                int getUserPrimaryKey = resultSet.getInt("user_primaryKey");

                System.out.println("loginDAO getId " + getUserIdFromDB);
                System.out.println("loginDAO userID " + userID);

                // return userInfo through UserVO constructor
                if (getUserIdFromDB.equals(userID) && getUserPwFromDB.equals(userPW)) {
                    userInfo = new UserVO(getUserPrimaryKey, getUserIdFromDB, getUserPwFromDB, true);
                    return userInfo;
                } else {
                    userInfo = new UserVO(getUserPrimaryKey, getUserIdFromDB, "WRONGPASSWORD", false);
                    return userInfo;
                }
            } else {
                // if user ID doesn't exist, return userPrimaryKey as -1
                // Use this value when SignUp situation
                userInfo = new UserVO(-1, "Doesn't exist userID", "Doesn't exist user ID", false);
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

        }

        return userInfo;
    }
}
