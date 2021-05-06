package src.memory;

import java.util.HashMap;

public class Regist {
    List<HashMap<String, int>> regs = new ArrayList<Map<String, int>>();
    HashMap<String, double> regF = new Map<String, int>();
   
    public regist(){
        regs.add("A",0);
        regs.add("X",0);
        regs.add("L",0);
        regs.add("B",0);
        regs.add("S",0);
        regs.add("F",0);
        regs.add("PC",0);
        regs.add("SW",0);
    }

    public HashMap<> getRegsName(){
        return;
    }

    public HashMap<> getRegsIndex(int index){
        
        switch(index){
            case 0:
                //registrador A
                return;
            case 1:
                //registrador B
                return;
            case 2:
                //registrador L
                return;
            case 3:
                //registrador B
                return;
            case 4:
                //registrador S
                return;
            case 5:
                //registrador F
                return;
            case 7:
                //registrador PC
                return;
            case 9:
                //registrador SW
                return;
            default:
                return;
        }

    }

    public HashMap<> setRegs(){
        return;
    }

}