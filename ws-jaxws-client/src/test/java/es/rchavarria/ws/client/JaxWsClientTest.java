package es.rchavarria.ws.client;

import static org.junit.Assert.fail;

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
		fail("Not yet implemented");
	}

}
