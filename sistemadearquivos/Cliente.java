
package sistemadearquivos;

import java.net.Socket;

public class Cliente {
    
    static Conexao c;
    static Socket socket;

    public Cliente() {
        try {
            socket = new Socket("localhost", 9600);
        } catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }
    
    public static void main(String[] args){
        
        Arquivo arquivo = new Arquivo();
        
        
        
    }
    
}
