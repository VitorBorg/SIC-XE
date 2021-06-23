package sicxe.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sicxe.App;
import sicxe.Helpers.Helpers;
import sicxe.Helpers.ParseSourceLine;
import sicxe.Table.Line;

import java.net.URL;
import java.util.ResourceBundle;

public class CPUViewController implements Initializable {

    final ObservableList<CPUObjectGenerator> cpuTableData = FXCollections.observableArrayList();

    @FXML TableColumn<CPUObjectGenerator, String> address;
    @FXML TableColumn<CPUObjectGenerator, String> label;
    @FXML TableColumn<CPUObjectGenerator, String> operator;
    @FXML TableColumn<CPUObjectGenerator, String> operand1;
    @FXML TableColumn<CPUObjectGenerator, String> operand2;

    @FXML TableView cpuViewTableView = new TableView();
    @FXML TextField textFieldA = new TextField();
    @FXML TextField textFieldS = new TextField();
    @FXML TextField textFieldT = new TextField();
    @FXML TextField textFieldL = new TextField();
    @FXML TextField textFieldB = new TextField();
    @FXML TextField textFieldX = new TextField();
    @FXML TextField textFieldSW = new TextField();
    @FXML TextField textFieldF = new TextField();
    @FXML TextField textFieldPC = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textFieldA.setText("0");
        textFieldS.setText("0");
        textFieldT.setText("0");
        textFieldL.setText("0");
        textFieldB.setText("0");
        textFieldX.setText("0");
        textFieldSW.setText("0");
        textFieldF.setText("0");
        textFieldF.setText("0");
        textFieldPC.setText("0");
        textFieldPC.setText("0");
        textFieldA.setEditable(false);
        textFieldS.setEditable(false);
        textFieldT.setEditable(false);
        textFieldL.setEditable(false);
        textFieldB.setEditable(false);
        textFieldX.setEditable(false);
        textFieldSW.setEditable(false);
        textFieldF.setEditable(false);
        textFieldPC.setEditable(false);
        cpuViewTableView.setEditable(false);
    }

    public void updateCPUView() {

        for (ParseSourceLine psl : App.listaCodigoFonte) {
            CPUObjectGenerator currentSlot = new CPUObjectGenerator(psl.getEndereco(), psl.getRotulo(), psl.getOperador(), psl.getOperando1(), psl.getOperando2());
            cpuTableData.add(currentSlot);
        }

        address.setCellValueFactory(new PropertyValueFactory<CPUObjectGenerator, String>("Address"));
        label.setCellValueFactory(new PropertyValueFactory<CPUObjectGenerator, String>("Label"));
        operator.setCellValueFactory(new PropertyValueFactory<CPUObjectGenerator, String>("Operator"));
        operand1.setCellValueFactory(new PropertyValueFactory<CPUObjectGenerator, String>("Operand1"));
        operand2.setCellValueFactory(new PropertyValueFactory<CPUObjectGenerator, String>("Operand2"));

        cpuViewTableView.setItems(cpuTableData);

        textFieldA.setText(App.reg.getRegisterValue("A"));
        textFieldS.setText(App.reg.getRegisterValue("S"));
        textFieldT.setText(App.reg.getRegisterValue("T"));
        textFieldL.setText(App.reg.getRegisterValue("L"));
        textFieldB.setText(App.reg.getRegisterValue("B"));
        textFieldX.setText(App.reg.getRegisterValue("X"));
        textFieldSW.setText(App.reg.getRegisterValue("SW"));
        textFieldF.setText(App.reg.getRegisterValue("F"));
        textFieldPC.setText(App.reg.getRegisterValue("PC"));
    }

    public void clearCPUView() {

        cpuTableData.clear();
        App.listaCodigoFonte.clear();

        textFieldA.setText("0");
        textFieldS.setText("0");
        textFieldT.setText("0");
        textFieldL.setText("0");
        textFieldB.setText("0");
        textFieldX.setText("0");
        textFieldSW.setText("0");
        textFieldF.setText("0");
        textFieldPC.setText("0");
    }
}
