package sicxe.Memory;

import sicxe.Helpers.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memory {

    List<HashMap<String, MemoryBlock>> memory;
    String address = "00000";

    int maxBitsOnWord = 24;

    public Memory() {
        this.memory = new ArrayList<HashMap<String, MemoryBlock>>();
    }

    public String save(String codObjeto) {

        String currentAddres = address;

        if (codObjeto.length() == 8) { // SE FOR TIPO 4 PRECISA QUEBRAR EM 2 PALAVRAS
            MemoryBlock data = new MemoryBlock(codObjeto.substring(0, 6));
            HashMap<String, MemoryBlock> dataAddress = new HashMap<String, MemoryBlock>();
            dataAddress.put(address, data);
            memory.add(dataAddress);

            this.address = Helpers.fillXBits(String.valueOf(Integer.parseInt(address) + 24),5);


            MemoryBlock data2 = new MemoryBlock(codObjeto.substring(6, 8));
            HashMap<String, MemoryBlock> dataAddress2 = new HashMap<String, MemoryBlock>();
            dataAddress2.put(address, data2);
            memory.add(dataAddress2);

        } else {
            MemoryBlock data = new MemoryBlock(codObjeto);
            HashMap<String, MemoryBlock> dataAddress = new HashMap<String, MemoryBlock>();
            dataAddress.put(address, data);
            memory.add(dataAddress);
        }

        upgradeMemoryAddress();
        return currentAddres;
    }

    private void upgradeMemoryAddress() {

        int addressInt = Integer.parseInt(address);
        addressInt += maxBitsOnWord;

        this.address = Helpers.fillXBits(String.valueOf(addressInt), 5);
    }

    public void printMemory() {

        for (HashMap<String, MemoryBlock> mem : memory) {
            System.out.println(mem);
        }
    }

    public String getAddress(String addressFind) {

        for(HashMap<String, MemoryBlock> line: memory) {
            for (String address : line.keySet()) {
                if(address.equals(addressFind)) {
                    return line.get(address).toString();
                }
            }
        }
        return "";
    }

    public List<String> getAddressList() {

        List<String> address = new ArrayList<String>();
        for(HashMap<String, MemoryBlock> line: memory) {
            for (String sigla : line.keySet()) {
                address.add(sigla);
            }
        }
        return address;
    }

    public List<String> getValueList() {

        List<String> datas = new ArrayList<String>();

        for (HashMap<String, MemoryBlock> mem : memory) {
            for (Map.Entry<String, MemoryBlock> entrada : mem.entrySet()) {
                datas.add(entrada.getValue().toString());
            }
        }
        return datas;
    }

    public  List<HashMap<String, MemoryBlock>> getMemory() {

        return memory;
    }

    public void updateValueFromAddres(String currentAddres, String value) {

        MemoryBlock newData = new MemoryBlock(value);

        for (HashMap<String, MemoryBlock> mem : memory) {
            mem.put(currentAddres, newData);
        }
    }

    public Boolean hasNext(String currentAddres) {

        int i = 0;
        int currentPos = 0;
        for(HashMap<String, MemoryBlock> line: memory) {
            for (String address : line.keySet()) {
                if(address.equals(currentAddres)) {
                    currentPos = i;
                }
                i++;
            }
        }

        try {
            if(currentPos + 1 != memory.size()) {
                return true;
            }
        } catch (Error err) {
            return false;
        }

        return false;
    }

    public String getNextValue(String currentAddres) {

        int i = 0;
        int currentPos = 0;
        for(HashMap<String, MemoryBlock> line: memory) {
            for (String address : line.keySet()) {
                if(address.equals(currentAddres)) {
                    currentPos = i;
                }
                i++;
            }
        }

        i = 0;
        for(HashMap<String, MemoryBlock> line: memory) {
            for (String address : line.keySet()) {
                if(i == currentPos + 1){
                    return line.get(address).toString();
                }
                i++;
            }
        }

        return "";
    }
}
