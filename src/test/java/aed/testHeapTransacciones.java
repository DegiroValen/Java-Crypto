package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class testHeapTransacciones {

    @Test
    public void ConstruccionConTransacciones() {
        ArrayList<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(1, 1, 2, 10)); 
        transacciones.add(new Transaccion(2, 3, 4, 20)); 
        transacciones.add(new Transaccion(3, 2, 3, 15)); 

        Heap<Transaccion> heap = new Heap<>(transacciones.size(), transacciones);
        assertEquals(20, heap.mayor().getValor().monto());
        assertEquals(2, heap.mayor().getValor().getId()); 
    }

    @Test
    public void desempatePorMayorId() {
        ArrayList<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(1, 2, 3, 10)); 
        transacciones.add(new Transaccion(5, 3, 4, 10)); 
        transacciones.add(new Transaccion(3, 3, 2, 10)); 

        Heap<Transaccion> heap = new Heap<>(transacciones.size(), transacciones);
        assertEquals(5, heap.mayor().getValor().getId());

        heap.mayor().eliminarMayor(); // borro la de mayor id, osea id= 5
        assertEquals(3, heap.mayor().getValor().getId());

    }

    @Test
    public void EliminarMayor() {
        ArrayList<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(1, 2, 3, 5));
        transacciones.add(new Transaccion(2, 2, 3, 8));
        transacciones.add(new Transaccion(3, 2, 3, 7));

        Heap<Transaccion> heap = new Heap<>(transacciones.size(), transacciones);
        heap.mayor().eliminarMayor(); // eliminar transaccion con monto 8

        assertEquals(7, heap.mayor().getValor().monto());
    }

    @Test
public void multiplesEmpates() {
    ArrayList<Transaccion> transacciones = new ArrayList<>();
    transacciones.add(new Transaccion(1, 2, 3, 100));
    transacciones.add(new Transaccion(2, 2, 3, 100));  
    transacciones.add(new Transaccion(3, 2, 3, 100));  
    transacciones.add(new Transaccion(4, 2, 3, 100));

    Heap<Transaccion> heap = new Heap<>(transacciones.size(), transacciones);

    // las voy borrando en orden descendente 
    assertEquals(4, heap.mayor().getValor().getId());
    heap.mayor().eliminarMayor();
    assertEquals(3, heap.mayor().getValor().getId());
    heap.mayor().eliminarMayor();
    assertEquals(2, heap.mayor().getValor().getId());
    heap.mayor().eliminarMayor();
    assertEquals(1, heap.mayor().getValor().getId());
}
}
