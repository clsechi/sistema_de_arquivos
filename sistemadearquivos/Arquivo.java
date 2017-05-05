
package sistemadearquivos;

import java.io.Serializable;
import java.util.Date;


public class Arquivo implements Serializable{
    
    private static String nome;
    private static byte[] conteudo;
    private static long tamanhoKB;
    private static Date dataHoraUpload;
    private static String diretorioDestino;

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Arquivo.nome = nome;
    }

    public static byte[] getConteudo() {
        return conteudo;
    }

    public static void setConteudo(byte[] conteudo) {
        Arquivo.conteudo = conteudo;
    }

    public static long getTamanhoKB() {
        return tamanhoKB;
    }

    public static void setTamanhoKB(long tamanhoKB) {
        Arquivo.tamanhoKB = tamanhoKB;
    }

    public static Date getDataHoraUpload() {
        return dataHoraUpload;
    }

    public static void setDataHoraUpload(Date dataHoraUpload) {
        Arquivo.dataHoraUpload = dataHoraUpload;
    }

    public static String getDiretorioDestino() {
        return diretorioDestino;
    }

    public static void setDiretorioDestino(String diretorioDestino) {
        Arquivo.diretorioDestino = diretorioDestino;
    }    
}
