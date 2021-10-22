import java.util.ArrayList;
import java.util.HashMap;

/*
     * Tabla de simbolos con los atributos
     */
    public class TablaSimbolos {

        //private String nombreTS;
        private HashMap<Integer, Atributos> mapaAtributos;
        private Tablas tablas;
    
        public TablaSimbolos (String nombreTS, Tablas Tablas){
            //this.nombreTS = nombreTS;
            this.tablas = Tablas;
            mapaAtributos = new HashMap<Integer, Atributos>();
        
        }

        /*
         * Devuelve el objeto atributo correspondiente al lexema
         */
        public Atributos buscarAtributo(String lexema){
            for (Integer idAtributos: mapaAtributos.keySet()){
                if( mapaAtributos.get(idAtributos).getLexema().equals(lexema)) 
                return mapaAtributos.get(idAtributos);
            }
            return null; 
        }

        /*
         * Devuelve el objeto atributo que tiene el idAtributo
         * @param idAtributos
         * @return
         */
        public Atributos getAtributo(int idAtributos){
            return mapaAtributos.get(idAtributos);
        }
        
        /*  public String getNombreTS(){
            return this.nombreTS;
        }*/
        
        /*  public void setNombreTS(String nombreTS){
            this.nombreTS= nombreTS;
        }*/
        
        /**
         * Inserta un nuevo atributo en la tabla correspondiente
         * @param lexema
         * @param idTabla
         */
        public void insertarAtributos (String lexema, int idTabla){
            Atributos atributo = new Atributos(tablas.getIdAtributos(),lexema, idTabla);
            this.mapaAtributos.put(atributo.getIdAtributos(),atributo);
        }

        public ArrayList<Atributos> getAtributos(){
            return new ArrayList<Atributos>(mapaAtributos.values());
        }
    
    }