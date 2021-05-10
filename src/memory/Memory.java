package src.memory;

import src.Translate;
import java.util.HashMap;
import java.util.List;
import java.lang.management.MemoryType;
import java.util.ArrayList;
//import java.util.Arrays;

public class Memory {
    List<HashMap<String, Byte>> memory = new ArrayList<HashMap<String, Byte>>();
    final int SIZEMEMORY = 1000;
    //byte[] memory = new byte[1000];

    public Memory(){
        HashMap<String, Byte> value = new HashMap<String, Byte>();

        //preenche a memória com as posições, e os valores em null
        for(int position = 0; position != SIZEMEMORY; position ++){
            value.put(Translate.DecToHex(position), null);
            memory.add(value);
        }
    }

    HashMap<String, Byte> getByte(int addr){
        //String address = Translate.DecToHex(addr);
        return memory.get(addr);
    }

    void setByte(int addr, int bt){
        String address = Translate.DecToHex(addr);
        Byte transBt = (byte) Translate.DecToBin(bt);

        HashMap<String, Byte> value = new HashMap<String, Byte>();
        value.put(address, transBt);

        memory.set(addr, value);
        return;
    }

    HashMap<String, Byte> getWord(int addr){
        return memory.get(addr);
    }

    void setWord(int addr, int v){
        return;
    }

    double getFloat(int addr){
        return 0;
    }

    void setFloat(int addr, double v){
        return;     
    }

    //private find
}
