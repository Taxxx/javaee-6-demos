package es.rchavarria.jpa;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdvancedPersistenceTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("test-jpa");
		em = emf.createEntityManager();

		tx = em.getTransaction();
	}
	
	@After
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	public void test() {
		ContactablePerson p = createContactablePerson();

		tx.begin();
		em.persist(p);
		tx.commit();
		
		ContactablePerson pp = em.find(ContactablePerson.class, new Long(1));
		assertEquals(p.getName(), pp.getName());
	}

	private ContactablePerson createContactablePerson() {
		ContactablePerson p = new ContactablePerson();
		p.setName("Foo bar");
		p.setAge(19);
		p.setPhones(createPhones());
		
		return p;
	}

	private List<Phone> createPhones() {
		List<Phone> phones = new ArrayList<Phone>(3);
		
		for(int i = 10; i < 14; i++) {
			Phone phone = new Phone();
			phone.setNumber("" + i);
			
			phones.add(phone);
		}
		
		return phones;
	}

}
