package com.hcl.poc.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.component.swagger.DefaultCamelSwaggerServlet;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class ApplicationConfig {
	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	CamelContextConfiguration contextConfiguration(@Value("${service.name}") String name) {
		return new CamelContextConfiguration() {

			@Override
			public void beforeApplicationStart(CamelContext cc) {
				SpringCamelContext springCamelContext = (SpringCamelContext) cc;
				springCamelContext.setName(name);
				objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			}

			@Override
			public void afterApplicationStart(CamelContext cc) {

			}
		};
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean camelServlet(@Value("${service.name}") String serviceName,
			@Value("${service.version}") String serviceVersion) {
		ServletRegistrationBean servlet = new ServletRegistrationBean();
		servlet.setServlet(new CamelHttpTransportServlet());
		servlet.addUrlMappings(String.format("/%s/%s/*", serviceName, serviceVersion));
		servlet.setName("CamelServlet");
		return servlet;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Bean
//    public ServletRegistrationBean swaggerServlet() {
//        ServletRegistrationBean swagger = new ServletRegistrationBean(new DefaultCamelSwaggerServlet(), "/{{service.name}}/{{service.version}}/");
//        Map<String, String> params = new HashMap<>();
//        //params.put("base.path", "/{{service.name}}/{{service.version}}/");
//        params.put("base.path", "api");
//        params.put("api.title", "my api title");
//        params.put("api.description", "my api description");
//        params.put("api.termsOfServiceUrl", "termsOfServiceUrl");
//        params.put("api.license", "license");
//        params.put("api.licenseUrl", "licenseUrl");
//        swagger.setInitParameters(params);
//        return swagger;
//    }

}
