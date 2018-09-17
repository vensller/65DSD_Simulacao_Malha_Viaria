package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivens
 */
public class MalhaViaria {
    
    private List<Aresta> arestas;    

    public MalhaViaria() {
        arestas = new ArrayList<Aresta>();
    }
    
    public void addAresta(Aresta a){
        arestas.add(a);
    }    

    public List<Aresta> getArestas() {
        return arestas;
    }

    @Override
    public String toString() {
        String retorno = "";
        
        for (Aresta a : arestas){
            retorno += "Início: " + a.getInicio().getX() + " " + a.getInicio().getY() + " Fim: " + a.getFim().getX() + " " + a.getFim().getY() + "\n";
        }
        
        return retorno;
    }
    
    
        
}
