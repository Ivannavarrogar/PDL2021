

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

    public TablaSimbolos getTabla(int id){
        return mapaTablas.get(id);
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
    public void insertarId(Atributos id){
        
    }

    /*  
    * Comprueba si existe el atributo correspondiente al lexema
    */
    public boolean existeAtributoA(String lexema){
        
        TablaSimbolos tabla = mapaTablas.get(getIdTablaActiva());
        ArrayList<Atributos> atributos = tabla.getAtributos();

        for (Atributos atrib : atributos) {
            if( atrib.getLexema().equals(lexema)){
                return true;
            }       
        }
        return false;
    }
    
    public boolean existeAtributoGlobal(String lexema){
        for (int i = 0 ; i < contadorTablas ; i++){
            TablaSimbolos tabla = mapaTablas.get(i);
            ArrayList<Atributos> atributos = tabla.getAtributos();

            for (Atributos atrib : atributos) {
                if( atrib.getLexema().equals(lexema)){
                    return true;
                }       
            }       
        }
        return false;
    
    }

    public String buscarIndice(String lexema){
        TablaSimbolos tabla = mapaTablas.get(getIdTablaActiva());
        String resultado = "";
        for (int i = 0 ; i < tabla.getIndex() ; i++){
            if (tabla.getAtributo(i).getLexema().equals(lexema)){
                resultado+= i; 
                break;
            }
        }
        return resultado;
    }
    
    public String buscarPuntero(String lexema){
        String resultado = "";
        for (int i = 0 ; i < contadorTablas ; i++){
            TablaSimbolos tabla = mapaTablas.get(i);
            for (int j = 0 ; j <= tabla.getIndex() ; j++){
                if (tabla.getAtributo(j).getLexema().equals(lexema)){
                    resultado+= i + "-" + j; 
                    break;
                }
            }
        }
        return resultado;
    }

    public Atributos buscarPorPuntero (String puntero){
        int tabla = -1;
        int indice = 0;
        String aux = "";
        for(int i=0; i  < puntero.length(); i++){
            if(!(puntero.charAt(i) == ('-'))){
              aux+= puntero.charAt(i);    
            }
            else{
                    tabla = Integer.parseInt(aux);
                    aux= "";
            }
          }
          indice = Integer.parseInt(aux);
          aux= "";
          return mapaTablas.get(tabla).getAtributo(indice);
    }

    
}
  
	
	