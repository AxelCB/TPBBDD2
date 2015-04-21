package bd2.model;

import java.util.Date;

/**
 * Representa el paso de una tarea 
 * por una pizarra en particular
 * 
 * @author grupo15
 * @since 2015-04-01
 */
public class Paso {

	/**
	 * Id de la entidad
	 */
	private Long id;
	
	/**
	 * Fecha de ingreso de la tarea a la pizarra
	 */
	private Date fechaDeIngreso;
	
	/**
	 * Pizarra a la que ingresa la tarea
	 */
	private Pizarra pizarra;

	public Paso(){
		super();
	}
	
	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param fechaDeIngreso
	 * @param pizarra
	 */
	public Paso(Date fechaDeIngreso, Pizarra pizarra) {
		super();
		this.fechaDeIngreso = fechaDeIngreso;
		this.pizarra = pizarra;
	}

	/**
	 * @return
	 */
	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	/**
	 * @param fechaDeIngreso
	 */
	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

	/**
	 * @return
	 */
	public Pizarra getPizarra() {
		return pizarra;
	}

	/**
	 * @param pizarra
	 */
	public void setPizarra(Pizarra pizarra) {
		this.pizarra = pizarra;
	}

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
