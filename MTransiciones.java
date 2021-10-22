
public class MTransiciones{

    private static final int DEL = 0;
    private static final int MAS = 1;
    private static final int IGUAL = 2;
    private static final int AND = 3;
    private static final int SLASHINV = 4 ; // \
    private static final int SLASH = 5;     // /
    private static final int DIG = 6;
    private static final int LET = 7;
    private static final int ABREP = 8;
    private static final int CIERRAP = 9;
    private static final int ABREC = 10;
    private static final int CIERRAC= 11;
    private static final int DOSPUNTOS = 12;
    private static final int COMA = 13;
    private static final int PUNTOCOMA = 14;
    private static final int ASTERISCO = 15;
    private static final int GUIONBAJO = 16;
    private static final int COMILLAS = 17; // "
    private static final int OTROS = 18 ;  // ~#@|%$!¿¡

    private static Tupla matriz[][];

    public MTransiciones() {

        matriz = new Tupla[12][19];

        matriz[0][DEL] = new Tupla(0, "L");
        matriz[0][MAS] = new Tupla(13, "D");
        matriz[0][IGUAL] = new Tupla(1, "AL");
        matriz[0][AND] = new Tupla(3, "AL");
        matriz[0][SLASHINV] = new Tupla(2, "AL");
        matriz[0][SLASH] = new Tupla(4, "L");
        matriz[0][DIG] = new Tupla(8, "HLQ");
        matriz[0][LET] = new Tupla(7, "AL");
        matriz[0][COMILLAS] = new Tupla(9, "AGL");
        matriz[0][ABREP] = new Tupla(13, "T");
        matriz[0][CIERRAP] = new Tupla(13, "T");
        matriz[0][ABREC] = new Tupla(13, "T");
        matriz[0][CIERRAC] = new Tupla(13, "T");
        matriz[0][PUNTOCOMA] = new Tupla(13, "T");
        matriz[0][DOSPUNTOS] = new Tupla(13, "T");
        matriz[0][COMA] = new Tupla(13, "T");
        matriz[0][ASTERISCO] = new Tupla(0, "U");
        matriz[0][OTROS] = new Tupla(0, "U");

        //Relacional ==  y asignacion =
        matriz[1][IGUAL] = new Tupla(10,"BL");
        matriz[1][DEL] = new Tupla(17,"E");
        matriz[1][DIG] = new Tupla(17,"EHL");
        matriz[1][LET] = new Tupla(7,"EAL");
        matriz[1][COMILLAS] = new Tupla(9, "EAGL");
        matriz[10][DIG] = new Tupla(8,"CHL");
        matriz[10][LET] = new Tupla(7,"CAL");
        matriz[10][COMILLAS] = new Tupla(18,"CAGL");
        matriz[10][ABREC] = new Tupla(18,"CT");
        matriz[10][DEL] = new Tupla(18,"CL");
      
        //AsignacionDivision \=
        matriz[2][IGUAL] = new Tupla(19, "J");

        //Logico
        matriz[3][AND] = new Tupla(11, "B");
        matriz[11][DIG] = new Tupla(12,"K");
        matriz[11][LET] = new Tupla(12,"K");
        matriz[11][COMILLAS] = new Tupla(12,"K");
        matriz[11][ABREC] = new Tupla(12,"K");
        matriz[11][DEL] = new Tupla(12,"K");
      

        //Comentarios
        matriz[4][ASTERISCO] = new Tupla(5, "L");
        matriz[5][LET] = new Tupla(5, "L");
        matriz[5][DIG] = new Tupla(5, "L");
        matriz[5][DEL] = new Tupla(5, "L");
        matriz[5][ASTERISCO] = new Tupla(6, "L");
        matriz[6][SLASH] = new Tupla(0, "L");
        matriz[6][OTROS] = new Tupla(5, "L");

        //Identificadores
        matriz[7][LET] = new Tupla(7, "BL");
        matriz[7][DIG] = new Tupla(7, "BL");
        matriz[7][GUIONBAJO] = new Tupla(7, "BL");
        matriz[7][DEL] = new Tupla(14, "M");
        matriz[7][PUNTOCOMA] = new Tupla(13,"MT");
        matriz[7][CIERRAP] = new Tupla(13,"MT");
        matriz[7][ABREP] = new Tupla(13,"MT");
        matriz[7][AND] = new Tupla(0,"MBAKL");
        matriz[7][IGUAL] = new Tupla(1,"MAL");
        matriz[7][MAS] = new Tupla(13,"MT");
        matriz[7][COMA] = new Tupla(13,"MT");

        //enteros
        matriz[8][DIG] = new Tupla(8, "IL");
        matriz[8][DEL] = new Tupla(15, "O");
        matriz[8][PUNTOCOMA] = new Tupla(13, "OT");
        matriz[8][CIERRAP] = new Tupla(13,"OT");
        matriz[8][OTROS] = new Tupla(13,"OL");
        matriz[8][IGUAL] = new Tupla(1,"OAL");

        //Cadenas
        matriz[9][DIG] = new Tupla(9, "BPL");
        matriz[9][LET] = new Tupla(9, "BPL");
        matriz[9][DEL] = new Tupla(9, "BPL");
        matriz[9][OTROS] = new Tupla(9, "BL");
        matriz[9][COMILLAS] = new Tupla(16, "BR");
        matriz[9][PUNTOCOMA] = new Tupla(13,"BRT");
        matriz[9][DOSPUNTOS] = new Tupla(9,"BL");
        matriz[9][CIERRAP] = new Tupla(13,"BRT");

        

    }

    public static String mtAccion(int estado, char caracter){
        if (matriz[estado][equiparar(caracter)] == null) {
            return null;
        }else{
            return matriz[estado][equiparar(caracter)].getAccion();
        }

    }

    public static int mtEstado(int estado, char caracter){
        return matriz[estado][equiparar(caracter)].getEstado(); 
    }

    public static int equiparar(char caracter){
        switch (caracter) {
            case ' ' :
                return DEL;
            case '\t':
                return DEL;
            case '\n':
                return DEL;
            case '+':
                return MAS;
            case '=':
                return IGUAL;
            case  '&':
                return AND;
            case  '\\':
                return SLASHINV;
            case  '/':
                return SLASH;
            case '0':
                return DIG;
            case '1' :
                return DIG;
            case '2':
                return DIG;
            case  '3':
                return DIG;
            case '4' :
                return DIG; 
            case'5':
                return DIG;
            case  '6':
                return DIG;
            case '7':
                return DIG; 
            case'8':
                return DIG; 
            case'9':
                return DIG;
            case '(':
                return ABREP;
            case ')':
                return CIERRAP ;
            case '{':
                return ABREC;
            case '}':
                return CIERRAC;
            case ':':
                return DOSPUNTOS;
            case ',':
                return COMA;
            case ';':
                return PUNTOCOMA;
            case '*':
                return ASTERISCO;
            case '_':
                return GUIONBAJO;
            case '"':
                return COMILLAS;
            case  '~':
                return OTROS;   
            case '#':
                return OTROS;
            case  '@':
                return OTROS;
            case'|':
                return OTROS;
            case '%':
                return OTROS;
            case  '$' :
                return OTROS;
            case  '!':
                return OTROS;
            case   '¿':
                return OTROS;
            case  '¡':
                return OTROS;
            default:
                return LET;
        }
    }
    }
