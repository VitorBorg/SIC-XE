package com.ps.Montador;

// INPUT: LDA #3
// OUTPUT: 2ARB

import com.ps.Carregador.Carregador;
import com.ps.Helpers.Helpers;
import com.ps.Helpers.ParseSourceLine;
import com.ps.Memory.Memory;
import com.ps.Operador.Operador;
import com.ps.Translate.Translate;

import java.util.ArrayList;
import java.util.List;

public class Montador {

    private String opcode = "";
    private Operador operador;
    private Memory memoria;

    List<ParseSourceLine> tabelaCodigoFonte;

    public Montador(List<ParseSourceLine> tcf, Memory memoria){
        this.tabelaCodigoFonte = tcf;
        this.operador = new Operador();
        this.memoria = memoria;
    }

    public void start(){
        for(ParseSourceLine codigoFonteLinha: tabelaCodigoFonte){

            this.opcode = getCodMachine(codigoFonteLinha.getOperador());

            int formatOfInstruction = this.operador.getFormat(opcode);

            if (formatOfInstruction == 1) { // formato 1 (opcode) - 1bye (8bits)
                decodeFormat1(opcode);
            }

            else if (formatOfInstruction == 2) { // format 2 (opcode) R1 R2 - 2 bytes (16bits)
                decodeFormat2(opcode, codigoFonteLinha.getOperando1(), codigoFonteLinha.getOperando2());
            }

            else if (formatOfInstruction == 3 || formatOfInstruction == 4) { // format 3 (opcode) X Y - 3 BYTES - 24BITS
//                decodeFormat3or4(opcode);
            }



        }
    }

    private String getCodMachine(String instruction){
        return Operador.getOpcodeFromOperator(instruction);
    }

    private String decodeFormat1(String code) {
        String[] codeSplit = code.split("");
        StringBuilder binaryOpCode = new StringBuilder();

        for (String cs : codeSplit) {
            String boc = Helpers.parseTo4Bits(Translate.HexToBin(cs));
            binaryOpCode.append(boc);
        }

       return Helpers.fillXBits(binaryOpCode.toString(), 8);
    }

    void decodeFormat2(String operator, String reg1, String reg2) {
        String[] codeSplit = operator.split("");
        String r1 = Helpers.getRegisterNumber(reg1).toString();
        String r2 = Helpers.getRegisterNumber(reg2).toString();
        StringBuilder fullBinary16bits = new StringBuilder();

        for (String cs : codeSplit) {
            String binaryOpCode = Helpers.parseTo4Bits(Translate.HexToBin(cs));
            fullBinary16bits.append(binaryOpCode);
        }

        String binaryR1 = Helpers.parseTo4Bits(Translate.HexToBin(r1));
        String binaryR2 = Helpers.parseTo4Bits(Translate.HexToBin(r2));

        fullBinary16bits.append(binaryR1);
        fullBinary16bits.append(binaryR2);

        String part1 = fullBinary16bits.substring(0, 8);
        String part2 = fullBinary16bits.substring(8, 16);


        System.out.println("Binario: " + part1 + part2);
        System.out.println("Codigo Objeto: " + Helpers.getCodObjeto(part1 + part2));

        memoria.save(Helpers.getCodObjeto(part1 + part2));
        // System.out.println("Full binary 16 bits: " + fullBinary16bits);
        // System.out.println("Codigo objeto: " + instruction.get(0) + r1 + r2);

    }

}
