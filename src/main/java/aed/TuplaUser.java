package  aed;
public class TuplaUser implements Comparable<TuplaUser>{
    private int ID;
    private int monto;
    // al guardar el indice, se rompe el encapsulamiento porque ya asumis que el heap esta hecho sobre un arreglo

    public TuplaUser(int ID, int monto){
        this.ID = ID;
        this.monto = monto;
    }
    
    public int ID(){
        return ID;
    }
    public int monto(){
        return monto;
    }


    @Override
    public int compareTo(TuplaUser otro) {
        if (this.monto != otro.monto){return this.monto - otro.monto;}
        return otro.ID- this.ID;
    }


}
