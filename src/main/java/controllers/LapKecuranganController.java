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
import models.KecuranganModel;
import utils.Helper;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LapKecuranganController implements Initializable {

    @FXML
    public TextField tf_search;
    @FXML
    private TableView<KecuranganModel> tbKecurangan;
    @FXML
    public TableColumn<KecuranganModel, String> chatID, laporan, date, status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        chatID.setCellValueFactory(new PropertyValueFactory<>("chatId"));
        laporan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        //add your data to the table here.
        try {
            String query = "select * from `laporan_kecurangan` where status is not null";
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet rs = stats.executeQuery(query);
            while (rs.next()) {
                KecuranganModels.add(new KecuranganModel(
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
                        rs.getString("keterangan"),
                        rs.getString("media"),
                        rs.getString("status")
                ));
            }
            rs.close();
            stats.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FilteredList<KecuranganModel> filteredData = new FilteredList<>(KecuranganModels, p -> true);
        System.out.println(tbKecurangan.getColumns().contains("Belum"));
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kecuranganModel -> {
                //Apabila field search tidak terisi, tampilkan seluruh data
                String lowerCaseFilter = newValue.toLowerCase();
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (kecuranganModel.getKeterangan().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<KecuranganModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbKecurangan.comparatorProperty());
        tbKecurangan.setItems(sortedData);
    }

    // add your data here from any source
    private ObservableList<KecuranganModel> KecuranganModels = FXCollections.observableArrayList();

    public void cell_onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            System.out.println(tbKecurangan.getSelectionModel().getSelectedItem().getChatId());
            System.out.println(tbKecurangan.getSelectionModel().getSelectedItem().getNama());
            System.out.println(tbKecurangan.getSelectionModel().getSelectedItem().getDate());
            System.out.println(tbKecurangan.getSelectionModel().getSelectedItem().getStatus());
        }
    }
}