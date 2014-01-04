package es.rchavarria.springmvc.core.services;

import java.util.List;

import es.rchavarria.springmvc.rest.domain.Property;

public interface PropertyService {

	List<Property> requestAllProperties();

	Property findById(String id);

	void deleteProperty(String string);

	void createProperty(Property property);

}
