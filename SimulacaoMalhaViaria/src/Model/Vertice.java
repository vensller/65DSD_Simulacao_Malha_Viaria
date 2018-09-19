package Model;

/**
 *
 * @author Ivens
 */
public class Vertice {
    
    private int x;
    private int y;
    private boolean borda;

    public Vertice(int x, int y, boolean usarEscala, boolean borda) {
        if (usarEscala){
            this.x = x*10;
            this.y = y*10;   
        }else{
            this.x = x;
            this.y = y;
        }
        this.borda = borda;
    }

    public boolean isBorda() {
        return borda;
    }

    public void setBorda(boolean borda) {
        this.borda = borda;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
    
}
