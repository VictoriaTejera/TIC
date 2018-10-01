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
import um.edu.uy.interfaz.cliente.ControladorRegistro;


@SpringBootApplication
public class Main extends Application {
	Button btnRegistrarse, btnIniciarSesion, btnConfirmarInicioSesion, btnListarRestaurantes;
	Scene scene, scene2, scene1, scene3, scene4;
	Stage thestage;
	private ConfigurableApplicationContext context;
	private FXMLLoader fxmlLoader;
	private Parent root1;
	
	@Override
	public void init() throws Exception{
		context = SpringApplication.run(Main.class);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(context::getBean);
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		thestage = primaryStage;
		btnRegistrarse = new Button();
		btnIniciarSesion = new Button();
		btnConfirmarInicioSesion = new Button();

		btnRegistrarse.setOnAction(e -> ButtonClicked(e));
		btnIniciarSesion.setOnAction(e -> ButtonClicked(e));
		btnConfirmarInicioSesion.setOnAction(e -> ButtonClicked(e));
		
		fxmlLoader.setLocation(ControladorRegistro.class.getResource("registrarse.fxml"));
		root1 = fxmlLoader.load();
		primaryStage.setScene(new Scene(root1));

		Parent root = FXMLLoader.load(ControladorRegistro.class.getResource("inicio.fxml"));
		//Parent root1 = FXMLLoader.load(getClass().getResource("registrarse.fxml"));
		Parent root2 = FXMLLoader.load(ControladorRegistro.class.getResource("iniciarSesion.fxml"));
		Parent root3 = FXMLLoader.load(ControladorRegistro.class.getResource("MenuPrincipal.fxml"));

		Scene scene = new Scene(root, 400, 400);
		//Scene scene1 = new Scene(root1, 400, 400);
		Scene scene2 = new Scene(root2, 400, 400);
		Scene scene4 = new Scene(root3, 400, 400);

		scene.getStylesheets().add(ControladorRegistro.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// SpringApplication.run(Main.class, args);
		launch(args);
	}

	private void ButtonClicked(ActionEvent e) {
		if (e.getSource() == btnRegistrarse)
			thestage.setScene(scene1);
		if (e.getSource() == btnIniciarSesion)
			thestage.setScene(scene2);
		if (e.getSource() == btnConfirmarInicioSesion)
			thestage.setScene(scene4);
		if (e.getSource() == btnListarRestaurantes)
			thestage.setScene(scene3);
	}

	public void stop() {
		context.close();
	}
}
