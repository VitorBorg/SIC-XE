package sicxe.GUI;

import javafx.beans.property.SimpleStringProperty;

public class MemoryObjectGenerator {

    private final SimpleStringProperty address;
    private final SimpleStringProperty data;

    MemoryObjectGenerator(String address, String content) {

        this.address = new SimpleStringProperty(address);
        this.data = new SimpleStringProperty(content);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }
}
