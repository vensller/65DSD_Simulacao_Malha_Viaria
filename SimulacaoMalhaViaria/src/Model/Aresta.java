package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivens
 */
public class Aresta {
    
    private Vertice inicio;
    private Vertice fim;
    private List<Vertice> caminho;
    private List<Vertice> saidas;

    public Aresta(Vertice inicio, Vertice fim) {
        this.inicio = inicio;
        this.fim = fim;
        this.caminho = new ArrayList();
        definirCaminho();
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }   

    public List<Vertice> getCaminho() {
        return caminho;
    }

    public List<Vertice> getSaidas() {
        return saidas;
    }

    public void setSaidas(List<Vertice> saidas) {
        this.saidas = saidas;
    }
    
    private void definirCaminho(){
        if (inicio.getX() != fim.getX()){
            if (inicio.getX() < fim.getX()){
                for (int x = inicio.getX() + 1; x < fim.getX(); x++)
                    caminho.add(new Vertice(x, inicio.getY(), false, false));
            }else{
                for (int x = fim.getX() + 1; x < inicio.getX(); x++)
                    caminho.add(new Vertice(x, inicio.getY(), false, false));
            }
        }else{
            if (inicio.getY() < fim.getY()){
                for (int y = inicio.getY() + 1; y < fim.getY(); y++)
                    caminho.add(new Vertice(inicio.getX(), y, false, false));
            }else{
                for (int y = fim.getY() + 1; y < inicio.getY(); y++)
                    caminho.add(new Vertice(inicio.getX(), y, false, false));
            }            
        }
    }
    
}
