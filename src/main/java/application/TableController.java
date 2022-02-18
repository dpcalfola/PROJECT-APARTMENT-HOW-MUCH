package application;

import databaseClass.transactionTable.TableDAO;
import databaseClass.transactionTable.TableVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private TableView<TableVO> tableView;
    @FXML
    private TableColumn<TableVO, Integer> tradeId;
    @FXML
    private TableColumn<TableVO, String> apartGroup;
    @FXML
    private TableColumn<TableVO, String> addressRoad;
    @FXML
    private TableColumn<TableVO, String> addressDetailed;
    @FXML
    private TableColumn<TableVO, String> price;
    @FXML
    private TableColumn<TableVO, String> area;
    @FXML
    private TableColumn<TableVO, String> constructionYear;
    @FXML
    private TableColumn<TableVO, String> floor;
    @FXML
    private TableColumn<TableVO, String> contractDate;
    ObservableList<TableVO> listM;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        tradeId.setCellValueFactory(new PropertyValueFactory<TableVO, Integer>("tradeID"));
        apartGroup.setCellValueFactory(new PropertyValueFactory<TableVO, String>("apartGroup"));
        addressRoad.setCellValueFactory(new PropertyValueFactory<TableVO, String>("addressRoad"));
        addressDetailed.setCellValueFactory(new PropertyValueFactory<TableVO, String>("addressDetailed"));
        price.setCellValueFactory(new PropertyValueFactory<TableVO, String>("price"));
        area.setCellValueFactory(new PropertyValueFactory<TableVO, String>("area"));
        constructionYear.setCellValueFactory(new PropertyValueFactory<TableVO, String>("constructionYear"));
        floor.setCellValueFactory(new PropertyValueFactory<TableVO, String>("floor"));
        contractDate.setCellValueFactory(new PropertyValueFactory<TableVO, String>("contractDate"));

        TableDAO tableDAO = new TableDAO();
        ArrayList<TableVO> tableVOS = tableDAO.tableList();

        for (int i = 0; i < tableVOS.size(); i++) {
            TableVO data = tableVOS.get(i);
            ObservableList<TableVO> observableList = tableView.getItems();
            observableList.add(data);
            tableView.setItems(observableList);
        }





    }

    public TableColumn<TableVO, Integer> getTradeId() {
        return tradeId;
    }

    public TableColumn<TableVO, String> getApartGroup() {
        return apartGroup;
    }

    public TableColumn<TableVO, String> getAddressRoad() {
        return addressRoad;
    }

    public TableColumn<TableVO, String> getAddressDetailed() {
        return addressDetailed;
    }

    public TableColumn<TableVO, String> getPrice() {
        return price;
    }

    public TableColumn<TableVO, String> getArea() {
        return area;
    }

    public TableColumn<TableVO, String> getConstructionYear() {
        return constructionYear;
    }

    public TableColumn<TableVO, String> getFloor() {
        return floor;
    }

    public TableColumn<TableVO, String> getContractDate() {
        return contractDate;
    }


}
