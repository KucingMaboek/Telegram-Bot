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
import models.GangguanModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LapGangguanController implements Initializable {

    @FXML
    private TableView<GangguanModel> tbGangguan;
    @FXML
    public TableColumn<GangguanModel, String> chatID;

    @FXML
    public TableColumn<GangguanModel, String> title;

    @FXML
    public TableColumn<GangguanModel, String> date;

    MainController mainCopyController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        chatID.setCellValueFactory(new PropertyValueFactory<>("chatID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        //add your data to the table here.
        for (int i = 0; i < 100; i++) {
            GangguanModels.add(new GangguanModel(
                    "@dummy_" + Integer.toString(i),
                    "tolong gan printer saya rusak",
                    "dummy"
            ));
        }
        tbGangguan.setItems(GangguanModels);
    }

    // add your data here from any source
    private ObservableList<GangguanModel> GangguanModels = FXCollections.observableArrayList();

    @FXML
    void btn_addItem(ActionEvent event) {
    }

    public void cell_onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            System.out.println(tbGangguan.getSelectionModel().getSelectedItem().getChatID());
            System.out.println(tbGangguan.getSelectionModel().getSelectedItem().getTitle());
            System.out.println(tbGangguan.getSelectionModel().getSelectedItem().getDate());
        }
    }
}