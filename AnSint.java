import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class AnSint {
//Estructura que nos sirve de llave para el mapa de acciones (no terminal, terminal)

    private Stack<String> pila;

    private static ArrayList<Error> listaErrores= new ArrayList<>();
    
    private AnLex lex;
    private Tablas tablas;
    private Token token;
    private boolean zonaDec;
    private boolean zonaVar;
    private String parse;
    private FileWriter parseFW;
  


    public AnSint(AnLex lexico, Tablas tablas, FileWriter parseFW) {
      this.pila = new Stack<String>();
      this.tablas =  tablas;
      this.lex = lexico;
      this.parseFW = parseFW;
      TablaAcciones matrizAcciones = new TablaAcciones();
      this.parse= "Des";
      this.zonaDec = false;
      this.zonaVar = false;
      pila.push("$");
      pila.push("P");

     
    }
    
    public void tsManager()throws IOException{
      token = lex.siguienteToken();
      if (zonaDec){
        if(zonaVar){
          
        }
      }
    
    }

    private void esDec (String tipoToken){
      if(tipoToken.equals("let")||tipoToken.equals("function")) {
        zonaDec = true;
      } 
    }

    private void esVar (String tipoToken){
      if(tipoToken.equals("let")) {
        zonaVar = true;
      }
      
    }

    public void analisisSintactico() throws IOException{
      Token sigToken = lex.siguienteToken();
      ArrayList<String> consecuentesSeparados;
      while(sigToken !=null){
        System.out.println(sigToken.getTipo());
        //Rama del caso en que la cima de la pila sea un Terminal
        if (esTerminal(pila.peek())){ 
          if(sonIguales(pila.peek(), sigToken.getTipo())){ //si el token es igual al simbolo de la pila se saca de la pila y se pasa al siguiente token
            pila.pop();
            sigToken= lex.siguienteToken();
            while (sigToken != null && sigToken.getTipo().equals("ignorar")){ //Si el siguiente token es ignorar (caso dummy) pasa tokens hasta que deja de ser ignorar
              sigToken= lex.siguienteToken();
            }
            System.out.println(pila.toString());

          }
          else {
            listaErrores.add(new Error("La cadena es sintacticamente erronea, el token (" + sigToken.getTipo() 
           + ") recibido no corresponde con lo esperado (" + pila.peek() +")" ));
          sigToken=null;
        }

        }

        //Rama del caso en que la cima de la pila no sea Terminal
        else{
          String aux = pila.peek();
          String nombreToken= sigToken.getTipo();
          pila.pop();
          
          if(TablaAcciones.buscarAccion(aux, nombreToken) !=null){
            String consecuente = TablaAcciones.buscarAccion(aux, nombreToken);
            parse += " " + TablaAcciones.buscarParse(aux, nombreToken);
            if (!(consecuente.equals("lambda"))){
              consecuentesSeparados= consecuenteToStrings(consecuente);
              for (int i=consecuentesSeparados.size(); i > 0; i--){
                pila.push(consecuentesSeparados.get(i-1));
              }
            }
          
          System.out.println(pila.toString());
          }
          else{
            listaErrores.add(new Error("La cadena es sintacticamente erronea, no hay ninguna regla para el no terminal " + aux + " con siguiente token: "
           +sigToken.getTipo()));
           sigToken= null;
          }
          
        }
        
      }
      if (pila.peek().equals("P")){ 
        pila.pop(); //La ultima transicion es P con Fin de cadena = P->lambda
        parse += " 3";
      }
      else{
        listaErrores.add(new Error("La cadena es sintacticamente erronea, no hay ninguna regla para el no terminal $ con un no terminal que no sea el axioma"));
      }
      System.out.println(parse);
      parseFW.write(parse);
      Error.ficheroErrores(listaErrores);
      parseFW.flush();
    }

 
    
private boolean esTerminal(String simbolo){
  String [] noTerminales = {"A", "B", "C", "D", "DP", "E", "EP", "F", "H", "J", "K", "L", "P", "Q", "S", "SP", "T" ,"U", "UP", "V", "VP", "X"};
  boolean res= true;
  for(int i=0; 22 > i; i++){
    if(noTerminales[i].equals(simbolo)){
      res = false;
      return res;
    }
  }
  return res;
}

private boolean sonIguales(String pila, String token){
  switch (pila) {
            case "entero" :
                return token.equals("cteEntera");
            case "+":
                return token.equals("operAritmetico");            
            case "==":
                return token.equals("operRelacional");
            case "&&":
                return token.equals("operLogico");
            case "=":
                return token.equals("operAsignacion");
            case  "(":
                return token.equals("abreP");
            case  ")":
                return token.equals("cierraP");
            case  ";":
                return token.equals("puntoComa");
            case "{":
                return token.equals("abreCo");
            case "}" :
                return token.equals("cierraCo");
            case ",":
                return token.equals("coma");           
            default:
                return pila.equals(token); 
  }
}

private ArrayList<String> consecuenteToStrings(String consecuente){
ArrayList<String> resultado = new ArrayList<String>();
String aux = "";
for(int i=0; i  < consecuente.length(); i++){
    if(!(consecuente.charAt(i) == (' '))){
      aux+= consecuente.charAt(i);    
    }
    else{
      resultado.add(aux);
      aux= "";
    }
  }
  resultado.add(aux);
return resultado;
}
  
}
