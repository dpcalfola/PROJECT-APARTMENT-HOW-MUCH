package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseModel.tableModel.*;
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


public class TableController extends PrimaryController implements Initializable {


    // static field
    // if there is no select this field value is 0
    private static int selectedBookmarkId;


    // table column property
    @FXML
    private TableView<TableSearchVO> tableView;
    @FXML
    private TableColumn<TableSearchVO, Integer> tradeId;
    @FXML
    private TableColumn<TableSearchVO, String> apartGroup;
    @FXML
    private TableColumn<TableSearchVO, String> addressRoad;
    @FXML
    private TableColumn<TableSearchVO, String> addressDetailed;
    @FXML
    private TableColumn<TableSearchVO, String> price;
    @FXML
    private TableColumn<TableSearchVO, String> area;
    @FXML
    private TableColumn<TableSearchVO, String> constructionYear;
    @FXML
    private TableColumn<TableSearchVO, String> floor;
    @FXML
    private TableColumn<TableSearchVO, String> contractDate;


    // Button id
    @FXML
    private Button insertBookmarkButton;

    @FXML
    private Button deleteBookmarkButton;


    // table-subView initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int userKey = getLoggedInUserKey();
        boolean onBookmark = isOnBookmark();

        // Depends on logged-in
        if (getLoggedInUserKey() == -1) {

            // No user logged in
            // -> all bookmark button deactivate

            // insertBookmarkButton deactivate,
            insertBookmarkButton.setOpacity(0.5);
            insertBookmarkButton.setDisable(true);
            // deleteBookmarkButton deactivate
            deleteBookmarkButton.setOpacity(0.5);
            deleteBookmarkButton.setDisable(true);

        } else {

            // If user logged in button activate status
            // depends on search mode
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

        }


        // ----- START: Get data from MySQL -----

        TableSearchDAO tableSearchDAO = new TableSearchDAO();
        List<TableSearchVO> tableSearchVOS;
        int caseCounter = 0;

        ConstraintModelVO tableControllerConstraintModelVO = getStaticModelConstraintModelVO();


        // Throw constraintModelVO, userKey, onBookmark  to TableModelDAO
        tableSearchVOS = tableSearchDAO.initialTableList(tableControllerConstraintModelVO, userKey, onBookmark);


        // ----- END: Get data from MySQL -----


        // .setCellValueFactory()
        tableViewSetCellValueFactory();

        // Show data on center pane
        ObservableList<TableSearchVO> observableTableList = tableView.getItems();

        for (TableSearchVO data : tableSearchVOS) {
            observableTableList.add(data);
            tableView.setItems(observableTableList);
            caseCounter++;
        }


        // Search filter
        searchFilter(observableTableList);

        // Set status display
        String massage;
        if (onBookmark) {
            massage = String.format("북마크 검색: %d 건의 결과를 찾았습니다", caseCounter);
        } else {
            massage = String.format("전체 검색: %d 건의 결과를 찾았습니다", caseCounter);
        }
        setStatusDisplayText(massage);


        // get selected row trade id and put into static field (bookmarkId)
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                // This code make avoid Exception:
                // Exception: (ArrayIndexOutOfBoundsException: Index -1 out of bounds for length... )
                if (tableView.getSelectionModel().getSelectedIndex() == -1) {
                    System.out.println("System : black low clicked");
                    return;
                }

                TableSearchVO selectedList = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
                selectedBookmarkId = selectedList.getTradeID();
                System.out.println(selectedBookmarkId);
            }
        });


    } // Initializing method end


    // Handle button action
    // 북마크 추가 버튼
    @FXML
    private void handleInsertBookmarkButtonAction(ActionEvent event) throws IOException {

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


    // 북마크 삭제 버튼
    @FXML
    private void handleDeleteBookmarkButtonAction(ActionEvent event) throws IOException {
        System.out.println("delete button clicked");

        // If no select -> escape this method
        if (selectedBookmarkId == 0) {
            return;
        }
        // Delete data from database
        DeleteBookmarkDAO deleteDAO = new DeleteBookmarkDAO();
        boolean isDeleteSucceed = deleteDAO.deleteBookmark(isOnBookmark(), getLoggedInUserKey(), selectedBookmarkId);

        // refresh bookmark table
        drawBookmarkDataOnCenter();
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
    private void searchFilter(ObservableList<TableSearchVO> observableTableList) {

        if (observableTableList != null) {

            FilteredList<TableSearchVO> filteredData = new FilteredList<>(observableTableList, b -> true);

            // get text from search text field on tap pane
            getSearchTextField().textProperty().addListener((observable, oldValue, newValue) -> {

                filteredData.setPredicate(tableSearchVO -> {

                    // if no value on searchTextField, no changes
                    if (newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String searchKeyword = newValue.trim();


                    if (tableSearchVO.getApartGroup().contains(searchKeyword)) {
                        return true;
                    } else if (tableSearchVO.getAddressDetailed().contains(searchKeyword)) {
                        return true;
                    } else if (tableSearchVO.getAddressRoad().contains(searchKeyword)) {
                        return true;
                    } else if (tableSearchVO.getConstructionYear().contains(searchKeyword)) {
                        return true;
                    } else if (tableSearchVO.getContractDate().contains(searchKeyword)) {
                        return true;
                    } else if (tableSearchVO.getFloor().contains(searchKeyword)) {
                        return true;
                    } else if (tableSearchVO.getPrice().replace(",", "").contains(searchKeyword)) {
                        return true; // it helps find price without "," keyword
                    } else if (tableSearchVO.getPrice().contains(searchKeyword)) {
                        return true;
                    } else if (tableSearchVO.getArea().contains(searchKeyword)) {
                        return true;
                    } else {
                        // no match found
                        return false;
                    }
                });
            });

            SortedList<TableSearchVO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);


        }
    }

    // static value getter
    public static int getSelectedBookmarkId() {
        return selectedBookmarkId;
    }


    // TableColumn getter
    public TableColumn<TableSearchVO, Integer> getTradeId() {
        return tradeId;
    }

    public TableColumn<TableSearchVO, String> getApartGroup() {
        return apartGroup;
    }

    public TableColumn<TableSearchVO, String> getAddressRoad() {
        return addressRoad;
    }

    public TableColumn<TableSearchVO, String> getAddressDetailed() {
        return addressDetailed;
    }

    public TableColumn<TableSearchVO, String> getPrice() {
        return price;
    }

    public TableColumn<TableSearchVO, String> getArea() {
        return area;
    }

    public TableColumn<TableSearchVO, String> getConstructionYear() {
        return constructionYear;
    }

    public TableColumn<TableSearchVO, String> getFloor() {
        return floor;
    }

    public TableColumn<TableSearchVO, String> getContractDate() {
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
