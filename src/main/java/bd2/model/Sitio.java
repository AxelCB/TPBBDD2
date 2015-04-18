package bd2.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Representa el sitio, el cual engloba
 * a todos los usuarios registrados y a 
 * todos los proyectos creados
 * 
 * @author grupo15
 * @since 2015-04-01
 */
public class Sitio {
	
	/**
	 * Id de la entidad
	 */
	private Long id;

	/**
	 * Coleccion de usuarios pertenecientes al sitio
	 */
	private Collection<Usuario> usuarios = new HashSet<Usuario>();
	
	/**
	 * Coleccion de proyectos creados en el sitio
	 */
	private Collection<Proyecto> proyectos = new HashSet<Proyecto>();

	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param usuarios
	 * @param proyectos
	 */
	public Sitio(Collection<Usuario> usuarios, Collection<Proyecto> proyectos) {
		super();
		this.usuarios = usuarios;
		this.proyectos = proyectos;
	}

	/**
	 * Constructor
	 * No recibe ningun parametro
	 */
	public Sitio() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Agrega un nuevo proyecto al sitio
	 * 
	 * @param proyecto
	 */
	public void agregarProyecto(Proyecto proyecto){
		this.proyectos.add(proyecto);
	}
	
	/**
	 * Agrega un nuevo usuario al sitio
	 * 
	 * @param usuario
	 */
	public void registrarUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}

	/**
	 * @return
	 */
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios
	 */
	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return
	 */
	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}

	/**
	 * @param proyectos
	 */
	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
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
