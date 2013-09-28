package es.rchavarria.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class BasicPersistenceTest {

	@Test
	public void test() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-jpa");
		EntityManager em = emf.createEntityManager();
		
		Person p = new Person();
		p.setName("Foo bar");
		p.setAge(19);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
		
		tx.begin();
		Person pp = em.find(Person.class, new Long(1));
		assertEquals(p.getName(), pp.getName());
		tx.commit();

		em.close();
		emf.close();
	}

}
