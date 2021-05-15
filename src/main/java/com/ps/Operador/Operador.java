package com.ps.Operador;

import java.util.HashMap;

public class Operador {

    private HashMap<String, Integer> operadores = new HashMap<>();

    public Operador() {
        operadores.put("ADD", 3);

        operadores.put("+ADD", 4);

        operadores.put("ADDR", 2);

        operadores.put("AND", 3);

        operadores.put("+AND", 4);

        operadores.put("CLEAR", 2);

        operadores.put("COMP", 3);

        operadores.put("+COMP", 4);

        operadores.put("COMPR", 2);

        operadores.put("DIV", 3);

        operadores.put("+DIV", 4);

        operadores.put("DIVR", 2);

        operadores.put("J", 3);

        operadores.put("+J", 4);

        operadores.put("JEQ", 3);

        operadores.put("+JEQ", 4);

        operadores.put("JGT", 3);

        operadores.put("+JGT", 4);

        operadores.put("JLT", 3);

        operadores.put("+JLT", 4);

        operadores.put("JSUB", 3);

        operadores.put("+JSUB", 4);

        operadores.put("LDA", 3);

        operadores.put("+LDA", 4);

        operadores.put("LDB", 3);

        operadores.put("+LDB", 4);

        operadores.put("LDCH", 3);

        operadores.put("+LDCH", 4);

        operadores.put("LDL", 3);

        operadores.put("+LDL", 4);

        operadores.put("LDS", 3);

        operadores.put("+LDS", 4);

        operadores.put("LDT", 3);

        operadores.put("+LDT", 4);

        operadores.put("LDX", 3);

        operadores.put("+LDX", 4);

        operadores.put("MUL", 3);

        operadores.put("+MUL", 4);

        operadores.put("MULR", 2);

        operadores.put("OR", 3);

        operadores.put("+OR", 4);

        operadores.put("RD", 3);

        operadores.put("+RD", 4);

        operadores.put("RMO", 2);

        operadores.put("RSUB", 3);

        operadores.put("+RSUB", 4);

        operadores.put("SHIFTL", 2);

        operadores.put("SHIFTR", 2);

        operadores.put("STA", 3);

        operadores.put("+STA", 4);

        operadores.put("STB", 3);

        operadores.put("+STB", 4);

        operadores.put("STCH", 3);

        operadores.put("+STCH", 4);

        operadores.put("STL", 3);

        operadores.put("+STL", 4);

        operadores.put("STS", 3);

        operadores.put("+STS", 4);

        operadores.put("STT", 3);

        operadores.put("+STT", 4);

        operadores.put("STX", 3);

        operadores.put("+STX", 4);

        operadores.put("SUB", 3);

        operadores.put("+SUB", 4);

        operadores.put("SUBR", 2);

        operadores.put("TD", 3);

        operadores.put("+TD", 4);

        operadores.put("TIX", 3);

        operadores.put("+TIX", 4);

        operadores.put("TIXR", 2);

        operadores.put("WD", 3);

        operadores.put("+WD", 4);
    }

    public int getFormat(String opcode) {
        String operador = getOperatorFromOpcode(opcode.replace("+", ""));
        if (opcode.contains("+")) {
            return operadores.get("+" + operador).intValue();
        } else {
            return operadores.get(operador).intValue();
        }
    }

    public static String getOperatorFromOpcode(String operator) {
        switch (operator.toUpperCase()) {
            case "18":
                return "ADD";
            case "90":
                return "ADDR";
            case "40":
                return "AND";
            // case "4":
            //     return "CLEAR";
            case "28":
                return "COMP";
            case "A0":
                return "COMPR";
            case "24":
                return "DIV";
            case "9C":
                return "DIVR";
            case "3C":
                return "J";
            case "30":
                return "JEQ";
            case "34":
                return "JGT";
            case "38":
                return "JLT";
            case "48":
                return "JSUB";
            case "0":
                return "LDA";
            case "68":
                return "LDB";
            case "50":
                return "LDCH";
            case "8":
                return "LDL";
            case "6C":
                return "LDS";
            case "74":
                return "LDT";
            // case "4":
            //     return "LDX";
            case "20":
                return "MUL";
            case "98":
                return "MULR";
            case "44":
                return "OR";
            case "AC":
                return "RMO";
            case "4C":
                return "RSUB";
            case "A4":
                return "SHIFTL";
            case "0C":
                return "STA";
            case "78":
                return "STB";
            case "54":
                return "STCH";
            case "7C":
                return "STS";
            case "14":
                return "STL";
            case "84":
                return "STT";
            case "10":
                return "STX";
            case "1C":
                return "SUB";
            case "94":
                return "SUBR";
            case "2C":
                return "TIX";
            case "B8":
                return "TIXR";
            case "B4":
                return "CLEAR";
            case "4":
                return "LDX";
            default:
                return "";
        }


    }

    public static String getOpcodeFromOperator(String operator) {
        switch (operator.toUpperCase()) {
            case "ADD":
                return "18";
            case "ADDR":
                return "90";
            case "AND":
                return "40";
            case "CLEAR":
                return "B4";
            case "COMP":
                return "28";
            case "COMPR":
                return "A0";
            case "DIV":
                return "24";
            case "DIVR":
                return "9C";
            case "J":
                return "3C";
            case "JEQ":
                return "30";
            case "JGT":
                return "34";
            case "JLT":
                return "38";
            case "JSUB":
                return "48";
            case "LDA":
                return "0";
            case "+LDA":
                return "0";
            case "LDB":
                return "68";
            case "LDCH":
                return "50";
            case "LDL":
                return "8";
            case "LDS":
                return "6C";
            case "LDT":
                return "74";
            case "LDX":
                return "4";
            case "MUL":
                return "20";
            case "MULR":
                return "98";
            case "OR":
                return "44";
            case "RMO":
                return "AC";
            case "RSUB":
                return "4C";
            case "SHIFTL":
                return "A4";
            case "STA":
                return "0C";
            case "STB":
                return "78";
            case "STCH":
                return "54";
            case "STS":
                return "7C";
            case "STL":
                return "14";
            case "STT":
                return "84";
            case "STX":
                return "10";
            case "SUB":
                return "1C";
            case "SUBR":
                return "94";
            case "TIX":
                return "2C";
            case "TIXR":
                return "B8";
            default:
                return "null";
        }

    }


}
