package databaseClass.testConnection;

import java.util.ArrayList;

public class TestConnection {

    public void testConnect() {
        TestConnectionDAO dao = new TestConnectionDAO();
        ArrayList<TestConnectionVO> list = dao.testConnectionList();

        for (int i = 0; i < list.size(); i++) {
            TestConnectionVO data = list.get(i);
            int user_primaryKey = data.getUserPrimaryKey();
            String user_id = data.getUserID();
            String user_pw = data.getUserPW();

            System.out.printf("%d, %s, %s\n", user_primaryKey, user_id, user_pw);

        }
    }
}
