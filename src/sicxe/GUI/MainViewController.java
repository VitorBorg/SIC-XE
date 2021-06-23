package sicxe.GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import sicxe.App;
import sicxe.Carregador.Carregador;
import sicxe.Helpers.ParseSourceLine;
import sicxe.Maquina.Maquina;
import sicxe.Memory.Memory;
import sicxe.Memory.Register;
import sicxe.Memory.Variables;
import sicxe.Montador.Montador;
import sicxe.Table.Table;
import sicxe.Macro.MacroDefine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private void handleStartButton() throws FileNotFoundException {
        if ((App.addressStringList != null) & ((App.dataStringList != null))) {
            App.memoryViewController.clearMemory();
        }

        if (App.listaCodigoFonte != null) {
            App.cpuViewController.clearCPUView();
        }

        App.listaCodigoFonte = new ArrayList<>();
        App.table = new Table();
        App.memoria = new Memory();
        App.vars = new Variables();
        App.reg = new Register();
        App.macro = new MacroDefine();

        // MACRO
        File input = (new File(String.valueOf(App.inputFile))); // <<<<--- arquivo entrada
        MacroDefine magic = new MacroDefine();
        magic.macroInitialize(input.getAbsolutePath());

        Scanner parsed = new Scanner(new FileReader("src/sicxe/Outputs/parsed.sic"));

        while (parsed.hasNextLine()) {
            String linhaDoArquivo = parsed.nextLine();
            ParseSourceLine cfl = new ParseSourceLine(App.line, App.endereco, linhaDoArquivo);
            App.line++;
            App.listaCodigoFonte.add(cfl);
            App.table.addLine(cfl.getValues());
        }

        //clears CPU and memory windows
        if ((App.addressStringList != null) & ((App.dataStringList != null))) {
            App.memoryViewController.clearMemory();
        }

        // MONTADOR
        App.montador = new Montador(App.memoria, App.listaCodigoFonte, App.reg, App.vars);
        App.montador.start();

        // CARREGADOR
        App.carregador = new Carregador(App.memoria);

        // ADD VARIABLES
        App.vars.start(App.listaCodigoFonte);

        //App.table.printTable();

        // MAQUINA
        App.maquina = new Maquina();
        App.maquina.maquinaExecucao();

        //update MemoryView columns
        App.addressStringList = App.memoria.getAddressList();
        App.dataStringList = App.memoria.getValueList();

        App.memoryViewController.updateMemory();

        //update CPUView TextFields and TextArea
        App.cpuViewController.updateCPUView();
    }

    @FXML
    private void handleStepButton() {
        //to do
    }
}