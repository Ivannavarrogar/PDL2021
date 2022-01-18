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
        private int primera=1;

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
            return index-1;
        }

        public void setDesplazamiento(int value){
            desplazamiento += value;
        }
        public int getDesplazamiento(){
            return desplazamiento;
        }
    
        public String getNombreTS(){
            return this.nombreTS;
        }
        public void setPrimera(){
            primera = 1;
        }
        
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
            int res = index-1;
            return res;
        }
        public int insertarFuncion(String lexema){
            Atributos id = new Atributos(lexema, "funcion");
            mapaAtributos.put(index, id);
            index++;
            int res = index-1;
            return res;
        }
        
        public void eliminarId(String lexema){
        for (int i = this.getIndex() ; i >= 0 ; i--
        ){
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
        public String toString(int indice){
            if (primera == 1){
             String tipo = this.getAtributo(indice).getTipo();
             int desplazamiento = this.getAtributo(indice).getDesplazamiento();
              String lexema = this.getAtributo(indice).getLexema();
             String resultado = "Contenido de la tabla " + this.getNombreTS() + ": \n * LEXEMA : " + lexema +"\n ATRIBUTOS: \n + tipo: " + tipo + "\n + despl: " 
             + desplazamiento + "\n -------------------- \n" ;
                primera = 0;
              return resultado;
            }
            else {
                String tipo = this.getAtributo(indice).getTipo();
             int desplazamiento = this.getAtributo(indice).getDesplazamiento();
              String lexema = this.getAtributo(indice).getLexema();
             String resultado = " * LEXEMA : " + lexema +"\n ATRIBUTOS: \n + tipo: " + tipo + "\n + despl: " + desplazamiento + "\n -------------------- \n" ;
              return resultado;
            }
        }

        public String toStringF(int indice){
            String tipo = this.getAtributo(indice).getTipoDevuelto();
                int desplazamiento = this.getAtributo(indice).getDesplazamiento();
                String lexema = this.getAtributo(indice).getLexema();
                int numParam = this.getAtributo(indice).getNumeroParametros();
                String tipoParam = this.getAtributo(indice).getTipoParametro();

            if (primera == 1){

                

                String resultado = "Contenido de la tabla " + this.getNombreTS() + ": \n * LEXEMA : " + lexema +"\n ATRIBUTOS: \n + tipo: funcion"  
                + "\n + tipo devuelto: "+tipo+"\n + tipo de parametros: "+ tipoParam +  "\n + numero de parametros: "+numParam+ "\n -------------------- \n" ;
                   primera = 0;
                 return resultado;
               }
               else {
               
                String resultado = " * LEXEMA : " + lexema +"\n ATRIBUTOS: \n + tipo: funcion"  +
                "\n + tipo devuelto: "+tipo+"\n + tipo de parametros: "+ tipoParam +  "\n + numero de parametros: "+numParam+ "\n -------------------- \n" ;
                 return resultado;
               }

        }
    
    }