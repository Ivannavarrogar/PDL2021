
public class Tupla{

    private int estado;
    private String accion;
        
    public Tupla (int estado, String accion){
        this.estado = estado;
        this.accion = accion;
        }
    
    public int getEstado (){
        return estado;
    }

    public String getAccion (){
        return accion;
    }
    
    private String noTerminal;
    private String terminal;

    public Tupla (String noTerminal, String terminal){
        this.noTerminal = noTerminal;
        this.terminal = terminal;
    }
    
     public String getNoTerminal (){
        return noTerminal;
    }

    public String getTerminal (){
        return terminal;    
    }

}
