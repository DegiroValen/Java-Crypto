package aed;

public class Bloque {
    private ListaEnlazada<Transaccion> bloque;
    private int cantTrans;
    private int montosTotal;

    public Bloque(){
        bloque = new ListaEnlazada<Transaccion>();
        cantTrans = 0;
        montosTotal = 0;
    }

    public ListaEnlazada<Transaccion>.Handle agregarTrans(Transaccion trans){
    ListaEnlazada<Transaccion>.Handle handle = bloque.agregarAtras(trans);
    trans.setHandle(handle);
        if (trans.id_comprador() != 0){ 
            cantTrans += 1;
            montosTotal += trans.monto();
        }
        return handle;
    }


public void eliminarPrimerTrans(int longCadena){

    if (longCadena >= 3000){
        Transaccion trans = bloque.obtenerPrimero(); 
        bloque.eliminarPrimero();
        montosTotal -= trans.monto();
    } else {
        bloque.eliminarPrimero();
    }
    if(cantTrans == 0){
        return;
    }else{
        cantTrans --;
    }
}

    public Transaccion obtenerPrimerTrans(){
        return bloque.obtenerPrimero();
    
    }
    
    public int mediaBloque(){
        if (this.cantTrans == 0) {return 0;}
            
        int res = montosTotal / cantTrans;
        return res;

    }

    public void eliminar(  Heap<Transaccion>.Handle<Transaccion> mayorTrans){
        mayorTrans.getValor().getHandle().eliminar();
        Transaccion trans = mayorTrans.getValor();
        montosTotal -= trans.monto();
        if(cantTrans == 0){
        return;
        }else{
        cantTrans --;
        }
    }


public Bloque(Bloque otro){

    this.bloque = new ListaEnlazada<>();
    this.cantTrans = otro.cantTrans;
    this.montosTotal = otro.montosTotal;

    if (otro.bloque.longitud() == 0){
        return;
    }
          

    ListaEnlazada<Transaccion>.Iterador it = otro.bloque.iterador();
    while (it.haySiguiente()) {
        Transaccion original = it.obtener();
        Transaccion copia = new Transaccion(
            original.getId(),
            original.id_comprador(),
            original.id_vendedor(),
            original.monto()
        );
        this.bloque.agregarAtras(copia);
        it.siguiente();
    }
}
public int longitud(){
    return bloque.longitud();
} 

}


