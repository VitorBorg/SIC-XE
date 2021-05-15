package sicxe.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {
    private List<String> line;
    private String column1 = "";
    private String column2 = "";
    private String column3 = "";
    private String column4 = "";
    private String column5 = "";
    private String column6 = "";

    public Line(String... columns) {
        this.column1 = columns[0];
        this.column2 = columns[1];
        this.column3 = columns[2];
        this.column4 = columns[3];
        this.column5 = columns[4];
        this.column6 = columns[5];
    }

    void addLine(String... columns) {
        this.column1 = columns[0];
        this.column2 = columns[1];
        this.column3 = columns[2];
        this.column4 = columns[3];
        this.column5 = columns[4];
        this.column6 = columns[5];

        this.line = new ArrayList<String>();

        this.line.addAll(Arrays.asList(this.column1, this.column2, this.column3, this.column4, this.column5, this.column6));

    }

    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public String getColumn3() {
        return column3;
    }

    public String getColumn4() {
        return column4;
    }

    public String getColumn5() {
        return column5;
    }

    public String getColumn6() {
        return column6;
    }
}
