package com.ps;

import com.ps.Carregador.Carregador;
import com.ps.Helpers.ParseSourceLine;
import com.ps.Memory.Memory;
import com.ps.Memory.Register;
import com.ps.Memory.Variables;
import com.ps.Montador.Montador;
import com.ps.Table.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
//    static Maquina maquina

    static Table table;

    public static void main(String[] args) throws FileNotFoundException {
        listaCodigoFonte = new ArrayList<ParseSourceLine>();
        table = new Table();
        memoria = new Memory();
        vars = new Variables();
        reg = new Register();

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

        // ADD VARIABLES
        vars.start(listaCodigoFonte);
//        vars.printVariables();

//        HashMap<String, String> var = new HashMap<String, String>();
//        var.put("VAR1","123");
//
//        vars.addVariable(var);
//        System.out.println(vars.getAddressFromVarName("VAR1"));

        // MAQUINA
        // maquina = new Maquina(memoria);

//        printTable();

    }

    static void printTable(){
        table.clear();
        for(ParseSourceLine sl: listaCodigoFonte){
            table.addLine(sl.getValues());
        }

        table.printTable();
    }

}
