package com.ps.Helpers;

import com.ps.Translate.Translate;

public class Helpers {

    public static String printIfNotNull(String value){
        return value == null ? "": value;
    }

    static public String fillXBits(String valor, int fillNumber){

        if(valor.length() > fillNumber){
            int bitsExtras = valor.length() - fillNumber;
            return valor.substring(bitsExtras, valor.length());
        }

        StringBuilder valorPreenchido = new StringBuilder();
        while (valorPreenchido.length() < fillNumber - valor.length()) {
            valorPreenchido.append('0');
        }
        valorPreenchido.append(valor);

        return valorPreenchido.toString();
    }

    static public String parseTo4Bits(int bits){
        String bitsToString = String.valueOf(bits);
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 4 - bitsToString.length()) {
            sb.append('0');
        }
        sb.append(bitsToString);

        return sb.toString();
    }

    static public Integer getRegisterNumber(String register) {
        switch(register.toUpperCase()){
            case "A":
                return 0;
            case "X":
                return 1;
            case "L":
                return 2;
            case "B":
                return 3;
            case "S":
                return 4;
            case "T":
                return 5;
            case "F":
                return 6;
            case "PC":
                return 8;
            case "SW":
                return 9;
            default:
                return -1;
        }
    }

    static public String getCodObjeto(String binario){
        int tamanho = binario.length();

        String parte1 = "";
        String parte2 = "";
        String parte3 = "";
        String parte4 = "";
        String parte5 = "";
        String parte6 = "";
        String parte7 = "";
        String parte8 = "";

        StringBuilder codObjeto = new StringBuilder();

        if(tamanho == 16) {
            parte1 = binario.substring(0,4);
            parte2 = binario.substring(4,8);
            parte3 = binario.substring(8,12);
            parte4 = binario.substring(12,16);

            codObjeto.append(Translate.BinToHex(parte1));
            codObjeto.append(Translate.BinToHex(parte2));
            codObjeto.append(Translate.BinToHex(parte3));
            codObjeto.append(Translate.BinToHex(parte4));

            return codObjeto.toString();
        }

         return "";
    }
}
