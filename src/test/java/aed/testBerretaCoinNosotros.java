package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class testBerretaCoinNosotros {
@Test
public void bloqueVacio() {
    Berretacoin sistema = new Berretacoin(3); 
    Transaccion[] bloque = new Transaccion[0]; // Bloque vacío

    sistema.agregarBloque(bloque); 

    // todos tienen 0, así que el menor id es el máximo tenedor
    assertEquals(1, sistema.maximoTenedor());
    assertEquals(0, sistema.montoMedioUltimoBloque());
    Transaccion[] resultado = sistema.txUltimoBloque();
    assertEquals(0, resultado.length);
    
}


    @Test
    public void hackeoDosConIgualMonto() {
        Berretacoin sistema = new Berretacoin(2);
        Transaccion[] bloque = {new Transaccion(0, 0, 1, 1),
                            new Transaccion(1, 1, 2, 1)};
        sistema.agregarBloque(bloque);
        sistema.hackearTx();        
        Transaccion[] resultado = sistema.txUltimoBloque();
        assertEquals(1, resultado.length);
        assertEquals(0, sistema.montoMedioUltimoBloque());
    }

    @Test
    public void mayorValorDesempataPorId() {
        Berretacoin sistema = new Berretacoin(3);
        Transaccion[] bloque1 = {
            new Transaccion(0, 0, 1, 1),
            new Transaccion(1, 1, 2, 5),
            new Transaccion(2, 2, 3, 5)
        };

        sistema.agregarBloque(bloque1);
        // el mayor id entre las de monto máximo es el 2
        assertEquals(new Transaccion(2, 2, 3, 5), sistema.txMayorValorUltimoBloque());
    }

    @Test
public void HackeoConEmpatesEnSaldo() {
    Berretacoin berretacoin = new Berretacoin(4);

    Transaccion[] bloque1 = {
        new Transaccion(0, 0, 1, 2), 
        new Transaccion(1, 1, 2, 2)  
    };
    berretacoin.agregarBloque(bloque1);

    Transaccion[] bloque2 = {
        new Transaccion(2, 0, 3, 2), 
        new Transaccion(3, 2, 4, 2) 
    };
    berretacoin.agregarBloque(bloque2);

    assertEquals(3, berretacoin.maximoTenedor()); 

    berretacoin.hackearTx(); 

    assertEquals(2, berretacoin.maximoTenedor()); 

    berretacoin.hackearTx(); 

    assertEquals(2, berretacoin.maximoTenedor());
}
    

}
