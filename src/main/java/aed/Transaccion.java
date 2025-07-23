package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;
    private ListaEnlazada<Transaccion>.Handle handle;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    public void setHandle(ListaEnlazada<Transaccion>.Handle nuevoHandle) {
    this.handle = nuevoHandle;    
    }

    public ListaEnlazada<Transaccion>.Handle getHandle() {
    return handle;
    }

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto != otro.monto){return this.monto - otro.monto;}
        return this.id - otro.id;
    }

  
@Override
public boolean equals(Object otro) {
    if (this == otro) return true;
    if (otro == null || getClass() != otro.getClass()) return false;

    Transaccion t = (Transaccion) otro;

    return this.id == t.id &&
           this.id_comprador == t.id_comprador &&
           this.id_vendedor == t.id_vendedor &&
           this.monto == t.monto;
}

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }
    public int getId () {return id;}
}
