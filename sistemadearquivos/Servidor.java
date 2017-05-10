
package sistemadearquivos;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    static ServerSocket serversocket;
    static Socket controlador_socket;
    static Conexao c;
    static String pasta = "/home/carlos/testes_java";
    
    //criar 3 classes main
    
    // cada servidor tem uma pasta e uma porta separadas
    
    
    public Servidor(int porta) {
        try {
            serversocket = new ServerSocket(porta);
            System.out.println("Engines On!!!" + porta);
            System.out.println("Waiting resquest...");
        } catch (Exception e){
            System.out.println("Nao criei o server socket...");
        }
        
        //new Servidor(6032);
    }
    
    public static void main(String args[]){
        Arquivo arquivo;
        Requisicao req;
        Resposta resp;
        
        //acho q ta errado ...
        new Servidor (9601);
        //new Servidor (9602);
        //new Servidor (9603);
        
        while(true){
            int option;
            String[] lista;
            String nome;
            
            if(connect()){
                
                
                req = (Requisicao) c.receive(controlador_socket);
                arquivo = req.getArquivo();
                option = req.getOption();
                
                resp = new Resposta();
                
                File file = new File(pasta);
                //se a pasta não existir ele cria
                if (!file.exists()){
                    file.mkdirs();
                }
                
                switch (option) {
                    case 1:
                        //lista
                        
                        lista = file.list();
                        
                        resp.setLista(lista);
                        resp.setStatus(0);
                        
                        c.send(controlador_socket, resp);
                        break;
                    case 2:
                        //remover
                        
                        nome = arquivo.getNome();
                        lista = file.list();
                        
                        for (int i = 0; i < lista.length; i++) {
                            if (nome.equals(lista[i])){
                                //deleta o arquivo
                                file = new File(pasta + "/" + nome);
                                if (file.delete()){
                                    resp.setStatus(0);
                                }
                                break;
                            } else {
                                //mensagem arquivo não encontrado
                                resp.setStatus(3);
                            }
                        }
                        
                        c.send(controlador_socket, resp);
                        
                        break;
                    case 3:
                        //gravar
                        
                        break;
                    case 4:
                        //ler
                        
                        nome = arquivo.getNome();
                        lista = file.list();
                        
                        
                        
                        for (int i = 0; i < lista.length; i++) {
                            if (nome.equals(lista[i])){
                                //move o arquivo para o objeto
                                file = new File(pasta + "/" + nome);
                                
                                byte[] byteFile = new byte[(int) file.length()];
                                
                                arquivo = new Arquivo();                                
                                arquivo.setNome(file.getName());
                                arquivo.setConteudo(byteFile);
                                
                                
                                
                                break;
                            } else {
                                //mensagem arquivo não encontrado
                                resp.setStatus(3);
                            }
                        }
                        
                        
                        
                        
                        
                        break;
                    case 5:
                        //deleta dos outros servidores                      
                        
                        String nomeRemove = arquivo.getNome();                        
                        String path = pasta + "/" + nomeRemove;
                        file = new File(path);
                        file.delete();
                        
                        
                        
                        break;
                } 
            } else {
                try {
                    serversocket.close();
                    break;
                } catch (Exception e) {
                    System.out.println("Nao desconectei...");
                }
            }
        }
    }
    
    static boolean connect() {
        
        try {
            controlador_socket = serversocket.accept();
            return true;
        } catch (Exception e) {
            System.out.println("Erro de connect..." + e.getMessage());
            return false;
        }
    }
    
    //portas diferente para cada servidor
    
}
