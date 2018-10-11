package um.edu.uy.interfaz.administrador;

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
import um.edu.uy.persistance.UsuarioMgr;
import um.edu.uy.persistance.entidades.Restaurante;
import um.edu.uy.persistance.entidades.Usuario;

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
	private TextField txtEmail;

	@FXML
	private TextField txtRut;

	private ApplicationContext applicationContext;

	public AdminControladorRegistro() {
	}

	@FXML
	void handleSubmitButtonAction(ActionEvent event) throws Exception {
		Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		stage = new Stage();

		if (event.getSource() == btnRegistrar) {
			Restaurante restaurante = new Restaurante(tstRut.getText(), tstNombre.getText(), tst)
			// Usuario user = new Usuario(txtNombre.getText(), txtContrasena.getText(),
			// Integer.parseInt(txtCelular.getText()));

			// if (usuMgr.usuarioYaFueCreado(user) == false) {
			// usuMgr.save(user);
			// root =
			// fxmlLoader.load(ControladorInicioSesion.class.getResourceAsStream("iniciarSesion.fxml"));
			// stage = (Stage) btnConfirmarRegistro.getScene().getWindow();
			// } else {
			// root =
			// fxmlLoader.load(ControladorInicioSesion.class.getResourceAsStream("UsuarioYaExiste.fxml"));
			// }
		}
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void initialize() {
		assert btnRegistrar != null : "fx:id=\"btnRegistrar\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtContrasena != null : "fx:id=\"txtContrasena\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
		assert txtRut != null : "fx:id=\"txtRut\" was not injected: check your FXML file 'RegistrarRestaurante.fxml'.";
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}