
package sistemadearquivos;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao c;
    
    //criar 3 classes main
    
    
    public Servidor(int porta) {
        try {
            serversocket = new ServerSocket(porta);
            System.out.println("Engines On!!!" + porta);
            System.out.println("Waiting resquest...");            
        } catch (Exception e){
            System.out.println("Nao criei o server socket...");
        }            
}
    
    public static void main(String args[]){
        
        //acho q ta errado ...
        new Servidor (9601);
        new Servidor (9602);
        new Servidor (9603);
        
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
            client_socket = serversocket.accept();
            return true;
        } catch (Exception e) {
            System.out.println("Erro de connect..." + e.getMessage());
            return false;
        }
    }
    
    //portas diferente para cada servidor
    
}
