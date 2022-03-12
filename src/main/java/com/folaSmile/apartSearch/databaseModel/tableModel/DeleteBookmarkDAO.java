package com.folaSmile.apartSearch.databaseModel.tableModel;

import com.folaSmile.apartSearch.databaseModel.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBookmarkDAO extends ConnectDB {

    public boolean deleteBookmark(boolean onBookmark, int loggedInUserKey, int tradeId) {

        // check status
        if (!onBookmark) {
            System.out.println("Cannot delete bookmark when bookmark search mode off");
            return false;
        }
        if (loggedInUserKey == -1) {
            System.out.println("Guest user cannot delete bookmark");
            return false;
        }

        // delete query
        PreparedStatement deleteBookmarkQuery = null;
        boolean isSucceed = false;


        try {
            connDB();
            deleteBookmarkQuery = conn.prepareStatement("""
                    DELETE
                    FROM bookmark
                    WHERE bookmark_user_key = ?
                    AND bookmark_trade_id = ?
                    """);
            deleteBookmarkQuery.setString(1, loggedInUserKey + " ");
            deleteBookmarkQuery.setString(2, tradeId + " ");

            deleteBookmarkQuery.execute();
            isSucceed = true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (deleteBookmarkQuery != null) {
                try {
                    deleteBookmarkQuery.close();
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
