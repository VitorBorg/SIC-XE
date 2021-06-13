package com.ps;

import com.ps.Carregador.Carregador;
import com.ps.Helpers.ParseSourceLine;
//import com.ps.Maquina.Maquina;
import com.ps.Memory.Memory;
import com.ps.Memory.Register;
import com.ps.Memory.Variables;
import com.ps.Montador.Montador;
import com.ps.Table.Table;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    static Integer line = 1;
    static Integer endereco = 0;
    static List<ParseSourceLine> listaCodigoFonte;
    static Montador montador;
    static Carregador carregador;
    static Memory memoria;
    static Variables vars;
    static Register reg;
//    static Maquina maquina;

    static Table table;

    public static void main(String[] args) throws FileNotFoundException {
        listaCodigoFonte = new ArrayList<ParseSourceLine>();
        table = new Table();
        memoria = new Memory();
        vars = new Variables();
        reg = new Register();

        createFile();

        Scanner in = new Scanner(new FileReader("D:\\workspace\\ps\\simulador\\simulador\\src\\main\\java\\com\\ps\\Inputs\\exemplo.txt"));
        while (in.hasNextLine()) {
            String linhaDoArquivo = in.nextLine();
            ParseSourceLine cfl = new ParseSourceLine(line, endereco, linhaDoArquivo);
            line++;
            listaCodigoFonte.add(cfl);

            table.addLine(cfl.getValues());
        }

        printTable();

        // MONTADOR
        montador = new Montador(memoria, listaCodigoFonte, reg, vars);
        montador.start();

        // CARREGADOR
        carregador = new Carregador(memoria);
        System.out.println("**------ Print Memory -------**");
        memoria.printMemory();
        System.out.println("**------ ------------ -------**");
//        memoria.updateValueFromAddres("00168","010001");
        System.out.println(memoria.getAddress("00144"));


        // ADD VARIABLES
        vars.start(listaCodigoFonte);

        // SET REGISTER
        reg.setRegisterValue("A","123");
        System.out.println(reg.getRegisterValue("A"));
//        vars.printVariables();
//        HashMap<String, String> var = new HashMap<String, String>();
//        var.put("VAR1","123");
//
//        vars.addVariable(var);
//        System.out.println(vars.getAddressFromVarName("VAR1"));

        // MAQUINA
        // maquina = new Maquina(memoria);

//        printTable();
//        maquina = new Maquina();


    }

    static void printTable(){
        table.clear();
        for(ParseSourceLine sl: listaCodigoFonte){
            table.addLine(sl.getValues());
        }

        table.printTable();
    }

    public static void whrite()
            throws IOException {
        String str = "ABX2\n22A8\nDWQ1\n4C";
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\workspace\\ps\\simulador\\simulador\\src\\main\\java\\com\\ps\\Outputs\\tuamae.obj"));
        writer.write(str);

        writer.close();
    }

    private static void createFile() {
        File file = new File("D:\\workspace\\ps\\simulador\\simulador\\src\\main\\java\\com\\ps\\Outputs\\tuamae.txt");
        boolean result;
        try {
            result = file.createNewFile();  //creates a new file
            if (result)      // test if successfully created a new file
            {
                whrite();
                System.out.println("file created " + file.getCanonicalPath()); //returns the path string
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}


