package com.folaSmile.apartSearch.databaseModel.connectionLog;

import com.folaSmile.apartSearch.databaseModel.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertLogDAO extends ConnectDB {

    public boolean insertLog(String clientVer) {

        PreparedStatement insertLogQuery = null;

        try {

            connDB();

            insertLogQuery = conn.prepareStatement("""
                    INSERT INTO connect_log (client_ver, connect_time) VALUES (?,NOW())
                     """);

            insertLogQuery.setString(1, clientVer);
            insertLogQuery.execute();

            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (insertLogQuery != null) {
                try {
                    insertLogQuery.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
