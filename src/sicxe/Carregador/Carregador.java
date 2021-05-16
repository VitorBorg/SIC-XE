package sicxe.Carregador;

import sicxe.Memory.Memory;

import java.util.List;

public class Carregador {

    private Memory memoria;

    public Carregador(Memory memoria) {

        this.memoria = memoria;
    }

    public void store(List<String> listCodObj) {

        for (String codObj : listCodObj) {
            this.memoria.save(codObj);
        }
    }

}
