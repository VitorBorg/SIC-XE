package com.ps.montador;

// INPUT: LDA #3
// OUTPUT: 2ARB

import com.ps.helpers.Helpers;
import com.ps.memory.Memory;
import com.ps.memory.Register;
import com.ps.memory.Variables;
import com.ps.operador.Operador;
import com.ps.translate.Translate;
import com.ps.helpers.ParseSourceLine;

import java.util.List;

public class Montador {

    private String opcode = "";
    private final Operador operador;
    private final Memory memoria;
    private final Register register;
    private final Variables variables;
    private final List<ParseSourceLine> listaCodigoFonte;
    private ParseSourceLine parseSourceLine;
    private String codigosObjetos = "";


    public Montador(Memory memoria, List<ParseSourceLine> listaCodigoFonte, Register register, Variables variables) {
        this.operador = new Operador();
        this.memoria = memoria;
        this.register = register;
        this.variables = variables;
        this.listaCodigoFonte = listaCodigoFonte;
    }

    public void start() {
        for (ParseSourceLine codigoFonteLinha : listaCodigoFonte) {
            this.parseSourceLine = codigoFonteLinha;
            this.opcode = getCodMachine(codigoFonteLinha.getOperador());

            int formatOfInstruction = this.operador.getFormat(opcode);

            if (formatOfInstruction == 2) { // format 2 (opcode) R1 R2 - 2 bytes (16bits)
                decodeFormat2(opcode, codigoFonteLinha.getOperando1(), codigoFonteLinha.getOperando2());
            } else if (formatOfInstruction == 3 || formatOfInstruction == 4) { // format 3 (opcode) X Y - 3 BYTES - 24BITS
                decodeFormat3or4(opcode, codigoFonteLinha.getOperando1(), codigoFonteLinha.getOperando2(), codigoFonteLinha.getOperador().contains("+"));
            }
        }

    }

    private String getCodMachine(String instruction) {
        return Operador.getOpcodeFromOperator(instruction);
    }

    void decodeFormat2(String operator, String reg1, String reg2) {
        String[] codeSplit = operator.split("");
        String r1 = Helpers.getRegisterNumber(reg1).toString();
        String r2 = reg2.equals("") ? "" : Helpers.getRegisterNumber(reg2).toString();
        StringBuilder fullBinary16bits = new StringBuilder();

        for (String cs : codeSplit) {
            String binaryOpCode = Helpers.parseTo4Bits(Translate.HexToBin(cs));
            fullBinary16bits.append(binaryOpCode);
        }


        String binaryR1 = Helpers.parseTo4Bits(Translate.HexToBin(r1));
        String binaryR2 = reg2.equals("") ? "0000" : Helpers.parseTo4Bits(Translate.HexToBin(r2));

        fullBinary16bits.append(binaryR1);
        fullBinary16bits.append(binaryR2);

        String part1 = fullBinary16bits.substring(0, 8);
        String part2 = fullBinary16bits.substring(8, 16);
        String address = memoria.save(Helpers.getCodObjeto(part1 + part2));

        this.codigosObjetos += Helpers.getCodObjeto(part1 + part2) + "\n";
        this.register.setRegisterValue("PC", Helpers.addPcToNextAddress(address)); //SET PC

        parseSourceLine.setEndereco(Integer.parseInt(address));

    }

    void decodeFormat3or4(String operatorOpcode, String op1, String op2, Boolean format4) {

        String[] opcodeSplit = operatorOpcode.split(""); // 18
        StringBuilder opcodeBuilder = new StringBuilder();
        String n = "0"; // Indireto
        String i = "0"; // Imediato
        String x = "0"; // Indexado
        String b = "0"; // Base
        String p = "0"; // PC
        String e = "0";
        String deslocamento = "";

        for (String cs : opcodeSplit) {
            String binaryOpCode = String.valueOf(Translate.HexToBin(cs));
            opcodeBuilder.append(binaryOpCode);
        }

        String opcode = opcodeBuilder.toString();

        if(opcode.length() <= 4){
            opcode = Helpers.fillXBits(opcode, 2);
            opcode = Helpers.fillXBits(opcode, 6);
        }
        else if(opcode.length() == 5){
            opcode = Helpers.fillXBits(opcode, 3);
            opcode = Helpers.fillXBits(opcode, 6);
        }
        else if(opcode.length() == 6){
            opcode = Helpers.fillXBits(opcode, 8); // adiciona dois zeros a esquerda
            opcode = Helpers.fillXBits(opcode, 6); // apaga os dois ultimos numeros a direita
        }
        else if(opcode.length() == 7){
            opcode = Helpers.fillXBits(opcode, 8); // adiciona um zero a esquerda
            opcode = Helpers.fillXBits(opcode, 6); // apaga os dois ultimos numeros a direita
        }
        else if(opcode.length() == 8){
            opcode = Helpers.fillXBits(opcode, 6); // remove os 2 numeros finais a direita
        }
//        System.out.println("opcode: " + opcodeBuilder);
//        String opcode = Helpers.fillXBits(opcodeBuilder.toString(), 6);

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


        String[] flags = {n, i, x, b, p, e};
        String addressType = getAddressType(flags);

        if (addressType.equals("i")) { // se for imediato
            deslocamento = calculaDeslocamentoImediato(op1, e);
        }

        if (addressType.equals("n")) { // se for indireto
            deslocamento = calculaDeslocamentoIndireto(op1);
        }

        if (addressType.equals("x")) { // se for indexado
            deslocamento = calculaDeslocamentoIndexado(op1);
        }

        if (addressType.equals("b")) { // se for calculo de base
            deslocamento = calculaDeslocamentoBase(op1);
        }


        if (addressType.equals("p")) { // se for calculo de pc
            deslocamento = calculaDeslocamentoPc(op1);
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


            StringBuilder fullBinary24or32bits = new StringBuilder();
            fullBinary24or32bits.append(opcode);
            fullBinary24or32bits.append(n);
            fullBinary24or32bits.append(i);
            fullBinary24or32bits.append(x);
            fullBinary24or32bits.append(b);
            fullBinary24or32bits.append(p);
            fullBinary24or32bits.append(e);
            fullBinary24or32bits.append(deslocamento);




        String address = memoria.save(Helpers.getCodObjeto(fullBinary24or32bits.toString()));
        this.register.setRegisterValue("PC", Helpers.addPcToNextAddress(address)); //SET PC

        parseSourceLine.setEndereco(Integer.parseInt(address));


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

    String calculaDeslocamentoIndireto(String label){
        // DESLOC = TA - PC          TA = TARGET ADDRESS
        return String.valueOf(Integer.parseInt(variables.getAddressFromVarName(label)) - Integer.parseInt(register.getRegisterValue("PC")));
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

    String calculaDeslocamentoIndexado(String label){
        // DESLOC = TA - B         TA = TARGET ADDRESS
        return String.valueOf(Integer.parseInt(variables.getAddressFromVarName(label)) - Integer.parseInt(register.getRegisterValue("B")));
    }

    String calculaDeslocamentoBase(String label){
        // DESLOC = TA - B         TA = TARGET ADDRESS
        return String.valueOf(Integer.parseInt(variables.getAddressFromVarName(label)) - Integer.parseInt(register.getRegisterValue("B")));
    }

    String calculaDeslocamentoPc(String label){
        // DESLOC = TA - PC        TA = TARGET ADDRESS
        return String.valueOf(Integer.parseInt(variables.getAddressFromVarName(label)) - Integer.parseInt(register.getRegisterValue("PC")));
    }



}
