package Main;

import Utilities.ArchiveReader;
import javax.swing.JFileChooser;

/**
 *
 * @author Ivens
 */
public class Main {
    
    public static void main(String[] args){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        int result = fileChooser.showDialog(null, "Escolha");        
        if (result == JFileChooser.APPROVE_OPTION){
            ArchiveReader reader = new ArchiveReader();
            reader.readMalhaViaria(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println("Teste!");              
        }     
    }
    
}
