//package com.ps.Maquina;
//
//import com.ps.App;
//import com.ps.Translate.*;
//import com.ps.Operador.*;
//import com.ps.Memory.*;
//
//import java.util.Objects;
//
//public class Maquina {
//
//    public static void maquinaExecucao() {
//
//        /*
//                       ------ OQ FALTA FAZER --------
//        1 - definir oq/como vai ser o (m..m+24) // ta definido m == possiçao atual da memoria (bytes), m+24 2 bytes a frente
//        2 - alterar a saida dos get de string para fazer as operações // facil, o problema é os metodo pra ler as op
//        3 - conferir o formato // yup conferido
//        4 - tem operações comentadas DECIDIR oq fazer com elas ou como fazer // decisão: apagar e ignorar, rezar pra nunca ser usada
//        5 - Ler a memoria até o final e ficar executando // em progresso
//        6 - fazer uso de variaveis quando tiver nos labels // UQ ?
//        */
//
//        //VVVVVVVVVVVVVVVVVVVVVVV//
//        String memoria ="A0B4";// Memory.getAddress("123"); //"A0B4";//entrada, muda quando a memoria tiver pronta
//        String BinMemFull = Translate.IntToString(Translate.HexToBin(memoria.substring(0, 4)));
//
//        String BinMem = Translate.IntToString(Translate.HexToBin(memoria.substring(0, 2)));
//        BinMem = BinMem.substring(0,6);
//
//        String localS = "00000",n, transferM,transferA;// local inicial da memoria Sring, n qtd para ser deslocada nos shift
//        int localI = Integer.parseInt(localS); // local inicial memoria int
//        boolean bool;//
//
//        while(App.memoria.hasNext(localS)==true){
//            localI = Integer.parseInt(localS);//para execuçao de operaçoes
//
//            switch (Translate.BinToHex(BinMem)) {
//                case "18": //ADD
//                    App.reg.setRegisterValue("A", Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) + ((Integer.parseInt(App.memoria.getAddress(Translate.DecToHex(localI+24)))))));
//                    break;
//                case "90"://ADDR
//                    App.reg.setRegisterValue(RetornaR2(localS),String.valueOf(Integer.parseInt(RetornaR2Value(localS)) + Integer.parseInt(RetornaR1value(localS))));
//                    break;
//                case "40"://"AND": //valores de getA e m tem q estar em int
//                    App.reg.getRegisterValue("A");
//                    bool = App.reg.getRegisterValue("A").equals (App.memoria.getAddress(Translate.DecToHex(localI+24)));
//                    if(bool & Integer.parseInt(App.reg.getRegisterValue("A")) > 0)
//                        App.reg.setRegisterValue("A", "1");       //n tenho certeza de como and funciona
//                    else
//                        App.reg.setRegisterValue("A", "0");
//                    break;
//                case "B4":// "CLEAR"
//                    App.reg.setRegisterValue(RetornaR1(localS), "0");
//                    break;
//                case "28"://"COMP":
//                    if((Integer.parseInt(App.reg.getRegisterValue("A"))) > Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+24))))){
//                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
//                    }else if((Integer.parseInt(App.reg.getRegisterValue("A"))) < Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+24))))){
//                        App.reg.setRegisterValue("SW", "2");// 2 para entrada maior
//                    }else{
//                        App.reg.setRegisterValue("SW", "3");// se forem iguais
//                    }
//                    break;
//                case "A0"://"COMPR":
//                    //(r1) : (r2)
//                    if(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) > Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS)))){
//                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
//                    }else if(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) < Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS)))){
//                        App.reg.setRegisterValue("SW", "2");// 2 para R2 maior
//                    }else{
//                        App.reg.setRegisterValue("SW", "3");// se forem iguais
//                    }
//                    break;
//                case "24"://"DIV":
//                    App.reg.setRegisterValue("A", String.valueOf((Integer.parseInt(App.reg.getRegisterValue("A"))) / (Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+24)))))));
//                    //A : (A) / (m..m+24)
//                case "9C"://"DIVR":
//                    App.reg.setRegisterValue(RetornaR2(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS))) / Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))));
//                    break;
//                case "3C"://"J":
//                    App.reg.setRegisterValue("PC", (App.memoria.getAddress(Translate.DecToHex(localI))));
//                    break;
//                case "30"://"JEQ":
//                    if(Objects.equals(App.reg.getRegisterValue("SW"), "3"))
//                        App.reg.setRegisterValue("PC", String.valueOf(App.memoria.getAddress(Translate.DecToHex(localI))));
//                    break;
//                case "34"://"JGT":
//                    if(Objects.equals(App.reg.getRegisterValue("SW"), "1"))
//                        App.reg.setRegisterValue("PC", String.valueOf(App.memoria.getAddress(Translate.DecToHex(localI))));
//                    break;
//                case "38"://"JLT":
//                    if(Objects.equals(App.reg.getRegisterValue("SW"), "2"))
//                        App.reg.setRegisterValue("PC", String.valueOf(App.memoria.getAddress(Translate.DecToHex(localI+24))));
//                    break;
//                case "48"://"JSUB":
//                    //L ← (PC); PC ← m < //////  JSUB jumps to the subroutine, placing the return address in register L ////acho q é isso
//                    break;
//                case "0"://"LDA":
//                    App.reg.setRegisterValue("A", (App.memoria.getAddress(Translate.DecToHex(localI+24))));
//                    break;
//                case "LDB"://"LDB":
//                    App.reg.setRegisterValue("B", (App.memoria.getAddress(Translate.DecToHex(localI+24))));
//                    break;
//                case "50"://"LDCH":
//                    transferM = App.memoria.getAddress(Translate.DecToHex(localI));
//                    transferA = App.reg.getRegisterValue("A").substring(0,16);
//                    transferA.concat(transferM);
//                    App.reg.setRegisterValue("A",transferA.concat(transferM));
//                    ////A [byte mais a direita] ← (m) // sera q da certo ?? ninguem sabe, o @Vitor falo q é isso mesmo, se der merda é na dele!
//                    break;
//                case "8"://"LDL":
//                    App.reg.setRegisterValue("L", (App.memoria.getAddress(Translate.DecToHex(localI+24))));
//                    break;
//                case "6C"://"LDS":
//                    App.reg.setRegisterValue("S", (App.memoria.getAddress(Translate.DecToHex(localI+24))));
//                    break;
//                case "74"://"LDT":
//                    App.reg.setRegisterValue("T", (App.memoria.getAddress(Translate.DecToHex(localI+24))));
//                    break;
//                case "4"://"LDX":
//                    App.reg.setRegisterValue("X", (App.memoria.getAddress(Translate.DecToHex(localI+24))));
//                    break;
//                case "20"://"MUL":
//                    App.reg.setRegisterValue("A", Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) * (Integer.parseInt(App.memoria.getAddress(Translate.DecToHex(localI+24))))));
//                    break;
//                case "98"://"MULR":
//                    App.reg.setRegisterValue(RetornaR2(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS))) * Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))));
//                    break;
//                case "44"://"OR":
//                    if((Integer.parseInt(App.reg.getRegisterValue("A"))) > 0 || 0 < (Integer.parseInt(App.memoria.getAddress(Translate.DecToHex(localI+24)))))
//                        App.reg.setRegisterValue("A", "1");
//                    else{
//                        App.reg.setRegisterValue("A", "0");
//                    }
//                    break;
//                case "AC"://"RMO":
//                    App.reg.setRegisterValue(RetornaR2(localS), App.reg.getRegisterValue(RetornaR1value(localS)));
//                    break;
//                case "4C"://"RSUB":
//                    App.reg.setRegisterValue("PC", App.reg.getRegisterValue("L"));
//                    break;
//                case "A4"://"SHIFTL":
//                    //Deslocamento a esquerda de n bit
//                    n = String.valueOf(Translate.HexToBin((App.memoria.getAddress(Translate.DecToHex(localI+24)))));
//                    n = n.substring(8,12);
//                    App.reg.setRegisterValue(RetornaR1(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) << Integer.parseInt(n)));
//                    break;
//                case "A8"://"SHIFTR":
//                    //Deslocamento a direita de n bit
//                    n = String.valueOf(Translate.HexToBin((App.memoria.getAddress(Translate.DecToHex(localI+24)))));
//                    n = n.substring(8,12);
//                    App.reg.setRegisterValue(RetornaR1(localS), String.valueOf(Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS))) >> Integer.parseInt(n)));
//                    break;
//                case "0C"://"STA":
//                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)+48),App.reg.getRegisterValue("A"));
//                    break;
//                case "78"://"STB":
//                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)+48),App.reg.getRegisterValue("B"));
//                    break;
//                case "54"://"STCH":
//                    transferA = App.reg.getRegisterValue("A").substring(16,24);
//                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)),transferA);
//                    break;
//                case "7C"://"STS":
//                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)+48),App.reg.getRegisterValue("S"));
//                    break;
//                case "14"://"STL":
//                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)+48),App.reg.getRegisterValue("L"));
//                    break;
//                case "84"://"STT":
//                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)+48),App.reg.getRegisterValue("T"));
//                    break;
//                case "10"://"STX":
//                    App.memoria.updateValueFromAddres(String.valueOf(Integer.parseInt(localS)+48),App.reg.getRegisterValue("X"));
//                    break;
//                case "1C"://"SUB":
//                    App.reg.setRegisterValue("A", Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) - ((Integer.parseInt(App.memoria.getAddress(Translate.DecToHex(localI+24)))))));
//                    break;
//                case "94"://"SUBR":
//                    App.reg.setRegisterValue(RetornaR2(localS), (Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue(RetornaR2Value(localS)))) - (Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))))));
//                    break;
//                case "2C"://"TIX":
//                    App.reg.setRegisterValue("X",String.valueOf(Integer.parseInt(App.reg.getRegisterValue("X")+1)));
//                    if((Integer.parseInt(App.reg.getRegisterValue("X"))) > Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+24))))){
//                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
//                    }else if((Integer.parseInt(App.reg.getRegisterValue("X"))) < Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+24))))){
//                        App.reg.setRegisterValue("SW", "2");// 2 para entrada maior
//                    }else{
//                        App.reg.setRegisterValue("SW", "3");// se forem iguais
//                    }
//                    // X ← (X) + 1; (X) : (m..m+24) NAO SEI OQ ESSA PORRA FAZ!!
//                    break;
//                case "B8"://"TIXR":
//                    //X ← (X) + 1; (X) : (r1) NAO OQ ESSA PORRA FAZ TBM!!
//                    App.reg.setRegisterValue("X",String.valueOf(Integer.parseInt(App.reg.getRegisterValue("X")+1)));
//                    if(Integer.parseInt(App.reg.getRegisterValue("X")) > Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))){
//                        App.reg.setRegisterValue("SW", "1"); // 1 para A maior
//                    }else if(Integer.parseInt(App.reg.getRegisterValue("X")) < Integer.parseInt(App.reg.getRegisterValue(RetornaR1value(localS)))){
//                        App.reg.setRegisterValue("SW", "2");// 2 para R2 maior
//                    }else{
//                        App.reg.setRegisterValue("SW", "3");// se forem iguais
//                    }
//                    break;
//                default:
//                    System.out.println("\nDeu ruim galera\n");// return 69420;//ou "null";
//            }
//            //incrementar PC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            localS = localS+24;
//        }
//    }
//    static private String RetornaR2(String localS){ // só pro Hartur :)
//        String hexa = App.memoria.getAddress(localS);
//        StringBuilder fullBinario = new StringBuilder();
//
//        for (String h: hexa.split("")){
//            String bina = String.valueOf(Translate.HexToBin(hexa));
//            fullBinario.append(bina);
//        }
//
//        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
//        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
//        String valorR2 = App.reg.getRegisterValue(R2);
//        String valorR1 = App.reg.getRegisterValue(R1);
//        return R2;
//    }
//
//    private static String RetornaR1(String localS){
//        String hexa = App.memoria.getAddress(localS);
//        StringBuilder fullBinario = new StringBuilder();
//
//        for (String h: hexa.split("")){
//            String bina = String.valueOf(Translate.HexToBin(hexa));
//            fullBinario.append(bina);
//        }
//
//        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
//        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
//        String valorR2 = App.reg.getRegisterValue(R2);
//        String valorR1 = App.reg.getRegisterValue(R1);
//        return R1;
//    }
//    private static String RetornaR1value(String localS){
//        String hexa = App.memoria.getAddress(localS);
//        StringBuilder fullBinario = new StringBuilder();
//
//        for (String h: hexa.split("")){
//            String bina = String.valueOf(Translate.HexToBin(hexa));
//            fullBinario.append(bina);
//        }
//
//        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
//        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
//        String valorR2 = App.reg.getRegisterValue(R2);
//        String valorR1 = App.reg.getRegisterValue(R1);
//        return valorR1;
//    }
//    private static String RetornaR2Value(String localS){
//        String hexa = App.memoria.getAddress(localS);
//        StringBuilder fullBinario = new StringBuilder();
//
//        for (String h: hexa.split("")){
//            String bina = String.valueOf(Translate.HexToBin(hexa));
//            fullBinario.append(bina);
//        }
//
//        String R2 = Translate.BinToHex(fullBinario.substring(12,16));
//        String R1 = Translate.BinToHex(fullBinario.substring(8,12));
//        String valorR2 = App.reg.getRegisterValue(R2);
//        String valorR1 = App.reg.getRegisterValue(R1);
//        return valorR2;
//    }
//}