package src.montador;

import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class asm {


    //MONTADOR
    public static void asmReader(String asm)
    {
        List<String> simbols = new ArrayList<>();

        HashMap<String, String> simbolsTable = new HashMap<String, String>();

        String[] reservedWords = {"ADD","ADDR","AND","CLEAR","COMP","COMPR","DIV",
        "J","JEQ","JGT","JLT","JSUB","LDA","LDB","LDCH","LDL","LDS","LDT","LDX",
        "MUL","MULR","OR","RMO","RSUB","SHIFTL","SHIFTR","STA","STB","STCH","STL",
        "STS","STT","STX","SUB","SUBR","TIX","TIXR", "SPACE", "CONST"};
        
        int ln = stepOne(asm, reservedWords);
        stepTwo(asm, reservedWords, ln);

    }


   static int stepOne(String asm, String[] reservedWords){
    System.out.println("\n Passo 1\n");
    int ln = 0;

    try{
        File textFile = new File(asm);
        Scanner scanner = new Scanner(textFile);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
            ln += 1;
        
            //verificacao dos tokes para reconhecer o que e um simbolo
            for(int i = 0; i < lines.length; i++){
                if(!(Arrays.stream(reservedWords).anyMatch(lines[i]::equals))){
                    //System.out.println("\n Simbolo: " + lines[i]);
                    //Verifica se ja tem, e adiciona a tabela
                }
            }       
        }
    } catch (Exception ex){
    }

    return ln;
    
   }

   static void stepTwo(String asm, String[] reservedWords, int ln){
    System.out.println("\n Passo 2\n");

    try{
        File textFile = new File(asm);
        Scanner scanner = new Scanner(textFile);

        //array para que identifica o opcode de cada linha
        int[] opcodes = new int[ln];   

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
        
            //Identifica os tokens e monta o enderecamento
            for(int i = 0; i < lines.length; i++){
                if(Arrays.stream(reservedWords).anyMatch(lines[i]::equals)){
                    //System.out.println("instrucao: " + lines[i]);
                    //Faz switch para identificar a instrucao

                    switch (lines[i].toUpperCase()) {
                        case "ADD":
                        opcodes[i] = 0;
                            break;
                        case "ADDR":
                        opcodes[i] = 0;
                            break;
                        case "AND":
                        opcodes[i] = 0;
                            break;
                        case "CLEAR":
                        opcodes[i] = 0;
                            break;
                        case "COMP":
                        opcodes[i] = 0;
                            break;
                        case "COMPR":
                        opcodes[i] = 0;
                            break;
                        case "DIV":
                        opcodes[i] = 0;
                            break;
                        case "J":
                        opcodes[i] = 0;
                            break;
                        case "JEQ":
                        opcodes[i] = 0;
                            break;
                        case "JGT":
                        opcodes[i] = 0;
                            break;
                        case "JLT":
                        opcodes[i] = 0;
                            break;
                        case "JSUB":
                        opcodes[i] = 0;
                            break;
                        case "LDA":
                        opcodes[i] = 0;
                            break;
                        case "LDB":
                        opcodes[i] = 0;
                            break;
                        case "LDCH":
                        opcodes[i] = 6;
                            break;
                        case "LDL":
                        opcodes[i] = 0;
                            break;
                        case "LDS":
                        opcodes[i] = 0;
                            break;
                        case "LDT":
                        opcodes[i] = 0;
                            break;
                        case "LDX":
                        opcodes[i] = 0;
                            break;
                        case "MUL":
                        opcodes[i] = 0;
                            break;
                        case "MULR":
                        opcodes[i] = 0;
                            break;
                        case "OR":
                        opcodes[i] = 0;
                            break;
                        case "RMO":
                        opcodes[i] = 0;
                            break;
                        case "RSUB":
                        opcodes[i] = 0;
                            break;
                        case "SHIFTL":
                        opcodes[i] = 0;
                            break;
                        case "STA":
                        opcodes[i] = 0;
                            break;
                        case "STB":
                        opcodes[i] = 0;
                            break;
                        case "STCH":
                        opcodes[i] = 0;
                            break;
                        case "STS":
                        opcodes[i] = 0;
                            break;
                        case "STL":
                        opcodes[i] = 0;
                            break;
                        case "STT":
                        opcodes[i] = 0;
                            break;
                        case "STX":
                        opcodes[i] = 0;
                            break;
                        case "SUB":
                        opcodes[i] = 0;
                            break;
                        case "SUBR":
                        opcodes[i] = 0;
                            break;
                        case "TIX":
                        opcodes[i] = 0;
                            break;
                        case "TIXR":
                        opcodes[i] = 0;
                            break;                                                                          
                        case "CONST":
                        //some
                            break;
                        case "SPACE":
                        //some
                            break;
                        default:
                            break;
                    }
                } else{
                    System.out.println("\nSIMBOLOS");
                    System.out.println("Simbolo: " + lines[i]);
                    //vai buscar o endereco e coloca na tabela
                }
            }       
        }
    } catch (Exception ex){
    }

   }
}
