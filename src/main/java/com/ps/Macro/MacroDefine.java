package com.ps.Macro;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.*; 
import java.util.Scanner;


import com.ps.Macro.Macrofunc;
import com.ps.Table.Line;

public class MacroDefine {

    public List<String> codeMacro = new ArrayList<String>();
    public List<String> codeTemp = new ArrayList<String>();

    public List<String> functionsWords = new ArrayList<String>();
    public List<String> functionsWordsReserved = new ArrayList<String>();

    public List<Macrofunc> hideMacros = new ArrayList<Macrofunc>();

    String[] reservedWords = {"ADD","+ADD","ADDR","AND","+AND","CLEAR","COMP","+COMP","COMPR","DIV","+DIV",
    "J","+J","JEQ","+JEQ","JGT","+JGT","JLT","+JLT","JSUB","+JSUB","LDA","+LDA","LDB","+LDB","LDCH","+LDCH",
    "LDL","+LDL","LDS","+LDS","LDT","+LDT","LDX","+LDX","MUL","+MUL","MULR","OR","+OR","RMO","RSUB","+RSUB",
    "SHIFTL","SHIFTR","STA","+STA","STB","+STB","STCH","+STCH","STL","+STL","STS","+STS","STT","+STT","STX",
    "+STX","SUB","+SUB","SUBR","TIX","+TIX","TIXR"};


    public static void main(String[] args) throws FileNotFoundException {
        MacroDefine magic = new MacroDefine();
        magic.macroInitialize();
    }

    public void macroInitialize(){

        //codeMacro le o arquivo com as macros ainda definidas e salva
        codeMacro = readerDef("F://Arquivos programacao//sic-final//teste2.asm");
        String sicDef = sicFile(codeMacro);

        //criamos um arquivo temporario 
        try{
            String path = "F://Arquivos programacao//sic-final//temp.asm";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    
            writer.write(sicDef);
            writer.close();
                
        } catch(Exception e){
            System.out.println("ERRO: PROBLEMA NA ESCRITA");
            System.out.println(e);
        }

        //seta nome das nossas macros em uma lista
        funcWords();
        

        //codeTempo le o arquivo sem as def das macros
        codeTemp = reader("F://Arquivos programacao//sic-final//temp.asm");
        String sic = sicFile(codeTemp);

        //escrever arquivo
        try{
            String path = "F://Arquivos programacao//sic-final//teste2Macro.sic";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            writer.write(sic);
            writer.close();
            
        } catch(Exception e){
            System.out.println(e);
        } 
           
    }

 List<String> readerDef(String code){

    List<String> newCode = new ArrayList<String>();

    try{
        File textFile = new File(code);
        Scanner scanner = new Scanner(textFile);

        boolean insideMacro = false;
        boolean def = false;
        int pos = 0;
        int ln = 0;

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            if(!insideMacro && line.equals("MACRO")) //estamos lendo a linha "MACRO"
                insideMacro = true;
            else if(insideMacro && line.equals("MEND")){ //fim da macro, buscamos por outra
                //final dessa macro
                insideMacro = false;
                def = false;
                pos += 1;
                ln = 0;
            }
            else if(!insideMacro && !line.equals("MACRO")){ //acabaram as definições de macro
                if (!line.isBlank())
                    newCode.add(line); //vamos retornar todas as linhas que nao sao definicoes de macro
            }
            else if(insideMacro && !def){ //estamos lendo a linha de definicao da macro
                def = true;
                //NOMEDAMACRO &VAR, &VAR, &VAR, ETC...
                processDef(line, pos);
            }
            else if(insideMacro && def){ //estamos lendo o conteudo da macro
                //LDA ARG1
                //ADD ARG2, ARG3
                hideMacros.get(pos).setContentLine();
                processContent(line, pos, ln);
                ln+=1;
            }
        }           
            scanner.close();

            return(newCode);
    } catch(Exception ex){
            System.out.println("ERRO: PROBLEMA NA IDENTIFICACAO DOS MACROS, " + ex);
            return(newCode);
    }

}

    void processDef(String text, int index){
        String nome = "";
        List<String> argsTemp = new ArrayList<String>();

        //functionsWordsReserved
        String words = text;
        words = words.replace(",", "");
        words = words.replace("&", "");
        String[] splitWords = words.split(" ");

        //System.out.println("Nome da Macro: " + splitWords[0]);
        for(int i=0; i < splitWords.length; i++){
            if(i == 0)
                nome = splitWords[i];
            else
                argsTemp.add(splitWords[i]);    
        }

        Macrofunc temp = new Macrofunc(nome);
        temp.addArgs(argsTemp);
        hideMacros.add(temp);
    }

    void processContent(String text, int position, int ln){
        boolean codigo = false;


        //tokens dos comandos
        String words = text;
        words = words.replace(",", "");
        words = words.replace("&", "");
        String[] splitWords = words.split(" ");

        //anyMatch deu problema pro meu jdk
        for(int t=0; t < reservedWords.length; t++){
            if(splitWords[0].equals(reservedWords[t])){
                codigo = true;
                break;
            }
        }
        if(!codigo) return;

        //argumentos
        int argsSize = splitWords.length - 1;
        hideMacros.get(position).setNumArgsByLine(argsSize);

        //adicionando o conteudo
        for(int v = 0; v < splitWords.length; v++)
            hideMacros.get(position).setContent(ln, splitWords[v]);
                
    }

    String sicFile(List<String> textFile){
        String sic = "";

        for(int i=0; i < textFile.size(); i++)
            sic += textFile.get(i) + "\n";
        

        return sic;
    }

    List<String> reader(String code){
        List<String> newCode = new ArrayList<String>();

        try{
            File textFile = new File(code);
            Scanner scanner = new Scanner(textFile);

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();

                //System.out.println("\n linha: " + line);
                String openMacro = process(line);

                String error = openMacro.substring(0,4);

                if(error.equals("ERRO")){
                    System.out.println(openMacro);
                    break;
                }

                newCode.add(openMacro);
            }            
                scanner.close();

                //for(int i = 0; i < newCode.size(); i++)
                //    System.out.println(newCode.get(i));

                return(newCode);
        } catch(Exception ex){
                System.out.println("ERRO: O ARQUIVO ESTA CORROMPIDO");
                return(newCode);
        }
    }

    String process(String text){

        for(int i = 0; i < functionsWords.size(); i++)
            if(text.toUpperCase().indexOf(functionsWords.get(i).toUpperCase()) != -1){
                String args = text;

                args = args.replace(functionsWords.get(i), "");
                //por algum motivo ele fez a substituicao anterior por um espaco
                args = args.substring(1, args.length());

                args = args.replace(",", "");
                //args = args.replace("&", "");
                String[] splitArgs = args.split(" ");

                
                //System.out.println("Argumentos: ");
                //for(int t = 0; t < lines.length; t++)
                 //   System.out.println(lines[t]);

                //System.out.println("processada: " + "\n" + expand(functionsWords[i], splitArgs, text));
                String open = expand(functionsWords.get(i), splitArgs, text);
                //System.out.println("Open Shit:\n" + open);
                return open;
            }

        return text;

    }
    
    String expand(String name, String[] argumentos, String text){
       // System.out.println("name: " + name + ", texto: " + text);

       //List<HashMap<String, String>> simbolsTable

       String exp = "";
       String expTotal = "";
       int index = 0;

        //para pegar o conteudo correto
        for(int v = 0; v < hideMacros.size(); v++){
            if(name.equals(hideMacros.get(v).getName()))
                index = v;
        }

        //lendo as linhas do conteudo da minha macro
        for(int i = 0; i < hideMacros.get(index).getContentSize(); i++){

            //verifica qual o index do argumento na definicao
            for(int t = 0; t < hideMacros.get(index).getContentSizeElement(i); t++){
                if(t == 0)
                    exp = hideMacros.get(index).getContentOfContent(i, 0);
                else{
                    String arg = hideMacros.get(index).getContentOfContent(i, t);

                    for(int c = 0; c < hideMacros.get(index).getArgsSize(); c++){
                        if(arg.equals(hideMacros.get(index).getArg(c))){

                            if(t < 2)
                                exp = exp + " " + argumentos[c];
                            else   
                                exp = exp + ", " + argumentos[c];
                        }
                    }
                }

            }
            //System.out.println("LINHA RENDERIZADA:\n " + exp + "\n\n");
            if(expTotal.length()==0){
                expTotal = expTotal + exp;
            }
            else 
                expTotal = expTotal + "\n" + exp;
        }
        //System.out.println("LINHA RENDERIZADA:\n" + expTotal + "\n\n");
        return expTotal;

    }

    void funcWords(){
        for(int i = 0; i < hideMacros.size(); i++){
            functionsWords.add(hideMacros.get(i).getName());
        }
    }
}