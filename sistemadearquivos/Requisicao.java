
package sistemadearquivos;

import java.io.Serializable;

public class Requisicao implements Serializable{
    
    public Requisicao() {
        
    }
    
    private Arquivo arquivo;    
    private int option;

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
    
    
    
}
