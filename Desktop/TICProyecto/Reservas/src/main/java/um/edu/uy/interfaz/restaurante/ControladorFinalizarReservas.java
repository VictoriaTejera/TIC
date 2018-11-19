package um.edu.uy.interfaz.restaurante;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import um.edu.uy.interfaz.cliente.ControladorInicio;
import um.edu.uy.interfaz.restaurante.clasesAuxiliares.ReservaAux;
import um.edu.uy.persistance.ReservaMgr;
import um.edu.uy.persistance.entidades.Reserva;

@Component
public class ControladorFinalizarReservas implements ApplicationContextAware{

    @FXML
    private TableColumn<ReservaAux, String> colUsuario;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<ReservaAux, String> colRestaurante;

    @FXML
    private TableColumn<ReservaAux, String> colFinalizar;
    
    @FXML
    private TableView<ReservaAux> reservas;
    
    private final StringProperty prop = new SimpleStringProperty();
    
    private ApplicationContext applicationContext;
    
    @Autowired
	private ControladorInicioSesionRest controladorInicio;
    
    @Autowired
    ReservaMgr reservaMgr;

    @FXML
	void volverAlMenu(ActionEvent event) throws IOException {
		Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		if (event.getSource() == btnVolver) {
			root = fxmlLoader.load(ControladorMenuRest.class.getResourceAsStream("MenuPrincipalRest.fxml"));
			stage = (Stage) btnVolver.getScene().getWindow();
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
    
    @FXML
	void initialize() {
    	assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'FinalizarReservas.fxml'.";
    	assert colFinalizar != null : "fx:id=\"colFinalizar\" was not injected: check your FXML file 'FinalizarReservas.fxml'.";
    	assert colRestaurante != null : "fx:id=\"colRestaurante\" was not injected: check your FXML file 'FinalizarReservas.fxml'.";
    	assert colUsuario != null : "fx:id=\"colUsuario\" was not injected: check your FXML file 'FinalizarReservas.fxml'.";
    	
    	colUsuario.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ReservaAux, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ReservaAux, String> r) {
						prop.setValue(r.getValue().getReserva().getUsuario().getNombre());
						return prop;
					}
				});
    	colRestaurante.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ReservaAux, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ReservaAux, String> r) {
						prop.setValue(r.getValue().getReserva().getRestaurante().getNombre());
						return prop;
					}
				});
    	
    	colFinalizar.setCellValueFactory(new PropertyValueFactory<ReservaAux, String>("finalizar"));
    	
    	List<Reserva> reservasConfirmadas = reservaMgr.obtenerReservasConfirmadasNoTerminadas(controladorInicio.getRutRestaurante());
		ObservableList<ReservaAux> reservasSinTerminar = FXCollections.observableArrayList();
		ReservaAux reserva;

		for (int i = 0; i < reservasConfirmadas.size(); i++) {
			reserva = new ReservaAux(reservasConfirmadas.get(i));
			reservasSinTerminar.add(reserva);
		}
		reservas.setItems(reservasSinTerminar);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	


}
