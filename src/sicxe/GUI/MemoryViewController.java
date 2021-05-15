package sicxe.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MemoryViewController implements Initializable {

    @FXML TableView<MemoryExample> memoryViewTableView;
    @FXML TableColumn<MemoryExample, String> address;
    @FXML TableColumn<MemoryExample, String> data;

    //example
    final ObservableList<MemoryExample> tableData = FXCollections.observableArrayList(
        new MemoryExample("0x000000", "data_01"),
        new MemoryExample("0x000004", "data_02"),
        new MemoryExample("0x000008", "data_03"),
        new MemoryExample("0x00000C", "data_04"),
        new MemoryExample("0x000010", "data_05"),
        new MemoryExample("0x000014", "data_06"),
        new MemoryExample("0x000018", "data_07"),
        new MemoryExample("0x00001C", "data_08"),
        new MemoryExample("0x000020", "data_09"),
        new MemoryExample("0x000024", "data_10"),
        new MemoryExample("0x000028", "data_11"),
        new MemoryExample("0x00002C", "data_12"),
        new MemoryExample("0x000030", "data_13")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        address.setCellValueFactory(new PropertyValueFactory<MemoryExample,String>("Address"));
        data.setCellValueFactory(new PropertyValueFactory<MemoryExample,String>("Data"));

        memoryViewTableView.setItems(tableData);
    }
}
