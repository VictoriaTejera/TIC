package um.edu.uy.interfaz.restaurante.clasesAuxiliares;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import um.edu.uy.MainRestaurante;
import um.edu.uy.interfaz.restaurante.ControladorReservasPendientes;
import um.edu.uy.persistance.ReservaMgr;
import um.edu.uy.persistance.entidades.Reserva;

public class ReservaAux {
	private Reserva reserva;
	private Button aceptar;
	private Button rechazar;
	private Button finalizar;

	ReservaMgr resMgr;

	@Autowired
	ControladorReservasPendientes controller;

	public ReservaAux(Reserva reserva) {
		this.reserva = reserva;
		this.aceptar = new Button("Aceptar");
		this.rechazar = new Button("Rechazar");
		this.finalizar = new Button("Finalizar");

		controller = (ControladorReservasPendientes) MainRestaurante.getContext()
				.getBean("ControladorReservasPendientes");
		controller.setReserva(reserva);
		resMgr = (ReservaMgr) MainRestaurante.getContext().getBean("ReservaMgr");

		aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resMgr.confirmarReserva(controller.getReserva().getId());
				showAlert("Confirmación de reserva", "Reserva confirmada con éxito");
			}
		});

		rechazar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resMgr.rechazarReserva(controller.getReserva().getRestaurante().getRUT(),
						controller.getReserva().getUsuario().getCelular(), controller.getReserva().getFecha());
				showAlert("Rechazo de reserva", "Reserva rechazada con éxito");
			}
		});
		finalizar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resMgr.terminarReserva(controller.getReserva().getRestaurante().getRUT(),
						controller.getReserva().getUsuario().getCelular(), controller.getReserva().getFecha());
				showAlert("Finalización de reserva", "Reserva finalizada con éxito");
			}
		});

	}

	public static void showAlert(String title, String contextText) {
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
				javafx.scene.control.Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contextText);
		alert.showAndWait();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public Button getAceptar() {
		return aceptar;
	}

	public Button getRechazar() {
		return rechazar;
	}

}