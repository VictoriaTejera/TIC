package um.edu.uy.interfaz.administrador;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import um.edu.uy.interfaz.cliente.ControladorInicio;
import um.edu.uy.interfaz.cliente.clasesAuxiliares.RestauranteAUX;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.entidades.Restaurante;

@Component
public class AdminControladorRegistro implements ApplicationContextAware {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnRegistrar;

	@FXML
	private TextField txtContrasena;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtTelefono;

	@FXML
	private TextField txtRut;

	@Autowired
	private RestauranteMgr resMgr;

	ApplicationContext applicationContext;

	public AdminControladorRegistro() {
		super();
	}

	@FXML
	void handleSubmitButtonAction(ActionEvent event) throws IOException {
		Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);

		if (event.getSource() == btnRegistrar) {
			Restaurante restaurante = null;
			try {
				restaurante = new Restaurante(txtRut.getText(), txtNombre.getText(),
						Integer.parseInt(txtTelefono.getText()), txtContrasena.getText());
			} catch (NumberFormatException e) {
				showAlert("Lo sentimos, ", "Ingrese un telefono valido");
			}
			if (resMgr.restauranteYaFueCreado(restaurante) == false) {
				resMgr.save(restaurante);
				root = fxmlLoader.load(AdminControladorRegistro.class.getResourceAsStream("final.fxml"));
				stage = (Stage) btnRegistrar.getScene().getWindow();
			} else {
				showAlert("Lo sentimos, ", "El restaurante ya ha sido creado.");
			}
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void initialize() {
		assert btnRegistrar != null : "fx:id=\"btnRegistrar\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtContrasena != null : "fx:id=\"txtContrasena\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtTelefono != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtRut != null : "fx:id=\"txtRut\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static void showAlert(String title, String contextText) {
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
				javafx.scene.control.Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contextText);
		alert.showAndWait();
	}

}