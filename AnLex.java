import java.io.*;
import java.util.ArrayList;


public class AnLex {

   private File fuente;
   private static FileReader fr;
   private static BufferedReader br;
   private static String linea;
   private static ArrayList<Token> tokensArrayList;
   private static ArrayList<Error> listaErrores;
   private static int estado;
   private static int posLinea;
   private Tablas tablas;
   private FileWriter tokenFW;

   public AnLex(File codigoFuente, Tablas tablas, FileWriter tokenFW) {
      try {
         fuente = codigoFuente;
         fr = new FileReader(fuente);
         br = new BufferedReader(fr);

      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      this.tokenFW = tokenFW;
      this.tablas = tablas;
      linea = "";
      tokensArrayList = new ArrayList<Token>();
      listaErrores = new ArrayList<Error>();
      new MTransiciones();
      estado = 0;
      posLinea = 0;
   }

   /**
    * Imprime el fichero de tokens y el fichero de errores, tambien cierra el
    * lector del fichero, debería de ser llamado al terminar de leer todo el
    * fichero
    */
   public void finLex() {
      Token.ficheroTokens(tokensArrayList);
      // ts.ficheroTS();
      Error.ficheroErrores(listaErrores);
      try {
         fr.close();
         br.close();
      } catch (IOException e) {
      }
   }

   /**
    * Crea el token correspondiente a la palabra reservada detectada
    * 
    * @param lexema que se ha formado por las iteraciones del analizador lexico
    * @return token correspondiente a la palabra reservada leida o null si no es
    *         palres
    * @throws IOException
    */
   private  Token palres(String lexema) throws IOException {
      Token tk = null;
      switch (lexema) {
         case "boolean":
            tk = new Token("boolean", "" + 14);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "int":
            tk = new Token("int", "" + 15);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "string":
            tk = new Token("string", "" + 16);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "let":
            tk = new Token("let", "" + 17);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "print":
            tk = new Token("print", "" + 18);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "input":
            tk = new Token("input", "" + 19);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "break":
            tk = new Token("break", "" + 20);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "function":
            tk = new Token("function", "" + 21);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "return":
            tk = new Token("return", "" + 22);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "default":
            tk = new Token("default", "" + 23);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "switch":
            tk = new Token("switch", "" + 24);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "case":
            tk = new Token("case", "" + 25);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "true":
            tk = new Token("true", "" + 26);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "false":
            tk = new Token("false", "" + 27);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         case "if":
            tk = new Token("if", "" + 28);
            tokenFW.write(tk.toString());
            tokenFW.flush();
            lexema = "";
            posLinea++;
            return tk;
         default:
            return tk;
      }
   }

   /**
    * Devuelve un token cada vez que es llamado
    * 
    * @param ts tabla de simbolos
    * @return token leido o null si ha terminado
    * @throws IOException
    */
   public Token siguienteToken() throws IOException {
      //Si la posicion de la linea es 0 debe leer una nueva linea del fichero
      if (posLinea == 0) {
         try {
            linea = br.readLine();
            //Si es null ha terminado de leer el fichero
            if (linea == null) {
               return null;
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      //Inicializamos variables para analizar la linea
      String lexema = "";
      int contador = 0;
      int valor = 0;
      Token tk = new Token("ignorar", "" + 90);
      //Algoritmo del Automata
      while (posLinea < linea.length()) {
         //Si estamos en un estado final, volvemos al inicial
         if (estado > 11) {
            estado = 0;
         }

         char caracter = linea.charAt(posLinea);
         String accion = MTransiciones.mtAccion(estado, caracter);
         //Si no hay accion , tenemos un error
         if (accion == null) {
            accion = "L";
            estado = 0;
            lexema = "";
            listaErrores
                  .add(new Error("Este caracter " + caracter + " no tiene transicion prevista para este estado "));
         }
         estado = MTransiciones.mtEstado(estado, caracter);
         
         //Acciones Semanticas
         for (int j = 0; j < accion.length(); j++) {

            switch (accion.charAt(j)) {
               case 'A':
                  lexema += caracter;
                  break;
               case 'B':
                  lexema += caracter;
                  break;
               case 'C':
                  tk = new Token("operRelacional", "" + 2);
                  tokenFW.write(tk.toString());
                  tokenFW.flush();
                  return tk;
               case 'D':
                  tk = new Token("operAritmetico", "" + 1);
                  tokenFW.write(tk.toString());
                  tokenFW.flush();
                  posLinea++;
                  return tk;
               case 'E':
                  tk = new Token("operAsignacion", "" + 5);
                  tokenFW.write(tk.toString());
                  tokenFW.flush();
                  lexema = "";
                  return tk;
               case 'G':
                  contador = 1;
                  break;
               case 'H':
                  valor = Character.getNumericValue(caracter);
                  break;
               case 'I':
                  valor = valor * 10 + Character.getNumericValue(caracter);
                  break;
               case 'J':
                  tk = new Token("operAsignacion", "" + 4);
                  tokenFW.write(tk.toString());
                  tokenFW.flush();
                  lexema = "";
                  posLinea++;
                  return tk;
               case 'K':
                  tk = new Token("operLogico", "" + 3);
                  tokenFW.write(tk.toString());
                  tokenFW.flush();
                  lexema = "";
                  estado = 0;
                  posLinea++;
                  return tk;
               case 'L':
                  posLinea++;
                  break;
               case 'M':
                  // Palabra reservada
                  tk = palres(lexema);
                  if (tk != null) {
                     return tk;
                  }
                  // Identificador
                  Atributos atrib = tablas.existeAtributo(lexema);
                  if (atrib != null) {
                     tk = new Token("id",lexema);
                     tokenFW.write(tk.toString());
                     tokenFW.flush();
                     lexema = "";
                     return tk;
                  } else {
                     tablas.insertarAtributos(lexema);
                     tk = new Token("id",lexema);
                     tokenFW.write(tk.toString());
                     tokenFW.flush();
                     lexema = "";
                     return tk;
                  }
               case 'O':
                  if (valor > 32767) {
                     listaErrores.add(new Error("Este entero " + valor + " ha sobrepasado el valor maximo(32767)"));
                     valor = 0;
                     break;
                  } else {
                     tk = new Token("cteEntera", "" + valor);
                     tokenFW.write(tk.toString());
                     tokenFW.flush();
                     valor = 0;
                     return tk;
                  }
               case 'Q':
                  if (posLinea > accion.length()) {
                     tk = new Token("cteEntera", "" + valor);
                     tokenFW.write(tk.toString());
                     tokenFW.flush();
                     valor = 0;
                     estado = 0;
                     return tk;
                  }
               case 'P':
                  contador++;
                  break;
               case 'R':
                  if (contador > 64) {
                     listaErrores.add(new Error("Esta cadena ha sobrepasado la longitud maxima(64)"));
                     lexema = "";
                     posLinea++;
                  } else {
                     tk = new Token("cadena", lexema);
                     tokenFW.write(tk.toString());
                     tokenFW.flush();
                     lexema = "";
                     posLinea++;
                     return tk;
                  }
                  break;
               case 'T':
                  switch (caracter) {
                     case '(':
                        tk = new Token("abreP", "" + 6);
                        tokenFW.write(tk.toString());
                        tokenFW.flush();
                        posLinea++;
                        return tk;
                     case ')':
                        tk = new Token("cierraP", "" + 7);
                        tokenFW.write(tk.toString());
                        tokenFW.flush();
                        posLinea++;
                        return tk;
                     case ';':
                        tk = new Token("puntoComa", "" + 8);
                        tokenFW.write(tk.toString());
                        tokenFW.flush();
                        posLinea++;
                        return tk;
                     case '{':
                        tk = new Token("abreCo", "" + 9);
                        tokenFW.write(tk.toString());
                        tokenFW.flush();
                        posLinea++;
                        return tk;
                     case '}':
                        tk = new Token("cierraCo", "" + 10);
                        tokenFW.write(tk.toString());
                        tokenFW.flush();
                        posLinea++;
                        return tk;
                     case ',':
                        tk = new Token("coma", "" + 11);
                        tokenFW.write(tk.toString());
                        tokenFW.flush();
                        posLinea++;
                        return tk;
                     case ':':
                        tk = new Token("dosPuntos", "" + 13);
                        tokenFW.write(tk.toString());
                        tokenFW.flush();
                        posLinea++;
                        return tk;
                  }
                  break;
            }
         }
      }
      if (posLinea == linea.length()) {
         posLinea = 0;
      }
      return tk;
   }
}
