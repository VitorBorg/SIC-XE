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

        // A0B1
        if(codObjeto.length() == 4){ // FORMAT 2
            MemoryBlock data = new MemoryBlock(codObjeto, 4);
            HashMap<String, MemoryBlock> dataAddress = new HashMap<String, MemoryBlock>();
            dataAddress.put(address, data);
            memory.add(dataAddress);

            upgradeMemoryAddress(2);
        }
    }

    private void upgradeMemoryAddress(int formatOperation){
        int addressInt = Integer.parseInt(address);
        addressInt += formatOperation;

        this.address = Helpers.fillXBits(String.valueOf(addressInt),5);
    }

    public void printMemory(){
        for(HashMap<String, MemoryBlock> mem: memory){
            System.out.println("> " + mem);
        }
    }
}
