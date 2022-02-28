package application;

import databaseClass.tableModel.ConstraintModelVO;
import databaseClass.tableModel.TableModelDAO;
import databaseClass.tableModel.TableModelVO;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;

import java.util.List;
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

        // 현재 로그인 된 유저의 정보를 가져와서
        // TableModelDAO 애 던진다.

        TableModelDAO tableModelDAO = new TableModelDAO();
        List<TableModelVO> tableModelVOS;

        ConstraintModelVO tableControllerConstraintModelVO = getStaticModelConstraintModelVO();
        tableModelVOS = tableModelDAO.initialTableList(tableControllerConstraintModelVO);


        tradeId.setCellValueFactory(new PropertyValueFactory<TableModelVO, Integer>("tradeID"));
        apartGroup.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("apartGroup"));
        addressRoad.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("addressRoad"));
        addressDetailed.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("addressDetailed"));
        price.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("price"));
        area.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("area"));
        constructionYear.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("constructionYear"));
        floor.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("floor"));
        contractDate.setCellValueFactory(new PropertyValueFactory<TableModelVO, String>("contractDate"));
        
        ObservableList<TableModelVO> observableTableList = tableView.getItems();

        for (TableModelVO data : tableModelVOS) {
            observableTableList.add(data);
            tableView.setItems(observableTableList);
        }

        // search filter
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
                    } else if (tableModelVO.getArea().toLowerCase().contains(searchKeyword)) {
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
