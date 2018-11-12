package um.edu.uy.interfaz.cliente;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import um.edu.uy.persistance.entidades.Restaurante;

@Component
public class ControladorDetallesRestaurante implements ApplicationContextAware{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnReservar;
    
    @FXML
    private Button btnVolver;

    @FXML
    private Label descripcion;

    @FXML
    private Label nombreRest;
    
    @FXML
    private Label txtDireccion;

    @FXML
    private Label txtHoraApertura;

    @FXML
    private Label txtHoraCierre;

    @FXML
    private Label txtRating;

    @FXML
    private Label txtTelefono;
    
    @Autowired
    ControladorListarRestaurantes controlador;
    
    private ApplicationContext applicationContext;
    
    private Restaurante rest;

    @FXML
    void initialize() {
        assert btnReservar != null : "fx:id=\"btnReservar\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert descripcion != null : "fx:id=\"descripcion\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert nombreRest != null : "fx:id=\"nombreRest\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert txtDireccion != null : "fx:id=\"txtDireccion\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert txtHoraApertura != null : "fx:id=\"txtHoraApertura\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert txtHoraCierre != null : "fx:id=\"txtHoraCierre\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert txtRating != null : "fx:id=\"txtRating\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'DetallesRestaurante.fxml'.";
        
        nombreRest.setText(controlador.restSeleccionado().getNombre());
        txtDireccion.setText(controlador.restSeleccionado().getDireccion());
        txtHoraApertura.setText(controlador.restSeleccionado().getHorarioAperura());
        txtHoraCierre.setText(controlador.restSeleccionado().getHorarioCierre());
        txtRating.setText(Float.toString(controlador.restSeleccionado().getRating()));
        txtTelefono.setText(Integer.toString(controlador.restSeleccionado().getTelefono()));
        descripcion.setText(controlador.restSeleccionado().getDescripcion());
    }
    
    @FXML
    void RealizarReserva(ActionEvent event) throws IOException {  	
    	Stage stage;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		stage = new Stage();
		
		if (event.getSource() == btnReservar) {
			root = fxmlLoader.load(ControladorInicioSesion.class.getResourceAsStream("Reservar.fxml"));	
			stage = (Stage) btnReservar.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		if (event.getSource() == btnVolver) {
			stage = (Stage) btnVolver.getScene().getWindow();
			stage.close();
		}
	}
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
    
    public Restaurante getRestaurante() {
    	return rest;
    }

}
