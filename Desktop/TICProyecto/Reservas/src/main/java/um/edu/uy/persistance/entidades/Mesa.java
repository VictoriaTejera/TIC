package um.edu.uy.persistance.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table
//@SuppressWarnings("unused")
public class Mesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer capacidad;
	@Column(columnDefinition="boolean default FALSE")
	private boolean reservada;	
	
	public Mesa() {
		reservada=false;
	}
	
	public Mesa(Integer capacidad) {
		this.capacidad = capacidad;
		reservada=false;
	}
	
	public void setReservada(boolean reservada) {
		this.reservada = reservada;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	public boolean isReservada() {
		return reservada;
	}
	public void reservar() {
		reservada=true;
	}
	
}
