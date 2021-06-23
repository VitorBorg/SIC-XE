package sicxe.GUI;

import javafx.beans.property.SimpleStringProperty;

public class CPUObjectGenerator {

    private final SimpleStringProperty address;
    private final SimpleStringProperty label;
    private final SimpleStringProperty operator;
    private final SimpleStringProperty operand1;
    private final SimpleStringProperty operand2;

    CPUObjectGenerator(String address, String label, String operator, String operand1, String operand2) {

        this.address = new SimpleStringProperty(address);
        this.label = new SimpleStringProperty(label);
        this.operator = new SimpleStringProperty(operator);
        this.operand1 = new SimpleStringProperty(operand1);
        this.operand2 = new SimpleStringProperty(operand2);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public String getLabel() {
        return label.get();
    }

    public SimpleStringProperty labelProperty() {
        return label;
    }

    public String getOperator() {
        return operator.get();
    }

    public SimpleStringProperty operatorProperty() {
        return operator;
    }

    public String getOperand1() {
        return operand1.get();
    }

    public SimpleStringProperty operand1Property() {
        return operand1;
    }

    public String getOperand2() {
        return operand2.get();
    }

    public SimpleStringProperty operand2Property() {
        return operand2;
    }
}
