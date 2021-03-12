package hibernate_04;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Demo {

	public static void main(String[] args) {
		
		Person person = null;
		
		// TODO Auto-generated method stub
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		List<Object> list = session.createQuery("").list(); 
		// bu query icerisine istedigin query yazilabiliyor sonucu type neyse oyle dondurulmesi 
		//gerekiyor. yani bir list de olabilir objede olabilir. list olacaksa q1.list() kullanilir. objeyse uniqueResult kullanilir.
		//SQLQuery seklinde cekmek istersek objenin type ini belirtmek gerekiyor. onu da addEntity(...class) seklinde veriyoruz
		//------->>>>Native Query<<<<-------
		
		/*
		 * SQLQuery query = session.createSQLQuery("select name, marks from student where marks>60");
		 * query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		 * List students = query.list();
		 * 
		 * for(Object o : students){
		 * 		Map m = (Map) o;
		 * 		System.out.println(m.get("name") + " : " + m.get("marks"));
		 * }
		 * 
		 */
		
		
		
		System.out.println(list);

		session.getTransaction().commit();
		session.close();

		Session session2 = sf.openSession();
		session2.beginTransaction(); 
		
		Query q2 = session2.createQuery("from person where id=101");
		
		person = (Person)q2.uniqueResult();
		System.out.println(person);

		session2.getTransaction().commit();
		session2.close();
		
	}

}
