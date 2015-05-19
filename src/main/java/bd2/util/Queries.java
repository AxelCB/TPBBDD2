package bd2.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session.isConnected()){
				session.close();
			}
		}
		
	}
	
	public static void listarPizzarras(Session session){
		Query query = session.createQuery("SELECT nombre FROM Pizarra");
		String impresion = "Pizarra: {0}";
		String enunciado = "surubi surubi:";
		listarGenerico(session, query, enunciado, impresion);
	}
	
	public static void listarGenerico(Session session, Query query, String enunciado, String impresion){
		System.out.println("--------------------------------------");
		System.out.println(enunciado);
		System.out.println("--------------------------------------");
		List<Object> resultados = Queries.ejecutar(session, query);
		for (Object object : resultados) {
			System.out.println(impresion+(String)object);
		}
		System.out.println("--------------------------------------");
	}
	
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
