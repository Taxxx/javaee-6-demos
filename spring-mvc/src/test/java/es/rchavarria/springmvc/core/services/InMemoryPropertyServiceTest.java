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

		assertEquals("city 1", property.getCity());
		assertEquals("address 1", property.getAddress());
		assertEquals(1, property.getPrice());
	}
	
	@Test
	public void testDeleteProperty() {
		assertEquals(3, service.requestAllProperties().size());
		
		service.deleteProperty("1");
		
		assertEquals(2, service.requestAllProperties().size());
	}
	
	@Test
	public void testCreateProperty() {
		assertEquals(3, service.requestAllProperties().size());
		
		service.createProperty(Property.fromAttributes("a city", "an address", 54321));
		
		assertEquals(4, service.requestAllProperties().size());
	}
	
	@Test
	public void testUpdateProperty() {
		service.createProperty(Property.fromAttributes("a city", "an address", 54321));
		assertEquals(4, service.requestAllProperties().size());
		
		service.updateProperty(Property.fromAttributes("a city", "different address", 12345));
		
		Property property = service.findById("a city");
		assertEquals( "it shouldn't create a new property", 
				4, service.requestAllProperties().size());
		assertEquals("it should update address",
				"different address", property.getAddress());
		assertEquals("it should update price",
				12345, property.getPrice());
	}

}
