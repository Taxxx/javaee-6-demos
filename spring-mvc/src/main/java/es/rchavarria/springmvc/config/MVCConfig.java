package es.rchavarria.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "es.rchavarria.springmvc.rest", "es.rchavarria.springmvc.core.services" })
public class MVCConfig {}