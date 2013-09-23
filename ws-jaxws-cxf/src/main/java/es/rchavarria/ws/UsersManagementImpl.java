package es.rchavarria.ws;

import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "es.rchavarria.ws.UsersManagement",
            serviceName = "Users")
public class UsersManagementImpl implements UsersManagement {

	private List<String> users = new LinkedList<String>();
	
	public String getUser(int userId) {
		userId--; // index starts at 0
		
		if(userId >= users.size()) 
			throw new RuntimeException("User not found. Id requested: " + userId);
		
		return users.get(userId);
	}

	public int addUser(String name) {
		users.add(name);
		
		return users.size();
	}
 
}