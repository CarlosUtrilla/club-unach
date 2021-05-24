import Classes.Carrito;
import Classes.Producto;
import Classes.Usuario;
import Cliente.ArrayOfAnyType;
import Cliente.ClubUnach;
import Cliente.ClubUnachSoap;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomInUp;
import animatefx.animation.ZoomOutDown;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author itoma
 */
public class LauncherController implements Initializable {

    
    
    private final ClubUnach ws = new ClubUnach();
    private final ClubUnachSoap conector = ws.getClubUnachSoap();
     Usuario user = Usuario.getUsuario();
    
    @FXML
    private TextField ProductoTxt;
    
    //Tabla Productos columnas
    @FXML
    private TableView<Producto> TablaProductos;
    @FXML
    private TableColumn<Producto, String> NombreCol;
    @FXML
    private TableColumn<Producto, String> PrecioCol;
    @FXML
    private TableColumn<Producto, String> MarcaCol;
    @FXML
    private TableColumn<Producto, String> CantidadCol;
    @FXML
    private TableColumn<Producto, String> CodigoCol;
    
    //Elementos formProducto para agregar productos y editarlos
    @FXML
    private AnchorPane FormProducto;
    @FXML
    private TextField NombreProductoTxt;
    @FXML
    private TextField MarcaTxt;
    @FXML
    private TextField PrecioTxt;
    @FXML
    private TextField CodigoTxt;
    @FXML
    private TextField CantidadTxt;
    @FXML
    private TextField IdProductoTxt;
    @FXML
    private Label LblFormProducto;
    
    //Carrito de compras
    @FXML
    private TableView<Carrito> TablaCarrito;
    @FXML
    private TextField CantidadCarritoTxt;
    @FXML
    private TableColumn<Carrito, String> ProductoCarritoCol;
    @FXML
    private TableColumn<Carrito, String> PrecioCarritoCol;
    @FXML
    private TableColumn<Carrito, String> TotalCarritoCol;
    @FXML
    private TableColumn<Carrito, String> CantidadCarritoCol;
    @FXML
    private Label TotalLbl;
    @FXML
    private JFXTabPane TbView;
    @FXML
    private Label LabelNombre;
    @FXML
    private Label LabelDireccion;
    @FXML
    private Label LabelTelefono;
    @FXML
    private Label LabelCorreo;
      @FXML
    private Pane PaneCarga;
    @FXML
    private Label LblId;
    @FXML
    private Label LblTelefono;
    @FXML
    private Label LblDireccion;
    @FXML
    private Label LblCorreo;
    @FXML
    private Label LblNombre;
    @FXML
    private AnchorPane FormUsuario;
    @FXML
    private JFXTextField TxtNombre;
    @FXML
    private JFXTextField TxtTelefono;
    @FXML
    private JFXTextField TxtDireccion;
    @FXML
    private JFXTextField TxtCorreo;
    @FXML
    private JFXPasswordField TxtContraseña;
    @FXML
    private Tab TabProducto;
   
    
    
    
  
  
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargaInfoUsuario();
       TotalLbl.setText("Total a pagar: $"+TotalCarrito);
        
       CantidadCarritoTxt.setText("0");
       //Columnas para producto
        NombreCol.setCellValueFactory(
                new PropertyValueFactory("Nombre"));
     
        PrecioCol.setCellValueFactory(
                new PropertyValueFactory("Precio"));
     
        MarcaCol.setCellValueFactory(
                new PropertyValueFactory("Marca")); 
         
        CantidadCol.setCellValueFactory(
                new PropertyValueFactory("Stock"));        
 
        CodigoCol.setCellValueFactory(
                new PropertyValueFactory("Codigo")); 
        //Columnas para carrito
        ProductoCarritoCol.setCellValueFactory(
                new PropertyValueFactory("Nombre"));
     
        PrecioCarritoCol.setCellValueFactory(
                new PropertyValueFactory("Precio"));
     
        TotalCarritoCol.setCellValueFactory(
                new PropertyValueFactory("Total")); 
          
        CantidadCarritoCol.setCellValueFactory(
                new PropertyValueFactory("Cantidad")); 
        CargarTablaProductos();
        
        TablaProductos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Obtiene la fila seleccionada y el objeto producto dentro de ella
                   TablePosition pos = TablaProductos.getSelectionModel().getSelectedCells().get(0);
                   int row = pos.getRow();
                   Producto current = TablaProductos.getItems().get(row);
                   
                   //Rellena los campos del form antes abrir
                   IdProductoTxt.setText(""+current.getId());
                   NombreProductoTxt.setText(current.getNombre());
                   MarcaTxt.setText(current.getMarca());
                    PrecioTxt.setText(""+current.getPrecio());
                    CodigoTxt.setText(current.getCodigo());
                    CantidadTxt.setText(""+current.getStock());
                    //Abre el form en modo actualizacion
                    ProductoUpdate= true;
                    LblFormProducto.setText("Editar Producto");
                    FormProducto.setVisible(true);
                   ZoomInUp zoom = new ZoomInUp(FormProducto);
                   zoom.setSpeed(2);
                   zoom.play();
                    zoom.setOnFinished((ActionEvent event) -> {
                            FormProducto.setStyle("-fx-background-color:  rgba(85, 85, 85, 0.7);");
                       });
                   
                }
            }
        });
        
        
    }    
   
    void CargarTablaProductos(){     
        //Descarga los productos del web service y los pone en una lista
        List<ArrayOfAnyType> lista = conector.listaProductos(ProductoTxt.getText()).getArrayOfAnyType();
        
        //Esta Lista tiene todos los productos
        ObservableList<Producto> ListaProductos = FXCollections.observableArrayList();
        
        //Ciclo para pasar de la lista a la ListaProductos todos los prodoctos
        for(int i = 0; i<lista.size();i++){
            List<Object> sublista = lista.get(i).getAnyType();
            int id = (int) sublista.get(0);
            String nombre = (String) sublista.get(1);
            int precio = (int) sublista.get(2);
            String codigo = (String) sublista.get(3);
            String marca = (String) sublista.get(4);
            int stock = (int) sublista.get(5);
            
            ListaProductos.add(new Producto(id,nombre,precio,codigo,marca,stock));
        }
        TablaProductos.setItems(ListaProductos);
    }

    
    @FXML
    private void BuscarProducto(KeyEvent event) {
            CargarTablaProductos();
    }
    boolean ProductoUpdate;
    @FXML
    private void AbrirFormProducto(MouseEvent event) {
        LblFormProducto.setText("Agregar Producto");
       FormProducto.setVisible(true);
        ZoomInUp zoom = new ZoomInUp(FormProducto);
                   zoom.setSpeed(2);
                   zoom.play();
                    zoom.setOnFinished((ActionEvent e) -> {
                            FormProducto.setStyle("-fx-background-color:  rgba(85, 85, 85, 0.7);");
                       });
       ProductoUpdate = false;
    }

    @FXML
    private void SubirProducto(MouseEvent event) {
        String nombre = NombreProductoTxt.getText();
        String marca = MarcaTxt.getText();
        int precio = Integer.parseInt(PrecioTxt.getText());
        String codigo = CodigoTxt.getText();
        int cantidad = Integer.parseInt(CantidadTxt.getText());
        
        if(ProductoUpdate){
            int id = Integer.parseInt(IdProductoTxt.getText());
            conector.editarProducto(id, nombre, precio, codigo, marca, cantidad);
        }else{
            
            conector.agregarProducto(nombre, precio, codigo, marca, cantidad);
        }
        ProductoUpdate = true;
        CerrarFormProducto();
        ProductoTxt.setText("");
        CargarTablaProductos();
    }

    @FXML
    private void CerrarFormProducto() {
         NombreProductoTxt.setText("");
         MarcaTxt.setText("");
         PrecioTxt.setText("");
         CodigoTxt.setText("");
         CantidadTxt.setText("");
         FormProducto.setStyle("-fx-background-color:  rgba(85, 85, 85, 0);");
         ZoomOutDown fade = new ZoomOutDown(FormProducto);
         fade.setSpeed(2);
         fade.setOnFinished((ActionEvent event) -> {
             FormProducto.setVisible(false);
             if(ProductoUpdate)
             new Pulse(TablaProductos).play();
         });
         fade.play();
         
    }
   ObservableList<Carrito> carrito = FXCollections.observableArrayList();
   int TotalCarrito = 0;
    @FXML
    private void AgregarCarrito(MouseEvent event) {
             //Extrae el producto seleccionado de la tabla productos
             
            TablePosition pos = TablaProductos.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Producto p = TablaProductos.getItems().get(row);
            int cantidad = Integer.parseInt(CantidadCarritoTxt.getText()); 
            
            if(cantidad>0 && cantidad<=p.getStock()){
                
                Carrito c = new Carrito(p,cantidad);
                int posicion = -1;
                for(int i = 0 ; i < carrito.size(); i++){
                    if(carrito.get(i).getId() == c.getId()){
                        posicion = i;
                    }
                }
                
                
                if(posicion==-1){
                    //Agregrega el producto al carrito
                    carrito.add(c);  
                    TablaCarrito.setItems(carrito);
                    
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Se agrego su producto al carrito");
                    alert.setTitle("Carrito de compras");
                    alert.showAndWait();
                }else{
                    carrito.set(posicion, c);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Se actualizo su carrito");
                    alert.setTitle("Carrito de compras");
                    alert.showAndWait();
                }
                TotalCarrito = 0;
                for(int i = 0; i < carrito.size(); i++){
                    TotalCarrito += carrito.get(i).getTotal();
                }
                TotalLbl.setText("Total a pagar: $"+TotalCarrito);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Agrege una cantidad correcta");
                alert.setTitle("Carrito de compras");
                alert.setContentText("La cantidad que agrego puede ser 0 o menor a la cantidad de productos en almacen. "
                        + "Por favor agrege una cantidad correcta e intente de nuevo.");
                alert.showAndWait();
            }
            
            
    }

    @FXML
    private void RealizarVenta(MouseEvent event) {
        PaneCarga.setVisible(true);
        if(!carrito.isEmpty()){
        new Thread(() -> {
         Platform.runLater(()->{
            Date fecha = new Date();
            DateFormat formato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            String productos = "";
            
            for (int i = 0; i < carrito.size(); i++) {
                productos+= carrito.get(i).getCantidad()+":"+carrito.get(i).getId()+":";
            }
            productos = productos.substring(0, productos.length() - 1);


            String ticket = "CLUB UNACH\n" +
                    "===============================\n" +
                    "Dirección: Blvd. Belisario Domínguez, "+
                    "Sin Nombre, Teran 29050, Tuxtla Gutiérrez, Chis.\n" +
                    "Rector: Carlos Faustino Natarén Nandayapa\n" +
                    "Teléfono: 961 615 0440\n"+
                    formato.format(fecha)+ "\n" +
                    "===============================\n"
                    +"CANT.   ARTICULO   PRE.UNIT   TOTAL\n";



            for(int i = 0; i<carrito.size();i++){
                ticket += carrito.get(i).getCantidad()
                        +"    "+carrito.get(i).getNombre()
                        +"    $" +carrito.get(i).getPrecio()
                        +"    $"+carrito.get(i).getTotal()
                        +"\n";
            }

            ticket+="===============================\n" +
                    "TOTAL DE COMPRA $" + TotalCarrito+"\n" +
                    "NOMBRE DEL CLIENTE: " +user.getNombre()+"\n"+
                    "!DISFRUTE SU COMPRA¡. VUELVA PRONTO\n"+
                    "===============================\n" +
                    "POR LA CONCIENCIA DE LA NECECIDAD DE SERVIR";

            System.out.println(""+user.getId());
            System.out.println(""+TotalCarrito);
            System.out.println(ticket);
            System.out.println(productos);
            conector.vender(user.getId(),TotalCarrito, ticket, productos);
            
            TotalCarrito = 0;
            TotalLbl.setText("Total a pagar: $"+TotalCarrito);
            CargarTablaProductos();
            carrito.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Su compra se realizo con exito");
                alert.setTitle("Carrito de compras");
                alert.setContentText("Muchas gracias por su compra. Regrese pronto a Club UNACH!");
                alert.showAndWait();
                
            PaneCarga.setVisible(false);
            new Pulse(TablaCarrito).play();
        });

        }).start();  
     }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Su carrito esta vacio");
                alert.setTitle("Carrito de compras");
                alert.setContentText("Antes de realizar una compra, primero debe tener productos en su carrito");
                alert.showAndWait();
                 PaneCarga.setVisible(false);
    }
    }

    @FXML
    private void AbrirHistorial(MouseEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("Historial.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Club UNACH Tienda Virtual");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();

    }

    @FXML
    private void AbrirFormUsuario(MouseEvent event) {
        TxtNombre.setText(user.getNombre());
        TxtCorreo.setText(user.getCorreoElectronico());
        TxtDireccion.setText(user.getDireccion());
        TxtTelefono.setText(user.getTelefono());
        TxtContraseña.setText(user.getContraseña());
        FormUsuario.setVisible(true);
        ZoomInUp zoom = new ZoomInUp(FormUsuario);
        zoom.setSpeed(2);
        zoom.play();
         zoom.setOnFinished((ActionEvent e) -> {
                 FormUsuario.setStyle("-fx-background-color:  rgba(85, 85, 85, 0.7);");
            });
    }

    @FXML
    private void CerrarFormUsuario() {
        FormUsuario.setStyle("-fx-background-color:  rgba(85, 85, 85, 0);");
         ZoomOutDown fade = new ZoomOutDown(FormUsuario);
         fade.setSpeed(2);
         fade.setOnFinished((ActionEvent e) -> {
             FormUsuario.setVisible(false);             
         });
         fade.play();
    }

    @FXML
    private void ActualizaUsuario(MouseEvent event) {
        PaneCarga.setVisible(true);
        
        String nombre = TxtNombre.getText();
        String telefono = TxtTelefono.getText();
        String direccion = TxtDireccion.getText();
        String correo = TxtCorreo.getText();
        String contraseña = TxtContraseña.getText();
        int id = user.getId();
        
        conector.actualizarCliente(id, nombre, telefono, correo, direccion, contraseña);
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Su actualizo su información");
                alert.setTitle("Usuario Club UNACH");
                alert.setContentText("La información de su perfil fue actualizada con exito.");
                alert.showAndWait();
        Usuario.createUsuarioSesion(id, nombre, direccion, telefono, correo, contraseña);        
        user = Usuario.getUsuario();
        CargaInfoUsuario();
        PaneCarga.setVisible(false);    
        CerrarFormUsuario();
    }

     void CargaInfoUsuario(){
       LabelNombre.setText(user.getNombre());
       LabelDireccion.setText(user.getDireccion());
       LabelTelefono.setText(user.getTelefono());
       LabelCorreo.setText(user.getCorreoElectronico());
      
       LblId.setText(""+user.getId());
       LblTelefono.setText(user.getTelefono());
       LblCorreo.setText(user.getCorreoElectronico());
       LblDireccion.setText(user.getDireccion());
       LblNombre.setText(user.getNombre());
    }

    @FXML
    private void SelectProduct(MouseEvent event) {
        
        TbView.getSelectionModel().select(1);
    }

    @FXML
    private void SelectCart(MouseEvent event) {
        TbView.getSelectionModel().select(2);
    }

    @FXML
    private void SelectUser(MouseEvent event) {
        TbView.getSelectionModel().select(3);
    }
    
}
