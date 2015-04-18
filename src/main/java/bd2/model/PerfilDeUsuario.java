package bd2.model;

import java.util.Date;

/**
 * Representa a los usuarios colaboradores
 * en un proyecto
 * 
 * @author grupo15
 * @since 2014-04-01
 *
 */
public class PerfilDeUsuario {

	/**
	 * Id de la entidad
	 */
	private Long id;
	
	/**
	 * Contiene la fecha de creacion del perfil
	 */
	private Date fechaDeCreacion;
	
	/**
	 * Contiene al usuario
	 */
	private Usuario usuario;

	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param fechaDeCreacion 	fecha de creacion del perfil
	 * @param usuario			usuario al que pertenecera el perfil
	 */
	public PerfilDeUsuario(Date fechaDeCreacion, Usuario usuario) {
		super();
		this.fechaDeCreacion = fechaDeCreacion;
		this.usuario = usuario;
	}
	
	/**
	 * Elimina el perfil del proyecto enviado como parámetro
	 * 
	 * @param proyecto		proyecto del cual ser eliminado
	 * @throws Exception	excepcion que se levanta al intentar 
	 * 						eliminar al creador del proyecto
	 */
	public void eliminarDe(Proyecto proyecto) throws Exception{
		proyecto.eliminarPerfil(this);
	}
	
	/**
	 * @return
	 */
	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}
	
	/**
	 * @param fechaDeCreacion
	 */
	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}
	
	/**
	 * @return
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
	/**
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @return
	 */
	public Boolean esCreador(){
		return false;
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
