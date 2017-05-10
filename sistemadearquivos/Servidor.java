
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
        Arquivo arquivo;
        Requisicao req = new Requisicao();
        Resposta resp = new Resposta();
        
        //acho q ta errado ...
        new Servidor (9601);
        new Servidor (9602);
        new Servidor (9603);
        
        while(true){
            int option;
            if(connect()){
                
                req = (Requisicao) c.receive(controlador_socket);
                arquivo = req.getArquivo();
                option = req.getOption();
                
                File file = new File(pasta);
                //se a pasta não existir ele cria
                if (!file.exists()){
                    file.mkdirs();
                }               
                
                switch (option) {
                    case 1:
                        //lista

                        String[] lista = file.list();

                        c.send(controlador_socket, resp);
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
            controlador_socket = serversocket.accept();
            return true;
        } catch (Exception e) {
            System.out.println("Erro de connect..." + e.getMessage());
            return false;
        }
    }
    
    //portas diferente para cada servidor
    
}
