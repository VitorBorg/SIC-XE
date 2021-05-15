package sicxe.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML TextField textFieldF1 = new TextField();
    @FXML TextField textFieldF2 = new TextField();
    @FXML TextField textFieldPC1 = new TextField();
    @FXML  TextField textFieldPC2 = new TextField();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textFieldA.setText("0x000000");
        textFieldA.setEditable(false);
        textFieldS.setText("0x000000");
        textFieldS.setEditable(false);
        textFieldT.setText("0x000000");
        textFieldT.setEditable(false);
        textFieldL.setText("0x000000");
        textFieldL.setEditable(false);
        textFieldB.setText("0x000000");
        textFieldB.setEditable(false);
        textFieldX.setText("0x000000");
        textFieldX.setEditable(false);
        textFieldSW.setText("0x000000");
        textFieldSW.setEditable(false);
        textFieldF1.setText("0x000000");
        textFieldF1.setEditable(false);
        textFieldF2.setText("0x000000");
        textFieldF2.setEditable(false);
        textFieldPC1.setText("0x000000");
        textFieldPC1.setEditable(false);
        textFieldPC2.setText("0x000000");
        textFieldPC2.setEditable(false);
        cpuViewTextArea.setText("?");
        cpuViewTextArea.setEditable(false);
    }

    public TextArea getCpuViewTextArea() {
        return cpuViewTextArea;
    }

    public TextField getTextFieldA() {
        return textFieldA;
    }

    public TextField getTextFieldS() {
        return textFieldS;
    }

    public TextField getTextFieldT() {
        return textFieldT;
    }

    public TextField getTextFieldL() {
        return textFieldL;
    }

    public TextField getTextFieldB() {
        return textFieldB;
    }

    public TextField getTextFieldX() {
        return textFieldX;
    }

    public TextField getTextFieldSW() {
        return textFieldSW;
    }

    public TextField getTextFieldF1() {
        return textFieldF1;
    }

    public TextField getTextFieldF2() {
        return textFieldF2;
    }

    public TextField getTextFieldPC1() {
        return textFieldPC1;
    }

    public TextField getTextFieldPC2() {
        return textFieldPC2;
    }
}
