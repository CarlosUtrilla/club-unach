package Classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author itoma
 */
public class Producto {
    private int id;
    private String nombre;
    private int precio;
    private String codigo;
    private String marca;
    private int stock;
    
    public Producto(int id, String nombre, int precio, String codigo, String marca, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
        this.marca = marca;
        this.stock = stock;
    }

   
    public int getId() {
        return id;
    }

    
    public String getNombre() {
        return nombre;
    }

   
    public int getPrecio() {
        return precio;
    }

    public String getCodigo() {
        return codigo;
    }

    

   
    public String getMarca() {
        return marca;
    }

    
    public int getStock() {
        return stock;
    }
    
    
}
