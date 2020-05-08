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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.KecuranganModel;
import utils.Helper;
import utils.Query;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LapKecuranganController implements Initializable {
    private ObservableList<KecuranganModel> KecuranganModels = FXCollections.observableArrayList();
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
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kecuranganModel -> {
                String lowerCaseFilter = newValue.toLowerCase();
                //Apabila field search tidak terisi, tampilkan seluruh data
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
            tf_id.setText(String.valueOf(tbKecurangan.getSelectionModel().getSelectedItem().getId()));
            tf_chatId.setText(tbKecurangan.getSelectionModel().getSelectedItem().getChatId());
            tf_date.setText(tbKecurangan.getSelectionModel().getSelectedItem().getDate());
            tf_nama.setText(String.valueOf(tbKecurangan.getSelectionModel().getSelectedItem().getNama()));
            tf_provinsi.setText(tbKecurangan.getSelectionModel().getSelectedItem().getProvinsi());
            tf_kota.setText(tbKecurangan.getSelectionModel().getSelectedItem().getKota());
            tf_kecamatan.setText(tbKecurangan.getSelectionModel().getSelectedItem().getKecamatan());
            tf_kelurahan.setText(tbKecurangan.getSelectionModel().getSelectedItem().getKelurahan());
            tf_alamat.setText(tbKecurangan.getSelectionModel().getSelectedItem().getAlamat());
            tf_nomorTelepon.setText(tbKecurangan.getSelectionModel().getSelectedItem().getNomorTelepon());
            ta_keterangan.setText(tbKecurangan.getSelectionModel().getSelectedItem().getKeterangan());
            Image image = new Image(tbKecurangan.getSelectionModel().getSelectedItem().getMedia());
            iv_media.setImage(image);
            statusValue = tbKecurangan.getSelectionModel().getSelectedItem().getStatus();
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
        query.updateData("laporan_kecurangan", "status", tf_chatId.getText(), cb_status.getValue());
        statusValue = cb_status.getValue();
    }

    //FXML Variables
    @FXML
    private TextField tf_nama, tf_provinsi, tf_kota, tf_kecamatan, tf_kelurahan, tf_alamat, tf_nomorTelepon, tf_id, tf_chatId, tf_date;
    @FXML
    private ChoiceBox<String> cb_status;
    @FXML
    private TextArea ta_keterangan;
    @FXML
    private ImageView iv_media;
    @FXML
    private Button btn_saveId;
    @FXML
    private AnchorPane pnl_list, pnl_detail;
    @FXML
    public TextField tf_search;
    @FXML
    private TableView<KecuranganModel> tbKecurangan;
    @FXML
    public TableColumn<KecuranganModel, String> chatID, laporan, date, status;
}