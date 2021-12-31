import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InicioCompilador {

    public static void main(String[] args) {
        try {
            File tokens = new File("tokens.txt");
            File tablaSimbolos = new File("tabla.txt");
            File parse = new File("parse.txt");
            File error = new File("errores.txt");

    
           FileWriter tokensFW = new FileWriter(tokens,false);
           FileWriter tablaFW = new FileWriter(tablaSimbolos,false);
           FileWriter parseFW = new FileWriter(parse,false);
           FileWriter errorFW = new FileWriter(error,false);

            
            File codigoFuente = new File("C:/Users/Iván/Desktop/pdl-main/material/prueba.txt");

            Tablas tablas = new Tablas();
            

            AnLex lex = new AnLex(codigoFuente,tablas,tokensFW);
            AnSint sint = new AnSint(lex, tablas, parseFW);

            sint.analisisSintactico();
           

            tokensFW.close();
            parseFW.close();
            errorFW.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
