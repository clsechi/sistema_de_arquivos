
package sistemadearquivos;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    static ServerSocket serversocket;
    static Socket cliente_socket;
    static Conexao c;
    
    
    public Servidor() {
        try {
            serversocket = new ServerSocket(9600);
            System.out.println("Engines On!!!");
            System.out.println("Waiting resquest...");            
        } catch (Exception e){
            System.out.println("Nao criei o server socket...");
        }            
}
    
    //portas diferente para cada servidor
    
}
