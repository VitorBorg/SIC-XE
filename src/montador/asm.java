package src.montador;

import src.Ligador;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import src.Translate;

import java.util.ArrayList;
import java.util.Arrays;

public class asm {

    List<HashMap<String, String>> simbolsTable = new ArrayList<HashMap<String, String>>();

    //OPCODE, OP1, OP2
    List<ArrayList<String>> code = new ArrayList<ArrayList<String>>();

    String[] reservedWords = {"ADD","+ADD","ADDR","AND","+AND","CLEAR","COMP","+COMP","COMPR","DIV","+DIV",
    "J","+J","JEQ","+JEQ","JGT","+JGT","JLT","+JLT","JSUB","+JSUB","LDA","+LDA","LDB","+LDB","LDCH","+LDCH",
    "LDL","+LDL","LDS","+LDS","LDT","+LDT","LDX","+LDX","MUL","+MUL","MULR","OR","+OR","RMO","RSUB","+RSUB",
    "SHIFTL","SHIFTR","STA","+STA","STB","+STB","STCH","+STCH","STL","+STL","STS","+STS","STT","+STT","STX",
    "+STX","SUB","+SUB","SUBR","TIX","+TIX","TIXR"};

    String[] reservedDesv = {"J","JEQ","JGT","JLT"};

    String[] reservedregs = {"A", "X", "L", "B", "S", "PC", "SW"};


    //MONTADOR
    public List<ArrayList<String>> asmReader(String asm)
    {    
            processo(asm, reservedWords);
            setMemory();

            Ligador.variablesSize = simbolsTable.size();


            System.out.println("\n\n");
            for(int i = 0; i < simbolsTable.size(); i++)
                System.out.println("Variavel: " + simbolsTable.get(i));

            System.out.println("\nTABELA DE CODIGO:");
            for(int i = 0; i < code.size(); i++)
                //System.out.println(code.get(i));
                if(code.get(i).size() < 3)
                    System.out.println(code.get(i).get(0) + ", " + code.get(i).get(1));
                else 
                    System.out.println(code.get(i).get(0) + ", " + code.get(i).get(1) + ", " + code.get(i).get(2));
            

            return code;

    }


    int processo(String asm, String[] reservedWords){
    try{
        File textFile = new File(asm);
        Scanner scanner = new Scanner(textFile);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            line = line.replace(",", "");
            String[] lines = line.split(" ");

            String label = "";

            Ligador.registradores.setRegs("PC", Ligador.registradores.getRegName("PC") + 1);

            int passo = 0;
            String operacao, operador1 = "null", operador2 = "null";

            //System.out.println(line);
            //verificao so pra nao tentar ler linha vazia
            if(lines.length > 1){

                boolean isRes = Arrays.stream(reservedWords).anyMatch(lines[passo]::equals);
                //boolean isDesvio = Arrays.stream(reservedDesv).anyMatch(lines[passo]::equals);

                //identifica se tem label ou nao
                if(!isRes){
                    boolean isRegs = Arrays.stream(reservedregs).anyMatch(lines[passo]::equals);

                    if(search(lines[passo]) == 99999){
                        if(isRegs){
                            System.out.println("ERRO!");
                            return 0;
                        }
                        HashMap<String, String> variable = new HashMap<>();
                        variable.put(lines[passo], String.valueOf(Ligador.registradores.getRegName("PC") - 1));

                        simbolsTable.add(variable);

                        label = (simbolsTable.size() - 1) + "";
                    } else {
                        label = search(lines[passo]) + "";
                    }

                    passo += 1;
                    isRes = Arrays.stream(reservedWords).anyMatch(lines[passo]::equals);
                    //isDesvio = Arrays.stream(reservedDesv).anyMatch(lines[passo]::equals);
                }

                if(isRes){
                    //verificacao tipo 3/4
                    if(lines[passo].substring(0,1).equals("+")){
                        String newLine = lines[passo].replace("+", "");

                        operacao = operacao(newLine);
                        operacao = "+" + operacao;
                    } else
                        operacao = operacao(lines[passo]);

                    passo += 1;

                    boolean isRegs = Arrays.stream(reservedregs).anyMatch(lines[passo]::equals);
                                    
                    int busca = search(lines[passo]);
                    //le o proximo valor, que sera um simbolo
                    //verifica se existe na tabela
                    //insere na tabela de simbolos se nao houver
                    if(busca == 99999 && !isRegs){
                        if(lines[passo].substring(0,1).equals("#")){
                            operador1 = String.valueOf(lines[passo]);
                        }else{
                            HashMap<String, String> variable = new HashMap<>();
                            variable.put(lines[passo], null);

                            simbolsTable.add(variable);
                            operador1 = String.valueOf(simbolsTable.size() - 1);
                        }
                    } else if(!isRegs){
                        operador1 = String.valueOf(busca);   
                    } else if (isRegs){
                        operador1 = lines[passo];
                        
                        if(lines.length - 1 == passo + 1)
                            operador2 = lines[passo + 1];        
                    }

                    //INSERE NO ARRAY SEPARADO
                    ArrayList<String> lineCode = new ArrayList<String>();
                    lineCode.add(label);
                    lineCode.add(operacao);
                    lineCode.add(operador1);

                    if(isRegs && (lines.length - 1 == passo + 1)){
                        lineCode.add(operador2);
                        code.add(lineCode);
                    } else{
                        code.add(lineCode);
                    }

                } else{
                    System.out.println("\n\nERRO! TOKEN: " + lines[passo] + "\n\n");
                    return 0;
                } 
            }
        }

        scanner.close();
    } catch (Exception ex){}

    return 1;
   }

   String operacao(String op){
        switch (op.toUpperCase()) {
            case "ADD":
                return "18";
            case "ADDR":
                return "90";
            case "AND":
                return "40";
            case "CLEAR":
                return "4";
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

   int search(String simbolo){
        //HashMap<String, Integer> value = new HashMap<String, Integer>();

        for(int i = 0; i < simbolsTable.size(); i++){
            boolean contains = simbolsTable.get(i).containsKey(simbolo);
            
            if(contains)
                return i;
        }

        return 99999; //valor definido para ser "null"
   }

   void setMemory(){
        for(int i = 0; i < simbolsTable.size(); i++){
            String value = simbolsTable.get(i).values().toString();
            value = value.replace("[", "");
            value = value.replace("]", "");

            if(!value.equals("null"))
                Ligador.memoria.setByte(i, Integer.parseInt(value)); 
        }
   }
}
