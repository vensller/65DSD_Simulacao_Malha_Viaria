package Model;

/**
 *
 * @author Ivens
 */
public class Veiculo extends Thread{

    private MalhaViaria malha;
    private Vertice verticeAtual;
    private ObservadorVeiculo obs;
    
    public Veiculo(MalhaViaria malha, ObservadorVeiculo obs){
        this.malha = malha;
        this.obs = obs;
        this.verticeAtual = null;
    }    
    
    @Override
    public void run() {
        if (verticeAtual == null){
            verticeAtual = malha.getBordaLivre();
            obs.desenhaCarro(verticeAtual.getX(), verticeAtual.getY());
        }
    }   
    
    
}
