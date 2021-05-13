package src.helpers;

public class Helper {
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
}
