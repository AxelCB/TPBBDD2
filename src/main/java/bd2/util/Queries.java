package bd2.util;

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
			listarTareasConDescripcion(session,"comprobar");
//			pizarraConMasTareas(session);
			emailsDeAdministradorDeProyectosConMasDeUnaPizarraArchivada(session);
			tareasConMasPasosQue(session, 2);
			pizarrasConTareasDeAmbosTipos(session);
			
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
				setString("descripcionParametro", "%"+descripcionParametro+"%");
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
		Query query = session.createQuery("FROM Pizarra WHERE id = ("
				+ "SELECT max(conteo) FROM ("
					+ "SELECT count(t.id) as conteo FROM Pizzara as p INNER JOIN p.tareas as t GROUP BY p.id))");
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println("Pizarra con más tareas: "+((Pizarra)object).getNombre()+" ("+((Pizarra)object).getTareas().size()+" tareas)");
		}
		System.out.println("--------------------------------------");
		/*
		 * SQL
	select * from Pizarra 
	WHERE id = (
		SELECT max(conteo) 
		FROM (
			SELECT count(t.id) AS conteo 
			FROM Pizarra as p 
				INNER JOIN Tarea as t ON(p.id=t.tarea_id)
			GROUP BY p.id
		) cuenta
	);
		 */
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
	
	public static void tareasQueHayanPasadoPorPizarra(Session session){
		System.out.println("--------------------------------------");
		System.out.println("e)Obtener las tareas que hayan pasado por la pizarra cuyo nombre contenga una secuencia de caracteres enviada como parámetro. Imprimir “Tarea: <descripción>”");
		System.out.println("--------------------------------------");
		Query query = session.createQuery("FROM Tarea as t INNER JOIN  ");
		String impresion = "Administrador: ";		
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+(String)object);
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
		Query query = session.createQuery("FROM Pizarra WHERE id in ("
				+ " SELECT p.id FROM TareaDeDesarrollo as tdd INNER JOIN Pizarra as p "
				+ ") AND id in ("
				+ " SELECT p.id FROM TareaDeInvestigacion as tdd INNER JOIN Pizarra as p )");
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
