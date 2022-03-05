package databaseClass.databaseTest;

import databaseClass.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test1DAO extends ConnectDB {

    private ResultSet resultSet;

    public List<Test1VO> testConnectionList() {
        ArrayList<Test1VO> list = new ArrayList<>();
        PreparedStatement getAllUserInfo = null;

        try {
            connDB();

            String query = "SELECT * FROM users";
            getAllUserInfo = conn.prepareStatement(query);
            resultSet = getAllUserInfo.executeQuery();

            while (resultSet.next()) {

                int userPrimaryKey = resultSet.getInt("user_primaryKey");
                String userID = resultSet.getString("user_id");
                String userPW = resultSet.getString("user_pw");

                Test1VO data = new Test1VO(userPrimaryKey, userID, userPW);
                list.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (getAllUserInfo != null) {
                try {
                    getAllUserInfo.close();
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

        return list;
    }
}
