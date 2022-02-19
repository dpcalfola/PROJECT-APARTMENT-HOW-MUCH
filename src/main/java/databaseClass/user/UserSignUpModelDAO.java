package databaseClass.user;

import databaseClass.ConnDB;

import java.sql.*;

public class UserSignUpModelDAO extends ConnDB {

    public boolean SignUpUser(String userID, String userPW) {

        PreparedStatement signUpQuery = null;
        boolean isSucceed = false;

        try {
            connDB();

            // make query
            signUpQuery = conn.prepareStatement("INSERT INTO user (user_id, user_pw)" +
                    "VALUES (?,?)");
            signUpQuery.setString(1, userID);
            signUpQuery.setString(2, userPW);


            // -----> warning : '.executeQuery()' use for SELECT query. NOT INSERT or UPDATE query<-----
            // -----> USE .execute() <-----
            signUpQuery.execute();
            isSucceed = true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            if (signUpQuery != null) {
                try {
                    signUpQuery.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucceed;


    }
}
