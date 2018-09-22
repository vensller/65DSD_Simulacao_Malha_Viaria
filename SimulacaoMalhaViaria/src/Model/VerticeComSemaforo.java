package Model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class VerticeComSemaforo extends Vertice{
    
    private Semaphore mutex;
    
    
    public VerticeComSemaforo(int x, int y, boolean usarEscala, boolean borda) {
        super(x, y, usarEscala, borda);
        mutex = new Semaphore(1);
    }
    
    public void alocarVertice(){
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(VerticeComSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean tentarAlocacao() {
        try {
            return mutex.tryAcquire(50, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(VerticeComSemaforo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void desalocar() {
        mutex.release();
    }
    
}
