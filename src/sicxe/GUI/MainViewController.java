package sicxe.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sicxe.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import sicxe.Carregador.Carregador;
import sicxe.Montador.Montador;
import sicxe.Memory.MemoryBlock;

import java.io.IOException;
import java.util.List;

public class MainViewController {

    //Menu > Close exits the program
    @FXML
    private void handleMenuCloseButton() {
        Platform.exit();
    }

    //opens the about window
    @FXML
    private void handleMenuAboutButton() throws IOException {
        App.showAboutView();
    }

    //opens a file selection dialog to select the .asm input file
    @FXML
    public void handleLoadFile() throws IOException {

        App.openFileDialog();
        App.inputViewController.getInputViewTextArea().setText(App.inputFileText);
    }

    //opens the input view window
    @FXML
    private void handleCPUView() {
        App.showCPUView();
    }

    //opens the memory view window
    @FXML
    private void handleMemoryView() {
        App.showMemoryView();
    }

    //opens the input view window
    @FXML
    private void handleInputView() {
        App.showInputView();
    }

    @FXML
    private void handleStartButton() {
        App.table.printTable();

        App.montador = new Montador(App.listaCodigoFonte, App.memoria);
        App.montador.start();

        // CARREGADOR
        App.carregador = new Carregador(App.memoria);

        App.addressStringList = App.memoria.getAddress();
        App.dataStringList = App.memoria.getDatas();;

        App.memoryViewController.updateMemory();
    }
}