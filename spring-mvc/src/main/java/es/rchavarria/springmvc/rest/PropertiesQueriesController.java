package es.rchavarria.springmvc.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.rchavarria.springmvc.core.services.PropertyService;
import es.rchavarria.springmvc.rest.domain.Property;

@Controller
@RequestMapping("/properties")
public class PropertiesQueriesController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PropertiesQueriesController.class);
	
	@Autowired
	private PropertyService propertyService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Property> getAllProperties() {
		LOGGER.info("returning all properties");
		return propertyService.requestAllProperties();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Property getProperty(@PathVariable String id) {
		LOGGER.info("parameter id: " + id);
		Property property = propertyService.findById(id);
		LOGGER.info("property: " + property.toString());
		return property;
	}
}
