package sicxe.Table;

import sicxe.Helpers.Helpers;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final List<Line> table;
    private Line line;

    public Table() {

        this.table = new ArrayList<Line>();
    }

    public void addLine(String... columns) {
        this.line = new Line(columns);
        this.table.add(this.line);
    }

    public void printTable() {
        String leftAlignFormat = "|%15s|%15s|%15s|%15s|%15s|%15s|%n";
        System.out.format("+-----------------------------------------------------------------------------------------------+%n");
        System.out.format("|     Linha     |   Endereco    |     Rotulo    |    Operador   |   Operando1   |   Operando2   |%n");
        System.out.format("+---------------+---------------+---------------+---------------+---------------+---------------+%n");
        for (Line tbl : table) {
            System.out.format(leftAlignFormat, Helpers.printIfNotNull(tbl.getColumn1()), Helpers.printIfNotNull(tbl.getColumn2()), Helpers.printIfNotNull(tbl.getColumn3()), Helpers.printIfNotNull(tbl.getColumn4()), Helpers.printIfNotNull(tbl.getColumn5()), Helpers.printIfNotNull(tbl.getColumn6()));
        }
        System.out.format("+---------------+---------------+---------------+---------------+---------------+---------------+%n");
    }


}
