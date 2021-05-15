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

        textFieldA.setEditable(false);
        textFieldS.setEditable(false);
        textFieldT.setEditable(false);
        textFieldL.setEditable(false);
        textFieldB.setEditable(false);
        textFieldX.setEditable(false);
        textFieldSW.setEditable(false);
        textFieldF1.setEditable(false);
        textFieldF2.setEditable(false);
        textFieldPC1.setEditable(false);
        textFieldPC2.setEditable(false);
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
