package databaseClass.testConnection;

import databaseClass.ConnDB;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TestConnectionDAO extends ConnDB {

    private ResultSet resultSet;

    public ArrayList<TestConnectionVO> testConnectionList() {
        ArrayList<TestConnectionVO> list = new ArrayList<>();

        try {
            connDB();

            String query = "SELECT * FROM user";
            resultSet = getStatement().executeQuery(query);

            while (resultSet.next()) {

                int userPrimaryKey = resultSet.getInt("user_primaryKey");
                String userID = resultSet.getString("user_id");
                String userPW = resultSet.getString("user_pw");

                TestConnectionVO data = new TestConnectionVO(userPrimaryKey, userID, userPW);
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
        }

        return list;
    }
}
