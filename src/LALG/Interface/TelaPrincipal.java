package LALG.Interface;

import LALG.Compilador.Helper;
import LALG.AnalisadorLexico.Lexico;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import LALG.Logger.MainLogger;
import LALG.AnalisadorSintatatico.Procedimento;
import LALG.AnalisadorSintatatico.Syntax;
import LALG.AnalisadorSintatatico.Var;
import com.thoughtworks.xstream.XStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import LALG.AnalisadorSintatatico.Contexto;

/**
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public final class TelaPrincipal extends javax.swing.JFrame {

    List<CodeTab> listaTab = new ArrayList<CodeTab>();
    List<String> sentences = new ArrayList<String>();
    public static Logger log = Logger.getLogger(TelaPrincipal.class);    
    /**
     *
     */
    public TelaPrincipal() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Compilador LALG - by Ediney Lopes, Fellipe Abib, Mateus Pereira");
        salvarMenuItem.setEnabled(false);
        compilarMenuItem.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        novoArquivoMenu = new javax.swing.JMenuItem();
        abrirMenuItem = new javax.swing.JMenuItem();
        salvarMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        sairMenuItem = new javax.swing.JMenuItem();
        ferramentasMenu = new javax.swing.JMenu();
        compilarMenuItem = new javax.swing.JMenuItem();
        exportTokensMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painel.setBackground(new java.awt.Color(255, 255, 255));
        painel.setAutoscrolls(true);
        painel.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jTextPane1.setEditable(false);
        jScrollPane1.setViewportView(jTextPane1);

        jMenu1.setText("Arquivo");

        novoArquivoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        novoArquivoMenu.setText("Novo Arquivo");
        novoArquivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoArquivoMenuActionPerformed(evt);
            }
        });
        jMenu1.add(novoArquivoMenu);

        abrirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        abrirMenuItem.setText("Abrir Arquivo");
        abrirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(abrirMenuItem);

        salvarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        salvarMenuItem.setText("Salvar");
        salvarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(salvarMenuItem);
        jMenu1.add(jSeparator1);

        sairMenuItem.setText("Sair");
        sairMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(sairMenuItem);

        jMenuBar1.add(jMenu1);

        ferramentasMenu.setText("Ferramentas");

        compilarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        compilarMenuItem.setText("Compilar");
        compilarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilarMenuItemActionPerformed(evt);
            }
        });
        ferramentasMenu.add(compilarMenuItem);

        exportTokensMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        exportTokensMenuItem.setText("Exportar Tokens");
        exportTokensMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportTokensMenuItemActionPerformed(evt);
            }
        });
        ferramentasMenu.add(exportTokensMenuItem);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        jMenuItem1.setText("Exportar Arvore Sintática");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        ferramentasMenu.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem2.setText("Mostrar Árvore Sintártica");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        ferramentasMenu.add(jMenuItem2);

        jMenuBar1.add(ferramentasMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                    .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("static-access")
    public File showSalvarDialog() {
        JFileChooser jfc = new JFileChooser("./");
        jfc.setApproveButtonText("Salvar");
        jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY);
        jfc.setMultiSelectionEnabled(false);
        jfc.setVisible(true);
        int opt = jfc.showOpenDialog(null);
        if (jfc.APPROVE_OPTION == opt) {
            File arquivo = new File(jfc.getCurrentDirectory(), jfc.getSelectedFile().getName());
            return arquivo;
        }
        return null;
    }

    @SuppressWarnings("static-access")
    public File showAbrirArquivoDialog() {
        JFileChooser jfc = new JFileChooser("./");
        jfc.setApproveButtonText("Abrir");
        jfc.setFileSelectionMode(jfc.FILES_ONLY);
        jfc.setMultiSelectionEnabled(false);
        jfc.setVisible(true);

        int opt = jfc.showOpenDialog(null);
        if (jfc.APPROVE_OPTION == opt) {
            log.log(Priority.DEBUG, "Arquivo carregado com sucesso!");
            return jfc.getSelectedFile();
        }
        log.log(Priority.ERROR, "Falha ao carregar arquivo!");
        return null;
    }

    /**
     *
     * @param tab
     */
    public void addTab(CodeTab tab) {
        listaTab.add(tab);
        painel.addTab(tab.getNome(), tab.getIco(), tab, tab.getTip());
        if (listaTab.size() > 0) {
            compilarMenuItem.setEnabled(true);
        }
    }

    private void sairMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairMenuItemActionPerformed

        int opcao;
        opcao = JOptionPane.showConfirmDialog(null,
                "Deseja sair do programa?",
                "Sair", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_sairMenuItemActionPerformed

    private void novoArquivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoArquivoMenuActionPerformed
        File ar = showSalvarDialog();
        if (ar != null) {
            CodeTab c = new CodeTab(ar);
            c.setSize(painel.getWidth(), painel.getHeight());
            c.pack();
            addTab(c);
            salvarMenuItem.setEnabled(true);
        }
    }//GEN-LAST:event_novoArquivoMenuActionPerformed

    private void compilarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compilarMenuItemActionPerformed
        jTextPane1.setText("");
        int selectedId = painel.getSelectedIndex();
        String code = listaTab.get(selectedId).getCampo().getText();
        listaTab.get(selectedId).save();
        String path = listaTab.get(selectedId).getFileAbsolutePath();
        if (!code.toString().isEmpty()) {
            MainLogger.logInfo("Arquivo " + listaTab.get(selectedId).getNome() + " salvo !");
            this.sentences = Helper.readFile(path);
            // Inicia-se a Análise Léxica
            Lexico.run(sentences);
            Syntax.run();
        }
    }//GEN-LAST:event_compilarMenuItemActionPerformed

    private void salvarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarMenuItemActionPerformed
        int selectedId = painel.getSelectedIndex();
        listaTab.get(selectedId).save();
    }//GEN-LAST:event_salvarMenuItemActionPerformed

    private void abrirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMenuItemActionPerformed
        File f = showAbrirArquivoDialog();
        if (f != null) {
            CodeTab c = new CodeTab(f);
            c.setSize(painel.getWidth(), painel.getHeight());
            c.pack();
            addTab(c);
            salvarMenuItem.setEnabled(true);
        }
    }//GEN-LAST:event_abrirMenuItemActionPerformed

    private void exportTokensMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportTokensMenuItemActionPerformed

        ViewTokenList dialog = new ViewTokenList(this, true);
        dialog.getjTextPane1().setText(Helper.printTokenList());
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Lista <token, classe>");
        dialog.setVisible(true);

    }//GEN-LAST:event_exportTokensMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ViewContextXml dialog = new ViewContextXml(null, true);
        dialog.getjTextPane1().setText(Helper.to_xml(Syntax.contexto));
        dialog.setTitle("Tabela de tipos");
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        ViewContextTable dialog = new ViewContextTable();
        dialog.setTitle("Tabela Sintática");
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (ClassNotFoundException ex) {
                    MainLogger.logError(ex.getMessage());
                } catch (InstantiationException ex) {
                    MainLogger.logError(ex.getMessage());
                } catch (IllegalAccessException ex) {
                    MainLogger.logError(ex.getMessage());
                } catch (UnsupportedLookAndFeelException ex) {
                    MainLogger.logError(ex.getMessage());
                }
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirMenuItem;
    private javax.swing.JMenuItem compilarMenuItem;
    private javax.swing.JMenuItem exportTokensMenuItem;
    private javax.swing.JMenu ferramentasMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JMenuItem novoArquivoMenu;
    private javax.swing.JTabbedPane painel;
    private javax.swing.JMenuItem sairMenuItem;
    private javax.swing.JMenuItem salvarMenuItem;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jTextPane1
     */
    public javax.swing.JTextPane getjTextPane1() {
        return jTextPane1;
    }

    /**
     * @param jTextPane1 the jTextPane1 to set
     */
    public void setjTextPane1(javax.swing.JTextPane jTextPane1) {
        this.jTextPane1 = jTextPane1;
    }
}
