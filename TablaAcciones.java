public class TablaAcciones {
    //No terminales
    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 2;
    private static final int D = 3;
    private static final int DP = 4; 
    private static final int E = 5;    
    private static final int EP = 6;
    private static final int F = 7;
    private static final int H = 8;
    private static final int J = 9;
    private static final int K = 10;
    private static final int L = 11;
    private static final int P = 12;
    private static final int Q = 13;
    private static final int S = 14; 
    private static final int SP = 15;  
    private static final int T = 16;
    private static final int U = 17;
    private static final int UP = 18;
    private static final int V = 19; 
    private static final int VP = 20; 
    private static final int X = 21;
    

    //Terminales
    private static final int ASIGNACION = 0;
    private static final int AND = 1;
    private static final int ABREP = 2;
    private static final int CIERRAP = 3;
    private static final int MAS = 4 ; 
    private static final int COMA = 5;     
    private static final int PCOMA = 6;
    private static final int IGUAL = 7;
    private static final int RELACIONAL = 8;
    private static final int BOOLEAN = 9;
    private static final int CADENA = 10;
    private static final int ENTERO= 11;
    private static final int FOR = 12;
    private static final int FUNCTION = 13;
    private static final int ID = 14;
    private static final int IF = 15;
    private static final int INPUT = 16;
    private static final int INT = 17;
    private static final int LET = 18;
    private static final int PRINT = 19 ; 
    private static final int RETURN = 20;     
    private static final int STRING = 21;
    private static final int ABREC = 22;
    private static final int CIERRAC = 23;
    private static final int FINCADENA = 24;

    private static Tupla matriz[][];

    public TablaAcciones(){
        
        matriz = new Tupla[22][25];

        matriz[A][CIERRAP] = new Tupla(32,"lambda");
        matriz[A][BOOLEAN] = new Tupla(31,"T 31.1 id K 31.2");
        matriz[A][INT] = new Tupla(31,"T 31.1 id K 31.2");
        matriz[A][STRING] = new Tupla(31,"T 31.1 id K 31.2");
        
        matriz[B][FOR] = new Tupla(7,"for ( J 7.1 ; E 7.2 ; J 7.3 ) { C 7.4 } 7.5");
        matriz[B][ID] = new Tupla(6,"S 6.1");
        matriz[B][IF] = new Tupla(5,"if ( E 5.1 ) S 5.2");
        matriz[B][INPUT] = new Tupla(6,"S 6.1");
        matriz[B][LET] = new Tupla(4,"let T 4.1 id ; 4.2");
        matriz[B][PRINT] = new Tupla(6,"S 6.1");
        matriz[B][RETURN] = new Tupla(6,"S 6.1");
        
        matriz[C][FOR] = new Tupla(22,"B C 22.1");
        matriz[C][ID] = new Tupla(22,"B C 22.1");
        matriz[C][IF] = new Tupla(22,"B C 22.1");
        matriz[C][INPUT] = new Tupla(22,"B C 22.1");
        matriz[C][LET] = new Tupla(22,"B C 22.1");
        matriz[C][PRINT] = new Tupla(22,"B C 22.1");
        matriz[C][RETURN] = new Tupla(22,"B C 22.1");
        matriz[C][CIERRAC] = new Tupla(23,"lambda");
        
        matriz[D][ABREP] = new Tupla(41,"V DP 41.1");
        matriz[D][CADENA] = new Tupla(41,"V DP 41.1");
        matriz[D][ENTERO] = new Tupla(41,"V DP 41.1");
        matriz[D][ID] = new Tupla(41,"V DP 41.1");
  
        matriz[DP][AND] = new Tupla(43,"lambda");
        matriz[DP][CIERRAP] = new Tupla(43,"lambda");
        matriz[DP][MAS] = new Tupla(42,"+ V DP 42.1");
        matriz[DP][COMA] = new Tupla(43,"lambda");
        matriz[DP][PCOMA] = new Tupla(43,"lambda");
        matriz[DP][RELACIONAL] = new Tupla(43,"lambda");
        
        matriz[E][ABREP] = new Tupla(35,"U EP 35.1");
        matriz[E][CADENA] = new Tupla(35,"U EP 35.1");
        matriz[E][ENTERO] = new Tupla(35,"U EP 35.1");
        matriz[E][ID] = new Tupla(35,"U EP 35.1");

        matriz[EP][AND] = new Tupla(36,"&& U EP 36.1");
        matriz[EP][CIERRAP] = new Tupla(37,"lambda");
        matriz[EP][COMA] = new Tupla(37,"lambda");
        matriz[EP][PCOMA] = new Tupla(37,"lambda");

        matriz[F][FUNCTION] = new Tupla(28, "function 28.1 id H 28.2 ( A ) { C } 28.3");
        
        matriz[H][ABREP] = new Tupla(30, "lambda");
        matriz[H][BOOLEAN] = new Tupla(29, "T 29.1");
        matriz[H][INT] = new Tupla(29, "T 29.1");
        matriz[H][STRING] = new Tupla(29, "T 29.1");
        
        matriz[J][CIERRAP] = new Tupla(9, "lambda");
        matriz[J][PCOMA] = new Tupla(9, "lambda");
        matriz[J][ID] = new Tupla(8, "8.1 id = E 8.2");
        
        matriz[K][CIERRAP] = new Tupla(34, "lambda");
        matriz[K][COMA] = new Tupla(33, ", T 33.1 id K 33.2");
        
        matriz[L][ABREP] = new Tupla(24,"E Q 24.1");
        matriz[L][CIERRAP] = new Tupla(25,"lambda");
        matriz[L][CADENA] = new Tupla(24,"E Q 24.1");
        matriz[L][ENTERO] = new Tupla(24,"E Q 24.1");
        matriz[L][ID] = new Tupla(24,"E Q 24.1");
        
        matriz[P][FOR] = new Tupla(1,"B P");
        matriz[P][ID] = new Tupla(1,"B P");
        matriz[P][IF] = new Tupla(1,"B P");
        matriz[P][INPUT] = new Tupla(1,"B P");
        matriz[P][LET] = new Tupla(1,"B P");
        matriz[P][PRINT] = new Tupla(1,"B P");
        matriz[P][RETURN] = new Tupla(1,"B P");
        matriz[P][FUNCTION] = new Tupla(2,"F P");
        matriz[P][FINCADENA] = new Tupla(3,"lambda");
        
        matriz[Q][CIERRAP] = new Tupla(27,"lambda");
        matriz[Q][COMA] = new Tupla(26,", E Q 26.1");
  
        matriz[S][ID] = new Tupla(13,"13.1 id SP 13.2");
        matriz[S][INPUT] = new Tupla(16,"input ( 16.1 id ) ; 16.2");
        matriz[S][PRINT] = new Tupla(15,"print ( E ) ; 15.1");
        matriz[S][RETURN] = new Tupla(14,"return X ; 14.1");
  
        matriz[SP][ABREP] = new Tupla(19,"( L ) ; 19.1");
        matriz[SP][IGUAL] = new Tupla(17,"= E ; 17.1");
        matriz[SP][ASIGNACION] = new Tupla(18,"%= E ; 18.1");
  
        matriz[T][BOOLEAN] = new Tupla(11,"boolean 11.1");
        matriz[T][INT] = new Tupla(10,"int 10.1");
        matriz[T][STRING] = new Tupla(12,"string 12.1");
        
        matriz[U][ABREP] =new Tupla(38,"D UP 38.1");
        matriz[U][CADENA] = new Tupla(38,"D UP 38.1");
        matriz[U][ENTERO] = new Tupla(38,"D UP 38.1");
        matriz[U][ID] = new Tupla(38,"D UP 38.1");
        
        matriz[UP][AND] = new Tupla(40,"lambda");
        matriz[UP][CIERRAP] = new Tupla(40,"lambda");
        matriz[UP][COMA] = new Tupla(40,"lambda");
        matriz[UP][PCOMA] = new Tupla(40,"lambda");
        matriz[UP][RELACIONAL] = new Tupla(39,"== D UP 39.1");
  
        matriz[V][ABREP] =new Tupla(45,"( E ) 45.1");
        matriz[V][CADENA] =new Tupla(47,"cadena 47.1");
        matriz[V][ENTERO] = new Tupla(46,"entero 46.1");
        matriz[V][ID] = new Tupla(44,"44.1 id VP 44.2");
  
        matriz[VP][AND] = new Tupla(49,"lambda");
        matriz[VP][CIERRAP] = new Tupla(49,"lambda");
        matriz[VP][MAS] = new Tupla(49,"lambda");
        matriz[VP][COMA] = new Tupla(49,"lambda");
        matriz[VP][PCOMA] = new Tupla(49,"lambda");
        matriz[VP][RELACIONAL] = new Tupla(49,"lambda");
        matriz[VP][ABREP] = new Tupla(48,"( L ) 48.1");
  
        matriz[X][ABREP] = new Tupla(20,"E 20.1");
        matriz[X][PCOMA] = new Tupla(21,"lambda");
        matriz[X][CADENA] = new Tupla(20,"E 20.1");
        matriz[X][ENTERO] = new Tupla(20,"E 20.1");
        matriz[X][ID] = new Tupla(20,"E 20.1");
    }

    public static String buscarAccion (String noTerminal, String terminal){
        if (matriz[equiparar(noTerminal)][equiparar(terminal)] == null) {
            return null;
        }else{
            return matriz[equiparar(noTerminal)][equiparar(terminal)].getAccion();
        }

    }

    public static int buscarParse (String noTerminal, String terminal){
        if (matriz[equiparar(noTerminal)][equiparar(terminal)] == null) {
            return -1;
        }else{
            return matriz[equiparar(noTerminal)][equiparar(terminal)].getEstado();
        }

    }

    public static int equiparar(String simbolo){
        switch (simbolo) {
            case "operAsignacionResto" :
                return ASIGNACION;
            case "operLogico":
                return AND;            
            case "abreP":
                return ABREP;
            case "cierraP":
                return CIERRAP;
            case "operAritmetico":
                return MAS;
            case  "coma":
                return COMA;
            case  "puntoComa":
                return PCOMA;
            case  "operAsignacion":
                return IGUAL;
            case "operRelacional":
                return RELACIONAL;
            case "boolean" :
                return BOOLEAN;
            case "cadena":
                return CADENA;
            case  "cteEntera":
                return ENTERO;
            case "for" :
                return FOR; 
            case "function":
                return FUNCTION;
            case "id" :
                return ID;
             case "if" :
                return IF;
            case "input" :
                return INPUT;
            case "int" :
                return INT;
            case "let" :
                return LET;
            case "print" :
                return PRINT;
            case "return" :
                return RETURN;
            case "string" :
                return STRING;
            case "abreCo" :
                return ABREC;
            case "cierraCo" :
                return CIERRAC;
            case "$" :
                return FINCADENA;
                
            //No terminales
            case  "A":
                return A;
            case "B":
                return B; 
            case "C":
                return C; 
            case "D":
                return D;
            case "DP":
                return DP;
            case "E":
                return E ;
            case "EP":
                return EP;
            case "F":
                return F;
            case "H":
                return H;
            case "J":
                return J;
            case "K":
                return K;
            case  "L":
                return L;   
            case "P":
                return P;
            case "Q":
                return Q;
            case "S":
                return S;
            case "SP":
                return SP;
            case "T" :
                return T;
            case "U":
                return U;
            case "UP":
                return UP;
            case "V":
                return V;
            case "VP":
                return VP;
            case "X":
                return X;
            default:
                return LET;
        }
    }
}
