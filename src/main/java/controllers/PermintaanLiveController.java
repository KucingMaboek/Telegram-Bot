package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.PermintaanLiveModel;
import utils.Helper;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PermintaanLiveController implements Initializable {

    @FXML
    public TextField tf_search;
    @FXML
    private TableView<PermintaanLiveModel> tbPermintaanLive;
    @FXML
    public TableColumn<PermintaanLiveModel, String> chatID, username, date, status;

    MainController mainCopyController;

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
        System.out.println(tbPermintaanLive.getColumns().contains("Belum"));
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
    }

    private ObservableList<PermintaanLiveModel> PermintaanLiveModels = FXCollections.observableArrayList();

    public void cell_onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            System.out.println(tbPermintaanLive.getSelectionModel().getSelectedItem().getChatId());
            System.out.println(tbPermintaanLive.getSelectionModel().getSelectedItem().getStatus());
            System.out.println(tbPermintaanLive.getSelectionModel().getSelectedItem().getDate());
        }
    }
}