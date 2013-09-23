package es.rchavarria.ws;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UsersManagementImplTest {

	private UsersManagementImpl sut;

	@Before
	public void setUp() {
		sut = new UsersManagementImpl();
	}
	
	@Test
	public void testAddOneUser() {
		assertEquals(1, sut.addUser("fisrt"));
	}
	
	@Test
	public void testAddSeveralUsers() {
		assertEquals(1, sut.addUser("first"));
		assertEquals(2, sut.addUser("second"));
		assertEquals(3, sut.addUser("third"));
	}
	
	@Test
	public void testAddUserDoNotDeleteRepeatedUsers() {
		assertEquals(1, sut.addUser("first"));
		assertEquals(2, sut.addUser("second"));
		assertEquals(3, sut.addUser("first"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetUserWithoutUsers() {
		assertEquals(null, sut.getUser(-1));
	}

	@Test
	public void testGetUser() {
		sut.addUser("first");
		assertEquals("first", sut.getUser(1));
	}
}
