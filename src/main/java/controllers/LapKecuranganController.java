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
import models.KecuranganModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LapKecuranganController implements Initializable {

    @FXML
    private TableView<KecuranganModel> tbKecurangan;
    @FXML
    public TableColumn<KecuranganModel, String> chatID;

    @FXML
    public TableColumn<KecuranganModel, String> title;

    @FXML
    public TableColumn<KecuranganModel, String> date;

    MainController mainCopyController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        chatID.setCellValueFactory(new PropertyValueFactory<>("chatID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        //add your data to the table here.
        for (int i = 0; i < 100; i++) {
            KecuranganModels.add(new KecuranganModel(
                    "@dummy_" + Integer.toString(i),
                    "bapak temen saya jual listrik eceran",
                    "dummy"
            ));
        }
        tbKecurangan.setItems(KecuranganModels);
    }

    // add your data here from any source
    private ObservableList<KecuranganModel> KecuranganModels = FXCollections.observableArrayList();

    @FXML
    void btn_addItem(ActionEvent event) {
    }

    public void cell_onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            System.out.println(tbKecurangan.getSelectionModel().getSelectedItem().getChatID());
            System.out.println(tbKecurangan.getSelectionModel().getSelectedItem().getTitle());
            System.out.println(tbKecurangan.getSelectionModel().getSelectedItem().getDate());
        }
    }
}