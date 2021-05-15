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
    private List<ParseSourceLine> listaCodigoFonte;
    private ParseSourceLine parseSourceLine;


    public Montador(Memory memoria, List<ParseSourceLine> listaCodigoFonte) {
        this.operador = new Operador();
        this.memoria = memoria;
        this.listaCodigoFonte = listaCodigoFonte;
    }

    public void start() {
        for (ParseSourceLine codigoFonteLinha : listaCodigoFonte) {
            this.parseSourceLine = codigoFonteLinha;
            this.opcode = getCodMachine(codigoFonteLinha.getOperador());

            int formatOfInstruction = this.operador.getFormat(opcode);

            if (formatOfInstruction == 1) { // formato 1 (opcode) - 1bye (8bits)
                decodeFormat1(opcode);
            } else if (formatOfInstruction == 2) { // format 2 (opcode) R1 R2 - 2 bytes (16bits)
                decodeFormat2(opcode, codigoFonteLinha.getOperando1(), codigoFonteLinha.getOperando2());
            } else if (formatOfInstruction == 3 || formatOfInstruction == 4) { // format 3 (opcode) X Y - 3 BYTES - 24BITS
                decodeFormat3or4(opcode, codigoFonteLinha.getOperando1(), codigoFonteLinha.getOperando2(), codigoFonteLinha.getOperador().contains("+"));
            }


        }
    }

    private String getCodMachine(String instruction) {
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




        String address = memoria.save(Helpers.getCodObjeto(part1 + part2));
        parseSourceLine.setEndereco(Integer.parseInt(address));

    }

    void decodeFormat3or4(String operatorOpcode, String op1, String op2, Boolean format4) {

        String[] opcodeSplit = operatorOpcode.split("");
        StringBuilder opcodeBuilder = new StringBuilder();
        String n = "0"; // Indireto
        String i = "0"; // Imediato
        String x = "0"; // Indexado
        String b = "0";
        String p = "0";
        String e = "0";
        String deslocamento = "";

        for (String cs : opcodeSplit) {
            String binaryOpCode = Helpers.parseTo4Bits(Translate.HexToBin(cs));
            opcodeBuilder.append(binaryOpCode);
        }
        String opcode = Helpers.fillXBits(opcodeBuilder.toString(), 6);

        if (op1.toUpperCase().contains("@")) { // se o operando contem @ é um endereçamento INDIRETO
            n = "1";
        }

        if (op1.contains("#")) { // se o operando contem # é um endereçamento IMEDIATO
            i = "1";
        }

        if (op1.toUpperCase().contains("X")) { // DUVIDA ???? se contem X é instrução indexada ?????
            x = "1";
        }

        if (op1.contains("B")) { // VERIFICAÇÃO SE TEM uso do registrador B
            b = "1";
        }

        if (op1.contains("PC")) { // VERIFICAÇÃO SE TEM uso do PC
            p = "1";
        }

        if (format4) { // VERIFICAÇÃO SE TEM O + PARA SABER SE É TIPO 3 OU 4
            e = "1";
        }

//        int PC  = baseAddress + (n.equals("1") ? 4 : 3);
//
//        if(isLabel(instruction.get(1))){
//            int displacement = Translate.HexToDec("0030") -  Translate.HexToDec("0003");
//            String displacementHex = Translate.DecToHex(displacement);
//
//            deslocamento = Helper.fillXBits(displacementHex, 4);
//
//
//            // System.out.println(displacement);
//            // System.out.println(Translate.DecToHex(displacement));
//            // if (isPCRelative(displacement)) {
//            //     b = '0';
//            //     p = '1';
//            //     return String.valueOf(b) + p + e;
//            // }
//        }

        /*
         * if(){ // ??? FAZER VERIFICAÇÃO DE QUANDO É PRA CALCULAR USANDO A BASE E
         * QUANDO É PARA CALCULAR USANDO PC
         *
         * }
         *
         * if(){ // ??? quando colocar N para 1
         *
         * }
         */


        String[] flags = {n, i, x, b, p, e};
        String addressType = getAddressType(flags);

        if (addressType.equals("i")) { // se for imediato
            deslocamento = calculaDeslocamentoImediato(op1, e);
        }

        if (addressType.equals("n")) { // se for indireto
            //deslocamento = calculaDeslocamentoIndireto(instruction.get(1));
        }

        if (addressType.equals("x")) { // se for indexado
            //deslocamento = calculaDeslocamentoIndexado(instruction.get(1));
        }

        if (addressType.equals("b")) { // se for calculo de base
            //deslocamento = calculaDeslocamentoBase(instruction.get(1));
        }


        if (addressType.equals("p")) { // se for calculo de pc
            //deslocamento = calculaDeslocamentoPc(instruction.get(1));
        }

        // System.out.println("opcode: " + opcode.toString() );
        // System.out.println("n: " + n);
        // System.out.println("i: " + i);
        // System.out.println("x: " + x);
        // System.out.println("b: " + b);
        // System.out.println("p: " + p);
        // System.out.println("e: " + e);
        // System.out.println("deslocamento: " + deslocamento);
        // System.out.println(opcode.toString()+ n+i+x+b+p+e+deslocamento);

        // System.out.println("FORMATO 3: " + instruction);

        // store in memory
            StringBuilder fullBinary24or32bits = new StringBuilder();
            fullBinary24or32bits.append(opcode.toString());
            fullBinary24or32bits.append(n);
            fullBinary24or32bits.append(i);
            fullBinary24or32bits.append(x);
            fullBinary24or32bits.append(b);
            fullBinary24or32bits.append(p);
            fullBinary24or32bits.append(e);
            fullBinary24or32bits.append(deslocamento);



        String address = memoria.save(Helpers.getCodObjeto(fullBinary24or32bits.toString()));
        parseSourceLine.setEndereco(Integer.parseInt(address));

        // storeMemory(part1, part2, part3);

    }

    String getAddressType(String[] flags) {
        String n = flags[0];
        String i = flags[1];
        String x = flags[2];
        String b = flags[3];
        String p = flags[4];

        // PC
        if (n.equals("1") && i.equals("1") && x.equals("0") && b.equals("0") && p.equals("1")) {
            return "p";
        }

        // BASE
        if (n.equals("1") && i.equals("1") && x.equals("0") && b.equals("1") && p.equals("0")) {
            return "b";
        }

        // IMEDIATO
        if (n.equals("0") && i.equals("1") && x.equals("0") && b.equals("0") && p.equals("0")) {
            return "i";
        }

        // INDEXADO
        if (n.equals("1") && i.equals("1") && x.equals("1") && b.equals("1") && p.equals("0")) {
            return "x";
        }

        // INDIRETO
        if (n.equals("1") && i.equals("0") && x.equals("0") && b.equals("0") && p.equals("1")) {
            return "n";
        }

        return "n/a";
    }

    String calculaDeslocamentoImediato(String imediato, String typeOfInstruction){
        String imediatoBinario = Translate.DecToBin(imediato.replace("#","")); // AJUSTAR PARA QUANDO FOR 11 ou mais de um INTEIRO

        if(typeOfInstruction.equals("0")){ // type 3
            return Helpers.fillXBits(imediatoBinario, 12);
        }
        else if(typeOfInstruction.equals("1")){ // type 4
            return Helpers.fillXBits(imediatoBinario, 20);
        }

        return "";
    }

}
