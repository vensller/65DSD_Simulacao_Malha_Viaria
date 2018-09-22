package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivens
 */
public class MalhaViaria {
    
    private List<Aresta> arestas;    
    private List<Aresta> arestasInicio;
    private List<Vertice> verticesCarros;

    public MalhaViaria() {
        arestas = new ArrayList();
        arestasInicio = new ArrayList();
        verticesCarros = new ArrayList();
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
    
    public Aresta getCaminho(){
        for (Aresta a : arestasInicio){
            if (a.getInicio().tentarAlocacao()){
                return a;
            }
        }
        
        return null;
    }
    
    public void defineBordasLivres(){
        for (Aresta a : arestas){
            if (a.getInicio().isBorda()) arestasInicio.add(a);
        }
    }     

    public List<Vertice> getVerticesCarros() {
        return verticesCarros;
    }    
    
        
}
