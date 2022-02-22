package databaseClass.table;

import databaseClass.ConnDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


// DAO == Data Access Object
// VO == Value Object

public class TableModelDAO extends ConnDB {

    // make TableModelVo list
    public ArrayList<TableModelVO> initialTableList(ConstraintModelVO constraintModelVO) {

        // base query
        String query = "SELECT " + "trade_id, apart_group, address_road, address_detailed, " +
                "FORMAT(trade_price_10000won, 0) AS trade_price_10000won, " +
                "area_mSquare, construction_year, floor, " +
                "contract_year_month, LPAD(contract_date, 2, 0) " +
                "FROM apartment_price ";


        // element of constraints area start
        String keyword = constraintModelVO.getConstraintKeyword();
        String minPrice = constraintModelVO.getConstraintMinPrice();
        String maxPrice = constraintModelVO.getConstraintMaxPrice();
        String minArea = constraintModelVO.getConstraintMinArea();
        String maxArea = constraintModelVO.getConstraintMaxArea();
        String minContractDate = constraintModelVO.getConstraintMinContractDate();
        String maxContractDate = constraintModelVO.getConstraintMaxContractDate();
        String minConstructYear = constraintModelVO.getConstraintMinConstructYear();
        String maxConstructYear = constraintModelVO.getConstraintMaxConstructYear();
        String minFloor = constraintModelVO.getConstraintMinFloor();
        String maxFloor = constraintModelVO.getConstraintMaxFloor();


        // if all constraint TextField are empty, do not add "WHERE"
        if (!keyword.isEmpty() || !minPrice.isEmpty() || !maxPrice.isEmpty() ||
                !minArea.isEmpty() || !maxArea.isEmpty() ||
                !minContractDate.isEmpty() || !maxContractDate.isEmpty() ||
                !minConstructYear.isEmpty() || !maxConstructYear.isEmpty() ||
                !minFloor.isEmpty() || !maxFloor.isEmpty()
        ) {
            query += " WHERE ";
        }


        // if keyword is exsist
        if (!keyword.isEmpty()) {
            // test code 5
            System.out.println("test code 5 : keyword is not empty");

            // %% mean % literal in String.format
            String keywordQuery = String.format("""
                            ( address_detailed LIKE '%%%s%%'
                            OR apart_group LIKE '%%%s%%'
                            OR trade_price_10000won LIKE '%%%s%%'
                            OR construction_year LIKE '%%%s%%'
                            OR address_road LIKE '%%%s%%'
                            OR expire_date LIKE '%%%s%%'
                            OR floor LIKE '%s'
                            OR agency_region LIKE '%%%s%%' )
                            """
                    , keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword);

            query += keywordQuery;
        }


        // get item order by latest contract date
        String queryOrderBy = "ORDER BY contract_year_month + contract_date DESC ";
        // limit getting number of items at once
        String queryLimit = "LIMIT 5000";

        query += queryOrderBy;
        query += queryLimit;

        // test code 6
        System.out.println("test code 5 : query  - " + query);

        // call database conn method (parmeter : query)
        return getTableList(query);
    }


    // get items from database
    private ArrayList<TableModelVO> getTableList(String query) {

        ArrayList<TableModelVO> list = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement getTableStmt = null;

        try {
            connDB();

            getTableStmt = conn.prepareStatement(query);

            resultSet = getTableStmt.executeQuery();

            while (resultSet.next()) {
                Integer tradeID = resultSet.getInt("trade_id");
                String apartGroup = resultSet.getString("apart_group");
                String addressRoad = resultSet.getString("address_road");
                String addressDetailed = resultSet.getString("address_detailed");
                String price = resultSet.getString("trade_price_10000won");
                String area = resultSet.getString("area_mSquare");
                String constructionYear = resultSet.getString("construction_year");
                String floor = resultSet.getString("floor");
                String contractDate = resultSet.getString("contract_year_month") + resultSet.getString("LPAD(contract_date, 2, 0)");
                TableModelVO data = new TableModelVO(tradeID, apartGroup, addressRoad, addressDetailed, price, area, constructionYear, floor, contractDate);

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
            if (getTableStmt != null) {
                try {
                    getTableStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return list;
    }


}
