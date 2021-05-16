package sicxe.Translate;

public class Translate {

    public static int BinToDec(String bin) { //recebe uma string binaria e retorna um int decimal
        
        int Dec = Integer.parseInt(bin, 2);
        return Dec;
    }


    public static int BinToDec(int bin) { //recebe um int binaria e retorna um int decimal
        String BinS = String.valueOf(bin);
        int Dec = Integer.parseInt(BinS, 2);
        return Dec;
    }

    public static int DecToBin(int Dec) {//recebe um int decimal e retorna int binario

        String Bin = Integer.toBinaryString(Dec);
        int BinF = Integer.parseInt(Bin);
        return BinF;
    }

    public static String DecToBin(String Dec) { // recebe string dec retorna int binario

        // String DecS = String.valueOf(Dec);
        // int BinF = Integer.parseInt(DecS);
        // return BinF;
        return Integer.toBinaryString(Integer.parseInt(Dec));
    }

    public static String DecToHex(int Dec) { // recebe um int decimal e retorna um string hexadec, hexdec s� � possivel em formato string

        String HexS = Integer.toHexString(Dec);
        return HexS;
    }

    public static int HexToDec(String Hex) {// recebe um int em dec e retorna string em hex

        int DecF = Integer.parseInt(Hex, 16);
        return DecF;
    }

    public static String BinToHex(String bin) {//recebe uma string binaria, transforma em decimal e transforma a deciaml em hex, retorna hex

        int dec = BinToDec(bin);
        String Hex = DecToHex(dec);
        return Hex;
    }

    public static int HexToBin(String Hex) {//recebe uma string hex, transforma em decimal, e retorna binario

        int Dec = HexToDec(Hex);
        int Bin = DecToBin(Dec);
        return Bin;
    }

    public static String IntToString(int x) { // converte qqer int para um string

        String str = String.valueOf(x);
        return str;
    }
}
