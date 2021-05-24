/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Classes.Usuario;
import Cliente.ArrayOfAnyType;
import Cliente.ClubUnach;
import Cliente.ClubUnachSoap;
import animatefx.animation.Flip;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author itoma
 */
public class HistorialController implements Initializable {

    
    
      private final ClubUnach ws = new ClubUnach();
    private final ClubUnachSoap cliente = ws.getClubUnachSoap();
    Usuario user = Usuario.getUsuario();
    List<ArrayOfAnyType> lista = cliente.historialCompras(user.getId()).getArrayOfAnyType();
    
    @FXML
    private Text ticket;
    
    int pos = 0;
    @FXML
    private JFXButton BtnAnt;
    @FXML
    private JFXButton BtnSig;
    @FXML
    private Label TxtTicket;
    @FXML
    private ScrollPane Pane;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(lista.size());
        if(lista.size()>0){
            CompruebaBtn();
             TxtTicket.setText("Ticket " + (pos+1) + " de " +lista.size());
            List<Object> sublista = lista.get(pos).getAnyType();
            String texto = (String) sublista.get(3);
            ticket.setText(texto);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Usted aun no tiene ninguna compra");
            alert.setContentText("No se puede mostrar su historial porque aun no tiene compras hechas.");
            alert.showAndWait();
            Stage st = (Stage) BtnSig.getScene().getWindow();
                            st.close();
        }
       
    }    
    void MostrarTicket(){
        CompruebaBtn();
         new Flip(Pane).setSpeed(3).play();  
        TxtTicket.setText("Ticket " + (pos+1) + " de " +lista.size());
        List<Object> sublista = lista.get(pos).getAnyType();
        String texto = (String) sublista.get(3);
        
             
        ticket.setText(texto);
    }

    @FXML
    private void Ant(MouseEvent event) {
        pos--;        
        System.out.println(pos);
        MostrarTicket();
    }

    @FXML
    private void Sig(MouseEvent event) {
        pos++;       
        System.out.println(pos);
        MostrarTicket();        
    }
    
    void CompruebaBtn(){
        if(pos>0){
            BtnAnt.setVisible(true);
        }else{
            BtnAnt.setVisible(false);
        }
        
        if(pos>=lista.size()-1){
            BtnSig.setVisible(false);
        }else if(pos<lista.size()-1){
            BtnSig.setVisible(true);
        }
    }
    
}
