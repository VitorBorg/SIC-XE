package sicxe.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sicxe.App;

public class MemoryViewController {

    @FXML
    TableView<MemoryObjectGenerator> memoryViewTableView;
    @FXML
    TableColumn<MemoryObjectGenerator, String> address;
    @FXML
    TableColumn<MemoryObjectGenerator, String> data;

    final ObservableList<MemoryObjectGenerator> memoryTableData = FXCollections.observableArrayList();

    public void updateMemory() {

        for (int i = 0; i < App.addressStringList.size(); i++) {
            MemoryObjectGenerator currentSlot = new MemoryObjectGenerator(App.addressStringList.get(i), App.dataStringList.get(i));
            memoryTableData.add(currentSlot);
        }

        address.setCellValueFactory(new PropertyValueFactory<MemoryObjectGenerator, String>("Address"));
        data.setCellValueFactory(new PropertyValueFactory<MemoryObjectGenerator, String>("Data"));

        memoryViewTableView.setItems(memoryTableData);
    }

    public void clearMemory() {

        memoryTableData.clear();
        App.addressStringList.clear();
        App.dataStringList.clear();
    }
}