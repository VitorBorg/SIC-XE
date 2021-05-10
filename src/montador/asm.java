package src.montador;

import src.Ligador;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

public class asm {

    List<HashMap<String, Integer>> simbolsTable = new ArrayList<HashMap<String, Integer>>();
    List<int[]> tabelaCodigo = new ArrayList<int[]>();

    String[] reservedWords = {"ADD","ADDR","AND","CLEAR","COMP","COMPR","DIV",
    "J","JEQ","JGT","JLT","JSUB","LDA","LDB","LDCH","LDL","LDS","LDT","LDX",
    "MUL","MULR","OR","RMO","RSUB","SHIFTL","SHIFTR","STA","STB","STCH","STL",
    "STS","STT","STX","SUB","SUBR","TIX","TIXR", "SPACE", "CONST", "START", "END"};

    String[] reservedDesv = {"J","JEQ","JGT","JLT"};



    //MONTADOR
    public void asmReader(String asm)
    {
        //List<String> simbols = new ArrayList<>();
        
        try{
            File textFile = new File(asm);
            Scanner scanner = new Scanner(textFile);

            //inicializa tabela codigo

            int ln = stepOne(scanner, reservedWords);

            stepTwo(scanner, reservedWords, ln);

            scanner.close();

            //chama a classe de instrucoes com a entrada sendo a tabela de codigo

        } catch (Exception exception){}

    }


    int stepOne(Scanner scanner, String[] reservedWords){
    System.out.println("\n Passo 1\n");
    int ln = 0; // TEM QUE TIRAR ESSSA MERDA

    try{
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
            ln += 1; 
            Ligador.registradores.setRegs("PC", Ligador.registradores.getRegName("PC").get("PC") + 1);
            int passo = 0;
        
            //é palavra reservada?
            boolean isRes = Arrays.stream(reservedWords).anyMatch(lines[passo]::equals);
            boolean isDesvio = Arrays.stream(reservedDesv).anyMatch(lines[passo]::equals);

            //identifica se tem label ou nao
            if(!isRes){
                //verifica se existe na tabela, insere
                if(search(lines[passo]) != 99999)
                    simbolsTable.get(0).put(lines[passo], null);

                passo += 1;
                isRes = Arrays.stream(reservedWords).anyMatch(lines[passo]::equals);
                isDesvio = Arrays.stream(reservedDesv).anyMatch(lines[passo]::equals);
            }

            //identifica os outros simbolos
            if(isRes){
                if(isDesvio){
                    passo += 1;

                    //pega o valor da linha, e (busca na tabela por nome) seta o valor do simbolo para o valor da linha
                    if(search(lines[passo]) == 99999)
                        simbolsTable.get(0).put(lines[passo], Ligador.registradores.getRegName("PC"));

                } else {
                    passo += 1;
                    //le o proximo valor, que sera um simbolo
                    //verifica se existe na tabela
                    //insere na tabela de simbolos se nao houver
                    if(search(lines[passo]) == 99999)
                        simbolsTable.get(0).put(lines[passo], null);
         
                    //verifica se existe um proximo valor desse lido (pelo tamanho do array)
                    //verifica se existe na tabela
                    //insere na tabela de simbolos se nao houver
                    if(passo < lines.length - 1){
                        if(search(lines[passo]) == 99999)
                            simbolsTable.get(0).put(lines[passo + 1], null);
                    }
                }


            } else{
                //ERRO INSTRUCAO NAO ENCONTRADA
            }        
                //System.out.println("\n Simbolo: " + lines[i]);
                   
        }

        scanner.close();
    } catch (Exception ex){}

    return ln;
    
   }

   void stepTwo(Scanner scanner, String[] reservedWords, int ln){
    System.out.println("\n Passo 2\n");

    try{
        //array para que identifica o opcode de cada linha
        int[] opcodes = new int[ln];   

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
        
            //Identifica os tokens e monta o enderecamento
            for(int i = 0; i < lines.length; i++){
                //é palavra reservada?
                boolean isRes = Arrays.stream(reservedWords).anyMatch(lines[i]::equals);

                if(isRes){
                    //System.out.println("instrucao: " + lines[i]);
                    //Faz switch para identificar a instrucao

                    switch (lines[i].toUpperCase()) {
                        case "ADD":
                        opcodes[i] = 0;
                        //coloca na tabela de codigo, com o respectivo codigo da operacao
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


   int search(String simbolo){
        //HashMap<String, Integer> value = new HashMap<String, Integer>();

        for(int i = 0; i < simbolsTable.size(); i++){
            boolean contains = simbolsTable.get(i).containsKey(simbolo);
            
            if(contains)
                return i;
        }

        if(simbolo.substring(0,1) == "#")
            return 99998;
        else 
            return 99999; //valor definido para ser "null"
   }
}
