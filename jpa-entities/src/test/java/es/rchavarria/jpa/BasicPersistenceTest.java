package es.rchavarria.jpa;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicPersistenceTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("test-jpa");
		em = emf.createEntityManager();

		tx = em.getTransaction();
		tx.begin();
	}
	
	@After
	public void tearDown() {
		tx.commit();
		em.close();
		emf.close();
	}
	
	@Test
	public void test() {
		Person p = createPerson();
		em.persist(p);
		tx.commit();
		
		tx.begin();
		Person pp = em.find(Person.class, new Long(1));
		assertEquals(p.getName(), pp.getName());
	}

	private Person createPerson() {
		Person p = new Person();
		p.setName("Foo bar");
		p.setAge(19);
		
		return p;
	}

}
