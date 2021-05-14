package com.ps.Memory;

import com.ps.Helpers.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memory {
    List<HashMap<String, MemoryBlock>> memory;
    String address = "00000";

    int maxBitsOnWord = 24;

    public Memory(){
       this.memory = new ArrayList<HashMap<String, MemoryBlock>>();
    }

    public void save(String codObjeto){

            if(codObjeto.length() == 8) { // SE FOR TIPO 4 PRECISA QUEBRAR EM 2 PALAVRAS
                MemoryBlock data = new MemoryBlock(codObjeto.substring(0,4));
                HashMap<String, MemoryBlock> dataAddress = new HashMap<String, MemoryBlock>();
                dataAddress.put(address, data);
                memory.add(dataAddress);

                MemoryBlock data2 = new MemoryBlock(codObjeto.substring(4,6));
                HashMap<String, MemoryBlock> dataAddress2 = new HashMap<String, MemoryBlock>();
                dataAddress.put(address, data2);
                memory.add(dataAddress);

            } else {
                MemoryBlock data = new MemoryBlock(codObjeto);
                HashMap<String, MemoryBlock> dataAddress = new HashMap<String, MemoryBlock>();
                dataAddress.put(address, data);
                memory.add(dataAddress);
            }

            upgradeMemoryAddress();


    }

    private void upgradeMemoryAddress(){
        int addressInt = Integer.parseInt(address);
        addressInt += maxBitsOnWord;

        this.address = Helpers.fillXBits(String.valueOf(addressInt),5);
    }

    public void printMemory(){
        for(HashMap<String, MemoryBlock> mem: memory){
            System.out.println("> " + mem);
        }
    }
}
