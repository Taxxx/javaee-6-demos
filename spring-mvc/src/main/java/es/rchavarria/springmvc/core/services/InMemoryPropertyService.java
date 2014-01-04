package es.rchavarria.springmvc.core.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import es.rchavarria.springmvc.rest.domain.Property;

@Component
public class InMemoryPropertyService implements PropertyService {

	private Map<String, Property> repository = new HashMap<String, Property>();
	
	@Override
	public List<Property> requestAllProperties() {
		return new ArrayList<Property>(repository.values());
	}

	@Override
	public Property findById(String id) {
		return repository.get(id);
	}

}
