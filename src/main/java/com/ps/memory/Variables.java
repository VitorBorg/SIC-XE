package com.ps.memory;

import com.ps.helpers.ParseSourceLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Variables {
    //label name, adress
    private final List<HashMap<String,String>> vars;

    public Variables(){
        this.vars = new ArrayList<HashMap<String,String>>();
    }

    public String getAddressFromVarName(String name){
        for(HashMap<String,String> _vars: vars){
            for (String key : _vars.keySet()) {
//                System.out.printf("%s = %s%n", key, _vars.get(key));
                return _vars.get(key);
            }
        }
      return "";

    }

    public void start(List<ParseSourceLine> listaCodigoFonte){
        for(ParseSourceLine psl: listaCodigoFonte){
            if(!psl.getRotulo().equals("")){ // se existir um rotulo
                HashMap<String,String> variable = new HashMap<String,String>();
                variable.put(psl.getRotulo(), psl.getEndereco());
                addVariable(variable, psl.getRotulo());
            }
        }
    }

    public Boolean addVariable(HashMap<String,String> reg, String varName){

        Boolean exists = false;
        Boolean added = false;
        for(HashMap<String,String> _vars: vars){
            for (String key : _vars.keySet()) {
                if(key.equals(varName)){
                    exists = true;
                }
            }
        }

        if(!exists){
            this.vars.add(reg);
            added = true;
        }

        return added;

    }

    public void printVariables(){
        System.out.println(vars);
    }


}
