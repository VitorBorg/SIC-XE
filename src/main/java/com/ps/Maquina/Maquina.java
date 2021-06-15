package com.ps.Maquina;

import com.ps.App;
import com.ps.Helpers.Helpers;
import com.ps.Translate.Translate;

import java.util.Objects;

public class Maquina {

    public Maquina(){
        maquinaExecucao();
    }

    public void maquinaExecucao() {

        String localS = "00000",n, transferM,transferA;// local inicial da memoria Sring, n qtd para ser deslocada nos shift
        int localI;
        boolean bool;//

        String currentLocalS;

        do {
            localI = Integer.parseInt(localS);//para execuçao de operaçoes

            StringBuilder stringBuilder = new StringBuilder();

            System.out.println("localS " + localS);

            for (String s: App.memoria.getAddress(localS).split("")) {
                stringBuilder.append(Helpers.fillXBits(String.valueOf(Translate.HexToBin(s)),4));
            }

            String format = stringBuilder.substring(11,12).equals("0") ? "3" : "4";
            String opCode6bits = stringBuilder.substring(0,6);
            StringBuilder opCode8bits = new StringBuilder();
            opCode8bits.append(opCode6bits);
            opCode8bits.append("00");

            System.out.println("pt1 = "+ opCode8bits.substring(0,4));
            System.out.println("pt2 = "+ opCode8bits.substring(4,8));

            String hexa1 = Translate.BinToHex(opCode8bits.substring(0,4));
            String hexa2 = Translate.BinToHex(opCode8bits.substring(4,8));
            String fullHexa = hexa1.equals("0") ? hexa2 : hexa1 + hexa2;

            System.out.println(">> fullHexa" + fullHexa);

            String registerAvalue = App.reg.getRegisterValue("A");
            String deslocamentoValue = String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)));

            switch (fullHexa) { //String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))))
                case "18": //ADD
                    App.reg.setRegisterValue("A",
                            String.valueOf(Integer.parseInt(registerAvalue) + Integer.parseInt(deslocamentoValue))
                    );
                    break;
                case "90"://ADDR
                    App.reg.setRegisterValue(RetornaR2(localS),String.valueOf(Integer.parseInt(RetornaR2Value(localS)) + Integer.parseInt(RetornaR1value(localS))));
                    break;
                case "40"://"AND": //valores de getA e m tem q estar em int
                    App.reg.getRegisterValue("A");
                    bool = App.reg.getRegisterValue("A").equals (String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))));
                    if(bool & Integer.parseInt(App.reg.getRegisterValue("A")) > 0)
                        App.reg.setRegisterValue("A", "1");       //n tenho certeza de como and funciona
                    else
                        App.reg.setRegisterValue("A", "0");
                    break;
                case "B4":// "CLEAR"
                    App.reg.setRegisterValue(RetornaR1(localS), "0");
                    break;
                case "28"://"COMP":

                    if(Integer.parseInt(registerAvalue) > Integer.parseInt(deslocamentoValue)) {
                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
                    }else if((Integer.parseInt(App.reg.getRegisterValue("A"))) < Integer.parseInt(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))))) {
                        App.reg.setRegisterValue("SW", "2");// 2 para entrada maior
                    }else{
                        App.reg.setRegisterValue("SW", "3");// se forem iguais
                    }
                    break;
                case "A0"://"COMPR":
                    //(r1) : (r2)
                    if(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) > Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS)))) {
                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
                    }else if(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) < Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS)))) {
                        App.reg.setRegisterValue("SW", "2");// 2 para R2 maior
                    }else{
                        App.reg.setRegisterValue("SW", "3");// se forem iguais
                    }
                    break;
                case "24"://"DIV":
                    App.reg.setRegisterValue("A", String.valueOf((Integer.parseInt(App.reg.getRegisterValue("A"))) / (Integer.parseInt(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))))));
                    //A : (A) / (m..m+24)
                case "9C"://"DIVR":
                    App.reg.setRegisterValue(RetornaR2(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS))) / Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))));
                    break;
                case "3C"://"J":
                    App.reg.setRegisterValue("PC", (App.memoria.getAddress(Translate.DecToHex(localI))));
                    break;
                case "30"://"JEQ":
                    if(Objects.equals(App.reg.getRegisterValue("SW"), "3"))
                        App.reg.setRegisterValue("PC", String.valueOf(App.memoria.getAddress(Translate.DecToHex(localI))));
                    break;
                case "34"://"JGT":
                    if(Objects.equals(App.reg.getRegisterValue("SW"), "1"))
                        App.reg.setRegisterValue("PC", String.valueOf(App.memoria.getAddress(Translate.DecToHex(localI))));
                    break;
                case "38"://"JLT":
                    if(Objects.equals(App.reg.getRegisterValue("SW"), "2"))
                        App.reg.setRegisterValue("PC", (String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))));
                    break;
                case "48"://"JSUB":
                    //L ← (PC); PC ← m < //////  JSUB jumps to the subroutine, placing the return address in register L ////acho q é isso
                    break;
                case "0"://"LDA":
//                    System.out.println("localS " + localS);
//                    System.out.println("Format" + format);
//                    System.out.println("getDeslocamento(localS, format)" + getDeslocamento(localS, format));
                    System.out.println("TIPO LDA");
                    App.reg.setRegisterValue("A",
                            String.valueOf(
                                    Translate.HexToDec(getDeslocamento(localS, format))
                            )
                    );
                    break;
                case "68"://"LDB":
                    App.reg.setRegisterValue("B", (String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))));
                    break;
                case "50"://"LDCH":
                    transferM = App.memoria.getAddress(Translate.DecToHex(localI));
                    transferA = App.reg.getRegisterValue("A").substring(0,16);
                    transferA.concat(transferM);
                    App.reg.setRegisterValue("A",transferA.concat(transferM));
                    ////A [byte mais a direita] ← (m) // sera q da certo ?? ninguem sabe, o @Vitor falo q é isso mesmo, se der merda é na dele!
                    break;
                case "8"://"LDL":
                    App.reg.setRegisterValue("L", (String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))));
                    break;
                case "6C"://"LDS":
                    App.reg.setRegisterValue("S", (String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))));
                    break;
                case "74"://"LDT":
                    App.reg.setRegisterValue("T", (String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))));
                    break;
                case "4"://"LDX":
                    App.reg.setRegisterValue("X", (String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))));
                    break;
                case "20"://"MUL":
                    App.reg.setRegisterValue("A", Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) * (Integer.parseInt(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))))));
                    break;
                case "98"://"MULR":
                    App.reg.setRegisterValue(RetornaR2(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS))) * Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))));
                    break;
                case "44"://"OR":
                    if((Integer.parseInt(App.reg.getRegisterValue("A"))) > 0 || 0 < (Integer.parseInt(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format)))))) {
                        App.reg.setRegisterValue("A", "1");
                    }else{
                        App.reg.setRegisterValue("A", "0");
                    }
                    break;
                case "AC"://"RMO":
                    App.reg.setRegisterValue(RetornaR2(localS), App.reg.getRegisterValue(RetornaR1value(localS)));
                    break;
                case "4C"://"RSUB":
                    App.reg.setRegisterValue("PC", App.reg.getRegisterValue("L"));
                    break;
                case "A4"://"SHIFTL":
                    //Deslocamento a esquerda de n bit
                    n = String.valueOf(Translate.HexToBin((String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))))));
                    n = n.substring(8,12);
                    App.reg.setRegisterValue(RetornaR1(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) << Integer.parseInt(n)));
                    break;
                case "A8"://"SHIFTR":
                    //Deslocamento a esquerda de n bit
                    n = String.valueOf(Translate.HexToBin(((getDeslocamento(localS, format)))));
                    n = n.substring(8,12);
                    App.reg.setRegisterValue(RetornaR1(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) >> Integer.parseInt(n)));
                    break;
                case "0C"://"STA":
                    App.memoria.updateValueFromAddres(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))),App.reg.getRegisterValue("A"));
                    break;
                case "78"://"STB":
                    App.memoria.updateValueFromAddres(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))),App.reg.getRegisterValue("B"));
                    break;
                case "54"://"STCH":
                    transferA = App.reg.getRegisterValue("A").substring(16,24);
                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)),transferA);
                    break;
                case "7C"://"STS":
                    App.memoria.updateValueFromAddres(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))),App.reg.getRegisterValue("S"));
                    break;
                case "14"://"STL":
                    App.memoria.updateValueFromAddres(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))),App.reg.getRegisterValue("L"));
                    break;
                case "84"://"STT":
                    App.memoria.updateValueFromAddres(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))),App.reg.getRegisterValue("T"));
                    break;
                case "10"://"STX":
                    App.memoria.updateValueFromAddres(String.valueOf(Translate.HexToDec(getDeslocamento(localS, format))),App.reg.getRegisterValue("X"));
                    break;
                case "1C"://"SUB":
                    App.reg.setRegisterValue("A", String.valueOf((Integer.parseInt(App.reg.getRegisterValue("A"))) - ((Integer.parseInt((getDeslocamento(localS, format)))))));
                    break;
                case "94"://"SUBR":
                    App.reg.setRegisterValue(RetornaR2(localS), (Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS)))) - (Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))))));
                    break;
                case "2C"://"TIX":
                    App.reg.setRegisterValue("X",String.valueOf(Integer.parseInt(App.reg.getRegisterValue("X")+1)));
                    if((Integer.parseInt(App.reg.getRegisterValue("X"))) > Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+24))))) {
                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
                    }else if((Integer.parseInt(App.reg.getRegisterValue("X"))) < Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+24))))) {
                        App.reg.setRegisterValue("SW", "2");// 2 para entrada maior
                    }else{
                        App.reg.setRegisterValue("SW", "3");// se forem iguais
                    }
                    // X ← (X) + 1; (X) : (m..m+24) NAO SEI OQ ESSA PORRA FAZ!!
                    break;
                case "B8"://"TIXR":
                    //X ← (X) + 1; (X) : (r1) NAO OQ ESSA PORRA FAZ TBM!!
                    App.reg.setRegisterValue("X",String.valueOf(Integer.parseInt(App.reg.getRegisterValue("X")+1)));
                    if(Integer.parseInt(App.reg.getRegisterValue("X")) > Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))) {
                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
                    }else if(Integer.parseInt(App.reg.getRegisterValue("X")) < Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))) {
                        App.reg.setRegisterValue("SW", "2");// 2 para R2 maior
                    }else{
                        App.reg.setRegisterValue("SW", "3");// se forem iguais
                    }
                    break;
                default:
                    System.out.println("\nDeu ruim galera\n");// return 69420;//ou "null";
            }
            currentLocalS = localS;
            localS = Helpers.fillXBits(String.valueOf(localI+24), 5);
        } while(App.memoria.hasNext(currentLocalS));
    }
    private static String RetornaR2(String localS) {
        String hexa = App.memoria.getAddress(localS);
        StringBuilder fullBinario = new StringBuilder();

        for (String h: hexa.split("")) {
            String bina = String.valueOf(Translate.HexToBin(hexa));
            fullBinario.append(bina);
        }

        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
        String valorR2 = App.reg.getRegisterValue(R2);
        String valorR1 = App.reg.getRegisterValue(R1);
        return R2;
    }

    private static String RetornaR1(String localS) {
        String hexa = App.memoria.getAddress(localS);
        StringBuilder fullBinario = new StringBuilder();

        for (String h: hexa.split("")) {
            String bina = String.valueOf(Translate.HexToBin(hexa));
            fullBinario.append(bina);
        }

        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
        String valorR2 = App.reg.getRegisterValue(R2);
        String valorR1 = App.reg.getRegisterValue(R1);
        return R1;
    }
    private static String RetornaR1value(String localS) {

        String hexa = App.memoria.getAddress(localS);
        StringBuilder fullBinario = new StringBuilder();

        for (String h: hexa.split("")) {
            String bina = String.valueOf(Translate.HexToBin(hexa));
            fullBinario.append(bina);
        }

        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
        String valorR2 = App.reg.getRegisterValue(R2);
        String valorR1 = App.reg.getRegisterValue(R1);
        return valorR1;
    }
    private static String RetornaR2Value(String localS) {
        String hexa = App.memoria.getAddress(localS);
        StringBuilder fullBinario = new StringBuilder();

        for (String h: hexa.split("")) {
            String bina = String.valueOf(Translate.HexToBin(hexa));
            fullBinario.append(bina);
        }

        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
        String valorR2 = App.reg.getRegisterValue(R2);
        String valorR1 = App.reg.getRegisterValue(R1);
        return valorR2;
    }

    public String getDeslocamento(String address, String format) {
        StringBuilder stringBuilderInstrucao = new StringBuilder();
        for (String s: App.memoria.getAddress(address).split("")) {
            stringBuilderInstrucao.append(Helpers.fillXBits(String.valueOf(Translate.HexToBin(s)),4));
        }
        String deslocamentoBits = format.equals("3") ? stringBuilderInstrucao.substring(12,24) : stringBuilderInstrucao.substring(12,32);
        String deslocamento = Translate.BinToHex(deslocamentoBits);

        return deslocamento;
    }
}