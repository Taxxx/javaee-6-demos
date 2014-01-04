package es.rchavarria.springmvc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.rchavarria.springmvc.core.services.PropertyService;

@Controller
@RequestMapping("/properties")
public class PropertiesQueriesController {
	
	@Autowired
	private PropertyService propertyService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<String> getAllProperties() {
		return propertyService.requestAllProperties();
	}
}
