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
import models.GangguanModel;
import utils.Helper;
import utils.Query;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LapGangguanController implements Initializable {
    private ObservableList<GangguanModel> GangguanModels = FXCollections.observableArrayList();
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
        laporan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            String query = "select * from `laporan_gangguan` where status is not null";
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet rs = stats.executeQuery(query);
            while (rs.next()) {
                GangguanModels.add(new GangguanModel(
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
        FilteredList<GangguanModel> filteredData = new FilteredList<>(GangguanModels, p -> true);
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(gangguanModel -> {
                String lowerCaseFilter = newValue.toLowerCase();
                //Apabila field search tidak terisi, tampilkan seluruh data
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (gangguanModel.getKeterangan().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<GangguanModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbGangguan.comparatorProperty());
        tbGangguan.setItems(sortedData);

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
            tf_id.setText(String.valueOf(tbGangguan.getSelectionModel().getSelectedItem().getId()));
            tf_chatId.setText(tbGangguan.getSelectionModel().getSelectedItem().getChatId());
            tf_date.setText(tbGangguan.getSelectionModel().getSelectedItem().getDate());
            tf_nama.setText(String.valueOf(tbGangguan.getSelectionModel().getSelectedItem().getNama()));
            tf_provinsi.setText(tbGangguan.getSelectionModel().getSelectedItem().getProvinsi());
            tf_kota.setText(tbGangguan.getSelectionModel().getSelectedItem().getKota());
            tf_kecamatan.setText(tbGangguan.getSelectionModel().getSelectedItem().getKecamatan());
            tf_kelurahan.setText(tbGangguan.getSelectionModel().getSelectedItem().getKelurahan());
            tf_alamat.setText(tbGangguan.getSelectionModel().getSelectedItem().getAlamat());
            tf_nomorTelepon.setText(tbGangguan.getSelectionModel().getSelectedItem().getNomorTelepon());
            ta_keterangan.setText(tbGangguan.getSelectionModel().getSelectedItem().getKeterangan());
            try {
                Image image = new Image(tbGangguan.getSelectionModel().getSelectedItem().getMedia());
                iv_media.setImage(image);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            statusValue = tbGangguan.getSelectionModel().getSelectedItem().getStatus();
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
        query.updateData("laporan_gangguan", "status", tf_chatId.getText(), cb_status.getValue(), tf_id.getText());
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
    private TableView<GangguanModel> tbGangguan;
    @FXML
    public TableColumn<GangguanModel, String> chatID, laporan, date, status;
}