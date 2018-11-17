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
import um.edu.uy.interfaz.cliente.ControladorRegistro;

@SpringBootApplication
public class MainCliente extends Application {
	Button btnRegistrarse, btnIniciarSesion, btnConfirmarInicioSesion, btnListarRestaurantes, btnConfirmarRegistro, btnReservar, btnReservasRealizadas,btnPuntuarRest;
	Scene scene, scene2, scene1, scene3, scene4, scene5, scene6,scene7;
	Stage thestage;
	private static ConfigurableApplicationContext context;
	private FXMLLoader fxmlLoader;
	private Parent root, root1, root2, root3, root4, root5, root6, root7;

	@Override
	public void init() throws Exception {
		MainCliente.context = SpringApplication.run(MainCliente.class);
	}

	@Override
	public void start(Stage primaryStage) throws IOException{
		thestage = primaryStage;
		btnRegistrarse = new Button();
		btnIniciarSesion = new Button();
		btnConfirmarInicioSesion = new Button();
		btnConfirmarRegistro = new Button();
		btnListarRestaurantes = new Button();
		btnReservar = new Button();
		btnReservasRealizadas = new Button();
		btnPuntuarRest = new Button();

		btnRegistrarse.setOnAction(e -> ButtonClicked(e));
		btnIniciarSesion.setOnAction(e -> ButtonClicked(e));
		btnConfirmarInicioSesion.setOnAction(e -> ButtonClicked(e));
		btnConfirmarRegistro.setOnAction(e -> ButtonClicked(e));
		btnListarRestaurantes.setOnAction(e -> ButtonClicked(e));
		btnReservar.setOnAction(e -> ButtonClicked(e));
		btnReservasRealizadas.setOnAction(e -> ButtonClicked(e));
		btnPuntuarRest.setOnAction(e -> ButtonClicked(e));

		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);

		root = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("inicio.fxml"));
		scene = new Scene(root);
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);

		root1 = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("registrarse.fxml"));
		scene1 = new Scene(root1);
		scene1.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);

		root2 = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("iniciarSesion.fxml"));
		scene2 = new Scene(root2);
		scene2.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());

		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);

		root3 = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("MenuPrincipal.fxml"));
		scene3 = new Scene(root3);
		scene3.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);
		
		root4 = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("ListarRestaurantes.fxml"));
		scene4 = new Scene(root4);
		scene4.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);
		
		root5 = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("Reservar.fxml"));
		scene5 = new Scene(root5);
		scene5.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);
		
		root6 = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("MisReservas.fxml"));
		scene6 = new Scene(root6);
		scene6.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());

		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(MainCliente.getContext()::getBean);
	
		root7 = fxmlLoader.load(ControladorRegistro.class.getResourceAsStream("PuntuarRestaurantes.fxml"));
		scene7 = new Scene(root7);
		scene7.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());

		scene.getStylesheets().add(ControladorInicio.class.getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void ButtonClicked(ActionEvent e) {
		if (e.getSource() == btnRegistrarse)
			thestage.setScene(scene1);
		if (e.getSource() == btnIniciarSesion)
			thestage.setScene(scene2);
		if (e.getSource() == btnConfirmarInicioSesion)
			thestage.setScene(scene3);
		if (e.getSource() == btnListarRestaurantes)
			thestage.setScene(scene4);
		if (e.getSource() == btnConfirmarRegistro)
			thestage.setScene(scene2);
		if (e.getSource() == btnReservar)
			thestage.setScene(scene5);
		if (e.getSource() == btnReservasRealizadas)
			thestage.setScene(scene6);
		if (e.getSource() == btnPuntuarRest)
			thestage.setScene(scene7);
	}

	public void stop() {
		context.close();
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

}