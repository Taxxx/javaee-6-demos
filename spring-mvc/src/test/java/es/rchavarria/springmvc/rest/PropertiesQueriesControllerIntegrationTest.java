package es.rchavarria.springmvc.rest;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import es.rchavarria.springmvc.core.services.PropertyService;
import es.rchavarria.springmvc.rest.domain.Property;

public class PropertiesQueriesControllerIntegrationTest {
  
    private MockMvc mockMvc;

    @InjectMocks
    PropertiesQueriesController controller;

    @Mock
    PropertyService propertyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = standaloneSetup(controller)
        		.setMessageConverters(new MappingJackson2HttpMessageConverter())
        		.build();
    }

    @Test
    public void testRequestAllPropertiesUsesHttpOK() throws Exception {
        when(propertyService.requestAllProperties()).thenReturn(allProperties());

        mockMvc.perform(get("/properties")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

	@Test
    public void testRequestAllPropertiesRendersOkAsJSON() throws Exception {
		when(propertyService.requestAllProperties()).thenReturn(allProperties());

        mockMvc.perform(get("/properties")
            .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
            .andExpect(jsonPath("$[0].city").value("first city"))
	        .andExpect(jsonPath("$[1].address").value("second address"))
	        .andExpect(jsonPath("$[2].price").value(300));
    }

    @Test
    public void testRequestAPropertyUsesHttpOK() throws Exception {
        when(propertyService.findById(anyString())).thenReturn(allProperties().get(0));

        mockMvc.perform(get("/properties/{id}", "an-arbitrary-id")
            .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void testRequestAPropertyRendersAsJSON() throws Exception {
        when(propertyService.findById(anyString())).thenReturn(allProperties().get(0));

        mockMvc.perform(get("/properties/{id}", "an-arbitrary-id")
            .accept(MediaType.APPLICATION_JSON))
//            .andDo(print())
            .andExpect(jsonPath("$.city").value("first city"))
	        .andExpect(jsonPath("$.address").value("first address"))
	        .andExpect(jsonPath("$.price").value(100));
    }

	// fixture method
    private List<Property> allProperties() {
		return Arrays.asList(
				new Property("first city", "first address", 100),
				new Property("second city", "second address", 200),
				new Property("third city", "third address", 300)
				);
	}
}
