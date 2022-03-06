package com.folaSmile.apartSearch.databaseClass.databaseTest;

import java.util.List;

public class Test1 {

    public void testConnect() {
        Test1DAO dao = new Test1DAO();
        List<Test1VO> list = dao.testConnectionList();

        for (int i = 0; i < list.size(); i++) {
            Test1VO data = list.get(i);
            int userPrimaryKey = data.getUserPrimaryKey();
            String userID = data.getUserID();
            String userPW = data.getUserPW();

            System.out.printf("%d, %s, %s\n", userPrimaryKey, userID, userPW);

        }
    }
}
