

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
    public void setIdTablaActiva(int index){
        this.tablaActiva= index;
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
        int res = contadorTablas-1;
        return res;
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
        boolean flagVacio = true;
        TablaSimbolos tabla = mapaTablas.get(getIdTablaActiva());

        for (int j = 0 ; j <= tabla.getIndex() && flagVacio  ; j++){
            if (tabla.getAtributo(j)!= null){ 
            if (tabla.getAtributo(j).getLexema().equals(lexema)){
                return true;
            }
        }
        else flagVacio= false;
        } 
        return false;
    }
    
    public boolean existeAtributoGlobal(String lexema){
        boolean flagVacio = true;
        for (int i = 0 ; i < contadorTablas ; i++){
            TablaSimbolos tabla = mapaTablas.get(i);

            for (int j = 0 ; j <= tabla.getIndex() && flagVacio  ; j++){
                if (tabla.getAtributo(j)!= null){ 
                if (tabla.getAtributo(j).getLexema().equals(lexema)){
                    return true;
                }
            }
            else flagVacio= false;
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
        boolean flag = true;
        for (int i = 0 ; i < contadorTablas && flag ; i++){
            TablaSimbolos tabla = mapaTablas.get(i);
            for (int j = 0 ; j <= tabla.getIndex() && flag ; j++){
                if (tabla.getAtributo(j)!= null){ 
                if (tabla.getAtributo(j).getLexema().equals(lexema)){
                    resultado+= i + "-" + j; 
                    flag = false;
                }
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
  
	
	