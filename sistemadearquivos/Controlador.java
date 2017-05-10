
package sistemadearquivos;

import java.net.ServerSocket;
import java.net.Socket;

public class Controlador {
    
    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao c;
    static Socket server1, server2, server3;
    
    
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
        new Controlador();
        
        while(true){
            if(connect()){               
                                
                arquivo = (Arquivo) c.receive(client_socket);
                System.out.println("Teste" + arquivo.getNome());
                
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
    
}
