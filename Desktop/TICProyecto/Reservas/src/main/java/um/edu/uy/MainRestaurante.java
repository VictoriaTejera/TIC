package um.edu.uy;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import um.edu.uy.interfaz.cliente.ControladorInicio;
import um.edu.uy.interfaz.restaurante.ControladorInicioSesionRest;

@SpringBootApplication
public class MainRestaurante extends Application{
	Button btnIniciarSesion, btnDatos, btnGuardarDatos, btnCargarImagen, btnReservas, btnFinalizarReservas, btnVerTotalAPagar;
	Scene scene, scene1, scene2, scene3, scene4, scene5, scene6;
	Stage thestage;
	
	private static ConfigurableApplicationContext context;
	private FXMLLoader fxmlLoader;
	private Parent root, root1, root2, root3, root4, root5, root6;

	
	@Override
	public void init() throws IOException  {
		MainRestaurante.context = SpringApplication.run(MainRestaurante.class);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		thestage = primaryStage;
		btnIniciarSesion = new Button();
		btnDatos = new Button();
		btnGuardarDatos = new Button();
		btnCargarImagen = new Button();
		btnReservas = new Button();
		btnFinalizarReservas = new Button();
		btnVerTotalAPagar = new Button();
		
		btnIniciarSesion.setOnAction(e -> ButtonClicked(e));
		btnDatos.setOnAction(e -> ButtonClicked(e));
		btnGuardarDatos.setOnAction(e -> ButtonClicked(e));
		btnCargarImagen.setOnAction(e -> ButtonClicked(e));
		btnReservas.setOnAction(e -> ButtonClicked(e));
		btnFinalizarReservas.setOnAction(e -> ButtonClicked(e));
		btnVerTotalAPagar.setOnAction(e -> ButtonClicked(e));
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainRestaurante.getContext()::getBean);

		root = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("inicio.fxml"));
		scene = new Scene(root);
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainRestaurante.getContext()::getBean);
		
		root1 = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("MenuPrincipalRest.fxml"));
		scene1 = new Scene(root1);
		scene1.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainRestaurante.getContext()::getBean);
		
		root2 = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("ActualizarDatosRest.fxml"));
		scene2 = new Scene(root2);
		scene2.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());

		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainRestaurante.getContext()::getBean);
		
		root4 = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("VerReservasPendientes.fxml"));
		scene4 = new Scene(root4);
		scene4.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainRestaurante.getContext()::getBean);
		
		root5 = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("FinalizarReservas.fxml"));
		scene5 = new Scene(root5);
		scene5.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainRestaurante.getContext()::getBean);
		
		root6 = fxmlLoader.load(ControladorInicioSesionRest.class.getResourceAsStream("recibirFechasParaPagosPendientes.fxml"));
		scene6 = new Scene(root6);
		scene6.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void ButtonClicked(ActionEvent e) {
		if (e.getSource() == btnIniciarSesion)
			thestage.setScene(scene1);
		if (e.getSource() == btnDatos)
			thestage.setScene(scene2);
		if (e.getSource() == btnGuardarDatos)
			thestage.setScene(scene1);	
		if (e.getSource() == btnReservas)
			thestage.setScene(scene4);	
		if (e.getSource() == btnFinalizarReservas)
			thestage.setScene(scene5);	
		if (e.getSource() == btnVerTotalAPagar)
			thestage.setScene(scene6);	
	}
	
	public void stop() {
		context.close();
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

}
