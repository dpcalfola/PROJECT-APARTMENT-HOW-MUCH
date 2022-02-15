package databaseClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnDB {

    String url = "jdbc:mysql://localhost:3306/real_estate_kor";
    //    String url = "jdbc:mysql://localhost:3306/project_Acafola";
    String user = "fola";
    String password = "123456789";

    private Connection conn;
    private Statement statement;

    public Statement getStatement() {
        return statement;
    }

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
