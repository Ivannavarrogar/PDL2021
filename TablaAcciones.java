public class TablaAcciones {
    //No terminales
    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 2;
    private static final int D = 3;
    private static final int DP = 4 ; 
    private static final int E = 5;    
    private static final int EP = 6;
    private static final int F = 7;
    private static final int G = 8;
    private static final int GP = 9;
    private static final int H = 10;
    private static final int I= 11;
    private static final int J = 12;
    private static final int K = 13;
    private static final int L = 14;
    private static final int P = 15;
    private static final int Q = 16;
    private static final int S = 17; 
    private static final int SP = 18;  
    private static final int T = 19;
    private static final int U = 20;
    private static final int UP = 21;
    private static final int V = 22; 
    private static final int VP = 23; 
    private static final int X = 24;
    

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
        
        matriz = new Tupla[25][25];

        matriz[A][CIERRAP] = new Tupla(33,"lambda");
        matriz[A][BOOLEAN] = new Tupla(32,"T id K");
        matriz[A][INT] = new Tupla(32,"T id K");
        matriz[A][STRING] = new Tupla(32,"T id K");
        
        matriz[B][FOR] = new Tupla(7,"for ( J ; E ; I ) { C }");
        matriz[B][ID] = new Tupla(6,"S");
        matriz[B][IF] = new Tupla(5,"if ( E ) S");
        matriz[B][INPUT] = new Tupla(6,"S");
        matriz[B][LET] = new Tupla(4,"let T id ;");
        matriz[B][PRINT] = new Tupla(6,"S");
        matriz[B][RETURN] = new Tupla(6,"S");
        
        matriz[C][FOR] = new Tupla(23,"B C");
        matriz[C][ID] = new Tupla(23,"B C");
        matriz[C][IF] = new Tupla(23,"B C");
        matriz[C][INPUT] = new Tupla(23,"B C");
        matriz[C][LET] = new Tupla(23,"B C");
        matriz[C][PRINT] = new Tupla(23,"B C");
        matriz[C][RETURN] = new Tupla(23,"B C");
        matriz[C][CIERRAC] = new Tupla(24,"lambda");
        
        matriz[D][CIERRAP] = new Tupla(42,"G DP");
        matriz[D][CADENA] = new Tupla(42,"G DP");
        matriz[D][ENTERO] = new Tupla(42,"G DP");
        matriz[D][ID] = new Tupla(42,"G DP");
  
        matriz[DP][AND] = new Tupla(44,"lambda");
        matriz[DP][CIERRAP] = new Tupla(44,"lambda");
        matriz[DP][MAS] = new Tupla(44,"lambda");
        matriz[DP][COMA] = new Tupla(44,"lambda");
        matriz[DP][PCOMA] = new Tupla(44,"lambda");
        matriz[DP][RELACIONAL] = new Tupla(43,"== G DP");
        
        matriz[E][ABREP] = new Tupla(36,"U EP");
        matriz[E][CADENA] = new Tupla(36,"U EP");
        matriz[E][ENTERO] = new Tupla(36,"U EP");
        matriz[E][ID] = new Tupla(36,"U EP");

        matriz[EP][AND] = new Tupla(37,"&& U EP");
        matriz[EP][CIERRAP] = new Tupla(38,"lambda");
        matriz[EP][COMA] = new Tupla(38,"lambda");
        matriz[EP][PCOMA] = new Tupla(38,"lambda");

        matriz[F][FUNCTION] = new Tupla(29, "function id H ( A ) { C }");
        
        matriz[G][ABREP] = new Tupla(45, "V GP");
        matriz[G][CADENA] = new Tupla(45, "V GP");
        matriz[G][ENTERO] = new Tupla(45, "V GP");
        matriz[G][ID] = new Tupla(45, "V GP");
        
        matriz[GP][ASIGNACION] = new Tupla(46, "%= V GP");
        matriz[GP][AND] = new Tupla(47, "lambda");
        matriz[GP][CIERRAP] = new Tupla(47, "lambda");
        matriz[GP][MAS] = new Tupla(47, "lambda");
        matriz[GP][COMA] = new Tupla(47, "lambda");
        matriz[GP][PCOMA] = new Tupla(47, "lambda");
        matriz[GP][RELACIONAL] = new Tupla(47, "lambda");
        
        matriz[H][ABREP] = new Tupla(31, "lambda");
        matriz[H][BOOLEAN] = new Tupla(30, "T");
        matriz[H][INT] = new Tupla(30, "T");
        matriz[H][STRING] = new Tupla(30, "T");
  
        matriz[I][CIERRAP] = new Tupla(9, "lambda");
        matriz[I][ID] = new Tupla(8, "id = id + entero");
        
        matriz[J][PCOMA] = new Tupla(11, "lambda");
        matriz[J][ID] = new Tupla(10, "id = entero");
        
        matriz[K][CIERRAP] = new Tupla(35, "lambda");
        matriz[K][COMA] = new Tupla(34, ", T id K");
        
        matriz[L][ABREP] = new Tupla(25,"E Q");
        matriz[L][CIERRAP] = new Tupla(26,"lambda");
        matriz[L][CADENA] = new Tupla(25,"E Q");
        matriz[L][ENTERO] = new Tupla(25,"E Q");
        matriz[L][ID] = new Tupla(25,"E Q");
        
        matriz[P][FOR] = new Tupla(1,"B P");
        matriz[P][ID] = new Tupla(1,"B P");
        matriz[P][IF] = new Tupla(1,"B P");
        matriz[P][INPUT] = new Tupla(1,"B P");
        matriz[P][LET] = new Tupla(1,"B P");
        matriz[P][PRINT] = new Tupla(1,"B P");
        matriz[P][RETURN] = new Tupla(1,"B P");
        matriz[P][FUNCTION] = new Tupla(2,"F P");
        matriz[P][FINCADENA] = new Tupla(3,"lambda");
        
        matriz[Q][CIERRAP] = new Tupla(28,"lambda");
        matriz[Q][COMA] = new Tupla(27,", E Q");
  
        matriz[S][ID] = new Tupla(15,"id SP");
        matriz[S][INPUT] = new Tupla(18,"input ( id ) ;");
        matriz[S][PRINT] = new Tupla(17,"print ( E ) ;");
        matriz[S][RETURN] = new Tupla(16,"return X ;");
  
        matriz[SP][ABREP] = new Tupla(20,"( L ) ;");
        matriz[SP][IGUAL] = new Tupla(19,"= E ;");
  
        matriz[T][BOOLEAN] = new Tupla(13,"boolean");
        matriz[T][INT] = new Tupla(12,"int");
        matriz[T][STRING] = new Tupla(14,"string");
        
        matriz[U][ABREP] =new Tupla(39,"D UP");
        matriz[U][CADENA] = new Tupla(39,"D UP");
        matriz[U][ENTERO] = new Tupla(39,"D UP");
        matriz[U][ID] = new Tupla(39,"D UP");
        
        matriz[UP][AND] = new Tupla(41,"lambda");
        matriz[UP][CIERRAP] = new Tupla(41,"lambda");
        matriz[UP][COMA] = new Tupla(41,"lambda");
        matriz[UP][PCOMA] = new Tupla(41,"lambda");
        matriz[UP][MAS] = new Tupla(40,"+ D UP");
  
        matriz[V][ABREP] =new Tupla(49,"( E )");
        matriz[V][CADENA] =new Tupla(51,"cadena");
        matriz[V][ENTERO] = new Tupla(50,"entero");
        matriz[V][ID] = new Tupla(48,"id VP");
  
        matriz[VP][ASIGNACION] = new Tupla(53,"lambda");
        matriz[VP][AND] = new Tupla(53,"lambda");
        matriz[VP][CIERRAP] = new Tupla(53,"lambda");
        matriz[VP][MAS] = new Tupla(53,"lambda");
        matriz[VP][COMA] = new Tupla(53,"lambda");
        matriz[VP][PCOMA] = new Tupla(53,"lambda");
        matriz[VP][RELACIONAL] = new Tupla(53,"lambda");
        matriz[VP][ABREP] = new Tupla(52,"( L )");
  
        matriz[X][CIERRAP] = new Tupla(21,"E");
        matriz[X][PCOMA] = new Tupla(22,"lambda");
        matriz[X][CADENA] = new Tupla(21,"E");
        matriz[X][ENTERO] = new Tupla(21,"E");
        matriz[X][ID] = new Tupla(21,"E");
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
            case "G":
                return G;
            case "GP":
                return GP;
            case "H":
                return H;
            case "I":
                return I;
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