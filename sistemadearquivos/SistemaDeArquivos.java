
package sistemadearquivos;

/* 

Carlos Sechi RA: 20422328
Esdras Bertoldo RA: 20401437

*/

import java.util.Scanner;
import javax.swing.JFileChooser;

public class SistemaDeArquivos {
    
    public static void main(String[] args) {
        
        Cliente cliente = new Cliente();
        
        int opr;
        char keep = 'Y';
        Scanner scn = new Scanner(System.in);
        
        
        while(keep == 'Y'){        
            opr = cliente.Menu();
        
            cliente.DefineMsgs(opr);
        
            System.out.println("Deseja efeutar outra opereção? (Y/N)");
            keep = scn.next().charAt(0);
        }
        
    }
    
    /*
    metodo para criar a janela para escolha do arquivo
    retorna o objeto para a classe Cliente
    */
    public JFileChooser Escolha(){
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogTitle("Escolha o arquivo");
        chooser.showOpenDialog(chooser);//abre a janela
        
        return chooser;
    }
}
