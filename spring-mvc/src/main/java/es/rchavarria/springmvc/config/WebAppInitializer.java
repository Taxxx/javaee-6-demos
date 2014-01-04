package es.rchavarria.springmvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOGGER.info("there are not config classes for the root context");
        return new Class<?>[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOGGER.info("servlet config classes are: " + MVCConfig.class.getSimpleName());
        return new Class<?>[] { MVCConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        LOGGER.info("servlet mappings are: '/'");
        return new String[] { "/" };
    }

}