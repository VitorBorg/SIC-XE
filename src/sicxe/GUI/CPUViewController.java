package sicxe.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sicxe.App;

import java.net.URL;
import java.util.ResourceBundle;

public class CPUViewController implements Initializable {

    @FXML TextArea cpuViewTextArea = new TextArea();
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
        cpuViewTextArea.setText("Press start on the main window to run the program.");
        textFieldA.setEditable(false);
        textFieldS.setEditable(false);
        textFieldT.setEditable(false);
        textFieldL.setEditable(false);
        textFieldB.setEditable(false);
        textFieldX.setEditable(false);
        textFieldSW.setEditable(false);
        textFieldF.setEditable(false);
        textFieldPC.setEditable(false);
        cpuViewTextArea.setEditable(false);
    }

    public void updateCPUView() {

        textFieldA.setText(App.reg.getRegisterValue("A"));
        textFieldS.setText(App.reg.getRegisterValue("S"));
        textFieldT.setText(App.reg.getRegisterValue("T"));
        textFieldL.setText(App.reg.getRegisterValue("L"));
        textFieldB.setText(App.reg.getRegisterValue("B"));
        textFieldX.setText(App.reg.getRegisterValue("X"));
        textFieldSW.setText(App.reg.getRegisterValue("SW"));
        textFieldF.setText(App.reg.getRegisterValue("F"));
        textFieldPC.setText(App.reg.getRegisterValue("PC"));
        cpuViewTextArea.setText("?");
    }

    public void clearCPUView() {

        textFieldA.setText("0");
        textFieldS.setText("0");
        textFieldT.setText("0");
        textFieldL.setText("0");
        textFieldB.setText("0");
        textFieldX.setText("0");
        textFieldSW.setText("0");
        textFieldF.setText("0");
        textFieldPC.setText("0");
        cpuViewTextArea.setText("Press start on the main window to run the program.");
    }
}
