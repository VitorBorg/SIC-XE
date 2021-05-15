package com.ps.Memory;

public class Variable {
    //label name, adress
    private String[] label = {"", ""};

    public Variable(String name, String adress){
        label[0] = name;
        label[1] = adress;
    }

    public String getLabelName(){
        return label[0];
    }
    
    public String getLabelAdress(){
        return label[1];
    }
    
}
