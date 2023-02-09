package com.folaSmile.apartSearch.databaseModel;


import com.folaSmile.apartSearch.env.Env;
import com.folaSmile.apartSearch.env.EnvVO;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectDB {

    Env env = new Env();
    EnvVO envVO = env.getEnvData(3);

    String url = envVO.getUrl();
    String user = envVO.getUser();
    String password = envVO.getPassword();

    public Connection conn;


    // 여기서 데이터베이스 닫을 경우 다른 곳에서 사용 못함
    // 사용하는 클래스에서 닫을 것
    public void connDB() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
