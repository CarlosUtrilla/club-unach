
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author itoma
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sesion.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Iniciar sesión");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
}
