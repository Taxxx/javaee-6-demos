package es.rchavarria.springmvc.core.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import es.rchavarria.springmvc.rest.domain.Property;

@Component
public class InMemoryPropertyService implements PropertyService {

	private Map<String, Property> repository;
	
	public InMemoryPropertyService() {
		repository = new HashMap<String, Property>();

		repository.put("1", new Property("city 1", "address 1", 1));
		repository.put("2", new Property("city 2", "address 2", 2));
		repository.put("3", new Property("city 3", "address 3", 3));
	}
	
	@Override
	public List<Property> requestAllProperties() {
		return new ArrayList<Property>(repository.values());
	}

	@Override
	public Property findById(String id) {
		return repository.get(id);
	}

}
