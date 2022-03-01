package databaseClass.tableModel;


import databaseClass.ConnDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBookmarkDAO extends ConnDB {

    public boolean insertBookmark(boolean onBookmark, int loggedInUserKey, int tradeId) {

        // Check status
        if (onBookmark) {
            System.out.println("Cannot insert bookmark when bookmark search mode on");
            return false;
        }
        if (loggedInUserKey == -1) {
            System.out.println("Guest user cannot save bookmark");
            return false;
        }


        // Insert query

        PreparedStatement insertBookmarkQuery = null;
        boolean isSucceed = false;


        try {
            connDB();
            insertBookmarkQuery = conn.prepareStatement("INSERT INTO bookmark (bookmark_user_key, bookmark_trade_id)\n" +
                    "VALUES (?, ?)");
            insertBookmarkQuery.setString(1, loggedInUserKey + " ");
            insertBookmarkQuery.setString(2, tradeId + " ");

            insertBookmarkQuery.execute();
            isSucceed = true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (insertBookmarkQuery != null) {
                try {
                    insertBookmarkQuery.close();
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


        return isSucceed;
    }

}

