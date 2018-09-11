package Utilities;

import Model.Aresta;
import Model.MalhaViaria;
import Model.Vertice;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivens
 */
public class ArchiveReader {
    
    public MalhaViaria readMalhaViaria(String archive){
        MalhaViaria malha = new MalhaViaria();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(archive));            
            String str = reader.readLine();
            str = reader.readLine();
            String[] arrayStr = null;                        
            
            while ((str = reader.readLine()) != null) {                
                arrayStr = str.split("\t");
                
                if (arrayStr.length != 4){
                    throw new IOException("Arquivo no formato incorreto!");
                }                
                
                Vertice origem = new Vertice(Integer.parseInt(arrayStr[0]), Integer.parseInt(arrayStr[1]));
                Vertice destino = new Vertice(Integer.parseInt(arrayStr[2]), Integer.parseInt(arrayStr[3]));
                malha.addAresta(new Aresta(origem, destino));
            }
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        System.out.println(malha);
        return malha;
    }
    
}
