package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivens
 */
public class MalhaViaria {
    
    private List<Aresta> arestas;    
    private List<Vertice> bordasLivres;

    public MalhaViaria() {
        arestas = new ArrayList();
        bordasLivres = new ArrayList();
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
            retorno += "Início: " + a.getInicio().getX() + " " + a.getInicio().getY() + a.getInicio().isBorda() + " Fim: " + a.getFim().getX() + " " + a.getFim().getY() + a.getFim().isBorda() + "\n" ;
        }
        
        return retorno;
    }
    
    //Falta aplicar exclusão mútua
    public Vertice getBordaLivre(){        
        if (!bordasLivres.isEmpty()){
            return bordasLivres.get(0);
        }
        
        return null;
    }
    
    public void defineBordasLivres(){
        for (Aresta a : arestas){
            if (a.getInicio().isBorda()) bordasLivres.add(a.getInicio());
        }
    }     
    
        
}
