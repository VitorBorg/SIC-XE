package sicxe.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sicxe.App;

public class MemoryViewController {

    @FXML TableView<MemoryExample> memoryViewTableView;
    @FXML TableColumn<MemoryExample, String> address;
    @FXML TableColumn<MemoryExample, String> data;

    final ObservableList<MemoryExample> tableData = FXCollections.observableArrayList();

    public void updateMemory() {

        for (int i = 0; i < App.addressStringList.size(); i++) {
            MemoryExample currentSlot = new MemoryExample(App.addressStringList.get(i), App.dataStringList.get(i));
            tableData.add(currentSlot);
        }

        address.setCellValueFactory(new PropertyValueFactory<MemoryExample, String>("Address"));
        data.setCellValueFactory(new PropertyValueFactory<MemoryExample, String>("Data"));

        memoryViewTableView.setItems(tableData);
    }
}
