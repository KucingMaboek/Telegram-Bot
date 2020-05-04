package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<InstalasiModel> tbInstalasi;
    @FXML
    public TableColumn<InstalasiModel, String> chatID;

    @FXML
    public TableColumn<InstalasiModel, String> nama;

    @FXML
    public TableColumn<InstalasiModel, String> date;

    @FXML
    public TableColumn<InstalasiModel, String> status;

    MainController mainCopyController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        chatID.setCellValueFactory(new PropertyValueFactory<>("chatId"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        //add your data to the table here.
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
            tbInstalasi.setItems(InstalasiModels);
            rs.close();
            stats.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // add your data here from any source
    private ObservableList<InstalasiModel> InstalasiModels = FXCollections.observableArrayList();

    @FXML
    void btn_addItem(ActionEvent event) {
    }

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