package src.instrucoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import src.Translate;
import src.helpers.Helper;
import src.memory.Memory;
import src.operator.Operador;

public class Instruction {

    private List<ArrayList<String>> codes;
    private Memory memory;
    private int baseAddress = 0;

    private Operador operador;

    public Instruction(List<ArrayList<String>> codes, Memory memory) {
        this.codes = codes;
        this.memory = memory;
        this.operador = new Operador();
    }

    public void loadInstruction() {
        for (ArrayList<String> code : codes) {
            decodeInstruction(code);
        }
    }

    void decodeInstruction(ArrayList<String> code) {
        int formatOfInstruction = this.operador.getFormat(code.get(0), code.size());

        if (formatOfInstruction == 1) { // formato 1 (opcode) - 1bye (8bits)
            decodeFormat1(code.get(0));
        }

        else if (formatOfInstruction == 2) { // format 2 (opcode) R1 R2 - 2 bytes (16bits)
            decodeFormat2(code);
        }

        else if (formatOfInstruction == 3 || formatOfInstruction == 4) { // format 3 (opcode) X Y - 3 BYTES - 24BITS
            decodeFormat3or4(code);
        }

    }

    void decodeFormat1(String code) {
        String[] codeSplit = code.split("");

        for (String cs : codeSplit) {
            String binaryOpCode = Helper.parseTo4Bits(Translate.HexToBin(cs));

            storeMemory(binaryOpCode);

        }
    }

    void decodeFormat2(ArrayList<String> instruction) {
        System.out.println("instruction" + instruction);
        String[] codeSplit = instruction.get(0).split("");
        String r1 = Helper.getRegisterNumber(instruction.get(1)).toString();
        String r2 = Helper.getRegisterNumber(instruction.get(2)).toString();
        StringBuilder fullBinary16bits = new StringBuilder();

        for (String cs : codeSplit) {
            String binaryOpCode = Helper.parseTo4Bits(Translate.HexToBin(cs));
            fullBinary16bits.append(binaryOpCode);
        }

        String binaryR1 = Helper.parseTo4Bits(Translate.HexToBin(r1));
        String binaryR2 = Helper.parseTo4Bits(Translate.HexToBin(r2));

        fullBinary16bits.append(binaryR1);
        fullBinary16bits.append(binaryR2);

        String part1 = fullBinary16bits.substring(0, 8);
        String part2 = fullBinary16bits.substring(8, 16);
        storeMemory(part1, part2);
        // System.out.println("Full binary 16 bits: " + fullBinary16bits);
        // System.out.println("Codigo objeto: " + instruction.get(0) + r1 + r2);

    }

    void decodeFormat3or4(ArrayList<String> instruction) {
        String[] opcodeSplit = instruction.get(0).split("");
        StringBuilder opcodeBuilder = new StringBuilder();
        String n = "0"; // Indireto
        String i = "0"; // Imediato
        String x = "0";
        String b = "0";
        String p = "0";
        String e = "0";
        String deslocamento = "";

        for (String cs : opcodeSplit) {
            String binaryOpCode = Helper.parseTo4Bits(Translate.HexToBin(cs));
            opcodeBuilder.append(binaryOpCode);
        }
        String opcode = Helper.fillXBits(opcodeBuilder.toString(), 6);

        if (instruction.get(1).contains("#")) { // se o operando contem # é um endereçamento imediato
            i = "1";
        }

        if (instruction.get(1).toUpperCase().contains("X") || instruction.get(2).toUpperCase().contains("X")) { // DUVIDA ???? se contem X é instrução indexada ?????
            x = "1";
        }        

        if (instruction.get(0).contains("+")) { // VERIFICAÇÃO SE TEM O + PARA SABER SE É TIPO 3 OU 4
            e = "1";
        }

        int PC  = baseAddress + (n.equals("1") ? 4 : 3);

        if(isLabel(instruction.get(1))){
            int displacement = Translate.BinToDec(getTargetAddress(instruction.get(1))) -  Translate.BinToDec(PC);
            System.out.println("test " + displacement);
            // System.out.println(displacement);
            // System.out.println(Translate.DecToHex(displacement));
            // if (isPCRelative(displacement)) {
            //     b = '0';
            //     p = '1';
            //     return String.valueOf(b) + p + e;
            // }
        }

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

        String[] flags = { n, i, x, b, p, e };
        String addressType = getAddressType(flags);

        if (addressType.equals("i")) { // se for imediato
            deslocamento = calculaDeslocamentoImediato(instruction.get(1));
        }

        if (addressType.equals("n")) { // se for indireto
            deslocamento = calculaDeslocamentoIndireto(instruction.get(1));
        }

        if (addressType.equals("x")) { // se for indexado
            deslocamento = calculaDeslocamentoIndexado(instruction.get(1));
        }

        if (addressType.equals("b")) { // se for calculo de base
            deslocamento = calculaDeslocamentoBase(instruction.get(1));
        }


        if (addressType.equals("p")) { // se for calculo de pc
            deslocamento = calculaDeslocamentoPc(instruction.get(1));
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
        StringBuilder fullBinary24bits = new StringBuilder();
        fullBinary24bits.append(opcode.toString());
        fullBinary24bits.append(n);
        fullBinary24bits.append(i);
        fullBinary24bits.append(x);
        fullBinary24bits.append(b);
        fullBinary24bits.append(p);
        fullBinary24bits.append(e);
        fullBinary24bits.append(deslocamento);
        String part1 = fullBinary24bits.substring(0, 8);
        String part2 = fullBinary24bits.substring(8, 16);
        String part3 = fullBinary24bits.substring(16, 24);
        storeMemory(part1, part2, part3);

    }

    Boolean isLabel(String label){
        List<String> listaDeLabels = new ArrayList<String>();
        listaDeLabels.add("RETADR");

        return listaDeLabels.contains(label);
    }

    String getTargetAddress(String instrucao){
        HashMap<String, String> listaDeLabels = new HashMap<String, String>();
        listaDeLabels.put("RETADR", "0030");

        if(listaDeLabels.containsValue(instrucao)){
            return listaDeLabels.get(instrucao);
        }

        return "";
    }

    void storeMemory(String... values) {
        if (values.length == 1)
            memory.setWord(baseAddress, Integer.parseInt(values[0]));
        // else if(values.length == 2) memory.setWord(baseAddress,
        // Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        // else if(values.length == 3)memory.setWord(baseAddress,
        // Integer.parseInt(values[0]), Integer.parseInt(values[1]),
        // Integer.parseInt(values[2]));
        this.baseAddress++;
    }

    String getAddressType(String[] flags) {
        String n = flags[0];
        String i = flags[1];
        String x = flags[2];
        String b = flags[3];
        String p = flags[4];
        String e = flags[5];

        // PC
        if (n.equals("1") && i.equals("1") && x.equals("0") && b.equals("0") && p.equals("1") && e.equals("0")) {
            return "p";
        }

        // BASE
        if (n.equals("1") && i.equals("1") && x.equals("0") && b.equals("1") && p.equals("0") && e.equals("0")) {
            return "b";
        }

        // IMEDIATO
        if (n.equals("0") && i.equals("1") && x.equals("0") && b.equals("0") && p.equals("0") && e.equals("0")) {
            return "i";
        }

        // INDEXADO
        if (n.equals("1") && i.equals("1") && x.equals("1") && b.equals("1") && p.equals("0") && e.equals("0")) {
            return "x";
        }

        // INDIRETO
        if (n.equals("1") && i.equals("0") && x.equals("0") && b.equals("0") && p.equals("1") && e.equals("0")) {
            return "n";
        }

        return "n/a";
    }

    String calculaDeslocamentoImediato(String valor) {
        String valorImediato = valor.replace("#", "");
     
        String[] valorImediatoQuebrado = valorImediato.split("");

        StringBuilder imediato = new StringBuilder();

        for (String viq : valorImediatoQuebrado) {
            String valorImediato4Bits = Helper.parseTo4Bits(Integer.parseInt(Translate.DecToBin(viq)));
            imediato.append(valorImediato4Bits);
        }
        System.out.println("imediato" + imediato);

        return Helper.fillXBits(imediato.toString(), 12);

    }

    String calculaDeslocamentoIndireto(String valor) {
        return "";
    }

    String calculaDeslocamentoBase(String valor) {
        return "";
    }

    String calculaDeslocamentoIndexado(String valor) {
        return "";
    }

    String calculaDeslocamentoPc(String valor) {
        return "";
    }

}
