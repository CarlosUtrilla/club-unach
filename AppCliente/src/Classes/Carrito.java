
package Classes;

import java.util.ArrayList;

public class Carrito extends Producto{
    private int Cantidad;
    private int Total;
  
    public Carrito(Producto p, int Cant) {
        super(p.getId(), p.getNombre(), p.getPrecio(), p.getCodigo(), p.getMarca(), p.getStock());
        this.Cantidad = Cant;
        this.Total = p.getPrecio()*Cant;
    }

   
    public int getCantidad() {
        return Cantidad;
    }

   
    public int getTotal() {
        return Total;
    }
  
   
  
   
}
