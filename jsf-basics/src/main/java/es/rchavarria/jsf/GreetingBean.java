package es.rchavarria.jsf;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "login", eager = true)
public class GreetingBean {

	public String getMessage() {
		return "Login user";
	}

	/**
	 * Action method returning the next view
	*/
	public String submit() {
		return "success";
	}
}
