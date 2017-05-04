
package sistemadearquivos;

import java.io.Serializable;
import java.util.Date;


public class Arquivo implements Serializable{
    
    private String nome;
    private byte[] conteudo;
    private long tamanhoKB;
    private Date dataHoraUpload;
    private String diretorioDestino;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public long getTamanhoKB() {
        return tamanhoKB;
    }

    public void setTamanhoKB(long tamanhoKB) {
        this.tamanhoKB = tamanhoKB;
    }

    public Date getDataHoraUpload() {
        return dataHoraUpload;
    }

    public void setDataHoraUpload(Date dataHoraUpload) {
        this.dataHoraUpload = dataHoraUpload;
    }

    public String getDiretorioDestino() {
        return diretorioDestino;
    }

    public void setDiretorioDestino(String diretorioDestino) {
        this.diretorioDestino = diretorioDestino;
    }  
    
}
