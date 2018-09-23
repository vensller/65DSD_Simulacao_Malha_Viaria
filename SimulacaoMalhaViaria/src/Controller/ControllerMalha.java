package Controller;

import Model.Aresta;
import Utilities.Configuracoes;
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
    private List<Veiculo> veiculos;
    
    public ControllerMalha(){
        this.reader = new ArchiveReader();
        this.malha = null;
        this.observadores = new ArrayList();
        this.observadoresDesenhos = new ArrayList();
        this.veiculos = new ArrayList();
    }
    
    public void carregarMalha(String archive){
       this.malha = reader.readMalhaViaria(archive);       
       
       if (malha != null){
           notificaMalhaCarregada();
       }else notificaErroMalha();
    }  
   
    public void limparMalha(){
        for (Veiculo v : veiculos){
            v.desativar();            
        }
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
    
    //Cria as threads baseando-se na configuração escolhida
    public void iniciaSimulacao() throws InterruptedException{          
        for (int x = 0; x < Configuracoes.getInstance().getNumeroVeiculos(); x++){
            Veiculo veiculo = new Veiculo(malha, this);
            veiculos.add(veiculo);
            veiculo.start();
            Thread.sleep(Configuracoes.getInstance().getIntervalo());
        }
    }
    
    public void pararSimulacao(){
        for (Veiculo v : veiculos){
            v.desativar();
        }
    }
    
    @Override
    public synchronized void removeDesenhoCarro(Vertice v) {
        if (malha != null){
            malha.getVerticesCarros().remove(v);
        }
    }
    
    @Override
    public synchronized void setaPosicaoCarro(Vertice v) {
        malha.getVerticesCarros().add(v);
        for (ObservadorMalha obs : observadores){
            obs.repintar();
        }
    }

    @Override
    public void repintar() {
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
