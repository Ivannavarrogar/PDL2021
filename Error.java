
import java.io.*;
import java.util.ArrayList;

public class Error {

    private String mensage;
    
    public Error(String mensage){
        this.mensage = mensage;   
    }
    
    public static void ficheroErrores(ArrayList<Error> listaErrores){
        File errores = new File("../pdl-main/material/errores.txt");
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(errores));
            
            for (int i = 0; i < listaErrores.size();i++) {
                bw.write(listaErrores.get(i).getMensage() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void ficheroParse(String parses){
        File parse = new File("../pdl-main/material/parse.txt");
        try {
            FileWriter parseFW =new FileWriter(parse,false);
            
            parseFW.write(parses);
            parseFW.flush();
            parseFW.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String getMensage(){
        return this.mensage;
    }
   
}
