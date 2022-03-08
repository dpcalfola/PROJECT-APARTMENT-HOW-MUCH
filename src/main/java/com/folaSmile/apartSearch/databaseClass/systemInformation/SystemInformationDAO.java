package com.folaSmile.apartSearch.databaseClass.systemInformation;

import com.folaSmile.apartSearch.databaseClass.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemInformationDAO extends ConnectDB {


    public SystemInformationVO getSystemInfo() {

        ResultSet resultSet = null;
        PreparedStatement systemInfoStatement = null;
        SystemInformationVO systemInfo = null;


        try {

            connDB();

            systemInfoStatement = conn.prepareStatement("""
                                        
                    select sys_info_key, database_ver, database_range, sys_notice_pk, system_notice.notice
                    from system_information
                    left join system_notice on sys_notice_pk = system_information.sys_notice_fk
                    ORDER BY sys_info_key DESC
                    LIMIT 1;
                                        
                    ;

                    """);


            resultSet = systemInfoStatement.executeQuery();


            if (resultSet.next()) {
                String sysInfoKey = resultSet.getString("sys_info_key");
                String getDatabaseVer = resultSet.getString("database_ver");
                String getDatabaseRange = resultSet.getString("database_range");
                String getSysNoticePK = resultSet.getString("sys_notice_pk");
                String getNotice = resultSet.getString("system_notice.notice");

                systemInfo = new SystemInformationVO(getDatabaseVer, getDatabaseRange, getSysNoticePK, getNotice);
            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (systemInfoStatement != null) {
                try {
                    systemInfoStatement.close();
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


        return systemInfo;
    }

}
