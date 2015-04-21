package bd2.model;

/**
 * Representa a los usuarios del sitio
 * 
 * @author grupo15
 * @since 2015-04-01
 */
public class Usuario {
	
	/**
	 * Id de la entidad
	 */
	private Long id;

	/**
	 * Nombre del usuario
	 */
	private String nombre;
	
	/**
	 * Email del usuario
	 */
	private String email;
	
	public Usuario(){
		super();
	}
	
	/**
	 * Constructor
	 * Recibe todas la variables de la clase
	 * 
	 * @param nombre
	 * @param email
	 */
	public Usuario(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	public String getEmail() {
		return email;
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
