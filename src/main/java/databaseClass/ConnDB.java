package databaseClass;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnDB {


    String url = externalUrl;
    String user = externalUser;
    String password = externalPassword;

    public Connection conn;


    // 여기서 데이터베이스 닫을 경우 다른 곳에서 사용 못함
    // 사용하는 클래스에서 닫을 것
    public void connDB() {
        try {

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected with MySQL database successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
