package com.ps.Memory;

import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<String[]> registradores;
    private String[] registrador = {"","","",""}; // POS 0 -> Nome   POS 1 -> NUMERO    POS 2 -> TAMANHO    POS 3 -> Valor

    public Register() {
        this.registradores = new ArrayList<String[]>();

        createRegisters("A","0", "24","");
        createRegisters("X","1", "24","");
        createRegisters("L","2", "24","");
        createRegisters("B","3", "24","");
        createRegisters("F","6", "48","");
        createRegisters("PC","8", "24","");
        createRegisters("SW","9", "24","");
    }

    void createRegisters(String nome, String numero, String tamanho, String valor){
        this.registrador = new String[]{nome, numero, tamanho, valor};
        this.registradores.add(registrador);
    }

    public String[] getRegisterByName(String name){
        for(String[] reg: registradores){
            if(reg[0] == name){
                return reg;
            }
        }
        return new String[]{"","","",""};
    }

    public String getRegisterValue(String name){
        String[] reg = getRegisterByName(name);
        return reg[3];
    }

    public String[] setRegisterValue(String name, String value){
        String[] reg = getRegisterByName(name);

        reg[3] = value;

        return reg;
    }




}
