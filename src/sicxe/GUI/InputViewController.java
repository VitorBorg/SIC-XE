package sicxe.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class InputViewController implements Initializable {

    @FXML
    public TextArea inputViewTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputViewTextArea.setText("No file loaded.");
        inputViewTextArea.setEditable(false);
    }

    public TextArea getInputViewTextArea() {
        return inputViewTextArea;
    }
}
