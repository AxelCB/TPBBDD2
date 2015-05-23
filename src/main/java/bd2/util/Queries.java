package bd2.util;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bd2.model.Pizarra;
import bd2.model.Tarea;

public class Queries {
	
	private static SessionFactory factory;

	public static void main(String[] args) {
		Session session = null;
		try{
			Configuration cfg = new Configuration();
			cfg.configure("hibernate/hibernate.cfg.xml");
			factory = cfg.buildSessionFactory();
			session = factory.openSession();

			listarPizzarras(session);
			listarTareasConDescripcion(session,"%site member%");
			pizarraConMasTareas(session);
			emailsDeAdministradorDeProyectosConMasDeUnaPizarraArchivada(session);
			tareasQueHayanPasadoPorPizarra(session,"backlogproyecto8149");  
			tareasConMasPasosQue(session, 2);
			pizarrasConTareasDeAmbosTipos(session);
			tareasPizarrasVencidasMarzo(session);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session.isConnected()){
				session.close();
			}
		}
		
	}
	
	public static void listarPizzarras(Session session){
		System.out.println("--------------------------------------");
		System.out.println("a) Listar los nombres de todas las pizarras.Imprimir en consola: ”Pizarra: <nombre>”");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("SELECT nombre FROM Pizarra");
		String impresion = "Pizarra: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+(String)object);
		}
		System.out.println("--------------------------------------");
	}
	
	public static void listarTareasConDescripcion(Session session,String descripcionParametro){
		System.out.println("--------------------------------------");
		System.out.println("b) Listar las tareas cuya descripción contenga una secuencia específica de caracteres (enviada como parámetro).Imprimir en consola: ”Tarea: <descripción>”");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("FROM Tarea as t WHERE descripcion LIKE :descripcionParametro").
				setString("descripcionParametro", descripcionParametro);
		String impresion = "Tarea: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+((Tarea)object).getDescripcion());
		}
		System.out.println("--------------------------------------");
	}
	
	public static void pizarraConMasTareas(Session session){
		System.out.println("--------------------------------------");
		System.out.println("c)Obtener la Pizarra que tenga más tareas. Imprimir ”Pizarra con más tareas: <nombre> (<cantidad de tareas> tareas)”");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("FROM Pizarra as p ORDER BY p.tareas.size DESC ").setMaxResults(1);
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println("Pizarra con más tareas: "+((Pizarra)object).getNombre()+" ("+((Pizarra)object).getTareas().size()+" tareas)");
		}
		System.out.println("--------------------------------------");

	}
	
	public static void emailsDeAdministradorDeProyectosConMasDeUnaPizarraArchivada(Session session){
		System.out.println("--------------------------------------");
		System.out.println("d)Obtener los emails de los administradores de los proyectos que tengan al menos una pizarra archivada. Imprimir “Administrador: <email>”");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("SELECT DISTINCT(u.email) "
			+ "FROM Proyecto as p "
				+ "INNER JOIN p.perfiles as pda "
				+ "INNER JOIN pda.usuario as u "
			+ "WHERE p.pizarrasArchivadas.size > 0");
		String impresion = "Administrador: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+(String)object);
		}
		System.out.println("--------------------------------------");
	}
	
	public static void tareasQueHayanPasadoPorPizarra(Session session,String descripcionParametro){
		System.out.println("--------------------------------------");
		System.out.println("e)Obtener las tareas que hayan pasado por la pizarra cuyo nombre contenga una secuencia de caracteres enviada como parámetro. Imprimir “Tarea: <descripción>”");
		System.out.println("--------------------------------------");
		Query query = session.
				createQuery("SELECT t FROM Tarea as t "
							+ "INNER JOIN t.pasos as pa "
							+ "INNER JOIN pa.pizarra as pi "
						+ "WHERE pi.nombre LIKE :descripcionParametro").
						setString("descripcionParametro", descripcionParametro);
		String impresion = "Tarea: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+((Tarea)object).getDescripcion());
		}
		System.out.println("--------------------------------------");
	}
	
	public static void tareasConMasPasosQue(Session session,Integer cantPasos){
		System.out.println("--------------------------------------");
		System.out.println("f)Obtener las tareas que hayan sido cambiadas de pizarra más de un número veces enviado como parámetro. Imprimir “Tarea: <descripción> (<cantidad de pasos> pasos)”");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("FROM Tarea as t WHERE t.pasos.size > :cantPasos ").setInteger("cantPasos", cantPasos);
		String impresion = "Tarea: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+((Tarea)object).getDescripcion()+"("+((Tarea)object).getPasos().size()+" pasos)");
		}
		System.out.println("--------------------------------------");
	}
	
	public static void pizarrasConTareasDeAmbosTipos(Session session){
		System.out.println("--------------------------------------");
		System.out.println("g)Obtener las pizarras que tengan tareas tanto de investigación como de desarrollo. Imprimir ”Pizarra: <nombre>” ");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("SELECT distinct(p) FROM Pizarra as p,TareaDeDesarrollo tdd,TareaDeInvestigacion tdi "
				+ "WHERE (tdd in elements(p.tareas) AND tdi in elements(p.tareas))");
		String impresion = "Pizarra: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+((Pizarra)object).getNombre());
		}
		System.out.println("--------------------------------------");
	}
	
	public static void tareasPizarrasVencidasMarzo(Session session){
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(2015, 2, 1);
		endDate.setTime(startDate.getTime());
		endDate.add(Calendar.MONTH, 1);
		endDate.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println("--------------------------------------");
		System.out.println("h)Obtener las pizarras que tengan tareas vencidas en marzo, es decir que sus fechas límite estén dentro marzo de 2015 y no estén completas. Imprimir “Pizarra: <nombre>”");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("SELECT DISTINCT(p) FROM Pizarra as p INNER JOIN p.tareas as t "
				+ "WHERE t.fechaLimite BETWEEN :startDate AND :endDate )")
				.setDate("startDate", startDate.getTime())
				.setDate("endDate", endDate.getTime());
		String impresion = "Pizarra: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+((Pizarra)object).getNombre());
		}
		System.out.println("--------------------------------------");
	}
	
//	public static void listarGenerico(Session session, Query query, String enunciado, String impresion){
//		System.out.println("--------------------------------------");
//		System.out.println(enunciado);
//		System.out.println("--------------------------------------");
//		List<Object> resultados = Queries.ejecutar(session, query);
//		for (Object object : resultados) {
//			System.out.println(impresion+(String)object);
//		}
//		System.out.println("--------------------------------------");
//	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> ejecutar(Session session,Query query){	
		Transaction tx = null;
		List<Object> resultados = null;
		try{
			tx = session.beginTransaction();
			resultados = query.list();
			tx.commit();
		}catch(Exception e){
			if(tx != null && tx.isActive()){
				tx.rollback();
			}
			e.printStackTrace();
		}
		return resultados;
	}
	
}
