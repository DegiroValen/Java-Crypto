package aed;
import java.util.ArrayList;

public class Berretacoin {
    private ListaEnlazada<Bloque> cadena;

    private ArrayList<Heap<TuplaUser>.Handle<TuplaUser>> users;  // un arraylist de Handle<TuplaUser>
    private Heap<TuplaUser> usersxmonto; //HEAP

    private Heap<Transaccion> bloquexmonto; //HEAP
    //Por handle esta conectado al ultimo bloque
    
    public Berretacoin(int n_usuarios){
        this.cadena = new ListaEnlazada<>(); //Creo la cadena vacía

        ArrayList<TuplaUser> usuarios = new ArrayList<>(n_usuarios);//Creo el array con usuarios

        for (int i=1;i <= n_usuarios;i++){
            usuarios.add(new TuplaUser(i,0));
        }

        usersxmonto = new Heap<TuplaUser>(n_usuarios, usuarios); //Creo un heap con users
        this.users = usersxmonto.handlesOrdenados(); // Retorno el users con handle, creado en la clase Heap 
        
    }

    public void agregarBloque(Transaccion[] transacciones){
        
        Bloque bloque = new Bloque(); //Creo la lista enlazada donde van a entrar las transacciones
        int posicion; //Reinicio la variable posicion, la uso nada mas para hacer mas inteligible el codigo
        ArrayList<Transaccion> trans_copia = new ArrayList<>(transacciones.length);//Una lista en orden para crear el heap

        for (int i=0;i< transacciones.length;i++){//Complejidad O(n), aca itero las transacciones una por una
            bloque.agregarTrans(transacciones[i]);//obtengo el handle al agregar en el bloque

            posicion = transacciones[i].id_vendedor()-1; //Posicion del vendedor
            Heap<TuplaUser>.Handle<TuplaUser> handle_vendedor = users.get(posicion); //obtengo el handle del heap con la pos del vendedor
            TuplaUser vendedor = handle_vendedor.getValor(); // obtengo la tupla de dicho vendedor
            handle_vendedor.actualizarValor(new TuplaUser(vendedor.ID(), vendedor.monto() + transacciones[i].monto()));
            //Actualizo el user del vendedor
            
            if (transacciones[i].id_comprador() != 0){//verifico que no sea de creacion

            posicion = transacciones[i].id_comprador()-1; //Posicion del comprador
            Heap<TuplaUser>.Handle<TuplaUser> handle_comprador = users.get(posicion);
            TuplaUser comprador = handle_comprador.getValor();
            handle_comprador.actualizarValor(new TuplaUser(comprador.ID(), comprador.monto() - transacciones[i].monto()));
            //Actualizo user del comprador

            //mediaUltBloq.editar(transacciones[i].monto(),1);//Agrego el monto usado y una transaccion a la cantidad -> ya no necesitamos, esta en Bloque
            }
            trans_copia.add(transacciones[i]);//Guardo la copia en orden
        }

        bloquexmonto = new Heap<Transaccion>( transacciones.length , trans_copia); //Creo el heap con las transacciones, ordenado por monto

        //mediaUltBloq = new TuplaUser( mediaUltBloq.ID(),numTrans-1,0); comentario: no se para que está¿
        cadena.agregarAdelante(bloque);// hay que ver si esta bien el agregar adelante devolviendo handle, si no va el void
    }

    public Transaccion txMayorValorUltimoBloque(){
        return bloquexmonto.mayor().getValor();//Simplemente me quedo que el elemento de arriba del Heap
    }

    public Transaccion[] txUltimoBloque(){
        Bloque ultBloque = cadena.obtenerPrimero(); //Tomo el ultimo elemento
        Bloque copia = new Bloque(ultBloque); //Lo copio para evitar aliassing  -> Complejidad O(cant transacciones a copiar)

    int longitudReal = ultBloque.longitud(); // o lo que sea para acceder a la cantidad real
    Transaccion[] bloque = new Transaccion[longitudReal];

        for (int i=0;i< longitudReal;i++){ //Itero los elementos uno por uno
            bloque[i] = copia.obtenerPrimerTrans();//Agarro el nodo de la posicion actual
            copia.eliminarPrimerTrans(cadena.longitud()); //Paso al siguiente
        }
        //El bucle tiene complejidad O(n) porque itero los elementos uno por uno        
        
        return bloque;
    }



    public int maximoTenedor(){
        return usersxmonto.mayor().getValor().ID();//Me quedo con la ID del elemento de mas arriba del heap
    }

public int montoMedioUltimoBloque() {
    return cadena.obtenerPrimero().mediaBloque();
}


public void hackearTx() {
    Heap<Transaccion>.Handle<Transaccion> mayorTrans = bloquexmonto.mayor();
    int posicion;

    // Vendedor
    posicion = mayorTrans.getValor().id_vendedor() - 1;
    Heap<TuplaUser>.Handle<TuplaUser> handle_vendedor = users.get(posicion);
    TuplaUser vendedor = handle_vendedor.getValor();
    handle_vendedor.actualizarValor(
        new TuplaUser(vendedor.ID(), vendedor.monto() - mayorTrans.getValor().monto())
    );

    // Comprador (si no es transacción de creación)
    if (mayorTrans.getValor().id_comprador() != 0) {
        posicion = mayorTrans.getValor().id_comprador() - 1;
        Heap<TuplaUser>.Handle<TuplaUser> handle_comprador = users.get(posicion);
        TuplaUser comprador = handle_comprador.getValor();
        handle_comprador.actualizarValor(
            new TuplaUser(comprador.ID(), comprador.monto() + mayorTrans.getValor().monto())
        );
    }

    // Eliminar del heap de transacciones
    mayorTrans.eliminarMayor();

    // Eliminar también del bloque
    cadena.obtenerPrimero().eliminar(mayorTrans);

    // Decrementar conteo
}

}


