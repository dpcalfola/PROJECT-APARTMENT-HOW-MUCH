package databaseClass.tableModel;

import databaseClass.ConnDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// DAO == Data Access Object
// VO == Value Object

public class TableModelDAO extends ConnDB {

    // make TableModelVo list
    public List<TableModelVO> initialTableList(ConstraintModelVO constraintModelVO, int userKey) {

        // select query
        String query = """ 
                SELECT
                trade_id,
                apart_group,
                address_road,
                address_detailed,
                FORMAT (trade_price_10000won, 0) AS trade_price_10000won,
                area_mSquare,
                contract_year_month*100 + contract_date AS contract_date,
                floor,
                construction_year
                FROM apartment_price
                """;

        String fromQuery;

        //---- START - element of constraints area 
        String keyword = constraintModelVO.getConstraintKeyword();
        String minPrice = constraintModelVO.getConstraintMinPrice();
        String maxPrice = constraintModelVO.getConstraintMaxPrice();
        String minArea = constraintModelVO.getConstraintMinArea();
        String maxArea = constraintModelVO.getConstraintMaxArea();
        String minContractYearMonthDate = constraintModelVO.getConstraintMinContractDate();
        String maxContractYearMonthDate = constraintModelVO.getConstraintMaxContractDate();
        String minConstructYear = constraintModelVO.getConstraintMinConstructYear();
        String maxConstructYear = constraintModelVO.getConstraintMaxConstructYear();
        String minFloor = constraintModelVO.getConstraintMinFloor();
        String maxFloor = constraintModelVO.getConstraintMaxFloor();
        //---- END - element of constraints area


        // If all constraint TextField are empty 
        // -> isConstraintEmpty = true
        // -> Skip WHERE clause 
        boolean isConstraintEmpty = keyword.isEmpty()
                && minPrice.isEmpty()
                && maxPrice.isEmpty()
                && minArea.isEmpty()
                && maxArea.isEmpty()
                && minContractYearMonthDate.isEmpty()
                && maxContractYearMonthDate.isEmpty()
                && minConstructYear.isEmpty()
                && maxConstructYear.isEmpty()
                && minFloor.isEmpty()
                && maxFloor.isEmpty();


        //---- Start of the WHERE clause ----//
        //---- Start of the WHERE clause ----//

        if (!isConstraintEmpty) {
            query += " WHERE ";
        }

        //
        // Keyword
        if (!keyword.isEmpty()) {

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
                    AND
                    """, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword);
            query += keywordQuery;

        }

        // Price
        if (!minPrice.isEmpty()) {
            String minPriceQuery = String.format("trade_price_10000won >= %s\n" + "AND ", minPrice);
            query += minPriceQuery;
        }
        if (!maxPrice.isEmpty()) {
            String maxPriceQuery = String.format("trade_price_10000won <= %s\n" + "AND ", maxPrice);
            query += maxPriceQuery;
        }

        // Area
        if (!minArea.isEmpty()) {
            String minAreaQuery = String.format("area_mSquare >= %s\n" + "AND ", minArea);
            query += minAreaQuery;
        }
        if (!maxArea.isEmpty()) {
            String maxAreaQuery = String.format("area_mSquare <= %s\n" + "AND ", maxArea);
            query += maxAreaQuery;
        }


        // if length of minContractDate are not 6 digit ??
        // -> handle by MainController

        // ContractDate
        if (!minContractYearMonthDate.isEmpty()) {
            String minContractYearMonthDateQuery =
                    String.format("contract_year_month*100 + apartment_price.contract_date >= %s\n" + "AND ",
                            minContractYearMonthDate);
            query += minContractYearMonthDateQuery;
        }
        if (!maxContractYearMonthDate.isEmpty()) {
            String maxContractYearMonthDateQuery =
                    String.format("contract_year_month*100 + apartment_price.contract_date <= %s\n" + "AND ",
                            maxContractYearMonthDate);
            query += maxContractYearMonthDateQuery;
        }

        // ConstructYear
        if (!minConstructYear.isEmpty()) {
            String minConstructYearQuery = String.format("construction_year >= %s\n" + "AND ", minConstructYear);
            query += minConstructYearQuery;
        }
        if (!maxConstructYear.isEmpty()) {
            String maxConstructYearQuery = String.format("construction_year <= %s\n" + "AND ", maxConstructYear);
            query += maxConstructYearQuery;
        }

        // Floor
        if (!minFloor.isEmpty()) {
            String minFloorQuery = String.format("floor >= %s\n" + "AND ", minFloor);
            query += minFloorQuery;
        }
        if (!maxFloor.isEmpty()) {
            String maxFloorQuery = String.format("floor <= %s\n" + "AND ", maxFloor);
            query += maxFloorQuery;
        }


        // remove suffix "AND " - if WHERE clause exsist 
        if (!isConstraintEmpty) {
            query = query.substring(0, query.lastIndexOf("AND"));
        }

        //---- End of the WHERE clause ----//
        //---- End of the WHERE clause ----//


        // criteria for sorting
        String queryOrderBy = "ORDER BY apart_group ";
        // limit getting number of items at once
        String queryLimit = "LIMIT 5000";

        query += queryOrderBy;
        query += queryLimit;

        // test code 6 : whole query
        System.out.println("test code 5 : query start \n " + query + "\n query end");


        // We got whole query eventually
        // SO now, call database conn method (parameter : query)
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
                String contractDate = resultSet.getString("contract_date");
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
