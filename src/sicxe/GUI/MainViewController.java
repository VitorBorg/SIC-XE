package sicxe.GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import sicxe.App;
import sicxe.Carregador.Carregador;
import sicxe.Montador.Montador;

import java.io.IOException;

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

    //executes the machine and updates CPUView and MemoryView
    @FXML
    private void handleStartButton() {
        App.table.printTable();

        App.montador = new Montador(App.listaCodigoFonte, App.memoria);
        App.montador.start();

        //carregador
        App.carregador = new Carregador(App.memoria);

        //update MemoryView columns
        App.addressStringList = App.memoria.getAddress();
        App.dataStringList = App.memoria.getDatas();;

        App.memoryViewController.updateMemory();
    }

    @FXML
    private void handleStepButton() {
        //to do
    }
}