/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.POO.Presentation.Desktop;

/**
 *
 * @author petronio
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mnuSistema = new javax.swing.JMenu();
        mnuSair = new javax.swing.JMenuItem();
        mnuCadastros = new javax.swing.JMenu();
        mnuAlunos = new javax.swing.JMenuItem();
        mnuProfessores = new javax.swing.JMenuItem();
        mnuTurmas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Escolar");

        mnuSistema.setText("Sistema");

        mnuSair.setText("Sair");
        mnuSistema.add(mnuSair);

        jMenuBar1.add(mnuSistema);

        mnuCadastros.setText("Cadastros");

        mnuAlunos.setText("Alunos");
        mnuAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAlunosActionPerformed(evt);
            }
        });
        mnuCadastros.add(mnuAlunos);

        mnuProfessores.setText("Professores");
        mnuCadastros.add(mnuProfessores);

        mnuTurmas.setText("Turmas");
        mnuCadastros.add(mnuTurmas);

        jMenuBar1.add(mnuCadastros);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAlunosActionPerformed
        
        // Criar o objeto da classe AlunoBuscar (JInternalFrame)
        AlunoBuscar tela = new AlunoBuscar();
        
        // Adicionar esse componente à tela atual
        this.add(tela);
        
        // Mostar a tela
        tela.show();
        
    }//GEN-LAST:event_mnuAlunosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuAlunos;
    private javax.swing.JMenu mnuCadastros;
    private javax.swing.JMenuItem mnuProfessores;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem mnuTurmas;
    // End of variables declaration//GEN-END:variables
}
