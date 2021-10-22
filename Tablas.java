

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Tabla de tablas de simbolos
 */
class Tablas {
    private int contadorTablas;
    private int idAtributos;
    private int tablaActiva;
    private HashMap <Integer,TablaSimbolos> mapaTablas;

    
    public Tablas() {
        this.contadorTablas = 1;
        this.idAtributos = 0;
        this.tablaActiva = 0;
        this.mapaTablas = new HashMap<Integer, TablaSimbolos>();
        mapaTablas.put(0, new TablaSimbolos("Main",this));
    }

    public int getcontadorTablas(){
        return contadorTablas;
    }
    
    public int getIdAtributos(){
        return idAtributos++;
    }

    public int getIdTablaActiva(){
        return tablaActiva;
    }
    
    /*
    * Cambia a la nueva tabla activa
    */
    public void nuevaActiva(int idTabla){
        this.tablaActiva = idTabla;
    }

    /*
    * Introduce una tabla de simbolos con atributos en la tabla de tablas
    */
    public int insertarTabla(String nombreTablaSimbolos){
        TablaSimbolos nuevaTabla = new TablaSimbolos(nombreTablaSimbolos,this);
        mapaTablas.put(contadorTablas, nuevaTabla);
        contadorTablas++;
        return contadorTablas--;
    }
    
    /*
    * Elimina una tabla de simbolos con atributos en la tabla de tablas
    */
    public void eliminarTabla(int idTabla){
        mapaTablas.remove(idTabla);
        contadorTablas--;
    }

    /*
    * Inserta un atributo en la tabla activa
    */
    public void insertarAtributos(String lexema){
        TablaSimbolos tabla = mapaTablas.get(tablaActiva);
        tabla.insertarAtributos(lexema, tablaActiva);
    }

    /*  
    * Comprueba si existe el atributo correspondiente al lexema
    */
    public Atributos existeAtributo(String lexema){
        
        TablaSimbolos tabla = mapaTablas.get(getIdTablaActiva());
        ArrayList<Atributos> atributos = tabla.getAtributos();

        for (Atributos atrib : atributos) {
            if( atrib.getLexema().equals(lexema)){
                return atrib;
            }       
        }
        return null;
    }

    
}
  
	
	