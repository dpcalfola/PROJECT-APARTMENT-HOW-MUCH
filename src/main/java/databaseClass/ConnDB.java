package databaseClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {

    String url = "jdbc:mysql://localhost:3306/real_estate_kor";
    String user = "fola";
    String password = "123456789";

    public Connection conn;
    private Statement statement;

    public Statement getStatement() {
        return statement;
    }


    // 여기서 데이터베이스 닫을 경우 다른 곳에서 사용 못함
    // 사용하는 클래스에서 닫을 것
    public void connDB() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected with MySQL database successfully");
            statement = conn.createStatement();
            System.out.println("Create Statement Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
