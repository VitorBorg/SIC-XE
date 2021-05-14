package src.memory;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class Regist {
    List<HashMap<String, Integer>> regs = new ArrayList<HashMap<String, Integer>>();
    HashMap<String, Double> regF = new HashMap<String, Double>();
   
    public Regist(){
        HashMap<String, Integer> hashValue = new HashMap<String, Integer>();

        regF.put("F", null);
        hashValue.put("A", 0); regs.add(hashValue);
        hashValue.put("X", 0); regs.add(hashValue);
        hashValue.put("L", 0); regs.add(hashValue);
        hashValue.put("B", 0); regs.add(hashValue);
        hashValue.put("S", 0); regs.add(hashValue);
        hashValue.put("PC", 0); regs.add(hashValue);
        hashValue.put("SW", 0); regs.add(hashValue);
    }

    public int getRegName(String value){
        switch (value.toUpperCase()){
            case "A":
                return regs.get(0).get("A");
            case "X":
                return regs.get(1).get("X");
            case "L":
                return regs.get(2).get("L");
            case "B":
                return regs.get(3).get("B");
            case "S":
                return regs.get(4).get("S");
            case "PC":
                return regs.get(5).get("PC");
            case "SW":
                return regs.get(6).get("SW");
            default:
                return (Integer) null;
        }

    }

    public Double getRegNameF(){
        return regF.get("F");
    }

    public int getRegIndex(int index){
        
        switch(index){
            case 0:
                return regs.get(0).get("A");
                //registrador A
            case 1:
                return regs.get(1).get("X");
                //registrador X
            case 2:
                return regs.get(2).get("L");
                //registrador L
            case 3:
                return regs.get(3).get("B");
                //registrador B
            case 4:
                return  regs.get(4).get("S");
                //registrador S
            case 7:
                return regs.get(5).get("PC");
                //registrador PC
            case 9:
                return regs.get(6).get("SW");
                //registrador SW
            default:
                return (Integer) null;
        }

    }

    public Double getRegIndexF(){
        return regF.get("F");
        //registrador F
    }

    public boolean setRegs(String value, int value2){
        HashMap<String, Integer> hashValue = new HashMap<String, Integer>();
        hashValue.put(value, value2);

        switch (value){
            case "A":
                regs.set(0, hashValue);
                return true;
            case "X":
                 regs.set(1, hashValue);
                return true;
            case "L":
                 regs.set(2, hashValue);
                return true;
            case "B":
                 regs.set(3, hashValue);
                return true;
            case "S":
                 regs.set(4, hashValue);
                return true;
            case "PC":
                 regs.set(5, hashValue);
                return true;
            case "SW":
                 regs.set(6, hashValue);
                return true;
            default:
                return false;
        }
    }

    public boolean setRegsF(Double value){
        regF.put("F", value);
        return true;
    }

}
