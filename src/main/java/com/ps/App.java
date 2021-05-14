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

/**
 * Hello world!
 *
 */
public class App 
{
    static Integer line = 1;
    static Integer endereco = 0;
    static List<ParseSourceLine> listaCodigoFonte;
    static Montador montador;
    static Carregador carregador;
    static Memory memoria;

    static Table table;

    public static void main( String[] args ) throws FileNotFoundException
    {
        listaCodigoFonte = new ArrayList<ParseSourceLine>();
        table = new Table();
        memoria = new Memory();

        Scanner in = new Scanner(new FileReader("D:\\workspace\\ps\\simulador\\simulador\\src\\main\\java\\com\\ps\\Inputs\\exemplo.txt"));
        while (in.hasNextLine()) {
            String linhaDoArquivo = in.nextLine();
            ParseSourceLine cfl = new ParseSourceLine(line, endereco, linhaDoArquivo);
            listaCodigoFonte.add(cfl);

            table.addLine(cfl.getValues());
        }

        table.printTable();

        montador = new Montador(listaCodigoFonte, memoria);
        montador.start();

        // CARREGADOR
        carregador = new Carregador(memoria);
        memoria.printMemory();
    }
}
