package src;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import src.instrucoes.Instruction;
import src.memory.Memory;
import src.memory.Regist;
import src.montador.asm;

public class Ligador {

    public static Regist registradores = new Regist();
    public static Memory memoria = new Memory();
    public static Instruction instruction;
    public static List<ArrayList<String>> code = new ArrayList<ArrayList<String>>();
    //Para acessar lista de lista => code.get(0).get(0);

    public static int variablesSize = 0;
    //quantidade de variaveis na memoria

    public static void main(String[] args) {

        asm file = new asm();

        //gera a tabela de codigo, e coloca variaveis na memoria
        code = file.asmReader("D:/documentos/faculdade/ps/SIC-XE/ex1.asm");

        instruction = new Instruction(code, memoria);
        instruction.loadInstruction();
        // memoria.printMemory();
        
        //endereca o codigo e coloca na memoria

        //executa as instrucoes na memoria
        
    }
}
