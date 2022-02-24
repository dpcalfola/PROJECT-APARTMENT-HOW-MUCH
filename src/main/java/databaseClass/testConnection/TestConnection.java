package databaseClass.testConnection;

import java.util.List;

public class TestConnection {

    public void testConnect() {
        TestConnectionDAO dao = new TestConnectionDAO();
        List<TestConnectionVO> list = dao.testConnectionList();

        for (int i = 0; i < list.size(); i++) {
            TestConnectionVO data = list.get(i);
            int userPrimaryKey = data.getUserPrimaryKey();
            String userID = data.getUserID();
            String userPW = data.getUserPW();

            System.out.printf("%d, %s, %s\n", userPrimaryKey, userID, userPW);

        }
    }
}
