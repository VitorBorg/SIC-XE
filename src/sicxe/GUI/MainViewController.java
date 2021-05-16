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

        App.cpuViewController.clearCPUView();

        App.listaCodigoFonte = new ArrayList<>();
        App.table = new Table();
        App.memoria = new Memory();
        App.vars = new Variables();
        App.reg = new Register();

        Scanner in = new Scanner(new FileReader(App.inputFile));
        while (in.hasNextLine()) {
            String linhaDoArquivo = in.nextLine();
            ParseSourceLine cfl = new ParseSourceLine(App.line, App.endereco, linhaDoArquivo);
            App.line++;
            App.listaCodigoFonte.add(cfl);

            App.table.addLine(cfl.getValues());
        }

        //clears CPU and memory windows
        if ((App.addressStringList != null) & ((App.dataStringList != null))) {
            App.memoryViewController.clearMemory();
        }

        //App.table.printTable();

        App.montador = new Montador(App.memoria, App.listaCodigoFonte, App.reg, App.vars);
        App.montador.start();

        // CARREGADOR
        App.carregador = new Carregador(App.memoria);
        //System.out.println("**------ Print Memory -------**");
        //App.memoria.printMemory();
        //System.out.println("**------ ------------ -------**");
        //memoria.updateValueFromAddres("00168","010001");
        //System.out.println(memoria.getNextValue("00144"));

        // ADD VARIABLES
        App.vars.start(App.listaCodigoFonte);
        //vars.printVariables();

        //HashMap<String, String> var = new HashMap<String, String>();
        //var.put("VAR1","123");

        //vars.addVariable(var);
        //System.out.println(vars.getAddressFromVarName("VAR1"));

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