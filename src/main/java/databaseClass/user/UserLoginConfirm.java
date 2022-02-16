package databaseClass.user;

public class UserLoginConfirm {

    public void confirmUser() {
        // 입력값을 데이터베이스와 비교하여 null 혹은 userInfo 를 받는 코드
        UserLoginDAO dao = new UserLoginDAO();
        UserVO userInfo = dao.loginUser("ezen1", "1234");

//        System.out.println("Confirm user ID: " + userInfo.getUserID());
//        System.out.println("Confirm user Pw: " + userInfo.getUserPW());
    }
}
