package com.folaSmile.apartSearch.databaseModel.tableModel;

public class ConstraintModelVO {

    private String constraintKeyword;
    private String constraintMinPrice, constraintMaxPrice, constraintMinArea, constraintMaxArea,
            constraintMinContractDate, constraintMaxContractDate, constraintMinConstructYear,
            constraintMaxConstructYear, constraintMinFloor, constraintMaxFloor;

    public ConstraintModelVO(String constraintKeyword, String constraintMinPrice, String constraintMaxPrice, String constraintMinArea,
                             String constraintMaxArea, String constraintMinContractDate, String constraintMaxContractDate, String constraintMinConstructYear,
                             String constraintMaxConstructYear, String constraintMinFloor, String constraintMaxFloor) {
        this.constraintKeyword = constraintKeyword;
        this.constraintMinPrice = constraintMinPrice;
        this.constraintMaxPrice = constraintMaxPrice;
        this.constraintMinArea = constraintMinArea;
        this.constraintMaxArea = constraintMaxArea;
        this.constraintMinContractDate = constraintMinContractDate;
        this.constraintMaxContractDate = constraintMaxContractDate;
        this.constraintMinConstructYear = constraintMinConstructYear;
        this.constraintMaxConstructYear = constraintMaxConstructYear;
        this.constraintMinFloor = constraintMinFloor;
        this.constraintMaxFloor = constraintMaxFloor;
    }

    public String getConstraintKeyword() {
        return constraintKeyword;
    }

    public String getConstraintMinPrice() {
        return constraintMinPrice;
    }

    public String getConstraintMaxPrice() {
        return constraintMaxPrice;
    }

    public String getConstraintMinArea() {
        return constraintMinArea;
    }

    public String getConstraintMaxArea() {
        return constraintMaxArea;
    }

    public String getConstraintMinContractDate() {
        return constraintMinContractDate;
    }

    public String getConstraintMaxContractDate() {
        return constraintMaxContractDate;
    }

    public String getConstraintMinConstructYear() {
        return constraintMinConstructYear;
    }

    public String getConstraintMaxConstructYear() {
        return constraintMaxConstructYear;
    }

    public String getConstraintMinFloor() {
        return constraintMinFloor;
    }

    public String getConstraintMaxFloor() {
        return constraintMaxFloor;
    }
}
