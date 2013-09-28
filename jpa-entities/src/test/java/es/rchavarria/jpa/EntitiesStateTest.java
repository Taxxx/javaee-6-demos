package es.rchavarria.jpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntitiesStateTest {

	private static EntityManagerFactory emf;
    private EntityManager em;
	private EntityTransaction tx;

    @BeforeClass
    public static void classSetUp() {
    	emf = Persistence.createEntityManagerFactory("test-jpa");
    }
    
    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        
        tx = em.getTransaction();
        tx.begin();
    }

    @After
    public void tearDown() throws Exception {
    	if(tx.isActive()) tx.commit();
    	if(em.isOpen()) em.close();
    }
    
    @AfterClass
    public static void classTearDown() {
    	emf.close();
    }
    
	@Test
	public void testNewState() {
		ContactablePerson p = createContactablePerson();
		
		assertFalse("entity's state is 'new'", em.contains(p));
	}

	@Test
	public void testFromNewToManaged() {
		ContactablePerson p = createContactablePerson();

		em.persist(p);
		assertTrue("entity's state is 'managed'", em.contains(p));
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
