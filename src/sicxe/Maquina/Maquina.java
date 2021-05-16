package sicxe.Maquina;

import sicxe.App;
import sicxe.Translate.*;
import sicxe.Operador.*;
import sicxe.Memory.*;

public class Maquina {

    public static void main(String[] args) {

        int contador = 0;

        //LER MEMORIA: VINI ############################

        //metodo para ler memoria!!!!
        //System.out.println(Translate.HexToBin(memoria.substring(0, 2)));

        /*
                       ------ OQ FALTA FAZER --------
        1 - definir oq/como vai ser o (m..m+2) // ta definido m == possiçao atual da memoria (bytes), m+2 2 bytes a frente
        2 - alterar a saida dos get de string para fazer as operações // facil, o problema é os metodo pra ler as op
        3 - conferir o formato // yup conferido
        4 - tem operações comentadas DECIDIR oq fazer com elas ou como fazer // decisão: apagar e ignorar, rezar pra nunca ser usada
        5 - Ler a memoria até o final e ficar executando // em progresso
        6 - fazer uso de variaveis quando tiver nos labels // UQ ?
        */

                                //VVVVVVVVVVVVVVVVVVVVVVV//
        String memoria ="A0B4";// Memory.getAddress("123"); //"A0B4";//entrada, muda quando a memoria tiver pronta
        String BinMemFull = Translate.IntToString(Translate.HexToBin(memoria.substring(0, 4)));

        String BinMem = Translate.IntToString(Translate.HexToBin(memoria.substring(0, 2)));
        BinMem = BinMem.substring(0,6);

        String comparador = "";//pra onde vai isso ?? n da pra dar return nos switch tem
        String localS = "00000";// local inicial da memoria Sring
        int localI = Integer.parseInt(localS); // local inicial memoria int
        boolean bool;//

while(App.memoria.hasNext(localS)==true){
    localI = Integer.parseInt(localS);//para execuçao de operaçoes

    switch (Translate.BinToHex(BinMem)) {
        case "18": //inverti os coisa
            App.reg.setRegisterValue("A", Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) + ((Integer.parseInt(App.memoria.getAddress(Translate.DecToHex(localI+2)))))));
            break;
        case "90":
            App.reg.setRegisterValue("R2", App.reg.getRegisterValue("R2") + App.reg.getRegisterValue("R1"));
            break;
        case "40"://"AND": //valores de getA e m tem q estar em int
            bool = App.reg.getRegisterValue("A").equals (App.memoria.getAddress(Translate.DecToHex(localI+2)));
            if(bool)
                App.reg.setRegisterValue("A", "1");//n tenho certeza de como and funciona
            if (!bool)
                App.reg.setRegisterValue("A", "0");
            break;
        case "4":// "CLEAR"
            App.reg.setRegisterValue("R1", "0");
            break;
        case "28"://"COMP":
            if((Integer.parseInt(App.reg.getRegisterValue("A"))) > Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+2))))){
                comparador = ">";
            }else if((Integer.parseInt(App.reg.getRegisterValue("A"))) < Integer.parseInt((App.memoria.getAddress(Translate.DecToHex(localI+2))))){
                comparador = "<";
            }else{
                comparador = "=";
            }
            //A : (m..m+2) comparação?// tmb n sei (vini)
            break;                      //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
            //////////////////tem q manda o comparador pra algum lugar mas n sei pra onde... n pode ser return precisa de um metodo/
        case "A0"://"COMPR":
            //(r1) : (r2)
            if(Integer.parseInt(App.reg.getRegisterValue("R1")) > Integer.parseInt(App.reg.getRegisterValue("R2"))){
                comparador = ">";
            }else if(Integer.parseInt(App.reg.getRegisterValue("R1")) < Integer.parseInt(App.reg.getRegisterValue("R2"))){
                comparador = "<";
            }else{
                comparador = "=";
            }
            break;
        //case "24"://"DIV":
        //A : (A) / (m..m+2)#############################################
        //return comparador;//N SEI OQ RETORNA ///////////////////////
        case "9C"://"DIVR":
            App.reg.setRegisterValue("R2", (App.reg.getRegisterValue("R2") / App.reg.getRegisterValue("R1")));
            break;
        case "3C"://"J":
            App.reg.setRegisterValue("PC", (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            break;
        case "30"://"JEQ":
            if(comparador == "="){
                App.reg.setRegisterValue("PC", (App.reg.getRegisterValue("PC") = (App.memoria.getAddress(Translate.DecToHex(localI+2))) ));
            }
            break;
        case "34"://"JGT":
            if(comparador == ">"){
                App.reg.setRegisterValue("PC", (App.reg.getRegisterValue("PC") = (App.memoria.getAddress(Translate.DecToHex(localI+2))) ));
            }
            break;
        case "38"://"JLT":
            if(comparador == "<"){
                App.reg.setRegisterValue("PC", (App.reg.getRegisterValue("PC") = (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            }
            break;
        //case "48"://"JSUB":
        //L ← (PC); PC ← m<               #####################################
        //    return "48";
        case "0"://"LDA":
            App.reg.setRegisterValue("A", (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            break;
        case "LDB"://"LDB":
            App.reg.setRegisterValue("B", (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            break;
        //case "50"://"LDCH":
        ////A [byte mais a direita] ← (m) ###############################################
        //    return "50";
        case "8"://"LDL":
            App.reg.setRegisterValue("L", (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            break;
        case "6C"://"LDS":
            App.reg.setRegisterValue("S", (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            break;
        case "74"://"LDT":
            App.reg.setRegisterValue("T", (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            break;
        case "4"://"LDX":
            App.reg.setRegisterValue("X", (App.memoria.getAddress(Translate.DecToHex(localI+2))));
            break;
        case "20"://"MUL":
            App.reg.setRegisterValue("A", Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) * ((Integer.parseInt(App.memoria.getAddress(Translate.DecToHex(localI+2))))));
            break;
        case "98"://"MULR":
            App.reg.setRegisterValue("R2", (App.reg.getRegisterValue("R2") * App.reg.getRegisterValue("R1")));
            break;
        case "44"://"OR":
            //valores de getA e m tem q estar em int
            App.reg.setRegisterValue("A", (App.reg.getRegisterValue("A") | (App.memoria.getAddress(Translate.DecToHex(localI+2))))); ////////////////////////////
            break;
        case "AC"://"RMO":
            App.reg.setRegisterValue("R2", App.reg.getRegisterValue("R1"));
            break;
        case "4C"://"RSUB":
            App.reg.setRegisterValue("PC", App.reg.getRegisterValue("L"));
            break;
        case "A4"://"SHIFTL":
            //Deslocamento a esquerda de n bit
            Integer.parseInt(n);
            App.reg.setRegisterValue("R1", App.reg.getRegisterValue("R1") << n);
            break;
        case "A8"://"SHIFTR":
            //Deslocamento a direita de n bit
            Integer.parseInt(n);
            App.reg.setRegisterValue("R1", (App.reg.getRegisterValue("R1") >> n));
            break;
        case "0C"://"STA":
            (App.memoria.getAddress(Translate.DecToHex(localI+2))) = App.reg.getRegisterValue("A");
            (App.memoria.getAddress(Translate.DecToHex(localI+2)));
            break;
        case "78"://"STB":
            (App.memoria.getAddress(Translate.DecToHex(localI+2))) = App.reg.getRegisterValue("B");
            (App.memoria.getAddress(Translate.DecToHex(localI+2)));
            break;
        case "54"://"STCH":
            (App.memoria.getAddress(Translate.DecToHex(localI))) = App.reg.getRegisterValue("A");
            (App.memoria.getAddress(Translate.DecToHex(localI)));
            break;
        case "7C"://"STS":
            (App.memoria.getAddress(Translate.DecToHex(localI+2))) = App.reg.getRegisterValue("S");
            break;
        case "14"://"STL":
            (App.memoria.getAddress(Translate.DecToHex(localI+2))) = App.reg.getRegisterValue("L");
            break;
        case "84"://"STT":
            (App.memoria.getAddress(Translate.DecToHex(localI+2))) = App.reg.getRegisterValue("T");
            break;
        case "10"://"STX":
            (App.memoria.getAddress(Translate.DecToHex(localI+2))) = App.reg.getRegisterValue("X");
            break;
        case "1C"://"SUB":
            App.reg.setRegisterValue("A", Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) - ((Integer.parseInt(App.memoria.getAddress(Translate.DecToHex(localI+2)))))));
            break;
        case "94"://"SUBR":
            App.reg.setRegisterValue("A", (Translate.IntToString((Integer.parseInt(App.reg.getRegisterValue("A"))) - App.reg.getRegisterValue("R1")))
            break;
        //case "2C"://"TIX":
        // X ← (X) + 1; (X) : (m..m+2) NAO SEI OQ ESSA PORRA FAZ!!
        //  break;
        //case "B8"://"TIXR":
        //X ← (X) + 1; (X) : (r1) NAO OQ ESSA PORRA FAZ TBM!!
        //  break;
        default:
           System.out.println("\nDeu ruim galera\n");// return 69420;//ou "null";
    }
    localS = localS+3; //depende do tipo de formato (2.3.4) tem q avançar diferente, +3 serve pra alguns ? :/
}
        //aqui passar com +
        if(BinMemFull.substring(11,12) == "1"){ //pegar direto todos os bits de ex: A0B4 bbit num 12 = N, se N=1 getformatfromopcode recebe +BitMem(6)
            //em hex
            BinMem = Operador.getOperatorFromOpcode("+"+Translate.BinToHex(BinMem));
        }else{
            BinMem = Operador.getOperatorFromOpcode(Translate.BinToHex(BinMem));
        }
        switch (BinMem){
            case "1":
                contador += 2;
                return contador;
            case "2":
                contador += 4;
                return contador;
            case "3":
                contador += 6;
                return contador;
            case "4":
                contador += 8;
                return contador;
        }

        /*
        ver se n = 1 manda com +

    ja vou receber pronto
    da memoria(hex 2 char)
    varios if pra executer as coisa
    A = 4 primeiros bit e 0 os 2 primeiros ?
    transforma pra Bin
    */

    }
}