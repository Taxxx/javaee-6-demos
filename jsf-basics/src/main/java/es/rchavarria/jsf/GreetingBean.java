package es.rchavarria.jsf;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "welcome", eager = true)
public class GreetingBean {

	public String getMessage() {
		return "A welcome message to you: Hello World!";
	}

	/**
	 * Action method returning the next view
	*/
	public String submit() {
		return "success";
	}
}
