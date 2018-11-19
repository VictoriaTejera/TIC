package um.edu.uy.interfaz.cliente;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import um.edu.uy.interfaz.cliente.clasesAuxiliares.RestauranteAUX;
import um.edu.uy.persistance.entidades.Restaurante;

@Component("ControladorPuntuarRestaurantes")
public class ControladorPuntuarRestaurantes implements ApplicationContextAware {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVolver;
    
    @FXML
    private TableColumn<RestauranteAUX, String> colPuntaje;
    
    @FXML
    private TableColumn<RestauranteAUX, String> colRestaurante;

	private ApplicationContext applicationContext;
	
	private Restaurante restaurante;

    @FXML
    void volverAlMenu(ActionEvent event) throws IOException {
        Parent root = null;
        Stage stage = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		stage = new Stage();
		if (event.getSource() == btnVolver) {
			root = fxmlLoader.load(ControladorInicioSesion.class.getResourceAsStream("MenuPrincipal.fxml"));
			stage = (Stage) btnVolver.getScene().getWindow();
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
    }


    @FXML
    void initialize() {
        assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'PuntuarRestaurantes.fxml'.";
        assert colPuntaje != null : "fx:id=\"colPuntaje\" was not injected: check your FXML file 'PuntuarRestaurantes.fxml'.";
        assert colRestaurante != null : "fx:id=\"colRestaurante\" was not injected: check your FXML file 'PuntuarRestaurantes.fxml'.";
    }
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
    
    public <T> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}
    
    public Restaurante getRestaurante() {
    	return this.restaurante;
    }
    
    public void setRestaurante(Restaurante res) {
    	this.restaurante = res;
    }

}

