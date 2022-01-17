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

    private ArrayList<Error> listaErrores;
    private AnLex lex;
    private Tablas tablas;
    private Token token;
    private boolean zonaDec;
    private boolean zonaVar;
    private String parse;
    private String puntero;
    private FileWriter tablaFW;
    private int contadorParam = 0;
    private boolean flagError = false;
    private String contenidoTablaLocal="";
    private boolean flagFuncFalsa = false;
    //private FileWriter parseFW;
  

    public AnSint(AnLex lexico, Tablas tablas, FileWriter tablaFW, ArrayList<Error> listaErrores) {
      this.pila = new Stack<String>();
      this.pAux1 = new Stack<String>();
      this.pAux2 = new Stack<String>();
      this.tablas =  tablas;
      this.lex = lexico;
      this.tablaFW = tablaFW;
      //this.parseFW = parseFW;
      TablaAcciones matrizAcciones = new TablaAcciones();
      this.parse= "Des";
      this.zonaDec = false;
      this.zonaVar = false;
      pila.push("$");
      pila.push("P");
      this.tipoPila = "";
      this.puntero = "";
      this.listaErrores = listaErrores;
     
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
            System.out.println(pAux1.toString());
            System.out.println(pAux2.toString());
          }
          else {
            listaErrores.add(new Error("Linea " + lex.getContLinea() +": La cadena es sintacticamente erronea, el token (" + sigToken.getTipo() 
           + ") recibido no corresponde con lo esperado (" + pila.peek() +")" ));
          sigToken=null;
          flagError = true;
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
            listaErrores.add(new Error( "Linea " + lex.getContLinea()+": La cadena es sintacticamente erronea, no hay ninguna regla para el no terminal " + noTerminal + " con siguiente token: "
           +sigToken.getTipo()));
           sigToken= null;
           flagError = true;
          }
          
        }
        
      }
      while (esAccionS(pila.peek()) && !flagError){
        ejecutarSemantica(pila.peek());
        pila.pop();
        System.out.println(pila.toString());
        System.out.println(pAux1.toString());
        System.out.println(pAux2.toString());
      }

      if (pila.peek().equals("P")){ 
        pila.pop(); //La ultima transicion es P con Fin de cadena = P->lambda
        parse += " 3";
      }
      else{
        listaErrores.add(new Error("La cadena es sintacticamente erronea, no hay ninguna regla para el no terminal $ con un no terminal que no sea el axioma"));
      }
      
      tablaFW.write(contenidoTablaLocal);
      tablaFW.flush();


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
  
private void ejecutarSemantica(String accionS) throws IOException{
  tablas.getTabla(tablas.getIdTablaActiva());
    switch(accionS){
      case "4.1" :     
                Atributos atributo = tablas.getTabla(tablas.getIdTablaActiva()).getAtributo(tablas.getTabla(tablas.getIdTablaActiva()).getIndex());          
                if (!lex.getFlagDuplicado()){
                  tipoPila = pAux2.peek();                
                  atributo.setTipo(tipoPila);                                           // Tipo para el atributo
                  atributo.setDesplazamientoId(tablas.getTabla(tablas.getIdTablaActiva()).getDesplazamiento());        // desplazamiento para el atributo = desp 
                  tablas.getTabla(tablas.getIdTablaActiva()).setDesplazamiento(calcularDesp(tipoPila));  // Desplazamiento para la tabla = desp =+ desp
                  int indice = tablas.getTabla(tablas.getIdTablaActiva()).getIndex();             
                  if (tablas.getIdTablaActiva()==0){ 
                    tablaFW.write(tablas.getTabla(tablas.getIdTablaActiva()).toString(indice));
                    tablaFW.flush();
                  }
                  else {
                    if (!flagFuncFalsa){ 
                    contenidoTablaLocal += tablas.getTabla(tablas.getIdTablaActiva()).toString(indice);
                    }
                  }
                }
                else {
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", el id: " + lex.getLexemaSemantico() 
                  + " ya existe1." ));
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

                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba una condición lógica dentro del if." ));
                }
                break;
      case "5.2" :
                for(int i = 0; i<5; i++){
                  pAux1.pop();
                  pAux2.pop();
                } 
                break;
      case "6.1" :
                String tipoS = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoS);
                break;
      case "7.1" :
                if(!(pAux2.peek() =="ent")){
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un indice de tipo entero." ));
                }
                break;
      case "7.2" :
                if(!(pAux2.peek() =="bool")){
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba una condición de salida lógica." ));
                }
                break;
      case "7.3" :
                if(!(pAux2.peek() =="ent")){
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba una expresion de tipo entero." ));
                }
                break;
      case "7.4" :
                pAux2.pop();
                pAux2.push(pila.peek());
                break;
      case "7.5" :
                pAux1.pop();
                pAux2.pop();
                String tipoC = pila.peek();
                for(int i = 0; i<10; i++){
                  pAux1.pop();
                  pAux2.pop();
                } 
                pAux2.pop();
                pAux2.push(tipoC);
                break;
      case "8.1" :
                if (!lex.getFlagDuplicadoGlobal()){
                  tablas.getTabla(tablas.getIdTablaActiva()).eliminarId(lex.getLexemaSemantico());
                  tablas.getTabla(0).insertarId(lex.getLexemaSemantico());
                  Atributos atributoGlobal = tablas.getTabla(0).getAtributo(tablas.getTabla(0).getIndex());
                  atributoGlobal.setTipo("ent");
                  atributoGlobal.setDesplazamientoId(tablas.getTabla(0).getDesplazamiento());
                  tablas.getTabla(0).setDesplazamiento(2);
                  int indice = tablas.getTabla(tablas.getIdTablaActiva()).getIndex();
                  if (tablas.getIdTablaActiva()==0){ 
                    tablaFW.write(tablas.getTabla(tablas.getIdTablaActiva()).toString(indice));
                    tablaFW.flush();
                  }
                  else {
                    if (!flagFuncFalsa){ 
                      contenidoTablaLocal += tablas.getTabla(tablas.getIdTablaActiva()).toString(indice);
                      }
                  }
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
                else{ listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", los tipos no coinciden1." ));
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
                String tipoId2 = "";
                if (lex.getFlagDuplicadoGlobal()){
                  tipoPila= tablas.buscarPuntero(lex.getLexemaSemantico());
                  tipoId2 = tablas.buscarPorPuntero(tipoPila).getTipo();
                  if (tipoId2 == "func"){
                    if(!lex.getFlagDuplicado()){                 
                    tablas.getTabla(tablas.getIdTablaActiva()).eliminarId(lex.getLexemaSemantico());
                    }
                  }
                  
                }
                if(lex.getFlagDuplicado()){
                  tipoPila = tablas.buscarPuntero(lex.getLexemaSemantico());
                }
                else if(tipoId2 != "func"){ 
                  tablas.getTabla(tablas.getIdTablaActiva()).getAtributo(tablas.getTabla(tablas.getIdTablaActiva()).getIndex()).setTipo("ent");
                  tipoPila = tablas.buscarPuntero(lex.getLexemaSemantico());
                } 
                tipoPila = tablas.buscarPuntero(lex.getLexemaSemantico());           
                break;
      case "13.2":
                String tipoSP = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                if ((tipoSP != "ent" && tipoSP != "cad" && tipoSP != "bool" && tipoSP != "" && tipoSP != "error")){
                  String tipoSPPunt = tipoSP ;
                  tipoSP = tablas.buscarPorPuntero(tipoSP).getTipo();
                  if (tipoSP == "func"){
                    tipoSP = tablas.buscarPorPuntero(tipoSPPunt).getTipoDevuelto();
                  }
                }
                Atributos atributoId = tablas.buscarPorPuntero(pAux2.peek());
                String tipoId3 = atributoId.getTipo();
                if(tipoSP != tipoId3 && tipoId3 != "func"){
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", los tipos no coinciden2." ));
                } else if(tipoId3 == "func"){
                    if (contadorParam != atributoId.getNumeroParametros()){
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", el numero de parametros no es el correcto." ));
                    }
                    if (tipoSP != atributoId.getTipoParametro()){
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", los tipos no coinciden con los de los parametros esperados." ));
                    }
                    contadorParam = 0;
                }
                pAux1.pop();
                pAux2.pop();
                break;
      case "14.1" :
                pAux1.pop();
                pAux2.pop();
                String tipoX= pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoX);
                break;
      case "15.1" ://
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                String tipoE4= pAux2.peek();
                if (tipoE4 != "ent" && tipoE4 != "cad" && tipoE4 != "bool" && tipoE4 != ""){
                  String tipoEP = tipoE4;
                  tipoE4 = tablas.buscarPorPuntero(tipoE4).getTipo();
                  if (tipoE4 =="func"){
                    tipoE4 = tablas.buscarPorPuntero(tipoEP).getTipoDevuelto();
                  }
                }
                if (tipoE4 != "ent" && tipoE4 != "cad"){
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", no se puede imprimir algo que no sea una cadena o un entero." ));
                  tipoE4="error";
                }
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                break;
      case "16.1" :
                if (lex.getFlagDuplicadoGlobal()){
                  tipoPila= tablas.buscarPuntero(lex.getLexemaSemantico());
                }
                else{ listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", el id: " + lex.getLexemaSemantico() + 
                " no ha sido declarado antes." ));}
                break;
      case "16.2" :
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                if (!lex.getFlagDuplicadoGlobal()){
                  tablas.getTabla(0).insertarId(lex.getLexemaSemantico());
                  Atributos atributoGlobal = tablas.getTabla(0).getAtributo(tablas.getTabla(0).getIndex());
                  atributoGlobal.setTipo("ent");
                  atributoGlobal.setDesplazamientoId(tablas.getTabla(0).getDesplazamiento());
                  tablas.getTabla(0).setDesplazamiento(2);
                }
                else {
                String tipoE5= tablas.buscarPorPuntero(pAux2.peek()).getTipo();
                if(tipoE5 == "func"){
                  tipoE5= tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto();
                }
                if (tipoE5 != "ent" && tipoE5 != "cad"){
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", la función input() solo acpeta enteros o cadenas." ));
                  tipoE5="error";
                }
              }
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                break;
      case "17.1" :
                pAux1.pop();
                pAux2.pop();
                String tipoE6 = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoE6);
                break;
      case "18.1" :
                pAux1.pop();
                pAux2.pop();
                String tipoE7 = pAux2.peek();
                if(tipoE7!= "ent"){
                  tipoE7 = "error";
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", la asignación con módulo solo funciona con enteros." ));
                }
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoE7);
                break;
      case "19.1" :   
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                String tipoL = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoL);
                break;
      case "20.1" :
                String tipoE3= pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoE3);
                break;
      case "22.1" :
                  String tipoB;
                  if(pAux2.peek()== ""){
                    pAux1.pop();
                    pAux2.pop();
                    tipoB = pAux2.peek();
                  } else{
                    tipoB = pAux2.peek();
                    pAux1.pop();
                    pAux2.pop();
                    if(pAux2.peek() == "" || pAux2.peek() == "error"){
                      
                    }else {
                      tipoB = "error";
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", la función solo puede tener un return." ));
                    }
                  }
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push(tipoB);
                  break;                                                             
      case "24.1" :
                 if (pAux2.peek() == "error" ){
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                 } 
                 else if (pAux2.peek() != ""){
                      String tipoQ = pAux2.peek();
                       pAux1.pop();
                       pAux2.pop();
                       if (tipoQ != "ent" && tipoQ != "cad" && tipoQ != "bool"){
                        String tipoQP = tipoQ;
                        tipoQ = tablas.buscarPorPuntero(tipoQ).getTipo();
                        if (tipoQ =="func"){
                          tipoQ = tablas.buscarPorPuntero(tipoQP).getTipoDevuelto();
                        }
                       }
                       if (tipoQ == pAux2.peek()){
                           pAux1.pop();
                           pAux2.pop();
                           pAux2.pop();
                           pAux2.push(tipoQ);
                        }
                  
                        else{
                          listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", todos los argumentos deben ser del mismo tipo2." ));                      
                          pAux1.pop();
                          pAux2.pop();
                          pAux2.pop();
                          pAux2.push("error");
                        }
                 }
                 else {
                  pAux1.pop();
                  pAux2.pop();
                  String tipoE2= pAux2.peek();
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push(tipoE2);
                 }
                 contadorParam++;
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
      } 
        else {
          String tipoQ = pAux2.peek();
          pAux1.pop();
          pAux2.pop();
          if (tipoQ != ""){ 
            if(pAux2.peek() == tipoQ ){
              pAux1.pop();
              pAux2.pop();
              pAux1.pop();
              pAux2.pop();
              pAux2.pop();
              pAux2.push(tipoQ);
            } else{
                listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", todos los argumentos deben ser del mismo tipo3." ));
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push("error");
            }
          }
          else {  
              String tipoE9 = pAux2.peek();
              pAux1.pop();
              pAux2.pop();
              pAux1.pop();
              pAux2.pop();
              pAux2.pop();
              pAux2.push(tipoE9);
          }
          contadorParam++;
      } 
      break;
      case "28.1" : 
                  if(tablas.getcontadorTablas() == 1){         
                    if (!lex.getFlagDuplicado()){
                      tablas.getTabla(tablas.getIdTablaActiva()).eliminarId(lex.getLexemaSemantico());
                      tablas.getTabla(tablas.getIdTablaActiva()).insertarFuncion(lex.getLexemaSemantico()); 
                      tipoPila = tablas.buscarPuntero(lex.getLexemaSemantico());                
                      tablas.getTabla(tablas.getIdTablaActiva()).getAtributo(tablas.getTabla(tablas.getIdTablaActiva()).getIndex()).setTipo("func");        // Tipo para el atributo
                      tablas.setIdTablaActiva(tablas.insertarTabla(lex.getLexemaSemantico()));
                      
                    }
                    else {
                     listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", esta función ya está declarada." ));
                     //tablas.getTabla(tablas.getIdTablaActiva()).eliminarId(lex.getLexemaSemantico());
                      tablas.getTabla(tablas.getIdTablaActiva()).insertarFuncion(lex.getLexemaSemantico()); 
                      tipoPila = tablas.buscarPuntero(lex.getLexemaSemantico());                
                      tablas.getTabla(tablas.getIdTablaActiva()).getAtributo(tablas.getTabla(tablas.getIdTablaActiva()).getIndex()).setTipo("func");        // Tipo para el atributo
                      tablas.setIdTablaActiva(tablas.insertarTabla(lex.getLexemaSemantico()));
                      flagFuncFalsa = true; 
                     tipoPila = "error";
                    }
                  } else {
                    listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", no se puede declarar una función dentro de otra." ));
                  }
                  break;
      case "28.2" :
                String tipoH1 = pAux2.peek();
                TablaSimbolos tablaMain = tablas.getTabla(0);
                tablas.getTabla(0).getAtributo(tablaMain.getIndex()).setTipoDevuelto(tipoH1);
                pAux2.pop();
                pAux2.push(tipoH1);               
                break;
      case "28.3" :
                pAux1.pop();
                pAux2.pop();
                String tipoC1 = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                if (pAux2.peek()!="error"){ 
                  if(tipoC1 != tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto()){ // queremos comparar tipodevuelto, no func
                    if (tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto() == ""){
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", esta función es vacía y no puede devolver nada."));
                    }
                    else{ 
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", esta función debe devolver el tipo " 
                      + tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto()));
                    }
                  }
                } 
               
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
               
                  int indiceE = tablas.getTabla(0).getIndex();
                  if (!flagFuncFalsa){ 
                    tablaFW.write(tablas.getTabla(0).toStringF(indiceE));
                    tablaFW.flush();
                    }
                                      
                tablas.eliminarTabla(1);
                flagFuncFalsa = false;
                tablas.setIdTablaActiva(0);
                
                break;
      case "29.1" :
                String tipoH = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoH);
                break;
      case "31.1" :
                Atributos atributo1 = tablas.getTabla(tablas.getIdTablaActiva()).getAtributo(tablas.getTabla(tablas.getIdTablaActiva()).getIndex());          
                if (!lex.getFlagDuplicado()){
                  TablaSimbolos tablaMain1 = tablas.getTabla(0);             
                  tipoPila = pAux2.peek();  
                  tablas.getTabla(0).getAtributo(tablaMain1.getIndex()).setTipoParametro(tipoPila);
                  tablas.getTabla(0).getAtributo(tablaMain1.getIndex()).setNumeroParametros();           
                  atributo1.setTipo(tipoPila);                                           // Tipo para el atributo
                  atributo1.setDesplazamientoId(tablas.getTabla(tablas.getIdTablaActiva()).getDesplazamiento());        // desplazamiento para el atributo = desp 
                  tablas.getTabla(tablas.getIdTablaActiva()).setDesplazamiento(calcularDesp(tipoPila));               // Desplazamiento para la tabla = desp =+ desp
                  int indice = tablas.getTabla(tablas.getIdTablaActiva()).getIndex();
                  if (tablas.getIdTablaActiva()==0){ 
                    tablaFW.write(tablas.getTabla(tablas.getIdTablaActiva()).toString(indice));
                    tablaFW.flush();
                  }
                  else {
                    if (!flagFuncFalsa){ 
                      contenidoTablaLocal += tablas.getTabla(tablas.getIdTablaActiva()).toString(indice);
                      }
                  }
                }
                else {
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", el id: " + lex.getLexemaSemantico()  + " ya existe2." ));
                }
                break;     
      case "31.2" :
                String tipoId1 = pAux2.peek();
                if (pAux2.peek() != tipoId1 || pAux2.peek() == "error"){
                  tipoId1 = "error";
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", todos los argumentos deben ser del mismo tipo1." ));
                } 
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoId1);
                break;  
      case "33.1" :
                Atributos atributo2 = tablas.getTabla(tablas.getIdTablaActiva()).getAtributo(tablas.getTabla(tablas.getIdTablaActiva()).getIndex());          
                if (!lex.getFlagDuplicado()){
                  TablaSimbolos tablaMain1 = tablas.getTabla(0);
                  tipoPila = pAux2.peek();                
                  atributo2.setTipo(tipoPila);                                           // Tipo para el atributo
                  atributo2.setDesplazamientoId(tablas.getTabla(tablas.getIdTablaActiva()).getDesplazamiento());        // desplazamiento para el atributo = desp 
                  tablas.getTabla(tablas.getIdTablaActiva()).setDesplazamiento(calcularDesp(tipoPila));               // Desplazamiento para la tabla = desp =+ desp
                  tablas.getTabla(0).getAtributo(tablaMain1.getIndex()).setNumeroParametros();
                  int indice = tablas.getTabla(tablas.getIdTablaActiva()).getIndex();
                  if (tablas.getIdTablaActiva()==0){ 
                    tablaFW.write(tablas.getTabla(tablas.getIdTablaActiva()).toString(indice));
                    tablaFW.flush();
                  }
                  else {
                    if (!flagFuncFalsa){ 
                      contenidoTablaLocal += tablas.getTabla(tablas.getIdTablaActiva()).toString(indice);
                      }
                  }
                }
                else {
                  listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", el id: " + lex.getLexemaSemantico() + " ya existe3." ));
                }
                break;
      case "33.2" :
                String tipoId4 = pAux2.peek();
                if (pAux2.peek() != tipoId4){
                  tipoId1 = "error";
                } 
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoId4);
                break;
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
                      if (pAux2.peek() == "ent" || pAux2.peek() == "cad"){ 
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", ambos operandos deben ser de tipo booleanos." ));
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                      }
                      else{
                        String tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipo();
                      if (tipoId5 == "func"){
                        tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto();
                      }
                      if (tipoId5 == "bool"){
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("bool");
                      }
                      
                      else{
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", ambos operandos deben ser de tipo booleanos." ));
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                      } 
                      }
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
                    if (pAux2.peek() == "ent" || pAux2.peek() == "cad"){ 
                    listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un booleano para la operación lógica and." ));
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("bool");
                    }
                    else{
                      String tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipo();
                      if (tipoId5 == "func"){
                        tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto();
                      }
                      if (tipoId5 == "bool"){
                        pAux1.pop();
                        pAux2.pop();
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("bool");
                      }
                      
                      else{
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un booleano para la operación lógica and." ));
                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                      }
                    }
                  } else{
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
                  pAux2.push("bool");
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
                    } else if (pAux2.peek() == "cad" ){
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", ambos argumentos deben ser del mismo tipo." ));
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                    }
                    else{
                      String tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipo();
                      if (tipoId5 == "func"){
                        tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto();
                      }
                      if (tipoId5 == "ent"){
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("bool");
                      }
                      
                      else{
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", ambos argumentos deben ser del mismo tipo." ));
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                      } 
                    }
              } 
                else{ 
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
                  if (pAux2.peek() == "cad"){
                    listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un entero para la comparación de iguales." ));
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("bool");
                  } 
                  else if(pAux2.peek() == "ent"){
                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("ent");
                    }
                    else{
                      String tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipo();
                      if (tipoId5 == "func"){
                        tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto();
                      }
                      if (tipoId5 == "ent"){
                        pAux1.pop();
                        pAux2.pop();
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("ent");
                      }
                      
                      else{
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un entero para la comparación de iguales." ));
                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                      }
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
                  pAux2.push("ent");
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

                    } 
                    else{
                      if (pAux2.peek() == "cad"){
                        listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", ambos operandos deben ser de tipo entero." ));
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("error");
                      }
                      else{
                        String tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipo();
                        if (tipoId5 == "func"){
                          tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto();
                        }
                        if (tipoId5 == "ent"){
                          pAux1.pop();
                          pAux2.pop();
                          pAux2.pop();
                          pAux2.push("ent");
                        }
                        
                        else{
                          listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", ambos operandos deben ser de tipo entero." ));
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("error");
                        }
                      }
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

                  if (pAux2.peek() == "cad"){
                    listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un entero para la operación suma." ));
                    pAux1.pop();
                    pAux2.pop();
                    pAux1.pop();
                    pAux2.pop();
                    pAux2.pop();
                    pAux2.push("error");
                  } 
                  else {
                    if(pAux2.peek() == "ent"){ 

                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("ent");
                    }
                    else{
                      String tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipo();
                      if (tipoId5 == "func"){
                        tipoId5 = tablas.buscarPorPuntero(pAux2.peek()).getTipoDevuelto();
                      }
                      if (tipoId5 == "ent"){
                        pAux1.pop();
                        pAux2.pop();
                        pAux1.pop();
                        pAux2.pop();
                        pAux2.pop();
                        pAux2.push("ent");
                      }
                      
                      else{
                      
                      
                        listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", se esperaba un entero para la operación suma." ));
                      pAux1.pop();
                      pAux2.pop();
                      pAux1.pop();
                      pAux2.pop();
                      pAux2.pop();
                      pAux2.push("error");
                      }
                    }
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
                if (lex.getFlagDuplicadoGlobal()){
                  String aux34343 = lex.getLexemaSemantico();
                  tipoPila= tablas.buscarPuntero(lex.getLexemaSemantico());
                  String tipoId5 = tablas.buscarPorPuntero(tipoPila).getTipo();
                  if (tipoId5 == "func"){
                    if (tablas.getIdTablaActiva() != 0){ 
                    tablas.getTabla(tablas.getIdTablaActiva()).eliminarId(lex.getLexemaSemantico());}
                  }
                }
                else{ listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", el id: " + lex.getLexemaSemantico() + " no ha sido declarado antes." ));
                }            
                
                tipoPila = tablas.buscarPuntero(lex.getLexemaSemantico());
                break;
      case "44.2" :
                String aux3434f3 = lex.getLexemaSemantico();
                if(pAux2.peek() != ""){
                  String tipoVP = pAux2.peek();
                  pAux1.pop();
                  pAux2.pop();
                  Atributos atributoId1 = tablas.buscarPorPuntero(pAux2.peek());
                  String tipoId5 = atributoId1.getTipo();
                  if(tipoVP != tipoId5 && tipoId5 != "func"){
                    listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", los tipos no coinciden3." ));
                  }
                  else{
                    if (contadorParam != atributoId1.getNumeroParametros()){
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", el numero de parametros no es el correcto." ));
                    }
                      if (tipoVP != atributoId1.getTipoParametro()){
                      listaErrores.add(new Error("Error semántico en la linea " + lex.getContLinea() + ", los tipos no coinciden con los de los parametros esperados." ));
                    }
                    contadorParam = 0; 
                  } 
                  String tipoId6 = pAux2.peek();
                  pAux1.pop();
                  pAux2.pop();
                  pAux2.pop();
                  pAux2.push(tipoId6);
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
                String tipoL1 = pAux2.peek();
                pAux1.pop();
                pAux2.pop();
                pAux1.pop();
                pAux2.pop();
                pAux2.pop();
                pAux2.push(tipoL1);                                                                                                                                                                                   
    }
}
  private int calcularDesp(String tipo){
    if (tipo == "ent" || tipo== "bool") return 2;
    else return 128;
  }
}
