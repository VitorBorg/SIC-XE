package src.instrucoes;

import java.util.ArrayList;
import java.util.List;

import src.Translate;
import src.memory.Memory;
import src.operator.Operador;

public class Instruction {

    private List<ArrayList<String>> codes;
    private Memory memory;

    private Operador operador;
    
    public Instruction(List<ArrayList<String>> codes, Memory memory){
        this.codes = codes;
        this.memory = memory;
        this.operador = new Operador();
    }

    public void loadInstruction(){
        System.out.println("Codigos e formatos");
        for(ArrayList<String> code: codes){
            decodeInstruction(code);
        }
    }

    void decodeInstruction(ArrayList<String> code){
        // PROBLEMA: O MONTADOR NAO RECONHECE INSTRUCOES DO TIPO 1 (SEM OPERANDOS) --> OBS: NAO TEM NENHUM NA TABELA COM TIPO 1
        int formatOfInstruction = this.operador.getFormat(code.get(0), code.size());
        if(formatOfInstruction == 1){ // formato 1 (opcode) - 1bye (8bits)
            decodeFormat1(code.get(0));
        }

        else if(formatOfInstruction == 2){
            // do
        }
    }

    void decodeFormat1(String code){
        // String opCodeOfInstruction = getOpCode(code.get(0));
        // System.out.println("Print");
        String[] codeSplit = code.split("");

        for(String cs: codeSplit){            
            String binaryOpCode = parseTo4Bits(Translate.HexToBin(cs));
            // memory.setWord(addr, v); // PROBLEMA: 1) Como saber em q posição salvar ? 2) NAO PODE SER SALVO UM INT, PRECISA SER STRING, JÁ QUE O INT REMOVE OS ZEROS INICIAIS
        }

    }

    // String getOpCode(String instruction){
    //     if(instruction.toUpperCase().equals("RSUB")){
    //         return "4C";
    //     }

    //     return null;
    // }

    String parseTo4Bits(int bits){
        String bitsToString = String.valueOf(bits);
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 4 - bitsToString.length()) {
            sb.append('0');
        }
        sb.append(bitsToString);

        return sb.toString();
    }
}
