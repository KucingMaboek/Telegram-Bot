package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.PermintaanLiveModel;
import utils.Helper;
import utils.Query;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PermintaanLiveController implements Initializable {
    private ObservableList<PermintaanLiveModel> PermintaanLiveModels = FXCollections.observableArrayList();
    private ObservableList<String> statusOption =
            FXCollections.observableArrayList(
                    "Belum di Proses",
                    "Sedang di Proses",
                    "Telah di Proses"
            );
    private String statusValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        chatID.setCellValueFactory(new PropertyValueFactory<>("chatId"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        try {
            String query = "select * from `permintaan_livechat` where status is not null";
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet rs = stats.executeQuery(query);
            while (rs.next()) {
                PermintaanLiveModels.add(new PermintaanLiveModel(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("chatId"),
                        rs.getString("username"),
                        rs.getString("status")
                ));
            }
            rs.close();
            stats.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FilteredList<PermintaanLiveModel> filteredData = new FilteredList<>(PermintaanLiveModels, p -> true);
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(permintaanLiveModel -> {
                String lowerCaseFilter = newValue.toLowerCase();
                //Apabila field search tidak terisi, tampilkan seluruh data
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (permintaanLiveModel.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<PermintaanLiveModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbPermintaanLive.comparatorProperty());
        tbPermintaanLive.setItems(sortedData);

        btn_saveId.disableProperty().setValue(true);
        cb_status.getItems().addAll(statusOption);
        cb_status.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue != null) {
                    if (statusValue.equals(String.valueOf(cb_status.getItems().get((Integer) newValue)))) {
                        System.out.println(statusValue);
                        System.out.println(cb_status.getItems().get((Integer) newValue));
                        btn_saveId.disableProperty().setValue(true);
                    } else {
                        btn_saveId.disableProperty().setValue(false);
                    }
                }
            }
        });
    }


    public void cell_onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            tf_id.setText(String.valueOf(tbPermintaanLive.getSelectionModel().getSelectedItem().getId()));
            tf_chatId.setText(tbPermintaanLive.getSelectionModel().getSelectedItem().getChatId());
            tf_date.setText(tbPermintaanLive.getSelectionModel().getSelectedItem().getDate());
            tf_time.setText(tbPermintaanLive.getSelectionModel().getSelectedItem().getTime());
            tf_username.setText(tbPermintaanLive.getSelectionModel().getSelectedItem().getUsername());
            statusValue = tbPermintaanLive.getSelectionModel().getSelectedItem().getStatus();
            cb_status.setValue(statusValue);
            pnl_detail.toFront();
        }
    }

    @FXML
    void btn_back(ActionEvent event) {
        pnl_list.toFront();
    }

    @FXML
    void btn_save(ActionEvent event) {
        Query query = new Query();
        query.updateData("permintaan_livechat", "status", tf_chatId.getText(), cb_status.getValue());
        statusValue = cb_status.getValue();
    }

    //FXML Variables
    @FXML
    private TextField tf_id, tf_chatId, tf_date, tf_time, tf_username;
    @FXML
    private ChoiceBox<String> cb_status;
    @FXML
    private Button btn_saveId;
    @FXML
    private AnchorPane pnl_list, pnl_detail;
    @FXML
    public TextField tf_search;
    @FXML
    private TableView<PermintaanLiveModel> tbPermintaanLive;
    @FXML
    public TableColumn<PermintaanLiveModel, String> chatID, username, date, status;
}