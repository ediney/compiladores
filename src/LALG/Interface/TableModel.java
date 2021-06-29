package LALG.Interface;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 *
 */
public class TableModel extends DefaultTableModel {

    private JTable tabela = new JTable();

    public void TableModel(JTable tabela) {
        this.setTabela(tabela);
    }

    /**
     * Método para adicionar uma nova linha na JTable
     */
    public void adicionaLinha() {
        // Obtem o modelo da JTable
        DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();

        // Adiciona uma nova linha em branco no modelo
        modelo.addRow(new String[]{"", ""});
    }

    /**
     * Remove a linha do modelo.
     * @param linha
     */
    public void removeLinha(int linha) {

        // Obtem o modelo da JTable
        DefaultTableModel modelo = (DefaultTableModel) getTabela().getModel();

        // Remove a linha
        modelo.removeRow(linha);
    }

    /**
     * Obtem a linha selecionada e chama o método para remover
     * do modelo
     */
    public void removeLinha() {

        // Obtem a linha selecionada na tabela e chama o método
        // para excluir a linha
        int linhaSelecionada = getTabela().getSelectedRow();

        // Verificamos se existe realmente alguma linha selecionada
        if (linhaSelecionada < 0) {
            return;
        } else {
            // Remove a linha do modelo
            removeLinha(linhaSelecionada);
        }
    }

    /*Getters e Setters*/
    /**
     * @return the tabela
     */
    public JTable getTabela() {
        return tabela;
    }

    /**
     * @param tabela the tabela to set
     */
    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }
}
