
package sistemadearquivos;

import javax.swing.JFileChooser;

public class SistemaDeArquivos {
    
    public static void main(String[] args) {
        
        Cliente cliente = new Cliente();
        
        int opr = 0;
        
        opr = cliente.Menu();
        
        cliente.DefineMsgs(opr);
        
        
        
        
    }
    
    /* aparentemente foi...
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
