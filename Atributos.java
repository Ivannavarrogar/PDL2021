import java.util.ArrayList;

public class Atributos {
    private String lexema;
    private String tipo;
    private int desplazamiento;
    private int numeroParametros;
    private String tipoParametro;
    private String modoPaso;
    private String tipoDevuelto;
    private String etiqueta;
    


    public Atributos( String lexema, String tipo){
        this.lexema = lexema;
        this.tipo=tipo;
        this.numeroParametros= 0;
        this.modoPaso = "";
        this.tipoParametro="";
        this.tipoDevuelto="";
        this.etiqueta="";
    }

    public Atributos( String lexema){
        this.lexema = lexema;
        this.tipo = "";
        this.desplazamiento=0;

    }

    
    public String getLexema() {
        return this.lexema;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDesplazamiento() {
        return this.desplazamiento;
    }

    public void setDesplazamientoId(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public int getNumeroParametros() {
        return this.numeroParametros;
    }

    public void setNumeroParametros() {
        this.numeroParametros++;
    }

    public String getTipoParametro() {
        return this.tipoParametro;
    }

    public void setTipoParametro(String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public String getTipoDevuelto() {
        return this.tipoDevuelto;
    }

    public void setTipoDevuelto(String tipoDevuelto) {
        this.tipoDevuelto = tipoDevuelto;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    public void incrementarIdAtributos(int idAtributos){
        idAtributos++;
    }
}
