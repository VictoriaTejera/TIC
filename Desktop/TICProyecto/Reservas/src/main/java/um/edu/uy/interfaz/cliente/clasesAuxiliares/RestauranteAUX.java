package um.edu.uy.interfaz.cliente.clasesAuxiliares;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jboss.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import um.edu.uy.MainCliente;
import um.edu.uy.interfaz.cliente.ControladorInicio;
import um.edu.uy.interfaz.cliente.ControladorListarRestaurantes;
import um.edu.uy.interfaz.cliente.ControladorPuntuarRestaurantes;
import um.edu.uy.persistance.RestauranteMgr;
import um.edu.uy.persistance.entidades.Restaurante;

public class RestauranteAUX {

	private Restaurante restaurante;
	private Button button;
	private ComboBox<Integer> puntaje;
	private Image logo;

	ControladorListarRestaurantes controller;
	ControladorPuntuarRestaurantes controladorPuntuar;

	RestauranteMgr resMgr;

	public RestauranteAUX(Restaurante restaurante) throws IOException {
		this.restaurante = restaurante;

		//byte[] array = resMgr.obtenerLogo(restaurante.getRUT());
		//BufferedImage img = ImageIO.read(new ByteArrayInputStream(array));
		
		resMgr.obtenerLogo(restaurante.getRUT());
		this.logo = SwingFXUtils.toFXImage(resMgr.obtenerLogo(restaurante.getRUT()), null);
		
		// this.imagen = restaurante.getImagen();
		
		this.button = new Button("Reservar");

		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				;
				Stage stage = null;
				fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);
				fxmlLoader.setLocation(ControladorInicio.class.getResource("Reservar2.fxml"));
				try {
					fxmlLoader.load();
				} catch (IOException ex) {
					Logger.getLogger(ControladorListarRestaurantes.class.getName()).log(null, ex);
				}

				controller = (ControladorListarRestaurantes) MainCliente.getContext()
						.getBean("ControladorListarRestaurantes");
				controller.setRestaurante(restaurante);

				Parent root = fxmlLoader.getRoot();
				stage = (Stage) button.getScene().getWindow();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
			}
		});

		ObservableList<Integer> puntos = FXCollections.observableArrayList();
		puntos.add(1);
		puntos.add(2);
		puntos.add(3);
		puntos.add(4);
		puntos.add(5);

//		this.puntaje.setItems(puntos);
		
//		puntaje.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				if(puntaje.getValue()!=null) {
//					resMgr = (RestauranteMgr) MainCliente.getContext().getBean("RestauranteMgr");
//					controladorPuntuar.setRestaurante(restaurante);
//					resMgr.agregarRating(controladorPuntuar.getRestaurante().getRUT(), puntaje.getValue());
//				}
//			}
//		});

	}

	public Button getButton() {
		return button;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

}
