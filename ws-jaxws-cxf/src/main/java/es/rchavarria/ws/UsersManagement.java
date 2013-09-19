package es.rchavarria.ws;

import javax.jws.WebService;
import javax.jws.WebParam;

@WebService
public interface UsersManagement {

	public String getUser(@WebParam(name="userId") int userId);
	
	public int addUser(@WebParam(name="name") String name);
}
