package Controller;

import Utilities.Configuracoes;

/**
 *
 * @author Ivens
 */
public class ControllerConfiguracoes {
    
    public void defineNumeroVeiculos(int numero){
        Configuracoes.getInstance().setNumeroVeiculos(numero);
    }
    
    public void defineOpcaoExclusaoMutua(int opcao){
        Configuracoes.getInstance().setOpcaoExclusaoMutua(opcao);
    }
    
    public void defineIntervalo(int intervalo){
        Configuracoes.getInstance().setIntervalo(intervalo);
    }
    
    public int getNumeroVeiculos(){
        return Configuracoes.getInstance().getNumeroVeiculos();
    }
    
    public int getExclusaoMutua(){
        return Configuracoes.getInstance().getOpcaoExclusaoMutua();
    }

    public int getIntervaloSelecao() {
        return Configuracoes.getInstance().getIntervalo();
    }
}
