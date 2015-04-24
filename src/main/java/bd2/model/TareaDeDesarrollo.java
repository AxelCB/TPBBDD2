package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Representa a una tarea de desarrollo
 * 
 * @author grupo15
 * @since 2015-04-01
 * @see Tarea
 */
public class TareaDeDesarrollo extends Tarea {

	/**
	 * Contiene los requerimientos especificos a una
	 * tarea de desarrollo
	 */
	private String requerimientos;

	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param completa
	 * @param fechaLimite
	 * @param descripcion
	 * @param pasos
	 * @param requerimientos
	 */
	public TareaDeDesarrollo(Boolean completa, Date fechaLimite, String descripcion, Collection<Paso> pasos, String requerimientos) {
		super(completa, fechaLimite, descripcion, pasos);
		this.requerimientos = requerimientos;
	}

	/**
	 * Constructor
	 * Recibe solamente la descripcion y la fecha
	 * limite de la tarea
	 * 
	 * @param descripcion
	 * @param fechaLimite
	 */
	public TareaDeDesarrollo(String descripcion, Date fechaLimite) {
		super(false, fechaLimite, descripcion, new ArrayList<Paso>());
		this.requerimientos = "";
	}

	/**
	 * Constructor
	 * No recibe parametros
	 */
	public TareaDeDesarrollo() {
		super(false, null, null, new ArrayList<Paso>());
	}

	/**
	 * @return
	 */
	public String getRequerimientos() {
		return requerimientos;
	}

	/**
	 * @param requerimientos
	 */
	public void setRequerimientos(String requerimientos) {
		this.requerimientos = requerimientos;
	}	
}
