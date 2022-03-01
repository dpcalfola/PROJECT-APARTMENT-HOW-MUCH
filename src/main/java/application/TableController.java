package application;

import databaseClass.tableModel.ConstraintModelVO;
import databaseClass.tableModel.InsertBookmarkDAO;
import databaseClass.tableModel.TableModelDAO;
import databaseClass.tableModel.TableModelVO;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;


public class TableController extends PrimaryModel implements Initializable {


    // static field
    // if there is no select this field value is 0
    private static int selectedBookmarkId;


    // table column property
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


    // Button id
    @FXML
    private Button insertBookmarkButton;

    @FXML
    private Button deleteBookmarkButton;

    // Handle button action
    @FXML
    private void handleInsertBookmarkButtonAction(ActionEvent event) throws IOException {
        System.out.println("insert button clicked");

        // If no select -> escape this method
        if (selectedBookmarkId == 0) {
            return;
        }

        // Connect database start
        InsertBookmarkDAO insertDAO = new InsertBookmarkDAO();
        boolean isInsertSucceed = insertDAO.insertBookmark(isOnBookmark(), getLoggedInUserKey(), selectedBookmarkId);


        // Set status display message
        String message = "";
        if (isInsertSucceed) {
            message = getLoggedInUserID() + "님 계정에" + selectedBookmarkId + "번 거래가 북마크에 담겼습니다.";
        } else {
            message = "북마크 담기가 실패했습니다.";

        }
        setStatusDisplayText(message);

    }

    @FXML
    private void handleDeleteBookmarkButtonAction(ActionEvent event) throws IOException {
        System.out.println("delete button clicked");

    }


    // table-subView initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // test code
        System.out.println("selectedBookmark value: " + selectedBookmarkId);

        int userKey = getLoggedInUserKey();
        boolean onBookmark = isOnBookmark();


        // Button activate/deactivate
        if (onBookmark) {
            // Bookmark search mode ->
            // insertBookmarkButton deactivate,
            insertBookmarkButton.setOpacity(0.5);
            insertBookmarkButton.setDisable(true);
            // deleteBookmarkButton activate
            deleteBookmarkButton.setOpacity(1);
            deleteBookmarkButton.setDisable(false);

        } else {
            // Whole search mode ->
            // insertBookmarkButton activate,
            insertBookmarkButton.setOpacity(1);
            insertBookmarkButton.setDisable(false);
            // deleteBookmarkButton deactivate
            deleteBookmarkButton.setOpacity(0.5);
            deleteBookmarkButton.setDisable(true);
        }


        // ----- START: Get data from MySQL -----

        TableModelDAO tableModelDAO = new TableModelDAO();
        List<TableModelVO> tableModelVOS;
        int caseCounter = 0;

        ConstraintModelVO tableControllerConstraintModelVO = getStaticModelConstraintModelVO();


        // Throw constraintModelVO, userKey, onBookmark  to TableModelDAO
        tableModelVOS = tableModelDAO.initialTableList(tableControllerConstraintModelVO, userKey, onBookmark);


        // ----- END: Get data from MySQL -----


        // .setCellValueFactory()
        tableViewSetCellValueFactory();

        // Show data on center pane
        ObservableList<TableModelVO> observableTableList = tableView.getItems();

        for (TableModelVO data : tableModelVOS) {
            observableTableList.add(data);
            tableView.setItems(observableTableList);
            caseCounter++;
        }


        // Search filter
        searchFilter(observableTableList);

        // Set status display
        String massage;
        if (onBookmark) {
            massage = String.format("북마크 검색: %d 건의 결과를 찾았습니다.", caseCounter);
        } else {
            massage = String.format("전체 검색: %d 건의 결과를 찾았습니다.", caseCounter);
        }
        setStatusDisplayText(massage);


        // get selected row trade id and put into static field (bookmarkId)
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                TableModelVO selectedList = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
                selectedBookmarkId = selectedList.getTradeID();
                System.out.println(selectedBookmarkId);
            }
        });


    }

    private void tableViewSetCellValueFactory() {
        tradeId.setCellValueFactory(new PropertyValueFactory<>("tradeID"));
        apartGroup.setCellValueFactory(new PropertyValueFactory<>("apartGroup"));
        addressRoad.setCellValueFactory(new PropertyValueFactory<>("addressRoad"));
        addressDetailed.setCellValueFactory(new PropertyValueFactory<>("addressDetailed"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        area.setCellValueFactory(new PropertyValueFactory<>("area"));
        constructionYear.setCellValueFactory(new PropertyValueFactory<>("constructionYear"));
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        contractDate.setCellValueFactory(new PropertyValueFactory<>("contractDate"));
    }


    // Copied code from YouTube :)
    private void searchFilter(ObservableList<TableModelVO> observableTableList) {

        if (observableTableList != null) {

            FilteredList<TableModelVO> filteredData = new FilteredList<>(observableTableList, b -> true);

            // get text from search text field on tap pane
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

    // static value getter
    public static int getSelectedBookmarkId() {
        return selectedBookmarkId;
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


    // Button Id getter
    public Button getInsertBookmarkButton() {
        return insertBookmarkButton;
    }

    public Button getDeleteBookmarkButton() {
        return deleteBookmarkButton;
    }
}
