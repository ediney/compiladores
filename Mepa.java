package auxiliar;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Mepa {
        private PrintStream PS;
        
        public boolean MestaEnFuncion;
        public String MLexemaUnidad;
        
        public int EtiquetaAct = 0;
        
        public Mepa(String codint) throws FileNotFoundException{
                PS = new PrintStream(codint);
        }
        
        public void Mimprimir(String[] args){
                for (String arg:args){
                        PS.print(arg);
                        PS.print(" ");
                }
                PS.println();
        }
        
        public String MobtProxEti(){
                EtiquetaAct++;
                return "label"+String.valueOf(EtiquetaAct);
        }
        
        public void fecharSair(){
                PS.close();
        }
}
