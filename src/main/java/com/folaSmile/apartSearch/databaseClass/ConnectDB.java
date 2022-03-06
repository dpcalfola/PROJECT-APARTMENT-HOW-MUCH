package com.folaSmile.apartSearch.databaseClass;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectDB {

    Env env = new Env();
    EnvVO envVO = env.getEnvData(2);

    String url = envVO.url;
    String user = envVO.user;
    String password = envVO.password;

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
