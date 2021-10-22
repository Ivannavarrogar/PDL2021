import java.io.*;
import java.util.ArrayList;

public class Token {

    private String tipoToken;
    private String atributo;

    public Token(String tipoToken, String atributo) {
        this.tipoToken = tipoToken;
        this.atributo = atributo;
    }

    public static void ficheroTokens(ArrayList<Token> lista) {
        File tokens = new File("C:/Users/ivann/Desktop/pdl-main/material/tokens.txt");
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(tokens));
            for (int i = 0; i < lista.size();i++) {
                bw.write("<"+lista.get(i).getTipo()+","+lista.get(i).getAtributo()+">"+"\n");
            }
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getTipo(){
        return tipoToken;
    }
   public String getAtributo(){
        return atributo;
    }

    public String toString(){
        return "<"+tipoToken+","+atributo+">"+"\n";
    }
  
}
