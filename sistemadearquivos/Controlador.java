
package sistemadearquivos;

import java.net.ServerSocket;
import java.net.Socket;

public class Controlador {
    
    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao c;
    static Socket server1, server2, server3;
    static int lastServer = 0;
    static boolean AllServersOn = true;
    
    
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
            System.out.println( server1.getLocalAddress() + "/" + server1.getPort() +  " conectado");
            server2 = new Socket("localhost", 9602);
            System.out.println( server2.getLocalAddress() + "/" + server2.getPort() +  " conectado");
            server3 = new Socket("localhost", 9603);
            System.out.println( server3.getLocalAddress() + "/" + server3.getPort() +  " conectado");
        } catch (Exception e) {
            System.out.println("Nao consegui conectar aos servidores");
            AllServersOn = false;            
        }
        
    }
    
    public static void main(String args[]){
        Requisicao req;
        Resposta resp;

        new Controlador();
        
        while(true){
            
            int option;
            Socket server;
            if(connect()){
                if(AllServersOn){

                    req = (Requisicao) c.receive(client_socket);
                    
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
                            
                            //envia para o servidor
                            c.send(server1, req);
                            //recebe a resposta do servidor
                            resp = (Resposta) c.receive(server1);
                            
                            if (resp.getStatus() == 0){
                                //remove dos outros servidores
                                req.setOption(5);
                                c.send(server2, req);
                                c.send(server3, req);
                            }
                            
                            //envia a resposta para o cliente
                            c.send(client_socket, resp);
                            
                            break;
                        case 3:
                            //gravar
                            
                            //envia para o servidor
                            c.send(server1, req);
                            //recebe a resposta do servidor
                            resp = (Resposta) c.receive(server1);
                            
                            //replica nos outros servidores
                            if (resp.getStatus() == 0){
                                /*
                                req.setOption(5);
                                c.send(server2, req);
                                c.send(server3, req);*/
                            }                            
                            
                            //envia a resposta para o cliente
                            c.send(client_socket, resp);
                            
                            break;
                        case 4:
                            //ler
                            
                            //envia para o servidor
                            c.send(server1, req);
                            //recebe a resposta do servidor
                            resp = (Resposta) c.receive(server1);
                            //envia a resposta para o cliente
                            c.send(client_socket, resp);
                            
                            break;
                    }
                } else {
                    //servidores offline
                    
                    resp = new Resposta();
                    //falta mostrar a msg de erro
                    resp.setStatus(2);
                    c.send(client_socket, resp);
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
    
    static boolean Servers(){
        
        
        return false;
    }
}
