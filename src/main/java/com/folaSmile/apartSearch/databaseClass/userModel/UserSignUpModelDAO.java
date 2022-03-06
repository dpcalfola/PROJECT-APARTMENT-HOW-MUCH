package com.folaSmile.apartSearch.databaseClass.userModel;

import com.folaSmile.apartSearch.databaseClass.ConnectDB;

import java.sql.*;

public class UserSignUpModelDAO extends ConnectDB {

    public boolean signUpUser(String userID, String userPW) {

        PreparedStatement signUpQuery = null;
        boolean isSucceed = false;

        try {
            connDB();

            // make query
            signUpQuery = conn.prepareStatement("INSERT INTO users (user_id, user_pw)" +
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
            if (signUpQuery != null) {
                try {
                    signUpQuery.close();
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

        return isSucceed;


    }
}
