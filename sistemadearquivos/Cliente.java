package sistemadearquivos;

import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Cliente {
    
    static Conexao c;
    
    static Socket socket;
    private Arquivo arquivo;
    Scanner scn = new Scanner(System.in);
    
    public Cliente() {
        try {
            socket = new Socket("localhost", 9600);
        } catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }
    
    public int Menu(){
        int opr = 0;
        boolean keep = true;
        
        System.out.println("**** Sistema de Arquivos Distribuido ****");
        System.out.println("Digite o número da operação desejada:\n"
                + "1 - Listar Arquivos\n"
                + "2 - Remover Arquivo\n"
                + "3 - Gravar Arquivo\n"
                + "4 - Ler Arquivo");
        opr = scn.nextInt();
        
        while (keep){
            if (opr < 1 || opr > 4){
                System.out.println("Operação inválida, por favor digite novamente:");
                opr = scn.nextInt();
            } else {
                keep = false;
            }
        }
        return opr;
    }
    
    public void DefineMsgs(int opr){
        //int opr = Menu();
        int status = 0;
        
        switch (opr) {
            case 1 :
                status = ListarArquivos();
                break;
            case 2 :
                RemoverArquivo();
                break;
            case 3 :
                GravarArquivo();
                break;
            case 4 :
                LerArquivo();
                break;
        }
        
        if (status == 0){
            System.out.println("Solicitação concluída com sucesso");
        } else {
            System.out.println("Solicitação não pode ser realizada");
        }
    }
    
    
    
    private void GravarArquivo(){
        System.out.println("*** Gravação de Arquivo ***");
        
        try{
            FileInputStream fis;
            
            SistemaDeArquivos sis = new SistemaDeArquivos();
            JFileChooser chooser = sis.Escolha();
            
            /*chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Escolha o arquivo");
            chooser.showOpenDialog(chooser);//abre a janela*/
            
            File ArquivoEscolhido = chooser.getSelectedFile();
            
            byte[] byteFile = new byte[(int) ArquivoEscolhido.length()];
            fis = new FileInputStream(ArquivoEscolhido);
            fis.read(byteFile);
            fis.close();
            
            long kbSize = ArquivoEscolhido.length() / 1024;
            
            arquivo = new Arquivo();
            arquivo.setConteudo(byteFile);
            arquivo.setDataHoraUpload(new Date());
            arquivo.setNome(ArquivoEscolhido.getName());
            arquivo.setTamanhoKB(kbSize);
            
            c.send(socket, arquivo);
            
        } catch (Exception e ){
            e.printStackTrace();
        }
        
       
    }
    
    private int ListarArquivos() {
        System.out.println("*** Listar Arquivos *** ");
        
        //listar todos os arquivos???
        
        return 0;
    }
    
    private void RemoverArquivo() {
        System.out.println("*** Remoção de Arquivo *** ");
        
        String FileName;
        
        System.out.println("Digite o nome do arquivo que deseja remover");
        FileName = scn.next();
        
        
        //envio do objeto
        arquivo = new Arquivo();
        arquivo.setNome(FileName);        
        c.send(socket, arquivo);      
        
    }
    
    private void LerArquivo() {
        System.out.println("*** Ler Arquivo *** ");
        
        String FileName;
        
        System.out.println("Digite o nome do arquivo que deseja ler");
        FileName = scn.next();
        
        
    }
    
    
}