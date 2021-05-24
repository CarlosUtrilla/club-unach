
import Classes.Usuario;
import Cliente.ArrayOfAnyType;
import Cliente.ClubUnach;
import Cliente.ClubUnachSoap;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author itoma
 */
public class SesionController implements Initializable {
    //Conexion al web service
      private final ClubUnach ws = new ClubUnach();
    private final ClubUnachSoap cliente = ws.getClubUnachSoap();
    //Estos campos son pata inicio de sesion
    @FXML
    private Tab InicioTab;
    @FXML
    private JFXTextField TxtUsuario;    
    @FXML
    private JFXPasswordField TxtPassword;
    //Estos campos son para el registro
    @FXML
    private Tab RegistroTab;
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
    private JFXTabPane TabRoot;
   
    @FXML
    private Pane PantallaCarga;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void IniciarSesion() throws IOException {
        String usuario = TxtUsuario.getText();
        String password = TxtPassword.getText();
        PantallaCarga.setVisible(true);
         List<ArrayOfAnyType> lista = cliente.iniciarSesion(usuario, password).getArrayOfAnyType();
         System.out.print(lista.size());
         
         if(lista.size()==1){
            List<Object> sublista = lista.get(0).getAnyType();
            int id = (int) sublista.get(0);
            String nombre =(String) sublista.get(1);
            String telefono = (String) sublista.get(2);
            String correo = (String) sublista.get(3);
            String direccion = (String) sublista.get(4);
            String contraseña = (String) sublista.get(5);

            Usuario.createUsuarioSesion(id, nombre, direccion, telefono, correo, contraseña);
            System.out.print("Se creo usuario");

            
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    Platform.runLater(()->{
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(SesionController.this.getClass().getResource("Launcher.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Club UNACH Tienda Virtual");
                            stage.setScene(new Scene(root1));
                            stage.show();
                            Stage st = (Stage) TabRoot.getScene().getWindow();
                            st.close();
                        }catch (IOException ex) {
                            Logger.getLogger(SesionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } catch (InterruptedException ex) {
                    Logger.getLogger(SesionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }).start();              
         }else{
             PantallaCarga.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Usuario o contraeña incorrectos");
            alert.setContentText("No se encontro ninguna cuenta con estas credenciales. Si aun no tiene una cuenta puede registrarse.");
            alert.showAndWait();
            
         }
    }
  
    @FXML
    private void CrearCuenta(MouseEvent event) {
        TabRoot.getSelectionModel().select(RegistroTab);
    }

    @FXML
    private void Registrarse(MouseEvent event) {
        PantallaCarga.setVisible(true);
        String nombre = TxtNombre.getText();
        String telefono = TxtTelefono.getText();
        String correo = TxtCorreo.getText();
        String direccion = TxtDireccion.getText();
        String contraseña = TxtContraseña.getText();
        if(contraseña!="" && nombre!="" && telefono!="" && correo!=""){
            cliente.agregarCliente(nombre, telefono, correo, direccion, contraseña);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Se creo su cuenta exitosamente");
            alert.setContentText("Ya puede iniciar sesión");
            alert.showAndWait();
            TabRoot.getSelectionModel().select(InicioTab);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Campos vacios");
            alert.setContentText("No puede dejar campos vacios, por favor rellene los campos faltantes");
            alert.showAndWait();
        }
        
        PantallaCarga.setVisible(false); 
    }

    @FXML
    private void Regresar(MouseEvent event) {
        TabRoot.getSelectionModel().select(InicioTab);
        TxtNombre.setText("");
        TxtTelefono.setText("");
        TxtDireccion.setText("");
        TxtCorreo.setText("");
        TxtContraseña.setText("");
        
    }

    @FXML
    private void KeyPassword(KeyEvent event) throws IOException {
        if(event.getCode().equals(KeyCode.ENTER)){
            IniciarSesion();
        }
    }
    
}
