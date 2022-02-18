package databaseClass.transactionTable;

import databaseClass.ConnDB;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TableDAO extends ConnDB {


    public ArrayList<TableVO> tableList() {
        ArrayList<TableVO> list = new ArrayList<>();

        ResultSet resultSet = null;
        PreparedStatement getTableStmt = null;

        try {
            connDB();

            getTableStmt = conn.prepareStatement(
                    "SELECT " +
                            "trade_id, apart_group, address_road, address_detailed, trade_price_10000won, " +
                            "`area_m^2`, construction_year, floor, contract_year_month, contract_date " +
                            "FROM apartment_price LIMIT 100"
            );

            resultSet = getTableStmt.executeQuery();

            while (resultSet.next()) {
                Integer tradeID = resultSet.getInt("trade_id");
                String apartGroup = resultSet.getString("apart_group");
                String addressRoad = resultSet.getString("address_road");
                String addressDetailed = resultSet.getString("address_detailed");
                String price = resultSet.getString("trade_price_10000won");
                String area = resultSet.getString("area_m^2");
                String constructionYear = resultSet.getString("construction_year");
                String floor = resultSet.getString("floor");
                String contractDate = resultSet.getString("contract_year_month") + resultSet.getString(
                        "contract_date");
                TableVO data = new TableVO(tradeID, apartGroup, addressRoad, addressDetailed, price, area,
                        constructionYear, floor, contractDate);

                list.add(data);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return list;
    }
}
