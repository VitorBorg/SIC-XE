package src.execution;
import java.util.ArrayList;
import src.Ligador;
import src.memory.Memory;

public class Maquina {
    // for pra ler a memoria
    //for(int position = 0; position != SIZEMEMORY; position ++){ //começa no zero ? vai até o fim ? // começa em qtdinst
        // como para no primeiro bit? getword e faz para na primeira string, le e descobre oq é
        // if 2  metodo pra coisa de 2 coisa
        // if 3  pra coisa de 3
        // if 4  e de 4
    //}
    String opcode;
    //ArrayList<String>
    //add() e o resto!
    public void execucao(){
        opcode = Ligador.memoria.getByte(Ligador.variablesSize).toString();              
        opcode.substring(0, 6);
        System.out.println(opcode);
    }
}
