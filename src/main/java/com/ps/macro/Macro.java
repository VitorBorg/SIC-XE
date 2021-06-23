package com.ps.macro;

import com.ps.table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Macro {

    List<String> macroList;

    public Macro(){
        this.macroList = new ArrayList<String>();
        initMacros();
    }

    public void initMacros(){
        this.macroList.addAll(Arrays.asList("MACROTAL","MACROTAL2"));
    }

    public void start(Table table){
        System.out.println(table);
    }


}
