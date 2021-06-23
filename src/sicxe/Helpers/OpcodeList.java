package sicxe.Helpers;

import java.util.*;

public class OpcodeList {
    Map<String, String> opcode2;

    public OpcodeList(){
        this.opcode2 = new HashMap<String, String>();
    }

    public void saveOpcodeFormat2(){
        this.opcode2.put("90","100100");
        this.opcode2.put("4","000001");
        this.opcode2.put("A0","101000");
        this.opcode2.put("9C","100111");
        this.opcode2.put("98","100110");
        this.opcode2.put("AC","101011");
        this.opcode2.put("AC","101011");
    }

}
