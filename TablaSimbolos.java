import java.util.ArrayList;
import java.util.HashMap;

/*
     * Tabla de simbolos con los atributos
     */
    public class TablaSimbolos {

        private String nombreTS;
        private HashMap<Integer, Atributos> mapaAtributos;
        private Tablas tablas;
        private int index;
        private int desplazamiento;

        public TablaSimbolos (String nombreTS, Tablas Tablas){
            this.nombreTS = nombreTS;
            this.tablas = Tablas;
            mapaAtributos = new HashMap<Integer, Atributos>();
            this.index=0;
            this.desplazamiento=0;
        }


        /*
         * Devuelve el objeto atributo correspondiente al lexema
         */
        public boolean buscarAtributo(String lexema){
            for (int i = 0 ; i <= index ; i++){
                if( mapaAtributos.get(i).getLexema().equals(lexema)) 
                return true;
            }
            return false; 
        }

        /*
         * Devuelve el objeto atributo que tiene el idAtributo
         * @param idAtributos
         * @return
         */
        public Atributos getAtributo(int idAtributos){
            return mapaAtributos.get(idAtributos);
        }
        public int getIndex(){
            return index;
        }

        public void setDesplazamiento(int value){
            desplazamiento += value;
        }
        public int getDesplazamiento(){
            return desplazamiento;
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
        public int insertarId (String lexema){
            Atributos id = new Atributos(lexema);
            mapaAtributos.put(index, id);
            index++;
            return index--;
        }
        public void eliminarId(String lexema){
        for (int i = 0 ; i < this.getIndex() ; i++){
            if (this.getAtributo(i).getLexema().equals(lexema)){ 
                mapaAtributos.remove(i);
                index--;
                break;
            }
        }
            
        }

        public ArrayList<Atributos> getAtributos(){
            return new ArrayList<Atributos>(mapaAtributos.values());
        }
    
    }