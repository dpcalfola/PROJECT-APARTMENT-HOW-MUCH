package testCode;

public class TestCode1 {
    public static void main(String[] args) {

        String str = "20200101";

        // substring YYYYMM
        String result1 = str.substring(0, 6);
        System.out.println("result1 : " + result1);


        String result2 = str.substring(6, 8);
        System.out.println("result2 : " + result2);
    }
}
