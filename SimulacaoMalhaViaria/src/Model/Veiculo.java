package Model;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class Veiculo extends Thread{

    private MalhaViaria malha;
    private Aresta atual;
    private ObservadorVeiculo obs;
    private int posicaoAtual;
    private boolean ativo;
    
    public Veiculo(MalhaViaria malha, ObservadorVeiculo obs){
        this.malha = malha;
        this.obs = obs;
        this.atual = malha.getCaminho();
        this.ativo = true;
        this.posicaoAtual = 0;
    }    
    
    @Override
    public void run() {
        while (ativo && (atual != null)){
            obs.setaPosicaoCarro(atual.getCaminho().get(posicaoAtual));
            
            for (posicaoAtual = 1; posicaoAtual < atual.getCaminho().size() - 1; posicaoAtual++){
                desalocaAnterior();
                atual.getCaminho().get(posicaoAtual).alocarVertice();
                obs.setaPosicaoCarro(atual.getCaminho().get(posicaoAtual));
                
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Veiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }            
            
            trocarCaminho();            
        }
    }   
    
    public void desativar(){
        ativo = false;
    }
    
    private void desalocaAnterior(){
        obs.removeDesenhoCarro(atual.getCaminho().get(posicaoAtual - 1));
        atual.getCaminho().get(posicaoAtual - 1).desalocar();
    }
    
    private void trocarCaminho(){
        if (!atual.getSaidas().isEmpty()){
            Random r = new Random();
            int opcao = r.nextInt(atual.getSaidas().size() - 1);
            boolean trocouCaminho = false;

            do{
                if (atual.getCaminho().get(posicaoAtual).tentarAlocacao() && atual.getSaidas().get(opcao).getInicio().tentarAlocacao()){
                    trocouCaminho = true;
                    desalocaAnterior();
                    atual = atual.getSaidas().get(opcao);
                    posicaoAtual = 0;
                }
            }while(!trocouCaminho);
        }else {
            desalocaAnterior();
            atual = null;
        }
    }
    
    
}
