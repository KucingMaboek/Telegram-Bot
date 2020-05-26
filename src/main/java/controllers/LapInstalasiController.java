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
import models.InstalasiModel;
import utils.Helper;
import utils.Query;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LapInstalasiController implements Initializable {
    private ObservableList<InstalasiModel> InstalasiModels = FXCollections.observableArrayList();
    private ObservableList<String> statusOption =
            FXCollections.observableArrayList(
                    "Belum di Proses",
                    "Sedang di Proses",
                    "Telah di Proses"
            );
    private String statusValue;

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
            tf_id.setText(String.valueOf(tbInstalasi.getSelectionModel().getSelectedItem().getId()));
            tf_chatId.setText(tbInstalasi.getSelectionModel().getSelectedItem().getChatId());
            tf_date.setText(tbInstalasi.getSelectionModel().getSelectedItem().getDate());
            tf_nama.setText(String.valueOf(tbInstalasi.getSelectionModel().getSelectedItem().getNama()));
            tf_provinsi.setText(tbInstalasi.getSelectionModel().getSelectedItem().getProvinsi());
            tf_kota.setText(tbInstalasi.getSelectionModel().getSelectedItem().getKota());
            tf_kecamatan.setText(tbInstalasi.getSelectionModel().getSelectedItem().getKecamatan());
            tf_kelurahan.setText(tbInstalasi.getSelectionModel().getSelectedItem().getKelurahan());
            tf_alamat.setText(tbInstalasi.getSelectionModel().getSelectedItem().getAlamat());
            tf_nomorTelepon.setText(tbInstalasi.getSelectionModel().getSelectedItem().getNomorTelepon());
            tf_email.setText(tbInstalasi.getSelectionModel().getSelectedItem().getEmail());
            tf_nik.setText(tbInstalasi.getSelectionModel().getSelectedItem().getNik());
            tf_npwp.setText(tbInstalasi.getSelectionModel().getSelectedItem().getNpwp());
            tf_layanan.setText(tbInstalasi.getSelectionModel().getSelectedItem().getLayanan());
            tf_peruntukan.setText(tbInstalasi.getSelectionModel().getSelectedItem().getPeruntukan());
            tf_daya.setText(tbInstalasi.getSelectionModel().getSelectedItem().getDaya());
            tf_tokenPerdana.setText(tbInstalasi.getSelectionModel().getSelectedItem().getTokenPerdana());
            cb_status.setValue(tbInstalasi.getSelectionModel().getSelectedItem().getStatus());
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
        query.updateData("instalasi_listrik", "status", tf_chatId.getText(), cb_status.getValue(), tf_id.getText());
        statusValue = cb_status.getValue();
    }


    //FXML Variables
    @FXML
    private TextField tf_nama, tf_provinsi, tf_kota, tf_kecamatan, tf_kelurahan, tf_alamat, tf_nomorTelepon, tf_email, tf_nik, tf_npwp, tf_layanan, tf_peruntukan, tf_daya, tf_tokenPerdana, tf_id, tf_chatId, tf_date;
    @FXML
    private ChoiceBox<String> cb_status;
    @FXML
    private Button btn_saveId;
    @FXML
    private AnchorPane pnl_list, pnl_detail;
    @FXML
    public TextField tf_search;
    @FXML
    private TableView<InstalasiModel> tbInstalasi;
    @FXML
    public TableColumn<InstalasiModel, String> chatID, nama, date, status;
}