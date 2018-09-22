package Model;

import java.util.Collections;
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
            
            for (posicaoAtual = posicaoAtual + 1; posicaoAtual < atual.getCaminho().size() - 1; posicaoAtual++){
                desalocaAnterior();
                alocaAtual();                
                try {
                    sleep(50);
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
    
    private void alocaAtual(){        
        atual.getCaminho().get(posicaoAtual).alocarVertice();
        obs.setaPosicaoCarro(atual.getCaminho().get(posicaoAtual));
    }
    
    //Realiza a escolha de qual caminho será seguido ao encontrar um cruzamento
    //Caso o vértice de fim é uma borda, a thread se encerra
    private void trocarCaminho(){
        if (!atual.getSaidas().isEmpty()){
            Collections.shuffle(atual.getSaidas()); //Embaralha as saídas para depois escolher a da posição = 0
            boolean trocouCaminho = false;
            do{
                if (atual.getCaminho().get(posicaoAtual).tentarAlocacao()){
                    if ((atual.getSaidas().get(0).getCaminho().size() > 1) 
                            && (atual.getSaidas().get(0).getCaminho().get(1).tentarAlocacao())){
                        trocouCaminho = true;
                        desalocaAnterior();//Vai desalocar a posicao que o veículo está                        
                        obs.setaPosicaoCarro(atual.getCaminho().get(posicaoAtual)); //Vai desenhar o veículo no cruzamento
                        posicaoAtual++;
                        desalocaAnterior();//Vai desalocar o cruzamento (já que conseguiu alocar alguma saída)
                        atual = atual.getSaidas().get(0);
                        posicaoAtual = 1;
                    }else atual.getCaminho().get(posicaoAtual).desalocar();
                }
            }while(!trocouCaminho);
        }else {
            desalocaAnterior();
            atual = null;
            ativo = false;
            obs.repintar();
        }
    }
    
    
}
