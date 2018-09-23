package Model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class VerticeComMonitor extends Vertice{
    
    private boolean alocado;
    
    public VerticeComMonitor(int x, int y, boolean usarEscala, boolean borda) {
        super(x, y, usarEscala, borda);
        alocado = false;
    }

    @Override
    public synchronized void alocarVertice() {
        if (alocado){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(VerticeComMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        alocado = true;
    }

    @Override
    public synchronized boolean tentarAlocacao() {
        if (!alocado){
            alocado = true;
            return true;
        }else return false;
    }

    @Override
    public synchronized void desalocar() {
        alocado = false;
        notify();
    }
    
}
