/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerMalha;
import Controller.ObservadorMalha;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        btnLimpar.setEnabled(false);
        btnIniciarSimulacao.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        DesktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mnOpcoes = new javax.swing.JMenu();
        btnCarregar = new javax.swing.JMenuItem();
        btnIniciarSimulacao = new javax.swing.JMenuItem();
        btnConfiguracoes = new javax.swing.JMenuItem();
        btnLimpar = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DesktopPane.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout DesktopPaneLayout = new javax.swing.GroupLayout(DesktopPane);
        DesktopPane.setLayout(DesktopPaneLayout);
        DesktopPaneLayout.setHorizontalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 894, Short.MAX_VALUE)
        );
        DesktopPaneLayout.setVerticalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        mnOpcoes.setText("Opções");

        btnCarregar.setText("Carregar Malha");
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });
        mnOpcoes.add(btnCarregar);

        btnIniciarSimulacao.setText("Iniciar Simulação");
        btnIniciarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSimulacaoActionPerformed(evt);
            }
        });
        mnOpcoes.add(btnIniciarSimulacao);

        btnConfiguracoes.setText("Configurações");
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        mnOpcoes.add(btnConfiguracoes);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(DesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(DesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
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

    private void btnIniciarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSimulacaoActionPerformed
        try {
            controller.iniciaSimulacao();
        } catch (InterruptedException ex) {
            Logger.getLogger(TelaMalha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIniciarSimulacaoActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        TelaConfiguracoes tela = new TelaConfiguracoes();
        tela.setVisible(true);
    }//GEN-LAST:event_btnConfiguracoesActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane;
    private javax.swing.JMenuItem btnCarregar;
    private javax.swing.JMenuItem btnConfiguracoes;
    private javax.swing.JMenuItem btnIniciarSimulacao;
    private javax.swing.JMenuItem btnLimpar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnOpcoes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void malhaCarregada() {
        painelMalha = new PainelMalha(controller);
        painelMalha.setSize(this.DesktopPane.getSize());
        DesktopPane.add(painelMalha);
        painelMalha.repaint();
        btnCarregar.setEnabled(false);
        btnIniciarSimulacao.setEnabled(true);
        btnLimpar.setEnabled(true);
    }

    @Override
    public void erroCarregarMalha() {
        JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar a malha, favor verifique o arquivo!");
    }

    @Override
    public void malhaLimpa() {
        JOptionPane.showMessageDialog(this, "Malha limpa com sucesso!");
        this.btnCarregar.setEnabled(true);
        btnIniciarSimulacao.setEnabled(false);
        painelMalha.repaint();
    }
    
    @Override
    public synchronized void repintar(){
        painelMalha.repaint();
    }
}
