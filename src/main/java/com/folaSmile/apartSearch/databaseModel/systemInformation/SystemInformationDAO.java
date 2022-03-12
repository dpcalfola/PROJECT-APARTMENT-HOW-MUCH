package com.folaSmile.apartSearch.databaseModel.systemInformation;

import com.folaSmile.apartSearch.databaseModel.ConnectDB;

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
                                        
                    select sys_info_key, database_ver, database_range, sys_notice_pk, system_notice.notice, database_case
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
                // sysNoticePK == 1 -> use inner notice text
                // sysNoticePK != 1 -> use database notice
                String getSysNoticePK = resultSet.getString("sys_notice_pk");
                String getNotice = resultSet.getString("system_notice.notice");
                int getDataCase = resultSet.getInt("database_case");

                systemInfo = new SystemInformationVO(getDatabaseVer, getDatabaseRange, getDataCase, getSysNoticePK,
                        getNotice);
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
