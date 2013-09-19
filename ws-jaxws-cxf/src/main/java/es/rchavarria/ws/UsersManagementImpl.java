package es.rchavarria.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "es.rchavarria.ws.UsersManagement",
            serviceName = "Users")
public class UsersManagementImpl implements UsersManagement {

	public String getUser(int userId) {
		return "foo bar user";
	}

	public int addUser(String name) {
		return 42;
	}
 
}