package bd2.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Contiene las diferentes tareas de un proyecto
 * 
 * @author grupo15
 * @since 2015-04-01
 */
public class Pizarra {
	
	/**
	 * Id de la entidad
	 */
	private Long id;

	/**
	 * Nombre de la pizarra
	 */
	private String nombre;
	
	/**
	 * Coleccion de tareas asociadas
	 */
	private Collection<Tarea> tareas = new HashSet<Tarea>();
	
	/**
	 * Constructor vacio
	 */
	public Pizarra(){
		super();
	}
	
	/**
	 * Constructor
	 * Recibe solo el nombre
	 * 
	 * @param nombre	nombre con el que se 
	 * 					identificara la pizarra
	 */
	public Pizarra(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Contructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param nombre	nombre con el que se
	 * 					identificara la pizarra
	 * @param tareas	tareas que componen la 
	 * 					pizarra
	 */
	public Pizarra(String nombre, Collection<Tarea> tareas) {
		super();
		this.nombre = nombre;
		this.tareas = tareas;
	}
	
	/**
	 * Agrega tarea a la pizarra receptora
	 * La tarea ademas registra su incorporación a la pizarra
	 * 
	 * @param tarea
	 */
	public void agregarTarea(Tarea tarea){
		tarea.agregarPaso(new Paso(new Date(), this));
		this.getTareas().add(tarea);
	}

	/**
	 *	Mueve una tarea propia a la pizarra destino. Luego de esto,
	 *	la tarea tarea no esta más en la pizarra receptora sino en destino.
	 *	La tarea ademas registra su paso a la pizarra destino. 
	 *
	 * @param tarea		tarea a movilizar
	 * @param destino	pizarra destino
	 */
	public void moverTareaAPizarra(Tarea tarea, Pizarra destino){
		this.getTareas().remove(tarea);
		destino.agregarTarea(tarea);
	}
	
	/**
	 * @param tarea
	 */
	public void eliminarTarea(Tarea tarea){
		this.tareas.remove(tarea);
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return
	 */
	public Collection<Tarea> getTareas() {
		return tareas;
	}

	/**
	 * @param tareas
	 */
	public void setTareas(Collection<Tarea> tareas) {
		this.tareas = tareas;
	}

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}