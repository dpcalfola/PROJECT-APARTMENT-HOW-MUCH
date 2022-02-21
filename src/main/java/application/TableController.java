package application;

import databaseClass.table.ConstraintModelVO;
import databaseClass.table.TableModelDAO;
import databaseClass.table.TableModelVO;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableController extends PrimaryModel implements Initializable {

    @FXML
    private TableView<TableModelVO> tableView;
    @FXML
    private TableColumn<TableModelVO, Integer> tradeId;
    @FXML
    private TableColumn<TableModelVO, String> apartGroup;
    @FXML
    private TableColumn<TableModelVO, String> addressRoad;
    @FXML
    private TableColumn<TableModelVO, String> addressDetailed;
    @FXML
    private TableColumn<TableModelVO, String> price;
    @FXML
    private TableColumn<TableModelVO, String> area;
    @FXML
    private TableColumn<TableModelVO, String> constructionYear;
    @FXML
    private TableColumn<TableModelVO, String> floor;
    @FXML
    private TableColumn<TableModelVO, String> contractDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tradeId.setCellValueFactory(new PropertyValueFactory<TableModelVO, Integer>("tradeID"));
        apartGroup.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("apartGroup"));
        addressRoad.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("addressRoad"));
        addressDetailed.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("addressDetailed"));
        price.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("price"));
        area.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("area"));
        constructionYear.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("constructionYear"));
        floor.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("floor"));
        contractDate.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("contractDate"));

        TableModelDAO tableModelDAO = new TableModelDAO();
        ArrayList<TableModelVO> tableModelVOS;

        ConstraintModelVO tableControllerConstraintModelVO = getPrimaryModelConstraintModelVO();
        tableModelVOS = tableModelDAO.initialTableList(tableControllerConstraintModelVO);
        

        //test Code 3 
        String testCode3 = tableControllerConstraintModelVO.getConstraintKeyword();
        System.out.println("testCode3: " + testCode3);
        System.out.println("TableController : " + tableControllerConstraintModelVO.getConstraintKeyword());

        // draw table start
        ObservableList<TableModelVO> observableTableList = tableView.getItems();

        for (int i = 0; i < tableModelVOS.size(); i++) {
            TableModelVO data = tableModelVOS.get(i);
            observableTableList.add(data);
            tableView.setItems(observableTableList);
        }

        if (observableTableList != null) {

            FilteredList<TableModelVO> filteredData = new FilteredList<>(observableTableList, b -> true);
            getSearchTextField().textProperty().addListener((observable, oldValue, newValue) -> {

                filteredData.setPredicate(tableModelVO -> {

                    // if no value on searchTextField, no changes
                    if (newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();


                    if (tableModelVO.getApartGroup().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (tableModelVO.getAddressDetailed().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (tableModelVO.getAddressRoad().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (tableModelVO.getConstructionYear().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (tableModelVO.getContractDate().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (tableModelVO.getFloor().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else if (tableModelVO.getPrice().toLowerCase().replace(",", "").contains(searchKeyword)) {
                        return true; // it helps find price without "," keyword
                    } else if (tableModelVO.getPrice().toLowerCase().contains(searchKeyword)) {
                        return true;
                    } else {
                        // no match found
                        return false;
                    }
                });
            });

            SortedList<TableModelVO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);
        }


    }

    // TableColumn getter
    public TableColumn<TableModelVO, Integer> getTradeId() {
        return tradeId;
    }

    public TableColumn<TableModelVO, String> getApartGroup() {
        return apartGroup;
    }

    public TableColumn<TableModelVO, String> getAddressRoad() {
        return addressRoad;
    }

    public TableColumn<TableModelVO, String> getAddressDetailed() {
        return addressDetailed;
    }

    public TableColumn<TableModelVO, String> getPrice() {
        return price;
    }

    public TableColumn<TableModelVO, String> getArea() {
        return area;
    }

    public TableColumn<TableModelVO, String> getConstructionYear() {
        return constructionYear;
    }

    public TableColumn<TableModelVO, String> getFloor() {
        return floor;
    }

    public TableColumn<TableModelVO, String> getContractDate() {
        return contractDate;
    }

}
