package sicxe.Macro;

import java.util.List;
import java.util.ArrayList;

public class Macrofunc {
    String name;
    public List<String> args = new ArrayList<String>();
    public List<List<String>> content = new ArrayList<List<String>>();
    public List<Integer> numArgsByLine = new ArrayList<Integer>();

    public Macrofunc(String nameMacro){
        name = nameMacro;
    }

    public String getName(){
        return name;
    }

    public int getArgsSize(){
        return args.size();
    }

    public String getArg(int pos){
        return args.get(pos);
    }

    List<String> readArgs(){
        return args;
    }

    public void addArgs(List<String> argumentos){

        for(int i = 0; i < argumentos.size(); i++)
            args.add(argumentos.get(i));

    }

    void setArgs(String arg){
        args.add(arg);
    }

    void setNumArgsByLine(int inteiro){
        numArgsByLine.add(inteiro);
    }

    int getNumArgsByLine(int index){
        return numArgsByLine.get(index);
    }

    int getNumArgsByLineSize(){
        return numArgsByLine.size();
    }

    int getContentSize(){
        return content.size();
    }

    int getContentSizeElement(int index){
        return content.get(index).size();
    }

    List<String> getContent(int index){
        return content.get(index);
    }
  
    String getContentOfContent(int index, int index2){
        return content.get(index).get(index2);
    }

    void setContentLine(){
        content.add(new ArrayList<String>());
    }

    void setContent(int x, String cont){
        content.get(x).add(cont);
    }
    
}
