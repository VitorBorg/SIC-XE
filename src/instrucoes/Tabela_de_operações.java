package src.instrucoes;

import java.util.HashMap;
import java.util.HashSet;

public final class tabelaOperacoes {
    private tabelaOperacoes(){
    }

    private static HashMap<String, Operation> tabelaOperacoes = new HashMap<>();

    public static HashMap<String, Operation> get() {
        return tabelaOperacoes;
    }

    public enum Format {
        ONE, TWO, THREE, FOUR
    }

    public enum OperandType {
        REGISTER, NONE, VALUE, DONT_CARE
    }
    
    private static void load() {
        tabelaOperacoes.put("ADD", new Operation("ADD", 0x18, Format.THREE, OperandType.VALUE, OperandType.DONT_CARE));

        tabelaOperacoes.put("+ADD", new Operation("+ADD", 0x18, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("ADDR", new Operation("ADDR", 0x90, Format.TWO, OperandType.REGISTER, OperandType.REGISTER));

        tabelaOperacoes.put("AND", new Operation("AND", 0x40, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+AND", new Operation("+AND", 0x40, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("CLEAR", new Operation("CLEAR", 0xB4, Format.TWO, OperandType.REGISTER, OperandType.NONE));

        tabelaOperacoes.put("COMP", new Operation("COMP", 0x28, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+COMP", new Operation("+COMP", 0x28, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("COMPR", new Operation("COMPR", 0xA0, Format.TWO, OperandType.REGISTER, OperandType.REGISTER));

        tabelaOperacoes.put("DIV", new Operation("DIV", 0x24, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+DIV", new Operation("+DIV", 0x24, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("DIVR", new Operation("DIVR", 0x9C, Format.TWO, OperandType.REGISTER, OperandType.REGISTER));

        tabelaOperacoes.put("J", new Operation("J", 0x3C, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+J", new Operation("+J", 0x3C, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("JEQ", new Operation("JEQ", 0x30, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+JEQ", new Operation("+JEQ", 0x30, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("JGT", new Operation("JGT", 0x34, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+JGT", new Operation("+JGT", 0x34, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("JLT", new Operation("JLT", 0x38, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+JLT", new Operation("+JLT", 0x38, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("JSUB", new Operation("JSUB", 0x48, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+JSUB", new Operation("+JSUB", 0x48, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("LDA", new Operation("LDA", 0x00, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+LDA", new Operation("+LDA", 0x00, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("LDB", new Operation("LDB", 0x68, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+LDB", new Operation("+LDB", 0x68, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("LDCH", new Operation("LDCH", 0x50, Format.THREE, OperandType.VALUE, OperandType.DONT_CARE));

        tabelaOperacoes.put("+LDCH", new Operation("+LDCH", 0x50, Format.FOUR, OperandType.VALUE, OperandType.DONT_CARE));

        tabelaOperacoes.put("LDL", new Operation("LDL", 0x08, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+LDL", new Operation("+LDL", 0x08, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("LDS", new Operation("LDS", 0x6C, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+LDS", new Operation("+LDS", 0x6C, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("LDT", new Operation("LDT", 0x74, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+LDT", new Operation("+LDT", 0x74, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("LDX", new Operation("LDX", 0x04, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+LDX", new Operation("+LDX", 0x04, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("MUL", new Operation("MUL", 0x20, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+MUL", new Operation("+MUL", 0x20, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("MULR", new Operation("MULR", 0x98, Format.TWO, OperandType.REGISTER, OperandType.REGISTER));

        tabelaOperacoes.put("OR", new Operation("OR", 0x44, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+OR", new Operation("+OR", 0x44, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("RD", new Operation("RD", 0xD8, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+RD", new Operation("+RD", 0xD8, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("RMO", new Operation("RMO", 0xAC, Format.TWO, OperandType.REGISTER, OperandType.REGISTER));

        tabelaOperacoes.put("RSUB", new Operation("RSUB", 0x4C, Format.THREE, OperandType.NONE, OperandType.NONE));

        tabelaOperacoes.put("+RSUB", new Operation("+RSUB", 0x4C, Format.FOUR, OperandType.NONE, OperandType.NONE));

        tabelaOperacoes.put("SHIFTL", new Operation("SHIFTL", 0xA4, Format.TWO, OperandType.REGISTER, OperandType.VALUE));

        tabelaOperacoes.put("SHIFTR", new Operation("SHIFTR", 0xA8, Format.TWO, OperandType.REGISTER, OperandType.VALUE));

        tabelaOperacoes.put("STA", new Operation("STA", 0x0C, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+STA", new Operation("+STA", 0x0C, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("STB", new Operation("STB", 0x78, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+STB", new Operation("+STB", 0x78, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("STCH", new Operation("STCH", 0x54, Format.THREE, OperandType.VALUE, OperandType.DONT_CARE));

        tabelaOperacoes.put("+STCH", new Operation("+STCH", 0x54, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("STL", new Operation("STL", 0x14, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+STL", new Operation("+STL", 0x14, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("STS", new Operation("STS", 0x7C, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+STS", new Operation("+STS", 0x7C, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("STT", new Operation("STT", 0x84, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+STT", new Operation("+STT", 0x84, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("STX", new Operation("STX", 0x10, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+STX", new Operation("+STX", 0x10, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("SUB", new Operation("SUB", 0x1C, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+SUB", new Operation("+SUB", 0x1C, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("SUBR", new Operation("SUBR", 0x94, Format.TWO, OperandType.REGISTER, OperandType.REGISTER));

        tabelaOperacoes.put("TD", new Operation("TD", 0xE0, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+TD", new Operation("+TD", 0xE0, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("TIX", new Operation("TIX", 0x2C, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+TIX", new Operation("+TIX", 0x2C, Format.FOUR, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("TIXR", new Operation("TIXR", 0xB8, Format.TWO, OperandType.REGISTER, OperandType.NONE));

        tabelaOperacoes.put("WD", new Operation("WD", 0xDC, Format.THREE, OperandType.VALUE, OperandType.NONE));

        tabelaOperacoes.put("+WD", new Operation("+WD", 0xDC, Format.FOUR, OperandType.VALUE, OperandType.NONE));
    }

}



