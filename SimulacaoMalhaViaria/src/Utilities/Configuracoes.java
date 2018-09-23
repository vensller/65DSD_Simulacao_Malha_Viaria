package Utilities;

/**
 *
 * @author Ivens
 */
public class Configuracoes {
    
    private static Configuracoes instance;
    private int numeroVeiculos;
    private int opcaoExclusaoMutua;
    private int intervalo;
    
    private Configuracoes(){
        numeroVeiculos = 1;
        intervalo = 1;
        opcaoExclusaoMutua = 0;        
    }
    
    public static synchronized Configuracoes getInstance(){
        if (instance == null){
            instance = new Configuracoes();
        }
        
        return instance;
    }

    public int getNumeroVeiculos() {
        return numeroVeiculos;
    }

    public int getOpcaoExclusaoMutua() {
        return opcaoExclusaoMutua;
    }

    public void setNumeroVeiculos(int numeroVeiculos) {
        this.numeroVeiculos = numeroVeiculos;
    }

    public void setOpcaoExclusaoMutua(int opcaoExclusaoMutua) {
        this.opcaoExclusaoMutua = opcaoExclusaoMutua;
    }   

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
    
    
}
