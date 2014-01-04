package es.rchavarria.springmvc.core.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.rchavarria.springmvc.rest.domain.Property;

public class InMemoryPropertyServiceTest {

	private PropertyService service;

	@Before
	public void setUp() {
		service = new InMemoryPropertyService();
	}
	
	@Test
	public void testGetAll() {
		assertEquals(3, service.requestAllProperties().size());
	}
	
	@Test
	public void testGetFirst() {
		Property property = service.findById("1");

		assertEquals("city 1", property.city);
		assertEquals("address 1", property.address);
		assertEquals(1, property.price);
	}
	
	@Test
	public void testDeleteProperty() {
		assertEquals(3, service.requestAllProperties().size());
		
		service.deleteProperty("1");
		
		assertEquals(2, service.requestAllProperties().size());
	}

}
