package persistance.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@SuppressWarnings("unused")
public class Restaurante implements Serializable{

	@Id
	private String nombre;
	private String barrio;
	private String horario;
	private String direccion;
	private Integer telefono;
	private Float rating;
	private Integer cantRatings;
	private String descripcion;
//	private List<Comida> menu;
	
	//foto que tipo de datos es??
	
	public Restaurante(String nombre, String direccion, Integer telefono) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public String getBarrio() {
		return barrio;
	}

	public String getHorario() {
		return horario;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public Float getRating() {
		return rating;
	}

	public Integer getCantRatings() {
		return cantRatings;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Restaurante(String nombre, String barrio, String horario, String direccion, Integer telefono, Float rating,
			Integer cantRatings, String descripcion, List<Comida> menu) {
		super();
		this.nombre = nombre;
		this.barrio = barrio;
		this.horario = horario;
		this.direccion = direccion;
		this.telefono = telefono;
		this.rating = rating;
		this.cantRatings = cantRatings;
		this.descripcion = descripcion;
//		this.menu = menu;
	}
	
	
	
	
}
