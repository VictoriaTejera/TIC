package um.edu.uy.interfaz.restaurante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import um.edu.uy.Main;
import um.edu.uy.persistance.ReservaMgr;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.entidades.Reserva;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

@Component
public class ControladorInicioSesionRest implements ApplicationContextAware {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnIniciarSesion;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private TextField txtUsuario;

	@Autowired
	private RestauranteMgr restauranteMgr;

	private ApplicationContext applicationContext;
	
	Restaurante restaurante;
	
	@Autowired
	private ReservaMgr resMgr;

	@FXML
    void handleSubmitButtonAction(ActionEvent event) throws IOException {
    	Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);

		if (event.getSource() == btnIniciarSesion) {
			String nombreUsuario=txtUsuario.getText();
			String contrasena=txtContrasena.getText();
			if (restauranteMgr.verificarUsuarioRestaurante(nombreUsuario, contrasena) == true) {
				stage = (Stage) btnIniciarSesion.getScene().getWindow();
				root = fxmlLoader.load(ControladorMenuRest.class.getResourceAsStream("MenuPrincipalRest.fxml"));
				//getRutRest();
			}else {
				showAlert("Lo sentimos, ", "El usuario o contraseņa son incorrectos. Vuelva a intentarlo");
			}
		}
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	String getRutRest() {
		return restauranteMgr.getRut(restaurante.getNombre(), restaurante.getPassword()); 
	}

	@FXML
	void initialize() {
		assert btnIniciarSesion != null : "fx:id=\"btnIniciarSesion\" was not injected: check your FXML file 'inicio.fxml'.";
		assert txtContrasena != null : "fx:id=\"txtContrasena\" was not injected: check your FXML file 'inicio.fxml'.";
		assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'inicio.fxml'.";
		
//		Usuario u1= new Usuario("s123", "123", Integer.parseInt("123"));
//		Restaurante r1 = new Restaurante("1234", "r1234", "", "1234");
//		Reserva res = new Reserva(u1, r1, Integer.parseInt("2"));
		//resMgr.save(123, "1234", 2);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
	public static void showAlert(String title, String contextText) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


}