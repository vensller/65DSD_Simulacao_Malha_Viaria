package View;

import Controller.ControllerMalha;
import Controller.ObservadorDesenhos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Ivens
 */
public class PainelMalha extends javax.swing.JPanel implements ObservadorDesenhos{

    private ControllerMalha controller;
    private Graphics graphics;
    /**
     * Creates new form PainelMalha
     */
    public PainelMalha(ControllerMalha controller) {
        this.controller = controller;
        this.controller.observarDesenhos(this);
        this.graphics = null;
        initComponents();
    }
    
    @Override
    public void paintComponent( Graphics g ){
        super.paintComponent(g);
        graphics = g;
        controller.desenharMalhaViaria();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void desenharVertice(int x, int y) {
        if (graphics != null) desenhaPonto(x, y, Color.red);
    }

    @Override
    public void desenharPontoAresta(int x, int y) {
        if (graphics != null) desenhaPonto(x, y, Color.GRAY);
    }
    
    private void desenhaPonto(int x, int y, Color cor){
        Graphics2D g2 = (Graphics2D) graphics;
        Ellipse2D.Double circle = new Ellipse2D.Double(x - 4, y - 4, 8, 8);
        g2.setColor(cor);
        g2.fill(circle);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
