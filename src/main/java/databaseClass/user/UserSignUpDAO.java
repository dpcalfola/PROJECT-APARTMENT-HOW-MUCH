package databaseClass.user;

import java.sql.*;

public class UserSignUpDAO {

    final String url = "jdbc:mysql://localhost:3306/real_estate_kor";
    final String user = "fola";
    final String password = "123456789";

    public UserVO SignUpUser(String userID, String userPW) {

        Connection connection = null;
        PreparedStatement signUpQuery = null;
        ResultSet resultSet = null;

        UserVO userInfo = new UserVO(0, "initial value", "initial value", false);


        try {
            connection = DriverManager.getConnection(url, user, password);
            signUpQuery = connection.prepareStatement("INSERT INTO user (user_id, user_pw)" +
                    "VALUES (?,?)");
            signUpQuery.setString(1, userID);
            signUpQuery.setString(2, userPW);

            signUpQuery.execute();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (signUpQuery != null) {
                try {
                    signUpQuery.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return userInfo;
    }
}
