
package sistemadearquivos;

/* 

Carlos Sechi RA: 20422328
Esdras Bertoldo RA: 20401437

*/

import java.util.Scanner;

public class SistemaDeArquivos {

    public SistemaDeArquivos() {
    }
    
    public static void main(String[] args) {
        
        Cliente cliente;
        
        int opr;
        char keep = 'Y';
        Scanner scn = new Scanner(System.in);
        
        while(keep == 'Y' || keep == 'y'){
            cliente = new Cliente();
            
            opr = cliente.Menu();
        
            cliente.DefineMsgs(opr);
        
            System.out.println("\nDeseja efeutar outra opereção? (Y/N)");
            keep = scn.next().charAt(0);
        }   
    }
}
