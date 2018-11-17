package um.edu.uy.interfaz.restaurante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import um.edu.uy.interfaz.administrador.AdminControladorRegistro;
import um.edu.uy.interfaz.cliente.ControladorInicio;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.entidades.Restaurante;

@Controller
public class ControladorVerTotalAPagar implements ApplicationContextAware {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnVolver;

	@FXML
	private Label txtCantidad;

	@FXML
	private Label txtFechaDesde;

	@FXML
	private Label txtFechaHasta;

	ApplicationContext applicationContext;

	@Autowired
	ControladorInicioSesionRest controlador;

	@Autowired
	ControladorFechasPagoPendiente controladorFechas;

	@Autowired
	RestauranteMgr restauranteMgr;

	@FXML
	void volver(ActionEvent event) throws IOException {
		Stage stage;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		stage = new Stage();
		if (event.getSource() == btnVolver) {
			stage = (Stage) btnVolver.getScene().getWindow();
			root = fxmlLoader.load(ControladorMenuRest.class.getResourceAsStream("MenuPrincipalRest.fxml"));
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void initialize() {
		assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'VerTotalAPagar.fxml'.";
		assert txtCantidad != null : "fx:id=\"txtCantidad\" was not injected: check your FXML file 'VerTotalAPagar.fxml'.";
		assert txtFechaDesde != null : "fx:id=\"txtFechaDesde\" was not injected: check your FXML file 'VerTotalAPagar.fxml'.";
		assert txtFechaHasta != null : "fx:id=\"txtFechaHasta\" was not injected: check your FXML file 'VerTotalAPagar.fxml'.";

		Restaurante rest = restauranteMgr.find(controlador.getRutRestaurante());

		txtCantidad.setText(rest.getRUT());
		txtFechaDesde.setText(controladorFechas.getFechaInicio());
		txtFechaHasta.setText(controladorFechas.getFechaFin());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
