package com.ps;

import com.ps.Carregador.Carregador;
import com.ps.Helpers.ParseSourceLine;
import com.ps.Memory.Memory;
import com.ps.Montador.Montador;
import com.ps.Table.Table;

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
//    static Maquina maquina

    static Table table;

    public static void main(String[] args) throws FileNotFoundException {
        listaCodigoFonte = new ArrayList<ParseSourceLine>();
        table = new Table();
        memoria = new Memory();

        Scanner in = new Scanner(new FileReader("src\\main\\java\\com\\ps\\Inputs\\exemplo2.txt"));
        while (in.hasNextLine()) {
            String linhaDoArquivo = in.nextLine();
            ParseSourceLine cfl = new ParseSourceLine(line, endereco, linhaDoArquivo);
            line++;
            listaCodigoFonte.add(cfl);

            table.addLine(cfl.getValues());
        }

        table.printTable();

        // MONTADOR
        montador = new Montador(memoria, listaCodigoFonte);
        montador.start();

        // CARREGADOR
        carregador = new Carregador(memoria);
        memoria.printMemory();

        // MAQUINA
        //        maquina = new Maquina(memoria);

//        table.clear();
//        for(ParseSourceLine sl: listaCodigoFonte){
//            table.addLine(sl.getValues());
//        }
//
//        table.printTable();
    }


}
