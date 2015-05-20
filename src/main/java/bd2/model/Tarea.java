package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * @author grupo15
 * @since 2015-04-01
 *
 */
public abstract class Tarea {
	
	/**
	 * Id de la entidad
	 */
	private Long id;

	/**
	 * Indica si la tarea fue completada o no
	 */
	private Boolean completa;
	
	/**
	 * Fecha limite para la cual la tarea debe
	 * ser completada
	 */
	private Date fechaLimite;
	
	/**
	 * Descripcion de la tarea
	 */
	private String descripcion;
	
	/**
	 * Contiene el historial de pasos de la tarea
	 */
	private Collection<Paso> pasos = new ArrayList<Paso>();
	
	
	/**
	 * Constructor vacio
	 */
	private Tarea() {
		super();
	}

	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param completa		
	 * @param fechaLimite
	 * @param descripcion
	 * @param pasos
	 */
	public Tarea(Boolean completa, Date fechaLimite, String descripcion, Collection<Paso> pasos) {
		super();
		this.completa = completa;
		this.fechaLimite = fechaLimite;
		this.descripcion = descripcion;
		this.pasos = pasos;
	}	

	/**
	 * Constructor
	 * Solo recibe los parametros descripcion y fecha
	 * limite para su creacion. Por defecto el estado
	 * de completa es falso
	 * 
	 * @param descripcion
	 * @param fechaLimite
	 */
	public Tarea(String descripcion, Date fechaLimite) {
		this.completa = false;
		this.descripcion = descripcion;
		this.fechaLimite = fechaLimite;
	}

	/**
	 * @return
	 */
	public Boolean completa() {
		return this.getCompleta();
	};

	/**
	 * @return
	 */
	public void completar(){
		this.completa = true;
	}

	/**
	 * Retorna true si la fecha límite de la tarea ya pasó, false en caso contrario.
	 * 
	 * @return
	 */
	public Boolean vencida(){
		return this.fechaLimite.before(new Date(System.currentTimeMillis()));
	}
	

	/**
	 * Se agrega a la pizarra enviada como parámetro. Se registra este 
	 * movimiento generando un nuevo Paso y agregándolo a la colección
	 * de pasos, con la fecha actual y la pizarra en cuestión.
	 * 
	 * @param pizarra	pizarra a ser agregada
	 */
	public void agregarAPizarra(Pizarra pizarra){
		pizarra.agregarTarea(this);
	}
	
	/**
	 * Agrega el paso enviado como parametro a la coleccion de pasos
	 * de esta Tarea.
	 * 
	 * @param paso
	 */
	public void agregarPaso(Paso paso){
		this.getPasos().add(paso);
	}

	/**
	 * @return
	 */
	public Boolean getCompleta() {
		return completa;
	}

	/**
	 * @param completa
	 */
	public void setCompleta(Boolean completa) {
		this.completa = completa;
	}

	/**
	 * @return
	 */
	public Date getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * @param fechaLimite
	 */
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Retorna el historial de pasos, donde cada objeto Paso representa la
	 * incorporación de la tarea en una pizarra, en una fecha dada.
	 * 
	 * @return
	 */
	public Collection<Paso> getPasos() {
		return pasos;
	}

	/**
	 * 
	 * @param pasos
	 */
	public void setPasos(Collection<Paso> pasos) {
		this.pasos = pasos;
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
