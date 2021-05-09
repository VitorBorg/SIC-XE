package src.memory;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
//import java.util.Arrays;

public class Memory {
    List<HashMap<String, Byte>> memory = new ArrayList<HashMap<String, Byte>>();
    final int SIZEMEMORY = 1000;
    //byte[] memory = new byte[1000];

    public Memory(){
        HashMap<String, Byte> value = new HashMap<String, Byte>();

        for(int position = 0; position != SIZEMEMORY; position ++){
            value.put(String.valueOf(position), null);
            memory.add(value);
        }
    }

    int getByte(int addr){
        return 0;
    }

    void setByte(int addr, int v){
        return;
    }

    int getWord(int addr){
        return 0;
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
}
