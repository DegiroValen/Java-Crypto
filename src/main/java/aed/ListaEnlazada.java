package aed;

public class ListaEnlazada<T>{
    private int longitud;
    private Nodo primero;
    private Nodo ultimo;
    

    private class Nodo {
        T valor;
        Nodo sig; 
        Nodo ant;

        public Nodo (T v) 
        {valor = v;
        }
    }

    public class Handle {
        private Nodo nodo;

        public Handle(Nodo nodo) {
            this.nodo = nodo;
        }

        public T getValor() {
            return nodo.valor;
        }

    public void eliminar() {
        if (nodo.ant == null) { // caso si el nodo es el primero
            primero = nodo.sig;
            if (primero != null) primero.ant = null;
        } else if (nodo.sig == null) { // caso el nodo es el ultimo
            ultimo = nodo.ant;
            if (ultimo != null) ultimo.sig = null;
        } else {
            nodo.ant.sig = nodo.sig;
            nodo.sig.ant = nodo.ant;
        }
        longitud--;
    }
    }


    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        if (longitud ==  0){
        Nodo nuevo = new Nodo(elem);
        primero = nuevo;
        ultimo = nuevo;      
        }
        else{ 
            Nodo nuevo = new Nodo(elem);
            nuevo.sig = primero;
            primero.ant = nuevo;
            primero = nuevo; //la casilla del primero pasa al nuevo
        }
        longitud++;

    }

public Handle agregarAtras(T elem) {
    Nodo nuevo = new Nodo(elem);
    
    if (longitud == 0) {
        primero = nuevo;
        ultimo = nuevo;
    } else {
        nuevo.ant = ultimo;
        ultimo.sig = nuevo;
        ultimo = nuevo;
    }
    longitud++;
    return new Handle(nuevo);
}


    public T obtenerPrimero() {
        if (primero == null){return null;}
        return primero.valor; // esto es O(1) solo obtengo el primero
    } 

    public T obtener(int i) {
        Nodo actual = primero;
        int j = 0;
        while(j != i){
            actual = actual.sig;
            j += 1;
        }
        return actual.valor;
    }


    public void eliminarPrimero() { // esto es O(1) solo borro el primero
        if (primero == null){
            return;
            }
        if (primero.sig == null) {
            primero = null;
            longitud --;
            return;
        }else{
            primero.valor = primero.sig.valor;
            primero.sig = primero.sig.sig;
        }
        longitud --;
    }
    


    public void modificarPosicion(int indice, T elem) {
        int contador = 0;
        Nodo actual = primero;
        while (contador < indice){
            actual = actual.sig;
            contador ++;
        }
        actual.valor = elem;

    }

public ListaEnlazada(ListaEnlazada<T> lista) {
    this.primero = null;
    this.ultimo = null;
    this.longitud = 0;

    ListaEnlazada<T>.Iterador it = lista.iterador();
    while (it.haySiguiente()) {
        this.agregarAtras(it.siguiente());
    }
}


public class Iterador {
    int puntero;
    Nodo actual;

    public Iterador() {
        puntero = 0;
        actual = primero;
    }

    public boolean haySiguiente() {
        return actual != null;
    }

    public T siguiente() {
        T res = actual.valor;
        actual = actual.sig;
        puntero++;
        return res;
    }

    public T obtener() {
        return actual.valor;
    }

    public boolean hayAnterior() {
        return puntero != 0;
    }

    public T anterior() {
        if (actual == null) {
            actual = ultimo;
        } else {
            actual = actual.ant;
        }
        puntero--;
        return actual.valor;
    }

} 
    
    public Iterador iterador() {
    return new Iterador();
    }
}

