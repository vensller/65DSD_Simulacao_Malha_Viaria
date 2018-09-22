package Utilities;

import Model.Aresta;
import Model.MalhaViaria;
import Model.Vertice;
import Model.VerticeComMonitor;
import Model.VerticeComSemaforo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivens
 */
public class ArchiveReader {

    public MalhaViaria readMalhaViaria(String archive) {
        List<Vertice> vertices = new ArrayList();
        MalhaViaria malha = new MalhaViaria();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(archive));
            int linhas = 0;
            int colunas = 0;
            String str = reader.readLine().trim();
            linhas = Integer.parseInt(str);
            str = reader.readLine().trim();
            colunas = Integer.parseInt(str);
            String[] arrayStr = null;

            while ((str = reader.readLine()) != null) {
                arrayStr = str.split("\t");
                
                int x = Integer.parseInt(arrayStr[0]);
                int y = Integer.parseInt(arrayStr[1]);
                boolean isBorda = (x == 0) || (x == colunas - 1) || (y == 0) || (y == linhas - 1);
                Vertice origem = criaVertice(x, y, isBorda, vertices);     

                x = Integer.parseInt(arrayStr[2]);
                y = Integer.parseInt(arrayStr[3]);
                isBorda = (x == 0) || (x == colunas - 1) || (y == 0) || (y == linhas - 1);               
                Vertice destino = criaVertice(x, y, isBorda, vertices);

                
                malha.addAresta(new Aresta(origem, destino));

            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        malha.defineBordasLivres();
        System.out.println(malha);
        defineSemaforos(malha);
        return malha;
    }
    
    private void defineSemaforos(MalhaViaria malha){       
        for (Aresta a : malha.getArestas()){
            List<Aresta> arestasSaidas = new ArrayList<>();
            if (!a.getFim().isBorda()){
                for (Aresta b : malha.getArestas()){
                    if (a != b){
                        if (a.getFim() == b.getInicio())
                            arestasSaidas.add(b);
                    }
                }
            }
            a.setSaidas(arestasSaidas);            
        }        
    }
    
    private Vertice criaVertice(int x, int y, boolean isBorda, List<Vertice> vertices){
        Vertice retorno = null;
        
        for (Vertice v : vertices){
            if ((v.getX() == x * 10) && (v.getY() == y * 10))
                retorno = v;
        }
        
        if (retorno == null){        
            if (Configuracoes.getInstance().getOpcaoExclusaoMutua() == 0)
                retorno = new VerticeComSemaforo(x, y, true, isBorda);
            else retorno = new VerticeComMonitor(x, y, true, isBorda);        
            
            vertices.add(retorno);
        }
        
        return retorno;
    }

}
