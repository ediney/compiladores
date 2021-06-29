package LALG.Interface;

import LALG.Logger.MainLogger;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/** CodeTab - Classe de telas para gerar tabelas
 *
 * @author Ediney Lopes, Fellipe Abib, Mateus Pereira
 */
public class CodeTab extends JPanel {

    private int id;
    private boolean saved;
    private Icon ico;
    private String nome;
    private String tip;
    private JTextArea campo = new JTextArea();
    private FileOutputStream outFile;
    private String fileAbsolutePath;
    private String filePath;
    public static Logger log = Logger.getLogger(CodeTab.class);

    /**
     *
     * @param file
     */
    public CodeTab(File file) {
        this.nome = file.getName();
        this.fileAbsolutePath = file.getAbsolutePath();
        this.filePath = file.getPath();
        this.setLayout(new BorderLayout());

        campo = new JTextArea();
        campo.setBorder(new NumeredBorder());
        JScrollPane scroll = new JScrollPane(campo);
        this.add(scroll);
        campo.setText(lerArquivo(file));
        campo.setBorder(new NumeredBorder());
        saved = true;
        try {
            outFile = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            log.log(Priority.ERROR, ex.getCause());
        }
    }

    private String lerArquivo(File f) {
        String st = "";
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                st += sc.nextLine();
                st += "\n";
            }
        } catch (IOException ex) {
            log.log(Priority.ERROR, ex.getMessage());
        }
        return st;
    }

    public void pack() {
        campo.setColumns(this.getWidth() / 11);
        campo.setRows(this.getHeight() / 11);
    }

    public void clear() {
        try {
            setOutFile(new FileOutputStream(new File(this.getFilePath())));
        } catch (IOException ex) {
            MainLogger.logError(ex.getMessage());
        }
    }

    public boolean save() {
        try {
            clear();
            getOutFile().write(campo.getText().getBytes());
            saved = false;
            log.log(Priority.DEBUG, "Arquivo salvo com sucesso: " + getFileAbsolutePath());
            return true;
        } catch (IOException ex) {
            log.log(Priority.ERROR, ex.getMessage());
            return false;
        }
    }

    public String getCode() {
        return this.campo.getText();
    }

    public void setCode(String code) {
        this.campo.setText(code);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Icon getIco() {
        return ico;
    }

    public void setIco(Icon ico) {
        this.ico = ico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public JTextArea getCampo() {
        return campo;
    }

    public void setCampo(JTextArea campo) {
        this.campo = campo;
    }

    public FileOutputStream getOutFile() {
        return outFile;
    }

    public void setOutFile(FileOutputStream outFile) {
        this.outFile = outFile;
    }

    /**
     * @return the tip
     */
    public String getTip() {
        return tip;
    }

    /**
     * @param tip the tip to set
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileAbsolutePath(String fileAbsolutePath) {
        this.fileAbsolutePath = fileAbsolutePath;
    }

    public String getFileAbsolutePath() {
        return fileAbsolutePath;
    }
}
