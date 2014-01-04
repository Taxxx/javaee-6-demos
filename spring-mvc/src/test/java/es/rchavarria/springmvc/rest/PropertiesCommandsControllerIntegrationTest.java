package es.rchavarria.springmvc.rest;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

public class PropertiesCommandsControllerIntegrationTest {
  
    private MockMvc mockMvc;

    @InjectMocks
    PropertiesCommandsController controller;

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
    public void testDeletePropertyUsesHttpOK() throws Exception {
        mockMvc.perform(delete("/properties/{id}", "id-to-delete")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
        
        verify(propertyService).deleteProperty("id-to-delete");
    }

    @Test
    public void testCreatePropertyUsesHttpOK() throws Exception {
        mockMvc.perform(post("/properties")
        	.content("{\"city\":\"a new city\",\"address\":\"a new address\",\"price\":54321}")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
        
        verify(propertyService).createProperty(any(Property.class));
    }
}
