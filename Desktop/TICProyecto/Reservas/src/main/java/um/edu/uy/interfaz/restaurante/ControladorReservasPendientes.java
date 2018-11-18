package um.edu.uy.interfaz.restaurante;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class ControladorReservasPendientes implements ApplicationContextAware {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<ReservaAux, String> columnaCantPersonas;

	@FXML
	private TableColumn<ReservaAux, String> columnaUsuario;

	@FXML
	private TableView<ReservaAux> reservasPendientes;

	@FXML
	private TableColumn<ReservaAux, String> colAceptar;

	@FXML
	private TableColumn<ReservaAux, String> colRechazar;

	@FXML
	private Button btnVolver;

	@Autowired
	private ReservaMgr resMgr;

	@Autowired
	private ControladorInicioSesionRest controladorInicio;

	private final StringProperty prop = new SimpleStringProperty();

	private static ApplicationContext applicationContext;

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
		assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'VerReservasPendientes.fxml'.";
		assert colAceptar != null : "fx:id=\"colAceptar\" was not injected: check your FXML file 'VerReservasPendientes.fxml'.";
		assert colRechazar != null : "fx:id=\"colRechazar\" was not injected: check your FXML file 'VerReservasPendientes.fxml'.";
		assert columnaCantPersonas != null : "fx:id=\"columnaCantPersonas\" was not injected: check your FXML file 'VerReservasPendientes.fxml'.";
		assert columnaUsuario != null : "fx:id=\"columnaUsuario\" was not injected: check your FXML file 'VerReservasPendientes.fxml'.";
		assert reservasPendientes != null : "fx:id=\"reservasPendientes\" was not injected: check your FXML file 'VerReservasPendientes.fxml'.";

		columnaUsuario.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ReservaAux, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ReservaAux, String> r) {
						prop.setValue(r.getValue().getReserva().getUsuario().getNombre());
						return prop;
					}
				});

		columnaCantPersonas.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ReservaAux, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ReservaAux, String> r) {
						prop.setValue(Integer.toString(r.getValue().getReserva().getCantPersonas()));
						return prop;
					}
				});

		colAceptar.setCellValueFactory(new PropertyValueFactory<ReservaAux, String>("aceptar"));

		colRechazar.setCellValueFactory(new PropertyValueFactory<ReservaAux, String>("rechazar"));

		List<Reserva> listaDePendientes = resMgr.obtenerReservasNoConfirmadas(controladorInicio.getRutRestaurante());
		ObservableList<ReservaAux> reservas = FXCollections.observableArrayList();
		ReservaAux reserva;

		for (int i = 0; i < listaDePendientes.size(); i++) {
			reserva = new ReservaAux(listaDePendientes.get(i));
			reservas.add(reserva);
		}
		reservasPendientes.setItems(reservas);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}
