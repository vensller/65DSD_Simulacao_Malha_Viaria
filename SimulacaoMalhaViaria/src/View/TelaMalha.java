/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerMalha;
import Controller.ObservadorMalha;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivens
 */
public class TelaMalha extends javax.swing.JFrame implements ObservadorMalha{

    private ControllerMalha controller;
    private PainelMalha painelMalha;
    /**
     * Creates new form TelaMalha
     */
    public TelaMalha() {        
        controller = new ControllerMalha();
        controller.observar(this);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        menuBar = new javax.swing.JMenuBar();
        mnOpcoes = new javax.swing.JMenu();
        btnCarregar = new javax.swing.JMenuItem();
        btnLimpar = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mnOpcoes.setText("Opções");

        btnCarregar.setText("Carregar Malha");
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });
        mnOpcoes.add(btnCarregar);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        mnOpcoes.add(btnLimpar);

        menuBar.add(mnOpcoes);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 497, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        int retorno = fileChooser.showOpenDialog(this);
        
        if (retorno == JFileChooser.APPROVE_OPTION){    
            controller.carregarMalha(fileChooser.getSelectedFile().getAbsolutePath());
        }        
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        controller.limparMalha();
    }//GEN-LAST:event_btnLimparActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnCarregar;
    private javax.swing.JMenuItem btnLimpar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnOpcoes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void malhaCarregada() {
        painelMalha = new PainelMalha(controller);
        painelMalha.setSize(this.getSize());
        this.add(painelMalha);
        this.setContentPane(painelMalha);
        this.btnCarregar.setEnabled(false);
    }

    @Override
    public void erroCarregarMalha() {
        JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar a malha, favor verifique o arquivo!");
    }

    @Override
    public void malhaLimpa() {
        JOptionPane.showMessageDialog(this, "Malha limpa com sucesso!");
        this.btnCarregar.setEnabled(true);
        painelMalha.repaint();
    }
}
