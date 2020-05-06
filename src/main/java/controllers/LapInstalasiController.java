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
import models.InstalasiModel;
import utils.Helper;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LapInstalasiController implements Initializable {

    @FXML
    public TextField tf_search;
    @FXML
    private TableView<InstalasiModel> tbInstalasi;
    @FXML
    public TableColumn<InstalasiModel, String> chatID, nama, date, status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatID.setCellValueFactory(new PropertyValueFactory<>("chatId"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        try {
            String query = "select * from `instalasi_listrik` where status is not null";
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet rs = stats.executeQuery(query);
            while (rs.next()) {
                InstalasiModels.add(new InstalasiModel(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getString("chatId"),
                        rs.getString("nama"),
                        rs.getString("provinsi"),
                        rs.getString("kota"),
                        rs.getString("kecamatan"),
                        rs.getString("kelurahan"),
                        rs.getString("alamat"),
                        rs.getString("nomorTelepon"),
                        rs.getString("email"),
                        rs.getString("nik"),
                        rs.getString("npwp"),
                        rs.getString("layanan"),
                        rs.getString("peruntukan"),
                        rs.getString("daya"),
                        rs.getString("tokenPerdana"),
                        rs.getString("status")
                ));
            }
            rs.close();
            stats.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FilteredList<InstalasiModel> filteredData = new FilteredList<>(InstalasiModels, p -> true);
        System.out.println(tbInstalasi.getColumns().contains("Belum"));
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(instalasiModel -> {
                String lowerCaseFilter = newValue.toLowerCase();
                //Apabila field search tidak terisi, tampilkan seluruh data
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (instalasiModel.getNama().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<InstalasiModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbInstalasi.comparatorProperty());
        tbInstalasi.setItems(sortedData);
    }

    private ObservableList<InstalasiModel> InstalasiModels = FXCollections.observableArrayList();

    public void cell_onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            System.out.println(tbInstalasi.getSelectionModel().getSelectedItem().getChatId());
            System.out.println(tbInstalasi.getSelectionModel().getSelectedItem().getNama());
            System.out.println(tbInstalasi.getSelectionModel().getSelectedItem().getDate());
            System.out.println(tbInstalasi.getSelectionModel().getSelectedItem().getStatus());
        }
    }
}