
package sistemadearquivos;

import java.net.ServerSocket;
import java.net.Socket;

public class Controlador {
    
    static ServerSocket serversocket;
    static Socket cliente_socket;
    static Conexao c;
    static Socket socket;
    
    
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
            socket = new Socket("localhost", 9601);
        } catch (Exception e) {
            System.out.println("Nao consegui conectar aos servidores");
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
    
}
