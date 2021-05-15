package sicxe.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseSourceLine {
    Integer linha;
    Integer endereco;
    String rotulo;
    String operador;
    String operando1;
    String operando2;
    List<String> listaDeOperadores;

    public ParseSourceLine(
            Integer linha,
            Integer endereco,
            String texto
    ) {
        this.listaDeOperadores = new ArrayList<String>();
        insereListaDeOperadores();

        this.linha = linha;
        this.endereco = endereco;

        String[] partesDoTexto = texto.split(" ");

        quebraPartesDoTexto(partesDoTexto);

    }

    public void exibeCodigoFonteLinha() {
        String leftAlignFormat = "|%15d|%15d|%15s|%15s|%15s|%15s|%n";
        System.out.format("+-----------------------------------------------------------------------------------------------+%n");
        System.out.format("|     Linha     |   Endereco    |     Rotulo    |    Operador   |   Operando1   |   Operando2   |%n");
        System.out.format("+---------------+---------------+---------------+---------------+---------------+---------------+%n");
        System.out.format(leftAlignFormat, this.linha, this.endereco, (this.rotulo != null ? this.rotulo : ""), this.operador, this.operando1, this.operando2);
        System.out.format("+---------------+---------------+---------------+---------------+---------------+---------------+%n");
    }

    public void quebraPartesDoTexto(String[] partes) {
        // PARTE [0] DO TEXTO
        if (partes.length > 1) {
            Boolean isRotulo = !this.listaDeOperadores.contains(partes[0].toUpperCase());
            if (isRotulo) {
                this.rotulo = partes[0];
            } else {
                this.operador = partes[0];
            }

            // PARTE [1] DO TEXTO
            Boolean isOperador = this.listaDeOperadores.contains(partes[1].toUpperCase());
            if (isOperador) {
                this.operador = partes[1];
            } else {
                this.operando1 = partes[1];
            }

            // SE TIVER PARTE [2] NO TEXTO
            if (partes.length > 2) {
                if (isOperador) {
                    this.operando1 = partes[2];
                } else {
                    this.operando2 = partes[2];
                }
            }
        } else {
            // SE ENTRAR AQUI É UM COMANDO STOP
            this.operador = partes[0];
        }
    }

    public void insereListaDeOperadores() {
        this.listaDeOperadores.addAll(Arrays.asList("ADD","ADDR","AND","CLEAR","COMP","COMPR","DIV",
                "J","JEQ","JGT","JLT","JSUB","LDA","+LDA","LDB","LDCH","LDL","LDS","LDT","LDX",
                "MUL","MULR","OR","RMO","RSUB","SHIFTL","SHIFTR","STA","STB","STCH","STL",
                "STS","STT","STX","SUB","SUBR","TIX","TIXR", "SPACE", "CONST", "START", "END"));
        // this.listaDeOperadores.add("READ");
        // this.listaDeOperadores.add("READ");
    }

    public String[] getValues() {
        String[] str = {this.linha.toString(), this.endereco.toString(), this.rotulo, this.operador, this.operando1, this.operando2};
        return str;
    }

    public String getLinha() {
        return linha != null ? linha.toString() : "";
    }

    public String getEndereco() {
        return endereco != null ? endereco.toString() : "";
    }

    public String getRotulo() {
        return Helpers.printIfNotNull(rotulo);
    }

    public String getOperador() {
        return Helpers.printIfNotNull(operador);
    }

    public String getOperando1() {
        return Helpers.printIfNotNull(operando1);
    }

    public String getOperando2() {
        return Helpers.printIfNotNull(operando2);
    }
}
