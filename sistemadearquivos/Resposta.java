
package sistemadearquivos;

import java.io.Serializable;


public class Resposta implements Serializable{
    
    public Resposta(){
        
    }
    
    private String lista[];
    private int status;
    private Arquivo arquivo;

    public String[] getLista() {
        return lista;
    }

    public void setLista(String[] lista) {
        this.lista = lista;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }
    
    
}
