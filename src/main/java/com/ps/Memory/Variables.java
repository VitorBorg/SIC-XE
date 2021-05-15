package com.ps.Memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variables {
    //label name, adress
    private List<HashMap<String,String>> vars;

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

//    public String getLabelAdress(){
//        return label[1];
//    }
    public void addVariable(HashMap<String,String> reg){
        this.vars.add(reg);
    }




}
