package bd2.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 * La clase proyecto se compone de pizarras,
 * las cuales se pueden archivar, y de perfiles de
 * usuario que participan en el. Uno de los perfiles
 * es de administrado, el cual es el creador del 
 * proyecto
 * 
 * @author 	grupo15
 * @since	2015-04-01
 *
 */
public class Proyecto {
	
	/**
	 * Id de la entidad
	 */
	private Long id;

	/**
	 * Contiene la coleccion de perfiles de usuarios que participan en el proyecto
	 */
	private Collection<PerfilDeUsuario> perfiles = new HashSet<PerfilDeUsuario>();
	
	/**
	 * Contiene la coleccion de pizarras que se utilizan en el proyecto
	 */
	private Collection<Pizarra> pizarras = new HashSet<Pizarra>();
	
	/**
	 * Contiene la coleccion de pizarras archivadas del proyecto
	 */
	private Collection<Pizarra> pizarrasArchivadas = new HashSet<Pizarra>();

	public Proyecto() {
		super();
	}

	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param perfiles				lista de perfiles de usuario que participan en el proyecto
	 * @param pizarras				lista de pizarras que se utilizan en el proyecto
	 * @param pizarrasArchivadas 	lista de pizarras archivadas del proyecto
	 */
	public Proyecto(Collection<PerfilDeUsuario> perfiles, Collection<Pizarra> pizarras, Collection<Pizarra> pizarrasArchivadas) {
		super();
		this.perfiles = perfiles;
		this.pizarras = pizarras;
		this.pizarrasArchivadas = pizarrasArchivadas;
	}

	/**
	 * Contructor
	 * El usuario enviado como parámetro tiene que agregarse al
	 * proyecto con perfil de administrador, y marcarse como creador
	 * 
	 * 
	 * @param creador	usuario con el cual se creara el perfil de administrador
	 */
	public Proyecto(Usuario creador) {
		super();
		this.perfiles.add(new PerfilDeAdministrador(new Date(), creador, true));
	}
	
	/**
	 * Genera un perfil de usuario administrador (PerfilDeAdministrador)
	 * con el usuario enviado como parámetro, y lo agrega a la colección de perfiles
	 * 
	 * @param usuario	usuario con el cual se creara el perfil de administrador
	 */
	public void agregarAdministrador(Usuario usuario){
		this.perfiles.add(new PerfilDeAdministrador(new Date(), usuario, false));
	}
	
	/**
	 * Genera un perfil de usuario colaborador (PerfilDeUsuario)
	 * con el usuario enviado como parámetro, y lo agrega a la colección de perfiles
	 * 
	 * @param usuario	usuario con el cual se creara el perfil de usuario
	 */
	public void agregarColaborador(Usuario usuario){
		this.perfiles.add(new PerfilDeUsuario(new Date(), usuario));
	}

	/**
	 * Retorna los usuarios dentro de los perfiles. Notar que retorna
	 * objetos de la clase Usuario, no de la clase PerfilDeUsuario.
	 * 
	 * @return coleccion de usuarios que son parte del proyecto
	 */
	public Collection<Usuario> getIntegrantes(){
		
		Collection<Usuario> users = new HashSet<Usuario>();
		Iterator<PerfilDeUsuario> i = this.perfiles.iterator();
	    
		while (i.hasNext()) {
	    	users.add(i.next().getUsuario());
	    }
		return users;
	}
	
	/**
	 * Elimina al usuario enviado como parámetro. 
	 * Si el usuario que se desea eliminar es el creador, 
	 * lanza una excepción con el mensaje: 
	 * "No se puede eliminar al creador del proyecto"
	 * 
	 * @param candidato		usuario a eliminar
	 * @throws Exception	excepcion que se levanta al intentar 
	 * 						eliminar al creador del proyecto
	 */
	public void eliminarUsuario(Usuario candidato) throws Exception{

		Iterator<PerfilDeUsuario> i = this.perfiles.iterator();
	    PerfilDeUsuario u;
	    Boolean fin = false;

		while ((i.hasNext()) && (!fin)) {
			u = i.next();
	    	if (u.getUsuario().equals(candidato)){
	    		if (!u.esCreador()){
	    			this.perfiles.remove(u);
	    			fin = true;
	    		}
	    		else throw new Exception("No se puede eliminar al creador del proyecto");
	    	}
	    }
	}
	
	/**
	 * @param perfilDeUsuario	perfil del usuario a ser eliminado
	 * @throws Exception		excepcion que se levanta al intentar 
	 * 							eliminar al creador del proyecto
	 */
	public void eliminarPerfil(PerfilDeUsuario perfilDeUsuario) throws Exception {
		this.eliminarUsuario(perfilDeUsuario.getUsuario());		
	}
	
	/**
	 * Archiva la pizarra enviada como parámetro.
	 * Esto significa que deja de estar en la colección de pizarras 
	 * para pasar a la colección de pizarras archivadas
	 * 
	 * @param pizarra pizarra perteneciente al proyecto que pasa a ser archivada
	 */
	public void archivarPizarra(Pizarra pizarra){
		this.pizarrasArchivadas.add(pizarra);
		this.pizarras.remove(pizarra);
	}
	
	/**
	 * @return
	 */
	public Collection<PerfilDeUsuario> getPerfiles() {
		return perfiles;
	}

	/**
	 * @param perfiles
	 */
	public void setPerfiles(Collection<PerfilDeUsuario> perfiles) {
		this.perfiles = perfiles;
	}

	/**
	 * @return
	 */
	public Collection<Pizarra> getPizarras() {
		return pizarras;
	}

	/**
	 * @param pizarras 	
	 */
	public void setPizarras(Collection<Pizarra> pizarras) {
		this.pizarras = pizarras;
	}

	/**
	 * @return
	 */
	public Collection<Pizarra> getPizarrasArchivadas() {
		return pizarrasArchivadas;
	}

	/**
	 * @param pizarrasArchivadas
	 */
	public void setPizarrasArchivadas(Collection<Pizarra> pizarrasArchivadas) {
		this.pizarrasArchivadas = pizarrasArchivadas;
	}

	/**
	 * Agrega la pizarra pasada como parametro a la coleccion
	 * de pizarras del proyecto.
	 * 
	 * @param pizarra
	 */
	public void agregarPizarra(Pizarra pizarra) {
		this.pizarras.add(pizarra);		
	}

	/**
	 * @return	usuario creador del proyecto
	 */
	public Usuario getCreador() {
		Iterator<PerfilDeUsuario> i = this.perfiles.iterator();
	    PerfilDeUsuario u = null;
	    Boolean fin = false;

		while ((i.hasNext()) && (!fin)) {
			u = i.next();
	    	if (u.esCreador()) fin = true;
	    }
		return u.getUsuario();
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
