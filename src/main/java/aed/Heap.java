package aed;
import java.util.*;

public class Heap<T extends Comparable<T>> {
    private int cantidad;
    private ArrayList<Handle<T>> littleHeap; //el heap esta conformado con un array de handles para referenciar a las TuplaUsers y poder acceder a ellas sin recorrer todo el heap
    private ArrayList<Handle<T>> handlesEnOrden;

    public class Handle<T> {//PARECE que <T> no es necesario, igual lo dejo porque funciona
        private T valor;
        private int indice; // esto me indica donde estoy en el heap

        public Handle(T valor, int indice) {
            this.valor = valor;
            this.indice = indice;
        }

        public T getValor() {
            return valor;
        }

        // metodo para actualizar el valor
        public void actualizarValor(T valorNuevo){
            valor = valorNuevo;
            orden(indice);
            // siftDown(indice)
            // siftUp(indice)
        }

       public void eliminarMayor() {
            swap(0, cantidad - 1);      // swap con el último
            cantidad--;                 // descartás el último
            orden(0);                   // reordenás desde i
        }

    }

    public Heap(int n_usuarios, ArrayList<T> lista){
        this.cantidad = n_usuarios;
        this.handlesEnOrden = new ArrayList<Handle<T>>(n_usuarios); //Preparo el array donde estan los users en ORDEN
        this.littleHeap = new ArrayList<Handle<T>>(n_usuarios); // Preparo el heap donde estaran los users ORDENADOS POR MONTO
        for (int i=0;i< n_usuarios;i++){
            littleHeap.add(new Handle<T>(lista.get(i), i)); //Agrego en el heap un Handle de cada User
            this.handlesEnOrden.add(this.littleHeap.get(i)); //Agrego en el array el handle de cada user
        }
        ordenDeFloyd(0); //Ordeno el heap
    }

    public ArrayList<Handle<T>> handlesOrdenados(){ //Retorno el array
        return handlesEnOrden;
    }
    
    private void swap(int i, int j) { //i y j son los indices de elementos que comparo respectivamente
        //Cambio la posicion como el valor en si
        Handle<T> valor = littleHeap.get(j);
        
        littleHeap.set(j, littleHeap.get(i));
        littleHeap.get(j).indice = j;
        
        littleHeap.set(i, valor);
        littleHeap.get(i).indice = i;

}
    

    private void orden(int i) {

        while (2*i + 2 < cantidad){ // caso actual tiene dos hijos
            T actual = littleHeap.get(i).valor;
            T hijoIzq = littleHeap.get(2*i + 1).valor;
            T hijoDer= littleHeap.get(2*i + 2).valor;

            
            if(hijoDer.compareTo(hijoIzq) > 0 ){ //Verifico cual es el hijo mayor para comparar ese con el padre
                if (actual.compareTo(hijoDer)<0){//Verifico si efectivamente el hijo dicho es mayor que el padre
                swap(i,2*i + 2); //Ejecuto el cambio de posicion

                i = 2*i +2;//Edito las posiciones de la funcion orden
                }

                else{break;} //Si el hijo es menor al padre, entonces rompo el bucle
            }
            else{//Este es el caso que el hijo izquiero es el mayor
                if (actual.compareTo(hijoIzq)<0){//Verifico si efectivamente el hijo dicho es mayor que el padre
                swap(i,2*i+1); //Ejecuto el cambio de posicion
                i = 2*i +1;//Edito las posiciones de la funcion orden
                }

                else{break;} //Si el hijo es menor al padre, entonces rompo el bucle
            }
        }
        
        if (2*i + 1 < cantidad){ // Verifico el caso de que solo tenga el hijo izquierdo
            T actual = littleHeap.get(i).valor;
            T hijoIzq = littleHeap.get(2*i + 1).valor;
            
            if (actual.compareTo(hijoIzq) < 0){
                swap(i,2*i+1);//Si el hijo es mayor, entonces lo cambio de posicion
                i = 2*i +2; //Edito las posiciones en la funcion
                }
        }
        
        while (i > 0 && i < cantidad){ // mientras el elemento no sea la raiz, voy subiendo el hijo si este es mayor
            T actual = littleHeap.get(i).valor;
            T padre = littleHeap.get((i-1)/2).valor;

            
            if(actual.compareTo(padre)>0 ){
                swap(i,(i-1)/2); //Si el padre es menor, entonces lo cambio de posicion
                //Edito las posiciones
                i = (i-1)/2;
            }
            else{break;} //Si el padre es mayor, entonces rompo el bucle
        }
            
    }

    private void ordenDeFloyd(int i) {//Hace el algoritmo de orden de Floyd
        if (2 * i + 1 < cantidad) ordenDeFloyd(2 * i + 1); // hijo izquierdo
        if (2 * i + 2 < cantidad) ordenDeFloyd(2 * i + 2); // hijo derecho
        int maxIndex = i;
    
        if (2 * i + 1 < cantidad) {
            if (littleHeap.get(2 * i + 1).getValor().compareTo(littleHeap.get(maxIndex).getValor()) > 0) {
                maxIndex = 2 * i + 1;
            }
        }
    
        if (2 * i + 2 < cantidad) {
            if (littleHeap.get(2 * i + 2).getValor().compareTo(littleHeap.get(maxIndex).getValor()) > 0) {
                maxIndex = 2 * i + 2;
            }
        }
    
        if (maxIndex != i) {
            swap(i, maxIndex);
        }
    }
    
    public Handle<T> mayor(){
        return littleHeap.get(0);
    }

}
