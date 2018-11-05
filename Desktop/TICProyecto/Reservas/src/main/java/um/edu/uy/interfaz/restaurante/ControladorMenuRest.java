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
import javafx.stage.Stage;
import um.edu.uy.Main;
import um.edu.uy.persistance.entidades.Restaurante;

@Component
public class ControladorMenuRest implements ApplicationContextAware{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDatos;

    @FXML
    private Button btnReservas;
    
    private ApplicationContext applicationContext;
    
    @Autowired
    ControladorActualizarDatosRest controller;
    
    private Restaurante restaurante;

    @FXML
    void handleSubmitButtonAction(ActionEvent event) throws IOException {
    	Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);

		if (event.getSource() == btnDatos) {
			stage = (Stage) btnDatos.getScene().getWindow();
			root = fxmlLoader.load(ControladorMenuRest.class.getResourceAsStream("ActualizarDatosRest.fxml"));
			controller.handleBarrioCbox(event);
//			controller.handleTipoComidaCbox(event);
		}
		stage.setScene(new Scene(root));
		stage.show();
	}
    
    @FXML
    void verReservasPendientes(ActionEvent event) throws IOException {
    	Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);

		if (event.getSource() == btnReservas) {
			stage = (Stage) btnReservas.getScene().getWindow();
			//ControladorReservasPendientes controller = Main.getContext().getBean(ControladorReservasPendientes.class);
			//controller.setRestaurante(restaurante);
			root = fxmlLoader.load(ControladorReservasPendientes.class.getResourceAsStream("VerReservasPendientes.fxml"));
		}
		stage.setScene(new Scene(root));
		stage.show();
    }
    
    @FXML
    void initialize() {
        assert btnDatos != null : "fx:id=\"btnDatos\" was not injected: check your FXML file 'MenuPrincipalRest.fxml'.";
        assert btnReservas != null : "fx:id=\"btnReservas\" was not injected: check your FXML file 'MenuPrincipalRest.fxml'.";
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public void setRestaurante(Restaurante r) {
		restaurante = r;
	}

}
