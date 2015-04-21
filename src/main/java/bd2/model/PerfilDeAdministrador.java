package bd2.model;

import java.util.Date;

/**
 * Representa a los usuarios adminitradores
 * de un proyecto.
 * 
 * @author grupo15
 * @since 2014-04-01
 * @see PerfilDeUsuario
 */
public class PerfilDeAdministrador extends PerfilDeUsuario {

	/**
	 * Flag que representa la creacion o no de un proyecto
	 */
	private Boolean creador;
	
	public PerfilDeAdministrador(){
		super();
	}
	
	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param fechaDeCreacion	fecha de creacion del perfil
	 * @param usuario			usuario al que pertenecera el perfil
	 * @param creador			si el usuario es creador o no
	 */
	public PerfilDeAdministrador(Date fechaDeCreacion, Usuario usuario, Boolean creador) {
		super(fechaDeCreacion, usuario);
		this.creador = creador;
	}
	
	/**
	 * Al igual que su comportamiento heredado, elimina el perfil, 
	 * pero en caso de que el perfil sea creador, lanza una excepción con el mensaje:
	 * “No se puede eliminar al creador del proyecto”
	 * 
	 * @param proyecto		proyecto del cual se quiere eliminar el perfil
	 * @throws Exception	excepcion que se levanta al intentar 
	 * 						eliminar al creador del proyecto
	 */
	@Override
	public void eliminarDe(Proyecto proyecto) throws Exception{
		if (this.creador) throw new Exception("No se puede eliminar al creador del proyecto");
		else super.eliminarDe(proyecto);
	}

	/**
	 * @return
	 */
	public Boolean getCreador() {
		return creador;
	}

	/**
	 * @param creador
	 */
	public void setCreador(Boolean creador) {
		this.creador = creador;
	}
	
	/**
	 * @return
	 */
	@Override
	public Boolean esCreador(){
		return this.getCreador();
	}
}
