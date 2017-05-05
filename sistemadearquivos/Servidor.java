
package sistemadearquivos;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    static ServerSocket serversocket;
    static Socket cliente_socket;
    static Conexao c;
    
    
    public Servidor() {
        try {
            serversocket = new ServerSocket(9601);
            System.out.println("Engines On!!!");
            System.out.println("Waiting resquest...");            
        } catch (Exception e){
            System.out.println("Nao criei o server socket...");
        }            
}
    
    public static void main(String args[]){
        
        while(true){
            if(connect()){
                
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
            cliente_socket = serversocket.accept();
            return true;
        } catch (Exception e) {
            System.out.println("Erro de connect..." + e.getMessage());
            return false;
        }
    }
    
    //portas diferente para cada servidor
    
}
