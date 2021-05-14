package src.memory;

import src.Translate;
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

        //preenche a memória com as posições, e os valores em null
        for(int position = 0; position != SIZEMEMORY; position ++){
            value.put(Translate.DecToHex(position), null);
            memory.add(value);

            setByte(position, 0);
        }
    }

    public Byte getByte(int addr){
        //String address = Translate.DecToHex(addr);
        //return memory.get(addr);

        return memory.get(addr).get(Translate.DecToHex(addr));
    }

    public void setByte(int addr, int bt){
        String address = Translate.DecToHex(addr);
        Byte transBt = (byte) Translate.DecToBin(bt);

        HashMap<String, Byte> value = new HashMap<String, Byte>();
        value.put(address, transBt);

        memory.set(addr, value);
        return;
    }

    public Byte[] getWord(int addr){
        Byte[] word = new Byte[3];

        word[0] = getByte(addr);
        word[1] = getByte(addr + 1);
        word[2] = getByte(addr + 2);

        return word;
    }

    public void setWord(int addr, int v){
        return;
    }

    public double getFloat(int addr){
        return 0;
    }

    public void setFloat(int addr, double v){
        return;     
    }

    public void printMemory(){
        System.out.println("\n\n MEMORIA");

        for(int i = 0; i < 100; i++)
            System.out.println(memory.get(i));
    }
}
