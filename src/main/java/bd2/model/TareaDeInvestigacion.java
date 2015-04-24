package bd2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Representa a una tarea de investigacion
 * 
 * @author grupo15
 * @since 2015-04-01
 * @see Tarea
 */
public class TareaDeInvestigacion extends Tarea {

	/**
	 * Contiene la hipotesis de la tarea de
	 * investigacion
	 */
	private String hipotesis;
	
	/**
	 * Contiene el resultado de la tarea de
	 * investigacion
	 */
	private String resultado;

	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param completa
	 * @param fechaLimite
	 * @param descripcion
	 * @param pasos
	 * @param hipotesis
	 * @param resultado
	 */
	public TareaDeInvestigacion(Boolean completa, Date fechaLimite, String descripcion, Collection<Paso> pasos, String hipotesis, String resultado) {
		super(completa, fechaLimite, descripcion, pasos);
		this.hipotesis = hipotesis;
		this.resultado = resultado;
	}

	/**
	 * Constructor
	 * No recibe parametros
	 */
	public TareaDeInvestigacion() {
		super(false, null, null, new ArrayList<Paso>());
	}

	/**
	 * Constructor
	 * Recibe solamente los parametros descripcion y fecha limite. 
	 * Setea por defecto la hipotesis y el resultado en ""
	 * 
	 * @param descripcion
	 * @param fechaLimite
	 */
	public TareaDeInvestigacion(String descripcion, Date fechaLimite) {
		super(false, fechaLimite, descripcion, new ArrayList<Paso>());
		this.hipotesis = "";
		this.resultado = "";		
	}

	/**
	 * @return
	 */
	public String getHipotesis() {
		return hipotesis;
	}

	/**
	 * @param hipotesis
	 */
	public void setHipotesis(String hipotesis) {
		this.hipotesis = hipotesis;
	}

	/**
	 * @return
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * @param resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
