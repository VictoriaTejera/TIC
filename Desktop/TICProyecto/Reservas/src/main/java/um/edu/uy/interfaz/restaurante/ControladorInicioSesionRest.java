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
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.entidades.Restaurante;

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

	@FXML
    void handleSubmitButtonAction(ActionEvent event) throws IOException {
    	Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);

		if (event.getSource() == btnIniciarSesion) {
			restaurante = new Restaurante(txtUsuario.getText(), txtContrasena.getText());
			if (restauranteMgr.verificarUsuarioRestaurante(restaurante) == true) {
				stage = (Stage) btnIniciarSesion.getScene().getWindow();
				root = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("MenuPrincipalRest.fxml"));
			}else {
				root = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("Warning.fxml"));
			}
		}
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	String getRutRest() {
		return restaurante.getRUT(); 
	}

	@FXML
	void initialize() {
		assert btnIniciarSesion != null : "fx:id=\"btnIniciarSesion\" was not injected: check your FXML file 'inicio.fxml'.";
		assert txtContrasena != null : "fx:id=\"txtContrasena\" was not injected: check your FXML file 'inicio.fxml'.";
		assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'inicio.fxml'.";
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}