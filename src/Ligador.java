package src;

import src.memory.Regist;
import src.montador.asm;

public class Ligador {

    public static Regist registradores = new Regist();

    public static void main(String[] args) {

        //asm = parser + montador
        asm file = new asm();

        file.asmReader("F:\\Documentos\\CC\\8° semestre - EAD\\PS\\arquivos\\Projeto\\ex1.asm");

        //executa com a maquina
    }
/*
    public Regist getRegister(){
        return registradores;
    }
    */
}
