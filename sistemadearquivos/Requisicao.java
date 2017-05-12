
package sistemadearquivos;

import java.io.Serializable;

public class Requisicao implements Serializable{
    
    public Requisicao() {
        
    }
    
    //private Arquivo arquivo;    
    private int option;
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

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
    
    
    
}
