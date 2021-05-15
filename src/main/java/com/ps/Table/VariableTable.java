package com.ps.Table;

import com.ps.Memory.Variable;

import java.util.ArrayList;
import java.util.List;

public class VariableTable {
    private List<Variable> table; 

    public VariableTable(){
        table = new ArrayList<Variable>();
    }

    public void addLine(String name, String adress){
        Variable lb = new Variable(name, adress);
        table.add(lb);
    }

    public String find(String name){
        for(int t = 0; t < table.size(); t++)
            if(table.get(t).getLabelName().equals(name))
                return table.get(t).getLabelAdress();
        
        return "NULL";
    } 
}


