package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class testHeapUsers {

    @Test
    public void MaximoSaldo() {
        ArrayList<TuplaUser> usuarios = new ArrayList<>();
        usuarios.add(new TuplaUser(1, 50));
        usuarios.add(new TuplaUser(2, 100));
        usuarios.add(new TuplaUser(3, 75));

        Heap<TuplaUser> heap = new Heap<>(usuarios.size(), usuarios);
        assertEquals(2, heap.mayor().getValor().ID());
    }

    @Test
    public void desempatePorMenorId() {
        ArrayList<TuplaUser> usuarios = new ArrayList<>();
        usuarios.add(new TuplaUser(1, 100));
        usuarios.add(new TuplaUser(3, 100));
        usuarios.add(new TuplaUser(2, 100)); // gana el de menor Id

        Heap<TuplaUser> heap = new Heap<>(usuarios.size(), usuarios);
        assertEquals(1, heap.mayor().getValor().ID());

        heap.mayor().eliminarMayor(); // elimino el mayor osea el de menor id
        assertEquals(2, heap.mayor().getValor().ID());


    }

    @Test
    public void ActualizarValorConHandle() {
        ArrayList<TuplaUser> usuarios = new ArrayList<>();
        usuarios.add(new TuplaUser(1, 10));
        usuarios.add(new TuplaUser(2, 20));
        usuarios.add(new TuplaUser(3, 40));

        Heap<TuplaUser> heap = new Heap<>(usuarios.size(), usuarios);
        Heap<TuplaUser>.Handle<TuplaUser> handle1 = heap.handlesOrdenados().get(0); // ID 1
        handle1.actualizarValor(new TuplaUser(1, 50)); // le actualizo el valor, haciendolo el maximo

        assertEquals(1, heap.mayor().getValor().ID());
        assertEquals(50, heap.mayor().getValor().monto());
    }

    @Test
    public void EliminarMayorUsuario() {
        ArrayList<TuplaUser> usuarios = new ArrayList<>();
        usuarios.add(new TuplaUser(1, 10));
        usuarios.add(new TuplaUser(2, 200));
        usuarios.add(new TuplaUser(3, 30));
        usuarios.add(new TuplaUser(4, 150));

        Heap<TuplaUser> heap = new Heap<>(usuarios.size(), usuarios);
        heap.mayor().eliminarMayor(); // se deberia borrar el id 2

        assertEquals(4, heap.mayor().getValor().ID()); // borro el mayor, deberia quedar el id 4 que es el que le sigue

        heap.mayor().eliminarMayor(); // se deberia borrar el id 4
        assertEquals(3, heap.mayor().getValor().ID());
    }

    @Test
public void bajadaYsubidaDeSaldos() {
    ArrayList<TuplaUser> usuarios = new ArrayList<>();
    usuarios.add(new TuplaUser(1, 50));
    usuarios.add(new TuplaUser(2, 100));
    usuarios.add(new TuplaUser(3, 75));

    Heap<TuplaUser> heap = new Heap<>(usuarios.size(), usuarios);
    ArrayList<Heap<TuplaUser>.Handle<TuplaUser>> handles = heap.handlesOrdenados();

    assertEquals(2, heap.mayor().getValor().ID()); // quiero que me devuelva el de mayor monto, ID = 2 

    handles.get(0).actualizarValor(new TuplaUser(1, 200)); // le actualizo el monto al usuario 1, ahora pasa a ser el maximo
    assertEquals(1, heap.mayor().getValor().ID());

    handles.get(0).actualizarValor(new TuplaUser(1, 60)); // le resto devuelta al usuario 1, el maximo deveria ser el id=2 de nuevo
    assertEquals(2, heap.mayor().getValor().ID());

    handles.get(2).actualizarValor(new TuplaUser(3, 150)); // le actualizo el saldo al usuario 3, deberia ser ahora el maximo
    assertEquals(3, heap.mayor().getValor().ID());

     heap.mayor().eliminarMayor(); // elimino el mayor id = 3
    assertEquals(2, heap.mayor().getValor().ID());
}
//  se que en berretacoin en el heap de users no se usa el eliminar, pero solo lo uso para el test
}

