package databaseClass;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnDB {


    // Inner database
    final String localUrl = "jdbc:mysql://localhost:3306/real_estate_kor";
    final String localUser = "fola";
    final String localPassword = "123456789";

    // External NAS sever docker database
    final String externalUrl = "jdbc:mysql://folaflor.site:61923/real_estate_kor_docker";
    final String externalUser = "fola1";
    final String externalPassword = "fola4321";


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
