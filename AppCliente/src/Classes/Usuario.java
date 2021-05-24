
package Classes;


public class Usuario {
    private final int Id;
    private String Nombre;
    private String Direccion;
    private String Telefono;
    private String Contraseña;
    private String CorreoElectronico;
    private static Usuario objeto;
    
    private Usuario(int Id, String Nombre, String Direccion, String Telefono, String CorreoElectronico, String Contraseña) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Contraseña = Contraseña;
        this.CorreoElectronico = CorreoElectronico;
    }
    
    public static void createUsuarioSesion(int Id, String Nombre, String Direccion, String Telefono, String CorreoElectronico, String Contraseña){

            objeto = new Usuario(Id,Nombre,Direccion,Telefono,CorreoElectronico,Contraseña);    
        
    }
    public static Usuario getUsuario(){       
        
        return objeto;
    }
    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

   

    /**
     * @return the CorreoElectronico
     */
    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    /**
     * @return the Contraseña
     */
    public String getContraseña() {
        return Contraseña;
    }
    
    
}
