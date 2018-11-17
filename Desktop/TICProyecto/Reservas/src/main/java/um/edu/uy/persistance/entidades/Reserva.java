package um.edu.uy.persistance.entidades;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;



import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Table
@Entity
@SuppressWarnings("unused")

public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	@Column(name="id", unique=true, nullable=false)
	private Long Id;
	private Integer cantPersonas;
	private Date fecha;

	@Column(columnDefinition="boolean default FALSE")
	private boolean confirmada;
	@Column(columnDefinition="boolean default FALSE")
	private boolean rechazada;
	@Column(columnDefinition="boolean default FALSE")
	private boolean terminada;
	
	@ManyToOne
	@Cascade(CascadeType.ALL)
	private Usuario usuario;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	private Restaurante restaurante;

	public Reserva() {
	}

	public Reserva(Usuario usuario, Restaurante restaurante, Integer cantPersonas) {
		super();
		this.usuario = usuario;
		this.restaurante = restaurante;
		this.cantPersonas = cantPersonas;
		confirmada=false;
		terminada=false;
		rechazada=false;
		
		java.util.Date fecha= new Date();
		this.fecha=fecha;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Integer getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(Integer cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public boolean isConfirmado() {
		return confirmada;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmada = confirmado;
	}
	
	public void setRechazada(boolean rechazada) {
		this.rechazada = rechazada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public String toString() {
		return "Reserva [Id=" + Id + ", cantPersonas=" + cantPersonas + ", confirmada=" + confirmada + ", terminada="
				+ terminada + ", usuario=" + usuario + ", restaurante=" + restaurante + "]";
	}
	
	
}
