package src.memory;

import src.Translate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
//import java.util.Arrays;

public class Memory {
    List<HashMap<String, Byte>> memory = new ArrayList<HashMap<String, Byte>>();
    final int SIZEMEMORY = 1000;
    //byte[] src.memory = new byte[1000];

    public Memory(){
        HashMap<String, Byte> value = new HashMap<String, Byte>();

        //preenche a memória com as posições, e os valores em null
        for(int position = 0; position != SIZEMEMORY; position ++){
            value.put(Translate.DecToHex(position), null);
            memory.add(value);

            setByte(position, 0);
        }
    }

    public String getByte(int addr){
        //String address = Translate.DecToHex(addr);
        //return src.memory.get(addr);

        //return src.memory.get(addr).get(Translate.DecToHex(addr));
        return memory.get(addr).values().toString();
    }

    public void setByte(int addr, int bt){
        String address = Translate.DecToHex(addr);
        Byte transBt = (byte) Translate.DecToBin(bt);

        HashMap<String, Byte> value = new HashMap<String, Byte>();
        value.put(address, transBt);

        memory.set(addr, value);
        return;
    }

    public String[] getWord(int addr){
        int address = addr * 3;
        String[] word = new String[3];

        word[0] = getByte(address).toString();
        word[1] = getByte(address + 1).toString();
        word[2] = getByte(address + 2).toString();

        return word;
    }

    public void setWord(int addr, int v[]){
        int address = addr * 3;
        setByte(address + 0, v[0]);
        setByte(address + 1, v[1]);
        setByte(address + 2, v[2]);

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

        for(int i = 0; i < SIZEMEMORY - 1; i+= 3){
            String key = memory.get(i).keySet().toString();
            key = key.replace("[", "");
            key = key.replace("]", "");

            String val = memory.get(i).values().toString();
            val = val.replace("[", "");
            val = val.replace("]", "");
            val = Translate.BinToHex(val);

            String val1 = memory.get(i+1).values().toString();
            val1 = val1.replace("[", "");
            val1 = val1.replace("]", "");
            val1 = Translate.BinToHex(val1);

            String val2 = memory.get(i+2).values().toString();
            val2 = val2.replace("[", "");
            val2 = val2.replace("]", "");
            val2 = Translate.BinToHex(val2);

            System.out.println("0x" + key + "   " + val + " " + val1 + " " + val2);

        }
    }
}
