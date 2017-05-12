
package sistemadearquivos;

import java.io.Serializable;


public class Resposta implements Serializable{
    
    public Resposta(){
        
    }
    
    private String lista[];
    private int status;
    private String nome;
    private String conteudo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

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
    
    
}
