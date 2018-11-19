package um.edu.uy.interfaz.cliente;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import um.edu.uy.persistance.ReservaMgr;

@Component
public class ControladorReservar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnReservar;
    
    @FXML
    private TextField cantPersonas;  
    
    @FXML
    private JFXTimePicker hora;

    @FXML
    private JFXDatePicker fecha;
  
    @Autowired
    ControladorInicioSesion controladorInicioSesion;    
    
    @Autowired
    ControladorListarRestaurantes controlador;
    
    @Autowired 
    ControladorDetallesRestaurante controlador2;
    
    @Autowired
    ReservaMgr reservaMgr;

    @FXML
    void handleButtonAction(ActionEvent event) {
    	Stage stage = null;
    	if (event.getSource() == btnReservar) {
    		try {
				reservaMgr.save(controladorInicioSesion.getUsuario().getCelular(), controlador2.getRestaurante().getRUT(),
						Integer.parseInt(cantPersonas.getText()));
				fecha.getValue();
				reservaMgr.agregarHora(hora.getValue() , controlador2.getRestaurante());
				
				stage = (Stage) btnReservar.getScene().getWindow();
				stage.close();
			}catch(NumberFormatException e) {
				showAlert("Ingrese una cantidad de personas.");
			}
    	}
    }

    @FXML
    void initialize() {
        assert btnReservar != null : "fx:id=\"btnReservar\" was not injected: check your FXML file 'Reservar.fxml'.";
        assert cantPersonas != null : "fx:id=\"cantPersonas\" was not injected: check your FXML file 'Reservar.fxml'.";
        
    }
    
	public static void showAlert(String title) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(null);
        alert.showAndWait();
    }

    
}


