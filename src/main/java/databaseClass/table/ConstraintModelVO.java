package databaseClass.table;

public class ConstraintModelVO {

    private String constraintKeyword;
    private Integer constraintMinPrice, constraintMaxPrice, constraintMinArea, constraintMaxArea,
            constraintMinContractDate, constraintMaxContractDate, constraintMinConstructYear,
            constraintMaxConstructYear, constraintMinFloor, constraintMaxFloor;


    public ConstraintModelVO(String constraintKeyword) {
        this.constraintKeyword = constraintKeyword;
    }

    public String getConstraintKeyword() {
        return constraintKeyword;
    }
}
