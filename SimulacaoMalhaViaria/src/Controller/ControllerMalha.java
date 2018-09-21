package Controller;

import Model.Aresta;
import Model.MalhaViaria;
import Model.ObservadorVeiculo;
import Model.Veiculo;
import Model.Vertice;
import Utilities.ArchiveReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivens
 */
public class ControllerMalha implements ObservadorVeiculo{
    
    private ArchiveReader reader;
    private MalhaViaria malha;   
    private List<ObservadorMalha> observadores;
    private List<ObservadorDesenhos> observadoresDesenhos;
    
    public ControllerMalha(){
        this.reader = new ArchiveReader();
        this.malha = null;
        this.observadores = new ArrayList();
        this.observadoresDesenhos = new ArrayList();
    }
    
    public void carregarMalha(String archive){
       this.malha = reader.readMalhaViaria(archive);       
       
       if (malha != null){
           notificaMalhaCarregada();
       }else notificaErroMalha();
    }  
   
    public void limparMalha(){
       this.malha = null;
       notificaMalhaLimpa();
   }
   
    public void observar(ObservadorMalha observador){
       this.observadores.add(observador);
    }  
   
    public void pararObservacao(ObservadorMalha observador){
       this.observadores.remove(observador);
    }
   
    public void observarDesenhos(ObservadorDesenhos observador){
       this.observadoresDesenhos.add(observador);
    }
   
    public void pararObservarDesenhos(ObservadorDesenhos observador){
       this.observadoresDesenhos.remove(observador);
    }       
   
    public void desenhaCarros(){
        if (malha != null){
            for (Vertice v : malha.getVerticesCarros()){
                desenhaCarro(v.getX(), v.getY());
            }
        }
    }   
   
    public void desenharMalhaViaria(){
       if (malha != null){
           for (Aresta a : malha.getArestas()){           
               for (Vertice v : a.getCaminho())
                   desenhaPontoAresta(v.getX(), v.getY());
                
               desenhaVertice(a.getInicio().getX(), a.getInicio().getY());
               desenhaVertice(a.getFim().getX(), a.getFim().getY());           
           }
       }
    }
    
    public void iniciaSimulacao(){          
           Veiculo veiculo = new Veiculo(malha, this);
           veiculo.start();
    }
    
    @Override
    public void removeDesenhoCarro(Vertice v) {
        malha.getVerticesCarros().remove(v);
    }
    
    @Override
    public void setaPosicaoCarro(Vertice v) {
        malha.getVerticesCarros().add(v);
        for (ObservadorMalha obs : observadores){
            obs.repintar();
        }
    }
   
    private void notificaMalhaLimpa(){
       for (ObservadorMalha obs : observadores)
           obs.malhaLimpa();
    }
   
    private void desenhaPontoAresta(int x, int y){
       for (ObservadorDesenhos obs : observadoresDesenhos)
           obs.desenharPontoAresta(x, y);
    }
   
    private void desenhaVertice(int x, int y){
       for (ObservadorDesenhos obs : observadoresDesenhos)
           obs.desenharVertice(x, y);
    }
    
    private void notificaMalhaCarregada(){
       for (ObservadorMalha obs : observadores)
           obs.malhaCarregada();
    }
   
    private void notificaErroMalha(){
       for (ObservadorMalha obs : observadores)
           obs.erroCarregarMalha();
    }

    private void desenhaCarro(int x, int y){
       for (ObservadorDesenhos obs : observadoresDesenhos){
           obs.desenharVeiculo(x, y);
       }
    }   
    
}
