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
import models.PermintaanLiveModel;

import java.net.URL;
import java.util.ResourceBundle;

public class PermintaanLiveController implements Initializable {

    @FXML
    private TableView<PermintaanLiveModel> tbPermintaanLive;
    @FXML
    public TableColumn<PermintaanLiveModel, String> chatID;

    @FXML
    public TableColumn<PermintaanLiveModel, String> status;

    @FXML
    public TableColumn<PermintaanLiveModel, String> date;

    MainController mainCopyController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        chatID.setCellValueFactory(new PropertyValueFactory<>("chatID"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        //add your data to the table here.
        for (int i = 0; i < 100; i++) {
            PermintaanLiveModels.add(new PermintaanLiveModel(
                    "@dummy_" + Integer.toString(i),
                    "Closed",
                    "dummy"
            ));
        }
        tbPermintaanLive.setItems(PermintaanLiveModels);
    }

    // add your data here from any source
    private ObservableList<PermintaanLiveModel> PermintaanLiveModels = FXCollections.observableArrayList();

    @FXML
    void btn_addItem(ActionEvent event) {
    }

    public void cell_onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            System.out.println(tbPermintaanLive.getSelectionModel().getSelectedItem().getChatID());
            System.out.println(tbPermintaanLive.getSelectionModel().getSelectedItem().getStatus());
            System.out.println(tbPermintaanLive.getSelectionModel().getSelectedItem().getDate());
        }
    }
}