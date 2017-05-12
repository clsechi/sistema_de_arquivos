package sistemadearquivos;

import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Cliente {
    
    static Conexao c;
    
    static Socket socket;
    //private Arquivo arquivo;
    Scanner scn = new Scanner(System.in);
    Requisicao req;
    Resposta resp;
    
    public Cliente() {
        try {
            socket = new Socket("localhost", 9600);
        } catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }
    
    public int Menu(){
        int opr = 0;
        boolean keep = true;
        
        System.out.println("**** Sistema de Arquivos Distribuido ****");
        System.out.println("Digite o número da operação desejada:\n"
                + "1 - Listar Arquivos\n"
                + "2 - Remover Arquivo\n"
                + "3 - Gravar Arquivo\n"
                + "4 - Ler Arquivo");
        opr = scn.nextInt();
        
        while (keep){
            if (opr < 1 || opr > 4){
                System.out.println("Operação inválida, por favor digite novamente:");
                opr = scn.nextInt();
            } else {
                keep = false;
            }
        }
        return opr;
    }
    
    public void DefineMsgs(int opr){
        
        switch (opr) {
            case 1 :
                ListarArquivos();
                break;
            case 2 :
                RemoverArquivo();
                break;
            case 3 :
                GravarArquivo();
                break;
            case 4 :
                LerArquivo();
                break;
        }
        
        
    }   
    
    private void GravarArquivo(){ //OK
            System.out.println("*** Gravação de Arquivo ***");
            
            String FileName, FileBody;
                        
            System.out.println("Insira o nome no arquivo:");
            FileName = scn.next();
            
            System.out.println("Insira o conteúdo do arquivo");
            FileBody = scn.next();
 
            //arquivo = new Arquivo();
            req = new Requisicao();            
            
            req.setNome(FileName);
            req.setConteudo(FileBody);
            //req.setDataHoraUpload(new Date());
            
            req.setOption(3);
            
            c.send(socket, req);
            
            resp = (Resposta) c.receive(socket);
            
            int status = resp.getStatus();
            
            if(status == 0 ){
                System.out.println("Arquivo " + FileName + " gravado no diretório:");                                          

                System.out.println("\nSolicitação concluída com sucesso!");
            } else {
                System.out.println("Solicitação não pode ser realizada! Servidor Offline...");
            }
            
            
    }
    
    private void LerArquivo() { //OK
        System.out.println("*** Ler Arquivo *** ");
        
        //arquivo = new Arquivo();
        req = new Requisicao();
        req.setOption(4);
        
        String FileName;
        
        System.out.println("Digite o nome do arquivo que deseja ler:");
        FileName = scn.next();
        
        req.setNome(FileName);
        
        c.send(socket, req);
        
        resp = (Resposta) c.receive(socket);
        
        int status = resp.getStatus();
        
        switch (status) {
            case 0:
                System.out.println("Nome do arquivo: " + resp.getNome());
                System.out.println("Conteudo: " + resp.getConteudo());
                System.out.println("\nSolicitação concluída com sucesso!");
                break;
            case 3:
                System.out.println("\nArquivo não encontrado!");
                break;
            default:
                System.out.println("\nSolicitação não pode ser realizada! Servidor Offline...");
                break;
        }
    }
    
    private void ListarArquivos() { // OK
        System.out.println("*** Listar Arquivos *** ");        
        String[] lista;
        int status;
        //listar todos os arquivos???
        //arquivo = new Arquivo();
        req = new Requisicao();       
        req.setOption(1);
        
        c.send(socket, req);

        resp = (Resposta) c.receive(socket);
        
        status = resp.getStatus();

        if(status == 0 ){
            lista = resp.getLista();
            
            System.out.println("Arquivos no diretório:");
            
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i]);
            }
            System.out.println("\nSolicitação concluída com sucesso!");
        } else {            
            System.out.println("\nSolicitação não pode ser realizada! Servidor Offline...");            
        }       
    }
    
    private void RemoverArquivo() { //OK
        System.out.println("*** Remoção de Arquivo *** ");
        int status;
        
        //arquivo = new Arquivo();
        req = new Requisicao();
        req.setOption(2);
        
        String FileName;
        
        System.out.println("Digite o nome do arquivo que deseja remover:");
        FileName = scn.next();        
        
        //envio do objeto
        req.setNome(FileName);        
        c.send(socket, req);
        
        resp = (Resposta) c.receive(socket);
        
        status = resp.getStatus();
        
        switch (status) {
            case 0:
                System.out.println("O aquivo " + FileName + " foi removido com sucesso!");
                
                break;
            case 3:
                System.out.println("\nO arquivo "+ FileName +" não foi encontrado!");
                break;
            default:
                System.out.println("\nSolicitação não pode ser realizada! Servidor Offline...");
                break;
        }
        
    } 
}