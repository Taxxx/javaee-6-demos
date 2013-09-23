package es.rchavarria.ws.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.rchavarria.ws.UsersManagement;
import es.rchavarria.ws.UsersManagementService;

public class JaxWsClientTest {

	private UsersManagement port;

	@Before
	public void setUp() {
		UsersManagementService service = new UsersManagementService();
		port = service.getUsersManagementPort();
	}
	
	@Test
	public void test() {
		assertEquals(1, port.addUser("The boss"));
		assertEquals(2, port.addUser("The king"));
		assertEquals(3, port.addUser("The queen"));
		
		assertEquals("The boss", port.getUser(1));
		assertEquals("The queen", port.getUser(3));
		assertEquals("The king", port.getUser(2));
	}

}
