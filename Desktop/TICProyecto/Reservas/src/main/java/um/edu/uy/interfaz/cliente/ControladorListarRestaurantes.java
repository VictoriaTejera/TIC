package um.edu.uy.interfaz.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import um.edu.uy.interfaz.cliente.clasesAuxiliares.RestauranteAUX;
import um.edu.uy.persistance.BarrioMgr;
import um.edu.uy.persistance.ComidaMgr;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.entidades.Restaurante;

@Component("ControladorListarRestaurantes")
public class ControladorListarRestaurantes implements ApplicationContextAware {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBuscar;

	@FXML
	private ComboBox<String> cboxBarrio;

	@FXML
	private ComboBox<String> cboxComida;

	@FXML
	private ComboBox<String> cboxPrecio;

	@FXML
	private TableColumn<RestauranteAUX, String> columnaDireccion;

	@FXML
	private TableColumn<RestauranteAUX, String> columnaNombre;

	@FXML
	private TableColumn<RestauranteAUX, String> columnaTelefono;

	@FXML
	private TableColumn<RestauranteAUX, String> columnaReservar;
	
	@FXML
	private TableColumn<RestauranteAUX, String> columnaFoto;

	@FXML
	private TableView<RestauranteAUX> tabla;

	@FXML
	private Button btnVolverAlMenu;

	@Autowired
	private RestauranteMgr restauranteMgr;

	@Autowired
	private BarrioMgr barrioMgr;

	@Autowired
	private ComidaMgr comidaMgr;

	private ApplicationContext applicationContext;

	private StringProperty prop = new SimpleStringProperty();
	
	private Restaurante res;
	
	public void llenarTabla() throws IOException {
		columnaNombre.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<RestauranteAUX, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<RestauranteAUX, String> r) {
						prop.setValue(r.getValue().getRestaurante().getNombre());
						return prop;
					}
				});

		columnaDireccion.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<RestauranteAUX, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<RestauranteAUX, String> r) {
						prop.setValue(r.getValue().getRestaurante().getDireccion());
						return prop;
					}
				});

		columnaTelefono.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<RestauranteAUX, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<RestauranteAUX, String> r) {
						prop.setValue(Integer.toString(r.getValue().getRestaurante().getTelefono()));
						return prop;
					}
				});
		
		columnaFoto.setCellValueFactory(new PropertyValueFactory<RestauranteAUX, String>("imagen"));

		columnaReservar.setCellValueFactory(new PropertyValueFactory<RestauranteAUX, String>("button"));

		ObservableList<RestauranteAUX> restaurantes = FXCollections.observableArrayList();
		RestauranteAUX restAux;

		for (int i = 0; i < restauranteMgr.getRestaurants().size(); i++) {
			restAux = new RestauranteAUX(restauranteMgr.getRestaurants().get(i));
			restaurantes.add(restAux);
		}

		tabla.setItems(restaurantes);
		
	}

	@FXML
	void filtroBarrio() {
		cboxBarrio.setItems(barrioMgr.getBarrios());
	}

	@FXML
	void filtroComida() {
		cboxComida.setItems(comidaMgr.getComidas());
	}

	@FXML
	void filtroPrecio() {
	}

	@FXML
	void ListarRestaurantes(ActionEvent event) throws IOException {
		if (event.getSource() == btnBuscar) {
			ObservableList<RestauranteAUX> rest = FXCollections.observableArrayList();
			RestauranteAUX restAux;
			if (cboxBarrio.getValue() != null) {
				for (int i = 0; i < restauranteMgr.filtrarPorBarrio(cboxBarrio.getValue()).size(); i++) {
					restAux = new RestauranteAUX(restauranteMgr.filtrarPorBarrio(cboxBarrio.getValue()).get(i));
					rest.add(restAux);
				}
				tabla.setItems(rest);
			}
			if (cboxComida.getValue() != null) {
				for (int i = 0; i < restauranteMgr.filtrarPorComida(cboxComida.getValue()).size(); i++) {
					restAux = new RestauranteAUX(restauranteMgr.filtrarPorComida(cboxComida.getValue()).get(i));
					rest.add(restAux);
				}
				tabla.setItems(rest);
			}

			/*
			 * if (cboxComida.getValue() != null && cboxBarrio.getValue() != null) { for
			 * (int i = 0; i < restaurante.filtrarPorVarios(cboxComida.getValue(),
			 * cboxBarrio.getValue()) .size(); i++) { restAux = new RestauranteAUX(
			 * restaurante.filtrarPorVarios(cboxComida.getValue(),
			 * cboxBarrio.getValue()).get(i)); rest.add(restAux); } }
			 */
		}

	}

	@FXML
	Restaurante restSeleccionado() {
		Restaurante res = tabla.getSelectionModel().getSelectedItem().getRestaurante();
		return res;
	}

	@FXML
	void mostrarRestaurante(ActionEvent event) throws IOException {
		Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		root = fxmlLoader.load(ControladorListarRestaurantes.class.getResourceAsStream("DetallesRestaurante.fxml"));
		// stage = (Stage) event.getSource().getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		stage.setScene(scene);
    	stage.show();

	}

	@FXML
	void volverAlMenu(ActionEvent event) throws IOException {
		Stage stage = null;
		Parent root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		stage = new Stage();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		if (event.getSource() == btnVolverAlMenu) {
			root = fxmlLoader.load(ControladorInicioSesion.class.getResourceAsStream("MenuPrincipal.fxml"));
			stage = (Stage) btnVolverAlMenu.getScene().getWindow();
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		stage.setScene(scene);
    	stage.show();

	}

	@FXML
	void initialize() throws IOException {
		assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert cboxBarrio != null : "fx:id=\"cboxBarrio\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert cboxComida != null : "fx:id=\"cboxComida\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert cboxPrecio != null : "fx:id=\"cboxPrecio\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert columnaDireccion != null : "fx:id=\"columnaDireccion\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert columnaNombre != null : "fx:id=\"columnaNombre\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert columnaTelefono != null : "fx:id=\"columnaTelefono\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert columnaFoto != null : "fx:id=\"columnaFoto\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		assert tabla != null : "fx:id=\"tabla\" was not injected: check your FXML file 'ListarRestaurantes.fxml'.";
		
		llenarTabla();
		filtroBarrio();
		filtroComida();
		filtroPrecio();
		

		tabla.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setControllerFactory(applicationContext::getBean);
				fxmlLoader.setLocation(getClass().getResource("DetallesRestaurante.fxml"));
				try {
					fxmlLoader.load();
				} catch (IOException ex) {
					Logger.getLogger(ControladorListarRestaurantes.class.getName()).log(null, ex);
				}
				Parent root = fxmlLoader.getRoot();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			}
		});

	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	public Restaurante getRestaurante() {
		return res;
	}

	public void setRestaurante(Restaurante res) {
		this.res = res;
	}
}
