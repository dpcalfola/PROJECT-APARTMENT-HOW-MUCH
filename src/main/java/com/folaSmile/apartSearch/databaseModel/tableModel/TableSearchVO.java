package com.folaSmile.apartSearch.databaseModel.tableModel;

public class TableSearchVO {

    // need refactoring : change type String to Integer : constructionYear, floor, contractDate
    // need refactoring : change type String to double : area
    // check up whether price data possible to change type String to Integer because it has comma
    private Integer tradeID;
    private String apartGroup, addressRoad, addressDetailed, price, area, constructionYear, floor, contractDate;

    //constructor
    public TableSearchVO(Integer tradeID, String apartGroup, String addressRoad, String addressDetailed, String price,
                         String area, String constructionYear, String floor, String contractDate) {

        // insert '-' into contract date
        if (contractDate.length() == 8) {
            contractDate = contractDate.substring(0, 4) + "-" + contractDate.substring(4, 6) + "-" + contractDate.substring(6);
        }

        this.tradeID = tradeID;
        this.apartGroup = apartGroup;
        this.addressRoad = addressRoad;
        this.addressDetailed = addressDetailed;
        this.price = price;
        this.area = area;
        this.constructionYear = constructionYear;
        this.floor = floor;
        this.contractDate = contractDate;
    }

    //getter
    public int getTradeID() {
        return tradeID;
    }

    public String getApartGroup() {
        return apartGroup;
    }

    public String getAddressRoad() {
        return addressRoad;
    }

    public String getAddressDetailed() {
        return addressDetailed;
    }

    public String getPrice() {
        return price;
    }

    public String getArea() {
        return area;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public String getFloor() {
        return floor;
    }

    public String getContractDate() {
        return contractDate;
    }
}
