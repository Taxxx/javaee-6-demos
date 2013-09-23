package es.rchavarria.ws;

import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "es.rchavarria.ws.UsersManagement",
            serviceName = "Users")
public class UsersManagementImpl implements UsersManagement {

	private List<String> users = new LinkedList<String>();
	
	public String getUser(int userId) {
		return "foo bar user";
	}

	public int addUser(String name) {
		users.add(name);
		
		return users.size();
	}
 
}