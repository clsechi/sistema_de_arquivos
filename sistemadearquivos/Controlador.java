
package sistemadearquivos;

import java.net.ServerSocket;
import java.net.Socket;

public class Controlador {
    
    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao c;
    static Socket server1, server2, server3;
    static int lastServer = 0;
    
    
    public Controlador() {
        //cliente
        try {
            serversocket = new ServerSocket(9600);
            System.out.println("Controlador Online");
            System.out.println("Waiting resquest...");
        } catch (Exception e){
            System.out.println("Controlador Offline");
        }
        
        //servidores
        
        try {
            server1 = new Socket("localhost", 9601);
            System.out.println("Server 1 conectado");
            server2 = new Socket("localhost", 9602);
            server3 = new Socket("localhost", 9603);
        } catch (Exception e) {
            System.out.println("Nao consegui conectar aos servidores");
        }
        
    }
    
    public static void main(String args[]){
        Arquivo arquivo;
        Requisicao req = new Requisicao();
        Resposta resp = new Resposta();
        
        new Controlador();
        
        while(true){
            int option;
            Socket server;
            if(connect()){               
                                
                req = (Requisicao) c.receive(client_socket);
                //arquivo = req.getArquivo();
                //System.out.println("Teste" + arquivo.getNome());
                //pega a operação desejada
                option = req.getOption();
                
                switch (option) {
                    case 1:
                        //lista
                        
                        //chama o metodo para baleancear os servidores
                        server = balance();
                        //ultimo servidor utilizado
                        lastServer = server.toString().charAt(6);
                        
                        //envia para o servidor
                        c.send(server, req);
                        
                        //recebe a resposta do servidor
                        resp = (Resposta) c.receive(server);
                        
                        //envia a resposta para o cliente
                        c.send(client_socket, resp);
                        
                        break;
                    case 2:
                        //remover
                        
                        break;
                    case 3:
                        //gravar
                        
                        break;
                    case 4:
                        //ler
                        
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
            client_socket = serversocket.accept();
            return true;
        } catch (Exception e) {
            System.out.println("Erro de connect..." + e.getMessage());
            return false;
        }
    }
    
    static Socket balance(){        
        switch (lastServer) {
            case 1:
                return server2;
            case 2:
                return server3;
            default:
                return server1;
        }
    }
}
