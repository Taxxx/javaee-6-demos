package es.rchavarria.jsf;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "paramReader", eager = true)
public class ParamReaderBean {

	public String getTitle() {
		return "Here is the result of reading parameters";
	}

}
