package um.edu.uy.interfaz.administrador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.BeansException;
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

@Component
public class AdminControladorPagosPendientes implements ApplicationContextAware {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnVerPagosPendientes;

	@FXML
	private TextField txtAnioFin;

	@FXML
	private TextField txtAnioInicio;

	@FXML
	private TextField txtDiaFin;

	@FXML
	private TextField txtDiaInicio;

	@FXML
	private TextField txtMesFin;

	@FXML
	private TextField txtMesInicio;

	ApplicationContext applicationContext;

	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	@FXML
	void verPagosPendientes(ActionEvent event) throws IOException {
		Stage stage;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		stage = new Stage();
		if (event.getSource() == btnVerPagosPendientes) {
			try {
				fechaInicio = LocalDate.of(Integer.parseInt(txtAnioInicio.getText()), Integer.parseInt(txtMesInicio.getText()), Integer.parseInt(txtDiaInicio.getText()));
				fechaFin = LocalDate.of(Integer.parseInt(txtAnioFin.getText()), Integer.parseInt(txtMesFin.getText()), Integer.parseInt(txtDiaFin.getText()));
			} catch (NumberFormatException e) {
				showAlert("Lo sentimos, ", "Ingrese una fecha de inicio y de finalizacion.");
			}

			stage = (Stage) btnVerPagosPendientes.getScene().getWindow();
			root = fxmlLoader.load(AdminControladorRegistro.class.getResourceAsStream("verPagosPendientes.fxml"));
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void initialize() {
		assert btnVerPagosPendientes != null : "fx:id=\"btnVerPagosPendientes\" was not injected: check your FXML file 'verPagosPendientes.fxml'.";
		assert txtAnioFin != null : "fx:id=\"txtAnioFin\" was not injected: check your FXML file 'recibirFechasParaPagosPendientes.fxml'.";
		assert txtAnioInicio != null : "fx:id=\"txtAnioInicio\" was not injected: check your FXML file 'recibirFechasParaPagosPendientes.fxml'.";
		assert txtDiaFin != null : "fx:id=\"txtDiaFin\" was not injected: check your FXML file 'recibirFechasParaPagosPendientes.fxml'.";
		assert txtDiaInicio != null : "fx:id=\"txtDiaInicio\" was not injected: check your FXML file 'recibirFechasParaPagosPendientes.fxml'.";
		assert txtMesFin != null : "fx:id=\"txtMesFin\" was not injected: check your FXML file 'recibirFechasParaPagosPendientes.fxml'.";
		assert txtMesInicio != null : "fx:id=\"txtMesInicio\" was not injected: check your FXML file 'recibirFechasParaPagosPendientes.fxml'.";

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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}
}
