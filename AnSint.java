import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class AnSint {
//Estructura que nos sirve de llave para el mapa de acciones (no terminal, terminal)

    private Stack<String> pila;
    private Stack<String> pAux1;
    private Stack<String> pAux2;

    private String tipoPila; //String para guardar el tipo de los argumentos de pila

    private static ArrayList<Error> listaErrores= new ArrayList<>();
    private static ArrayList<Error> listaErroresSem= new ArrayList<>();
    private AnLex lex;
    private Tablas tablas;
    private Token token;
    private boolean zonaDec;
    private boolean zonaVar;
    private String parse;
    private String puntero;
    //private FileWriter parseFW;
  

    public AnSint(AnLex lexico, Tablas tablas) {
      this.pila = new Stack<String>();
      this.pAux1 = new Stack<String>();
      this.pAux2 = new Stack<String>();
      this.tablas =  tablas;
      this.lex = lexico;
      //this.parseFW = parseFW;
      TablaAcciones matrizAcciones = new TablaAcciones();
      this.parse= "Des";
      this.zonaDec = false;
      this.zonaVar = false;
      pila.push("$");
      pila.push("P");
      this.tipoPila = "";
      this.puntero = "";
     
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
        puntero = sigToken.getAtributo();
        System.out.println(sigToken.getTipo());
        //Rama del caso en que la cima de la pila sea un Terminal
        if (esTerminal(pila.peek())){ 
          if(sonIguales(pila.peek(), sigToken.getTipo())){ //si el token es igual al simbolo de la pila se saca de la pila y se pasa al siguiente token
            pAux1.push(pila.peek()); //Semantico
            pAux2.push(tipoPila);    //Semantico 
            tipoPila = "";
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
        else if(esAccionS(pila.peek())){
          ejecutarSemantica(pila.peek());
          pila.pop();
        }

        else {
          String noTerminal = pila.peek();
          String nombreToken= sigToken.getTipo();
          pAux1.push(pila.peek()); //Semantico
          pAux2.push(tipoPila);    //Semantico 
          tipoPila = "";
          pila.pop();
          
          if(TablaAcciones.buscarAccion(noTerminal, nombreToken) !=null){
            String consecuente = TablaAcciones.buscarAccion(noTerminal, nombreToken);
            parse += " " + TablaAcciones.buscarParse(noTerminal, nombreToken);
            if (!(consecuente.equals("lambda"))){
              consecuentesSeparados= consecuenteToStrings(consecuente);
              for (int i=consecuentesSeparados.size(); i > 0; i--){
                pila.push(consecuentesSeparados.get(i-1));
              }
            }
          
          System.out.println(pila.toString());
          System.out.println(pAux1.toString());
          System.out.println(pAux2.toString());
          }
          else{
            listaErrores.add(new Error("La cadena es sintacticamente erronea, no hay ninguna regla para el no terminal " + noTerminal + " con siguiente token: "
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
      //parseFW.write(parse);
      Error.ficheroErrores(listaErrores);
     // parseFW.flush();
      Error.ficheroParse(parse);
    }
 
    
private boolean esTerminal(String simbolo){
  String [] noTerminales = {"A", "B", "C", "D", "DP", "E", "EP", "F", "H", "J", "K", "L", "P", "Q", "S", "SP", "T" ,"U", "UP", "V", "VP", "X"};
  boolean res= true;
  for(int i=0; 22 > i; i++){
    if(noTerminales[i].equals(simbolo) || esAccionS(simbolo)){
      res = false;
      return res;
    }
  }
  return res;
}

private boolean esAccionS (String simbolo){
  if (simbolo.charAt(0) == '1' || simbolo.charAt(0) == '2'|| simbolo.charAt(0) == '3'|| simbolo.charAt(0) == '4'|| simbolo.charAt(0) == '5'|| simbolo.charAt(0) == '6'
    || simbolo.charAt(0) == '7' || simbolo.charAt(0) == '8' || simbolo.charAt(0) == '9')
    return true;
  else return false;
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
  
private void ejecutarSemantica(String accionS){
  TablaSimbolos tablaActiva = tablas.getTabla(tablas.getIdTablaActiva());
    switch(accionS){
      case "4.1" :     
                Atributos atributo = tablaActiva.getAtributo(tablaActiva.getIndex());          
                if (!lex.getFlagDuplicado()){
                  tipoPila = pAux2.peek();                
                  atributo.setTipo(tipoPila);                                           // Tipo para el atributo
                  atributo.setDesplazamientoId(tablaActiva.getDesplazamiento());        // desplazamiento para el atributo = desp 
                  tablaActiva.setDesplazamiento(calcularDesp(tipoPila));                // Desplazamiento para la tabla = desp =+ desp
                }
                else {
                  listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + " ,el id: " + atributo.getLexema() + " ya existe." ));
                }
                break;
      case "4.2" :
                for(int i = 0; i<4; i++){
                  pAux1.pop();
                  pAux2.pop();
                }                 
                break;
      case "5.1" :
                if(!(pAux2.peek() =="bool")){
                  listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + " ,se esperaba una condición lógica dentro del if." ));
                }
                break;
      case "5.2" :
                for(int i = 0; i<5; i++){
                  pAux1.pop();
                  pAux2.pop();
                } 
                break;
      case "6.1" :
                break;
      case "6.2" :
                pAux1.pop();
                pAux2.pop();
                break;
      case "7.1" :
                if(!(pAux2.peek() =="ent")){
                  listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + " ,se esperaba un indice de tipo entero." ));
                }
                break;
      case "7.2" :
                if(!(pAux2.peek() =="bool")){
                  listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + " ,se esperaba una condición de salida lógica." ));
                }
                break;
      case "7.3" :
                if(!(pAux2.peek() =="ent")){
                  listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + " ,se esperaba una expresion de tipo entero." ));
                }
                break;
      case "7.4" :
                for(int i = 0; i<11; i++){
                  pAux1.pop();
                  pAux2.pop();
                } 
                break;
      case "8.1" :
                if (!lex.getFlagDuplicadoGlobal()){
                  TablaSimbolos mainT = tablas.getTabla(0);
                  tablaActiva.eliminarId(lex.getLexemaSemantico());
                  mainT.insertarId(lex.getLexemaSemantico());
                  Atributos atributoGlobal = mainT.getAtributo(mainT.getIndex());
                  atributoGlobal.setTipo("ent");
                  atributoGlobal.setDesplazamientoId(mainT.getDesplazamiento());
                  mainT.setDesplazamiento(2);
                }
                tipoPila="ent";
                break;
      case "8.2" :
                String tipoE = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                if (pAux2.peek() == tipoE){
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push(tipoE);
                }
                else{ listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + " ,los tipos no coinciden." ));
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push("error");
                }
                break;
      case "10.1" :
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push("ent");
                break;
      case "11.1" :
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push("bool");
                break;
      case "12.1" :
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push("cad");
                break;
      case "13.1" :
                if (lex.getFlagDuplicadoGlobal()){
                  tipoPila= tablas.buscarPuntero(lex.getLexemaSemantico());
                }
                else listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + " ,el id: " + lex.getLexemaSemantico() + " no ha sido declarado antes." ));
                break;
      case "13.2" :
                return ;
      case "14.1" :
                return ;
      case "14.2" :
                return ;
      case "15.1" :
                return ;
      case "15.2" :
                return ;
      case "16.1" :
                return ;
      case "16.2" :
                return ;
      case "17.1" :
                return ;
      case "17.2" :
                return ;
      case "18.1" :
                return ;
      case "18.2" :
                return ;
      case "19.1" :
                return ;
      case "19.2" :
                return ;
      case "20.1" :
                return ;
      case "20.2" :
                return ;
      case "22.1" :
                return ;                                                                      
      case "22.2" :
                return ;
      case "24.1" :
                return ; 
      case "24.2" :
                 if (pAux2.peek() == "error" ){
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                 } else if (pAux2.peek() != ""){
                      String tipoQ = pAux2.peek();
                       pAux1.pop();
                       pAux2.pop();
                       if (tipoQ == pAux2.peek()){
                           pAux1.pop();
                           pAux2.pop();
                           pAux2.pop();
                           pAux2.push(tipoQ);
                   }else{
                      pAux1.pop();
                      pAux2.pop();
                      String tipoE2 = pAux2.peek();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push(tipoE2);
                   }
                 }
                 break;
      case "26.1" :
        if (pAux2.peek() == "error" ){
          pAux1.pop();
          pAux2.pop();
          pAux1.pop();
          pAux2.pop();
          pAux1.pop();
          pAux2.pop();
          pAux2.pop();
          pAux2.push("error");
      } else {
          String tipoQ = pAux2.peek();
          pAux1.pop();
          pAux2.pop();
          if(pAux2.peek() == tipoQ ){
            pAux1.pop();
            pAux2.pop();
            pAux1.pop();
            pAux2.pop();
            pAux2.pop();
            pAux2.push(tipoQ);
          } else{
              pAux1.pop();
              pAux2.pop();
              pAux1.pop();
              pAux2.pop();
              pAux2.pop();
              pAux2.push("error");
          }
      } 
      break;
      case "28.1" :
                return ;
      case "28.2" :
                return ;
      case "29.1" :
                return ;
      case "29.2" :
                return ;
      case "31.1" :
                return ;
      case "31.2" :
                return ;
      case "33.1" :
                return ;
      case "33.2" :
                return ;
      case "35.1" :
                if (pAux2.peek() == "error" ){
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                } else if(pAux2.peek() == "bool" ){
                    pAux1.pop();
                    pAux2.pop();
                    if(pAux2.peek() == "bool" ){
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("bool");
                    } else{
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                    }
                } else{ 
                    pAux1.pop();
                    pAux2.pop();
                    String tipoU =pAux2.peek();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push(tipoU);
                }
                break;
      case "36.1" :
                if (pAux2.peek() != "error"){
                  pAux1.pop();
                  pAux2.pop();
                  if (pAux2.peek() != "bool"){
                    listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un booleano para la operación lógica and." ));
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                  } else {
                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("bool");
                    }
                }else{
                  pAux1.pop();
                  pAux2.pop();
                  pAux1.pop();
                  pAux2.pop();
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push("error");
                }
                break;
      case "38.1" :
                if (pAux2.peek() == "error" ){
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                } else if(pAux2.peek() == "ent" ){
                    pAux1.pop();
                    pAux2.pop();
                    if(pAux2.peek() == "ent" ){
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("bool");
                    } else{
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                    }
                } else{ 
                    pAux1.pop();
                    pAux2.pop();
                    String tipoU =pAux2.peek();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push(tipoU);
                }
                break;
      case "39.1" :
                if (pAux2.peek() != "error"){
                  pAux1.pop();
                  pAux2.pop();
                  if (pAux2.peek() != "ent"){
                    listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un entero para la comparación de iguales." ));
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                  } else {
                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("ent");
                    }
                }
                else{
                  pAux1.pop();
                  pAux2.pop();
                  pAux1.pop();
                  pAux2.pop();
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push("error");
                }
                break;
      case "41.1" :
                if (pAux2.peek() == "error"){
                  pAux1.pop();
                  pAux2.pop();
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push("error");
                } else if(pAux2.peek() == "ent"){
                    pAux1.pop();
                    pAux2.pop();
                    if(pAux2.peek() == "ent"){
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("ent");
                    } else{
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("error");
                      }
                } else{ 
                    pAux1.pop();
                    pAux2.pop();
                    String tipoV =pAux2.peek();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push(tipoV);
                  }
                break;  
      case "42.1" :
                if (pAux2.peek() != "error"){
                  pAux1.pop();
                  pAux2.pop();
                  if (pAux2.peek() != "ent"){
                    listaErroresSem.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un entero para la operación suma." ));
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                  } else {
                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("ent");
                    }
                } else{
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                  }
                 break;
      case "44.1" :
                 tipoPila = tablas.buscarPorPuntero(puntero).getTipo(); 
                break;
      case "44.2" :
              if(pAux2.peek() != ""){
                  
              }
              else{ 
                pAux1.pop();
                pAux2.pop();
                String tipoId =pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoId);
              }
              break;
      case "45.1" :
                pAux1.pop();
                pAux2.pop();
                String tipoE1 = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoE1);
                break;
      case "46.1" :
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push("ent");
                break;
      case "47.1" :
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push("cad");
                break;
      case "48.1" :
                pAux1.pop();
                pAux2.pop();
                String tipoL = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoL);
      case "48.2" :
                return ;                                                                                                                                                                                   
     
    }
}
  private int calcularDesp(String tipo){
    if (tipo == "ent" || tipo== "bool") return 2;
    else return 128;
  }
}
