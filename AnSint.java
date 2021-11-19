import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Stack;

public class AnSint {

    private static Hashtable <Tupla,String> accion = new Hashtable<Tupla,String>();

    private static Hashtable <Tupla,Integer> goTo = new Hashtable<Tupla,Integer>();

    private Stack<String> pila;
    
    private AnLex lex;
    private Tablas tablas;

  
    private FileWriter parseFW;

    public AnSint(AnLex lexico, Tablas tablas, FileWriter ParseFW) {
      this.pila = new Stack<String>();
      this.parseFW = ParseFW;
      this.tablas =  tablas;
      this.lex = lexico;
     // construirAccion();
     //construirGoTo();
    }
    private void aux()throws IOException{
      Token token = lex.siguienteToken();
    }
   /* private void generarParse(int regla) {
      try {
          parseFW.append(" "+String.valueOf(regla));
          parseFW.flush();
      } catch (IOException e) {
          e.printStackTrace();
      }
     } */

    /*private int valorarK(int k, String acc){
      String auxString= acc.substring(1, acc.length() - 1);
      String lista = "";
      int auxInt = Integer.parseInt(auxString);
      
      int[] k1 = {1, 2, 8, 10, 11, 12, 13, 14, 16, 17, 18, 20, 21, 24, 27, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42, 43, 47, 53, 55, 56, 57, 
        58, 59, 60, 61, 62, 63, 64};
      int[] k2 = {34, 35, 51, 52, 54};
      int[] k3 = {3, 4, 9, 15, 19, 25, 26, 28, 30, 44, 45, 46, 48, 49};
      int[] k4 ={22, 23, 29, 50};
      
       for (int i= 0; i <= k1.length-1 ; i++ ){
         if (auxInt == k1[i]) lista= "k1";
       }

       for (int i= 0; i <= k2.length-1 ; i++ ){
         if (auxInt == k2[i]) lista= "k2";
       }

      for (int i= 0; i <= k3.length-1 ; i++ ){
         if (auxInt == k3[i]) lista= "k3";
       }
       
       for (int i= 0; i <= k4.length-1 ; i++ ){
        if (auxInt == k4[i]) lista= "k4";
      }
      
      if(auxInt == 5 || auxInt == 6) lista = "k6";
      if(auxInt == 7) lista = "k8";
      
      switch(lista){
        case ("k1"):
           k = 1; 
           break;
        
        case ("k2"):
            k = 2;
            break;
      
        case ("k3"):
            k = 3;
            break;

        case ("k4"):
           k = 4; 
           break;
        
        case ("k6"):
           k = 6; 
           break;

        case ("k8"):
           k = 8; 
           
        
      break;
      }
      return k;
    }

    public void analisisSintactico() throws IOException{
      Token token = lex.siguienteToken();
      String estadoi = "";
      String acc ="";
      int estadok = 0;
      int k = 0; // k = numero de no terminales de una regla
                 // en caso de que no haya  k = 1

      while(token !=null){

        //Buscar accion
        if( estadoi.equals("")){// Primera vez que entramos
         acc = accion.get(new Tupla(0, token.getAtributo())); 
        }else{ // A partir de aqui estado siempre sera un string no vacio, 
               //hay que parsearlo para poder mirar en accion
         acc = accion.get(new Tupla(Integer.parseInt(estadoi), token.getAtributo()));
        }
        
        //Desplazar y apilar
        if(acc.charAt(0) == 'd'){
          estadoi = (acc.substring(1, acc.length()-1));
          pila.push(token.getAtributo()); //meter a / terminal
          pila.push(estadoi); // meter estadoi
          token = lex.siguienteToken();
          
        }else{
          //Reducir
          if( acc.charAt(0) == 'R'){
            
            for (int i = 0; i < 2*valorarK(k, acc); i++) {

              pila.pop();       
            }
            
            estadoi = acc.substring(1, acc.length() - 1);
            pila.push(estadoi); //que va despues de la R
            //estadok = goTo.get(key)
            //pila.push()estadok = goTo.get(key)
            
          }else{
            //Aceptar
            if(acc.equals("ACEPTAR")){
              break;
            }else{//Error
              
            }
          }
        }
        
      }
 
    }

    private void construirGoTo(){
      goTo.put(new Tupla(0,"K"),10);
      goTo.put(new Tupla(0,"O"),11);
      goTo.put(new Tupla(0,"S"),1);

      goTo.put(new Tupla(3,"A"),12);
      goTo.put(new Tupla(3,"P"),13);

      goTo.put(new Tupla(4,"T"),18);

      goTo.put(new Tupla(5,"T"),20); 

      goTo.put(new Tupla(6,"T"),21);

      goTo.put(new Tupla(7,"A"),22);
      goTo.put(new Tupla(7,"P"),13);

      goTo.put(new Tupla(8,"G"),23);
      goTo.put(new Tupla(8,"J"),24);
      goTo.put(new Tupla(8,"L"),26);
      goTo.put(new Tupla(8,"O"),25);
      goTo.put(new Tupla(8,"P"),28);
      goTo.put(new Tupla(8,"Q"),27);

      goTo.put(new Tupla(9,"T"),31);

      goTo.put(new Tupla(11,"R"),33);

      goTo.put(new Tupla(12,"O"),34);
      
      goTo.put(new Tupla(18,"K"),10);
      goTo.put(new Tupla(18,"O"),11);
      goTo.put(new Tupla(18,"S"),35);
      
      goTo.put(new Tupla(20,"D"),36);
      goTo.put(new Tupla(20,"L"),38);
      goTo.put(new Tupla(20,"O"),37);
      
      goTo.put(new Tupla(21,"0"),39);
      
      goTo.put(new Tupla(22,"0"),40);

      goTo.put(new Tupla(24,"L"),46);
      goTo.put(new Tupla(24,"0"),44);
      goTo.put(new Tupla(24,"Q"),45);
      goTo.put(new Tupla(24,"Y"),43);

      goTo.put(new Tupla(25,"T"),49);
      goTo.put(new Tupla(25,"Y"),48);

      goTo.put(new Tupla(26,"Y"),52);

      goTo.put(new Tupla(27,"Y"),53);

      goTo.put(new Tupla(31,"O"),54);
      
      goTo.put(new Tupla(33,"G"),56);
      goTo.put(new Tupla(33,"I"),55);
      goTo.put(new Tupla(33,"J"),58);
      goTo.put(new Tupla(33,"L"),60);
      goTo.put(new Tupla(33,"O"),59);
      goTo.put(new Tupla(33,"P"),28);
      goTo.put(new Tupla(33,"Q"),57);
      goTo.put(new Tupla(33,"G"),56);
      
      goTo.put(new Tupla(34,"H"),64);
      goTo.put(new Tupla(34,"P"),65);
      goTo.put(new Tupla(34,"R"),66);
      
      goTo.put(new Tupla(35,"U"),67);

      goTo.put(new Tupla(36,"U"),69);
      
      goTo.put(new Tupla(37,"R"),70);
      goTo.put(new Tupla(37,"T"),49);
      goTo.put(new Tupla(37,"X"),71);

      goTo.put(new Tupla(39,"U"),72);

      goTo.put(new Tupla(40,"T"),73);

      goTo.put(new Tupla(42,"R"),74);

      goTo.put(new Tupla(43,"J"),75);
      goTo.put(new Tupla(43,"L"),46);
      goTo.put(new Tupla(43,"O"),44);
      goTo.put(new Tupla(43,"Q"),45);

      goTo.put(new Tupla(44,"T"),49);
      goTo.put(new Tupla(44,"Y"),48);

      goTo.put(new Tupla(45,"Y"),53);

      goTo.put(new Tupla(46,"Y"),52);

      goTo.put(new Tupla(47,"X"),76);

      goTo.put(new Tupla(48,"J"),77);
      goTo.put(new Tupla(48,"L"),46);
      goTo.put(new Tupla(48,"O"),44);
      goTo.put(new Tupla(48,"Q"),45);

      goTo.put(new Tupla(49,"L"),80);
      goTo.put(new Tupla(49,"M"),78);
      goTo.put(new Tupla(49,"O"),79);
      goTo.put(new Tupla(49,"P"),81);

      goTo.put(new Tupla(52,"J"),82);
      goTo.put(new Tupla(52,"L"),46);
      goTo.put(new Tupla(52,"O"),44);
      goTo.put(new Tupla(52,"Q"),45);

      goTo.put(new Tupla(53,"J"),83);
      goTo.put(new Tupla(53,"L"),46);
      goTo.put(new Tupla(53,"O"),44);
      goTo.put(new Tupla(53,"Q"),45);

      goTo.put(new Tupla(54,"U"),84);

      goTo.put(new Tupla(57,"Y"),53);

      goTo.put(new Tupla(58,"Y"),43);

      goTo.put(new Tupla(59,"T"),49);
      goTo.put(new Tupla(59,"X"),47);
      goTo.put(new Tupla(59,"Y"),48);

      goTo.put(new Tupla(60,"Y"),52);

      goTo.put(new Tupla(66,"G"),88);
      goTo.put(new Tupla(66,"I"),87);
      goTo.put(new Tupla(66,"J"),90);
      goTo.put(new Tupla(66,"L"),60);
      goTo.put(new Tupla(66,"O"),25);
      goTo.put(new Tupla(66,"P"),28);
      goTo.put(new Tupla(66,"Q"),89);

      goTo.put(new Tupla(69,"V"),92);

      goTo.put(new Tupla(70,"R"),94);

      goTo.put(new Tupla(71,"X"),95);

      goTo.put(new Tupla(72,"V"),96);

      goTo.put(new Tupla(73,"A"),98);
      goTo.put(new Tupla(73,"E"),97);
      goTo.put(new Tupla(73,"P"),13);

      goTo.put(new Tupla(74,"G"),99);
      goTo.put(new Tupla(74,"J"),24);
      goTo.put(new Tupla(74,"L"),26);
      goTo.put(new Tupla(74,"O"),25);
      goTo.put(new Tupla(74,"P"),28);
      goTo.put(new Tupla(74,"Q"),27);

      goTo.put(new Tupla(76,"O"),101);
      goTo.put(new Tupla(78,"L"),102);
      goTo.put(new Tupla(79,"N"),103);
      goTo.put(new Tupla(79,"T"),49);

      goTo.put(new Tupla(80,"N"),105);

      goTo.put(new Tupla(89,"Y"),106);

      goTo.put(new Tupla(90,"Y"),43);

      goTo.put(new Tupla(92,"K"),10);
      goTo.put(new Tupla(92,"O"),11);
      goTo.put(new Tupla(92,"S"),107);

      goTo.put(new Tupla(94,"O"),108);

      goTo.put(new Tupla(95,"O"),109);

      goTo.put(new Tupla(96,"B"),110);
      goTo.put(new Tupla(96,"P"),111);

      goTo.put(new Tupla(97,"U"),114);

      goTo.put(new Tupla(98,"O"),115);
      goTo.put(new Tupla(98,"O"),115);

      goTo.put(new Tupla(104,"O"),118);

      goTo.put(new Tupla(106,"J"),83);
      goTo.put(new Tupla(106,"L"),46);
      goTo.put(new Tupla(106,"O"),44);
      goTo.put(new Tupla(106,"Q"),45);

      goTo.put(new Tupla(107,"W"),119);

      goTo.put(new Tupla(110,"W"),121);

      goTo.put(new Tupla(112,"O"),122);

      goTo.put(new Tupla(114,"V"),124);

      goTo.put(new Tupla(115,"F"),125);
      goTo.put(new Tupla(115,"P"),127);

      goTo.put(new Tupla(118,"L"),49);
      goTo.put(new Tupla(118,"M"),128);
      goTo.put(new Tupla(118,"O"),129);
      goTo.put(new Tupla(118,"P"),81);
      goTo.put(new Tupla(123,"C"),130);
      goTo.put(new Tupla(123,"K"),10);
      goTo.put(new Tupla(123,"O"),11);
      goTo.put(new Tupla(123,"P"),132);
      goTo.put(new Tupla(123,"S"),131);
      goTo.put(new Tupla(123,"C"),130);
      goTo.put(new Tupla(124,"K"),10);
      goTo.put(new Tupla(124,"O"),11);
      goTo.put(new Tupla(124,"S"),134);
      goTo.put(new Tupla(126,"A"),135);
      goTo.put(new Tupla(126,"P"),13);
      goTo.put(new Tupla(129,"N"),136);
      goTo.put(new Tupla(129,"T"),49);
      goTo.put(new Tupla(131,"C"),137);
      goTo.put(new Tupla(131,"K"),10);
      goTo.put(new Tupla(131,"O"),11);
      goTo.put(new Tupla(131,"P"),132);
      goTo.put(new Tupla(131,"S"),131);
      goTo.put(new Tupla(134,"W"),139);
      goTo.put(new Tupla(135,"O"),140);
      goTo.put(new Tupla(137,"B"),141);
      goTo.put(new Tupla(137,"P"),111);
      goTo.put(new Tupla(138,"B"),142);
      goTo.put(new Tupla(138,"P"),111);
      goTo.put(new Tupla(140,"F"),143);
      goTo.put(new Tupla(140,"P"),127);  
    }
    
  

    private void construirAccion(){
      accion.put(new Tupla(0, "let"),"d3");
      accion.put(new Tupla(0, "alert"),"d4");
      accion.put(new Tupla(0, "input"),"d9");
      accion.put(new Tupla(0, "function"),"d7");
      accion.put(new Tupla(0, "return"),"d8");
      accion.put(new Tupla(0, "switch"),"d6");
      accion.put(new Tupla(0, "if"),"d5");
      accion.put(new Tupla(0, "eof"),"d2");
      
      accion.put(new Tupla(1, "$"),"ACEPTAR");
      
      accion.put(new Tupla(2, "$"),"R2");
      accion.put(new Tupla(2, "break"),"R2");
      accion.put(new Tupla(2, ")"),"R2");
      
      accion.put(new Tupla(3, "boolean"),"d16");
      accion.put(new Tupla(3, "number"),"d14");
      accion.put(new Tupla(3, "string"),"d15");
      accion.put(new Tupla(3, "$"),"d17");
      
      accion.put(new Tupla(4, "("),"d19");
      
      accion.put(new Tupla(5, "("),"d19");

      accion.put(new Tupla(6, "("),"d19");

      accion.put(new Tupla(7, "boolean"),"d16");
      accion.put(new Tupla(7, "number"),"d14");
      accion.put(new Tupla(7, "string"),"d15");

      accion.put(new Tupla(8, "id"),"d30");
      accion.put(new Tupla(8, "CteEntera"),"d29");
      accion.put(new Tupla(8, "$"),"d17");
      
      accion.put(new Tupla(9, "("),"d19");
      
      accion.put(new Tupla(10, "break"),"R10");
      accion.put(new Tupla(10, ")"),"R10");
      accion.put(new Tupla(10, "}"),"R10");
      accion.put(new Tupla(10, "$"),"R10");
      
      accion.put(new Tupla(11, "IGUAL"),"d32");
      
      accion.put(new Tupla(12, "id"),"d30");
      
      accion.put(new Tupla(13, "id"),"R14");
      
      accion.put(new Tupla(14, "id"),"R11");
      accion.put(new Tupla(14, "number"),"R11");
      
      accion.put(new Tupla(15, "id"),"R12");
      accion.put(new Tupla(15, "string"),"R12");
      
      accion.put(new Tupla(16, "id"),"R13");
      accion.put(new Tupla(16, "boolean"),"R13");

      accion.put(new Tupla(17, "$"),"R56");

      accion.put(new Tupla(18, "id"),"d30");
      accion.put(new Tupla(18, "let"),"d3");
      accion.put(new Tupla(18, "alert"),"d4");
      accion.put(new Tupla(18, "input"),"d9");
      accion.put(new Tupla(18, "function"),"d7");
      accion.put(new Tupla(18, "return"),"d8");
      accion.put(new Tupla(18, "switch"),"d6");
      accion.put(new Tupla(18, "if"),"d5");
      accion.put(new Tupla(18, "eof"),"d2");

      accion.put(new Tupla(19, "id"),"R59");
      accion.put(new Tupla(19, "boolean"),"R59");
      accion.put(new Tupla(19, "number"),"R59");
      accion.put(new Tupla(19, "string"),"R59");
      accion.put(new Tupla(19, "let"),"R59");
      accion.put(new Tupla(19, "alert"),"R59");
      accion.put(new Tupla(19, "input"),"R59");
      accion.put(new Tupla(19, "function"),"R59");
      accion.put(new Tupla(19, "return"),"R59");
      accion.put(new Tupla(19, "switch"),"R59");
      accion.put(new Tupla(19, "if"),"R59");
      accion.put(new Tupla(19, "eof"),"R59");
      accion.put(new Tupla(19, "$"),"R59");

      accion.put(new Tupla(20, "id"),"d30");
      
      accion.put(new Tupla(21, "id"),"d30");
      
      accion.put(new Tupla(22, "id"),"d30");
      
      accion.put(new Tupla(23, "BARRA"),"d42");
      accion.put(new Tupla(23, ";"),"d41");

      accion.put(new Tupla(24, "id"),"d30");
      accion.put(new Tupla(24, "CteEntera"),"d29");

      accion.put(new Tupla(25, "MAS"),"d51");
      accion.put(new Tupla(25, "("),"d19");
      accion.put(new Tupla(25, "&"),"d50");

      accion.put(new Tupla(26, "MAS"),"d51");
      
      accion.put(new Tupla(27, "MAS"),"d51");
      
      accion.put(new Tupla(28, "BARRA"),"R33");
      accion.put(new Tupla(28, ";"),"R33");
      
      accion.put(new Tupla(29, "MAS"),"R57");
      
      accion.put(new Tupla(30, "MAS"),"R55");
      accion.put(new Tupla(30, "IGUAL"),"R55");
      accion.put(new Tupla(30, "("),"R55");
      accion.put(new Tupla(30, ")"),"R55");
      accion.put(new Tupla(30, "&"),"R55");
      accion.put(new Tupla(30, ","),"R55");
      accion.put(new Tupla(30, ":"),"R55");
      accion.put(new Tupla(30, "$"),"R55");

      accion.put(new Tupla(31, "id"),"d30");

      accion.put(new Tupla(32, "id"),"R58");
      accion.put(new Tupla(32, "TRUE"),"R58");
      accion.put(new Tupla(32, "FALSE"),"R58");
      accion.put(new Tupla(32, "CteEntera"),"R58");
      accion.put(new Tupla(32, "cadena"),"R58");
      accion.put(new Tupla(32, "IGUAL"),"R58");
      accion.put(new Tupla(32, "$"),"R58");

      accion.put(new Tupla(33, "id"),"d30"); 
      accion.put(new Tupla(33, "TRUE"),"d62"); 
      accion.put(new Tupla(33, "FALSE"),"d63"); 
      accion.put(new Tupla(33, "CteEntera"),"d29"); 
      accion.put(new Tupla(33, "cadena"),"d61"); 
      accion.put(new Tupla(33, "$"),"d17"); 

      accion.put(new Tupla(34, "IGUAL"),"d32"); 
      accion.put(new Tupla(34, "$"),"d17"); 
      
      accion.put(new Tupla(35, ")"),"d68"); 

      accion.put(new Tupla(36, ")"),"d68");  

      accion.put(new Tupla(37, "IGUAL"),"d32"); 
      accion.put(new Tupla(37, "("),"d19"); 
      accion.put(new Tupla(37, "&"),"d50"); 

      accion.put(new Tupla(38, ")"),"R24"); 

      accion.put(new Tupla(39, ")"),"d68");

      accion.put(new Tupla(40, ")"),"d68");

      accion.put(new Tupla(41, "break"),"R8");
      accion.put(new Tupla(41, ")"),"R8");
      accion.put(new Tupla(41, "}"),"R8");
      accion.put(new Tupla(41, "$"),"R8");

      accion.put(new Tupla(42, "IGUAL"),"d32");

      accion.put(new Tupla(43, "id"),"d30");
      accion.put(new Tupla(43, "CteEntera"),"d29");

      accion.put(new Tupla(44, "MAS"),"d51");
      accion.put(new Tupla(44, "("),"d19");

      accion.put(new Tupla(45, "MAS"),"d51");

      accion.put(new Tupla(46, "MAS"),"d51");

      accion.put(new Tupla(47, "&"),"d50");

      accion.put(new Tupla(48, "id"),"d30");
      accion.put(new Tupla(48, "CteEntera"),"d29");

      accion.put(new Tupla(49, "id"),"d30");
      accion.put(new Tupla(49, "$"),"d17");

      accion.put(new Tupla(50, "id"),"R63");
      accion.put(new Tupla(50, "&"),"R63");

      accion.put(new Tupla(51, "id"),"R64");
      accion.put(new Tupla(51, "CteEntera"),"R64");

      accion.put(new Tupla(52, "id"),"d30");
      accion.put(new Tupla(52, "CteEntera"),"d29");

      accion.put(new Tupla(53, "id"),"d30");
      accion.put(new Tupla(53, "CteEntera"),"d29");

      accion.put(new Tupla(54, ")"),"d68");

      accion.put(new Tupla(55, ";"),"d85");

      accion.put(new Tupla(56, "BARRA"),"d42");
      accion.put(new Tupla(56, ";"),"d86");

      accion.put(new Tupla(57, "MAS"),"d51");

      accion.put(new Tupla(58, "MAS"),"d51");

      accion.put(new Tupla(59, "MAS"),"d51");
      accion.put(new Tupla(59, "("),"d19");
      accion.put(new Tupla(59, "&"),"d50");

      accion.put(new Tupla(60, "MAS"),"d51");

      accion.put(new Tupla(61, ";"),"R37");
      accion.put(new Tupla(61, "$"),"R37");

      accion.put(new Tupla(62, ":"),"R38");
      accion.put(new Tupla(62, "$"),"R38");

      accion.put(new Tupla(63, ":"),"R39");
      accion.put(new Tupla(63, "$"),"R39");

      accion.put(new Tupla(64, "break"),"R3");
      accion.put(new Tupla(64, ")"),"R3");
      accion.put(new Tupla(64, "}"),"R3");
      accion.put(new Tupla(64, "$"),"R3");

      accion.put(new Tupla(65, ";"),"R36");

      accion.put(new Tupla(66, "id"),"d30");
      accion.put(new Tupla(66, "TRUE"),"d62");
      accion.put(new Tupla(66, "FALSE"),"d63");
      accion.put(new Tupla(66, "CteEntera"),"d29");
      accion.put(new Tupla(66, "cadena"),"d61");
      accion.put(new Tupla(66, "$"),"d17");

      accion.put(new Tupla(67, ";"),"d91");

      accion.put(new Tupla(68, "{"),"R60");
      accion.put(new Tupla(68, ";"),"R60");

      accion.put(new Tupla(69, "{"),"d93");

      accion.put(new Tupla(70, "IGUAL"),"d32");

      accion.put(new Tupla(71, "&"),"d50");

      accion.put(new Tupla(72, "{"),"d93");
    
      accion.put(new Tupla(73, "boolean"),"d16");
      accion.put(new Tupla(73, "number"),"d14");
      accion.put(new Tupla(73, "string"),"d15");
      accion.put(new Tupla(73, "$"),"d17");

      accion.put(new Tupla(74, "id"),"d30");
      accion.put(new Tupla(74, "$"),"d17");

      accion.put(new Tupla(75, ";"),"d10");

      accion.put(new Tupla(76, "id"),"d30");

      accion.put(new Tupla(77, "MAS"),"R44");
      accion.put(new Tupla(77, ";"),"R44");
      accion.put(new Tupla(77, "$"),"R44");

      accion.put(new Tupla(78, ")"),"d68");
      
      accion.put(new Tupla(79, "("),"d19");
      accion.put(new Tupla(79, ","),"d104");

      accion.put(new Tupla(80, ","),"d104");

      accion.put(new Tupla(81, ")"),"R53");
      accion.put(new Tupla(81, "$"),"R53");

      accion.put(new Tupla(82, "MAS"),"R46");
      accion.put(new Tupla(82, ";"),"R46");
      accion.put(new Tupla(82, "$"),"R46");

      accion.put(new Tupla(83, "MAS"),"R45");
      accion.put(new Tupla(83, ";"),"R45");
      accion.put(new Tupla(83, "$"),"R45");

      accion.put(new Tupla(84, "break"),"R9");
      accion.put(new Tupla(84, ")"),"R9");
      accion.put(new Tupla(84, "}"),"R9");
      accion.put(new Tupla(84, "$"),"R9");

      accion.put(new Tupla(85, "$"),"R48");

      accion.put(new Tupla(86, "$"),"R49");

      accion.put(new Tupla(87, ";"),"R34");

      accion.put(new Tupla(88, "BARRA"),"d42");

      accion.put(new Tupla(89, "MAS"),"d51");

      accion.put(new Tupla(90, "MAS"),"d51");

      accion.put(new Tupla(91, "break"),"R4");
      accion.put(new Tupla(91, ")"),"R4");
      accion.put(new Tupla(91, "}"),"R4");
      accion.put(new Tupla(91, "$"),"R4");

      accion.put(new Tupla(92, "id"),"d30");
      accion.put(new Tupla(92, "let"),"d3");
      accion.put(new Tupla(92, "alert"),"d4");
      accion.put(new Tupla(92, "input"),"d9");
      accion.put(new Tupla(92, "function"),"d7");
      accion.put(new Tupla(92, "return"),"d8");
      accion.put(new Tupla(92, "switch"),"d6");
      accion.put(new Tupla(92, "if"),"d5");
      accion.put(new Tupla(92, "eof"),"d2");

      accion.put(new Tupla(93, "id"),"R61");
      accion.put(new Tupla(93, "let"),"R61");
      accion.put(new Tupla(93, "alert"),"R61");
      accion.put(new Tupla(93, "input"),"R61");
      accion.put(new Tupla(93, "function"),"R61");
      accion.put(new Tupla(93, "return"),"R61");
      accion.put(new Tupla(93, "switch"),"R61");
      accion.put(new Tupla(93, "if"),"R61");
      accion.put(new Tupla(93, "eof"),"R61");

      accion.put(new Tupla(94, "id"),"d30");

      accion.put(new Tupla(95, "id"),"d30");

      accion.put(new Tupla(96, "default"),"d113");
      accion.put(new Tupla(96, "case"),"d112");
      accion.put(new Tupla(96, "$"),"d117");

      accion.put(new Tupla(97, ")"),"d68");

      accion.put(new Tupla(98, "id"),"d30");
      
      accion.put(new Tupla(99, "BARRA"),"d42");
      accion.put(new Tupla(99, ";"),"d116");

      accion.put(new Tupla(100, "BARRA"),"R28");
      accion.put(new Tupla(100, ";"),"R28");

      accion.put(new Tupla(101, "BARRA"),"R29");
      accion.put(new Tupla(101, ";"),"R29");

      accion.put(new Tupla(102, ";"),"d117");

      accion.put(new Tupla(103, ")"),"R51");
      accion.put(new Tupla(103, "$"),"R51");
      
      accion.put(new Tupla(104, "id"),"d30");

      accion.put(new Tupla(105, ")"),"R52");
      accion.put(new Tupla(105, "$"),"R52");

      accion.put(new Tupla(106, "id"),"d30");
      accion.put(new Tupla(106, "CteEntera"),"d29");

      accion.put(new Tupla(107, "}"),"d120");
      
      accion.put(new Tupla(108, ")"),"R22");

      accion.put(new Tupla(109, ")"),"R23");

      accion.put(new Tupla(110, "}"),"d121");

      accion.put(new Tupla(111, "}"),"R17");
      accion.put(new Tupla(111, "$"),"R17");

      accion.put(new Tupla(112, "id"),"d30");

      accion.put(new Tupla(113, ":"),"d123");

      accion.put(new Tupla(114, "("),"d93");

      accion.put(new Tupla(115, ","),"d126");
      accion.put(new Tupla(115, "$"),"d17");

      accion.put(new Tupla(116, "BARRA"),"R30");
      accion.put(new Tupla(116, ";"),"R30");

      accion.put(new Tupla(117, "MAS"),"R50");
      accion.put(new Tupla(117, ","),"R50");
      accion.put(new Tupla(117, "$"),"R50");

      accion.put(new Tupla(118, "id"),"d30");
      accion.put(new Tupla(118, "$"),"d17");

      accion.put(new Tupla(119, "break"),"R5");
      accion.put(new Tupla(119, ")"),"R5");
      accion.put(new Tupla(119, "}"),"R5");
      accion.put(new Tupla(119, "$"),"R5");

      accion.put(new Tupla(120, "$"),"R62");

      accion.put(new Tupla(121, "break"),"R6");
      accion.put(new Tupla(121, ")"),"R6");
      accion.put(new Tupla(121, "}"),"R6");
      accion.put(new Tupla(121, "$"),"R6");

      accion.put(new Tupla(122, ":"),"d123");

      accion.put(new Tupla(123, "id"),"d30");
      accion.put(new Tupla(123, "let"),"d3");
      accion.put(new Tupla(123, "alert"),"d4");
      accion.put(new Tupla(123, "input"),"d9");
      accion.put(new Tupla(123, "break"),"d133");
      accion.put(new Tupla(123, "function"),"d7");
      accion.put(new Tupla(123, "return"),"d8");
      accion.put(new Tupla(123, "switch"),"d6");
      accion.put(new Tupla(123, "if"),"d5");
      accion.put(new Tupla(123, "eof"),"d2");
      accion.put(new Tupla(123, "$"),"d17");

      accion.put(new Tupla(124, "id"),"d30");
      accion.put(new Tupla(124, "let"),"d3");
      accion.put(new Tupla(124, "alert"),"d4");
      accion.put(new Tupla(124, "input"),"d9");
      accion.put(new Tupla(124, "function"),"d7");
      accion.put(new Tupla(124, "return"),"d8");
      accion.put(new Tupla(124, "switch"),"d6");
      accion.put(new Tupla(124, "if"),"d5");
      accion.put(new Tupla(124, "eof"),"d2");

      accion.put(new Tupla(125, ")"),"R25");

      accion.put(new Tupla(126, "boolean"),"d16");
      accion.put(new Tupla(126, "number"),"d14");
      accion.put(new Tupla(126, "string"),"d15");
      accion.put(new Tupla(126, "$"),"d17");

      accion.put(new Tupla(127, "$"),"R27");

      accion.put(new Tupla(128, "$"),"R54");

      accion.put(new Tupla(129, "("),"d19");
      accion.put(new Tupla(129, ","),"d104");

      accion.put(new Tupla(130, "}"),"R16");
      accion.put(new Tupla(130, "$"),"R16");

      
      accion.put(new Tupla(131, "id"),"d30");
      accion.put(new Tupla(131, "let"),"d3");
      accion.put(new Tupla(131, "alert"),"d4");
      accion.put(new Tupla(131, "input"),"d9");
      accion.put(new Tupla(131, "break"),"d133");
      accion.put(new Tupla(131, "function"),"d7");
      accion.put(new Tupla(131, "return"),"d8");
      accion.put(new Tupla(131, "switch"),"d6");
      accion.put(new Tupla(131, "if"),"d5");
      accion.put(new Tupla(131, "eof"),"d2");
      accion.put(new Tupla(131, "&"),"d17");

      accion.put(new Tupla(132, "default"),"R20");
      accion.put(new Tupla(132, "case"),"R20");
      accion.put(new Tupla(132, "$"),"R20");

      accion.put(new Tupla(133, ";"),"d138");

      accion.put(new Tupla(134, "}"),"d120");

      accion.put(new Tupla(135, "id"),"d30");

      accion.put(new Tupla(136, ")"),"R51");
      accion.put(new Tupla(136, "$"),"R51");

      accion.put(new Tupla(137, "default"),"d113");
      accion.put(new Tupla(137, "case"),"d112");
      accion.put(new Tupla(137, "&"),"d17");

      accion.put(new Tupla(138, "default"),"d113");
      accion.put(new Tupla(138, "case"),"d112");
      accion.put(new Tupla(138, "&"),"d17");

      accion.put(new Tupla(139, "break"),"R7");
      accion.put(new Tupla(139, ")"),"R7");
      accion.put(new Tupla(139, "}"),"R7");
      accion.put(new Tupla(139, "$"),"R7");

      accion.put(new Tupla(140, ","),"d126");
      accion.put(new Tupla(140, "$"),"d17");

      accion.put(new Tupla(141, "default"),"R19");
      accion.put(new Tupla(141, "case"),"R19");
      accion.put(new Tupla(141, "$"),"R19");

      accion.put(new Tupla(142, "default"),"R18");
      accion.put(new Tupla(142, "case"),"R18");
      accion.put(new Tupla(142, "$"),"R18");

      accion.put(new Tupla(143, "$"),"R26");     
      
    }

    private void regla1(){

    
    }

    private void regla2(){
      
    }

    private void regla3(){
    
    }
    
    private void regla4(){
    
    }
    
    private void regla5(){
    
    }

    private void regla6(){
    
    }  
     
    private void regla7(){
    
    }

    private void regla8(){
    
    }
    
    private void regla9(){
    
    }

    private void regla10(){
    
    }

    private void regla11(){
    
    }

    private void regla12(){
    
    }

    private void regla13(){
    
    }

    private void regla14(){
    
    }

    private void regla15(){
    
    }

    private void regla16(){
    
    }
    
    private void regla17(){
    
    }

    private void regla18(){
    
    }

    private void regla19(){
    
    }

    private void regla20(){
    
    }
    
    private void regla21(){
    
    }

    private void regla22(){
    
    }
    
    private void regla23(){
    
    }
    
    private void regla24(){
    
    }

    private void regla25(){
    
    }
    
    private void regla26(){
    
    }

    private void regla27(){
    
    }

    private void regla28(){
    
    }
    
    private void regla29(){
    
    }

    private void regla30(){
    
    }

    private void regla31(){
    
    }

    private void regla32(){
    
    }

    private void regla33(){
    
    }

    private void regla34(){
    
    }

    private void regla35(){
    
    }

    private void regla36(){
    
    }

    private void regla37(){
    
    }
    
    private void regla38(){
    
    }
    
    private void regla39(){
    
    }
    
    private void regla40(){
    
    }
    
    private void regla41(){
    
    }
    
    private void regla42(){
    
    }
    
    private void regla43(){
    
    }
    
    private void regla44(){
    
    }
    
    private void regla45(){
    
    }
    
    private void regla46(){
    
    }
    
    private void regla47(){
    
    }
    
    private void regla48(){
    
    }

    private void regla49(){
    
    }

    private void regla50(){
    
    }
    
    private void regla51(){
    
    }

    private void regla52(){
    
    }

    private void regla53(){
    
    }
    
    private void regla54(){
    
    }
    
    private void regla55(){
    
    }

    
    private void regla56(){
    
    }
    
    private void regla57(){
    
    }
    
    private void regla58(){
    
    }
    
    private void regla59(){
    
    }
    
    private void regla60(){
    
    }
    
    private void regla61(){
    
    }
    
    private void regla62(){
    
    }
    
    private void regla63(){
    
    }
    
    private void regla64(){
    
    }
       
    private void regla65(){
  
    }
*/
  
}