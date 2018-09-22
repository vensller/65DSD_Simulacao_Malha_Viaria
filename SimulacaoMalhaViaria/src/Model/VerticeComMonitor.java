package Model;

/**
 *
 * @author Ivens
 */
public class VerticeComMonitor extends Vertice{
    
    public VerticeComMonitor(int x, int y, boolean usarEscala, boolean borda) {
        super(x, y, usarEscala, borda);
    }

    @Override
    public void alocarVertice() {
        
    }

    @Override
    public boolean tentarAlocacao() {
        return true;
    }

    @Override
    public void desalocar() {
        
    }
    
}
