package es.rchavarria.springmvc.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

public class PropertiesQueriesIntegrationTest {
  
    MockMvc mockMvc;

    @InjectMocks
    PropertiesQueriesController controller;

    @Mock
    PropertyService propertyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = standaloneSetup(controller)
        		.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testRequestAllCoursesUsesHttpOK() throws Exception {
        when(propertyService.requestAllProperties()).thenReturn(allProperties());

        this.mockMvc.perform(get("/properties")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

	@Test
    public void testRequestAllCoursesRendersOkAsJSON() throws Exception {
		when(propertyService.requestAllProperties()).thenReturn(allProperties());

        this.mockMvc.perform(get("/properties")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print());
        // TODO add expectations
    }

	// fixture method
    private List<String> allProperties() {
		return Arrays.asList("one", "two", "three");
	}
}
