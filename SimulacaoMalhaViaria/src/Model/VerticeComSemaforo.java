package Model;

/**
 *
 * @author Ivens
 */
public class VerticeComSemaforo extends Vertice{
    
    public VerticeComSemaforo(int x, int y, boolean usarEscala, boolean borda) {
        super(x, y, usarEscala, borda);
    }
    
    public void alocarVertice(){
        
    }

    @Override
    public boolean tentarAlocacao() {
        return true;
    }

    @Override
    public void desalocar() {
        
    }
    
}
