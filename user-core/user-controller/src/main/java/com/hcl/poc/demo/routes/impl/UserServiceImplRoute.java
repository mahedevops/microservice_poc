package com.hcl.poc.demo.routes.impl;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.hcl.poc.demo.services.UserService;
import com.hcl.poc.demo.services.exceptions.NoRecordFoundException;
@Component
public class UserServiceImplRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
   
     onException(NoRecordFoundException.class)
         .log(LoggingLevel.ERROR,"${exception.message}")
         .setBody(constant(null))
         .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("204"))
         .handled(true);
     
     onException(Exception.class)
         .log(LoggingLevel.ERROR,"${exception.stacktrace}")
         .setBody(constant(null))
         .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"))
         .handled(true);
     
		
		from("direct:getUsers")
		.routeId("getUsersImplRoutes")
		.log(LoggingLevel.INFO, "Entering UserServiceImplRoute#getUsersImplRoutes")
		.bean(UserService.class,"getUsers")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
		.log(LoggingLevel.INFO, "Exiting UserServiceImplRoute#getUsersImplRoutes");
		
		from("direct:getUserById")
		.routeId("getUserByIdImplRoutes")
		.log(LoggingLevel.INFO, "Entering UserServiceImplRoute#getUserByIdImplRoutes")
		.bean(UserService.class,"getUserById")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
		.log(LoggingLevel.INFO, "Exiting UserServiceImplRoute#getUserByIdImplRoutes");
		
		from("direct:createUser")
		.routeId("createUserImplRoutes")
		.log(LoggingLevel.INFO, "Entering UserServiceImplRoute#createUserImplRoutes")
		.bean(UserService.class,"createUser")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
		.log(LoggingLevel.INFO, "Exting UserServiceImplRoute#createUserImplRoutes");
		
		from("direct:updateUser")
		.routeId("updateUserImplRoutes")
		.log(LoggingLevel.INFO, "Entering UserServiceImplRoute#updateUserImplRoutes")
		.bean(UserService.class,"updateUser")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
		.log(LoggingLevel.INFO, "Exting UserServiceImplRoute#updateUserImplRoutes");
		
		
		from("direct:deleteUser")
		.routeId("deleteUserImplRoutes")
		.log(LoggingLevel.INFO, "Entering UserServiceImplRoute#deleteUserImplRoutes")
		.bean(UserService.class,"deleteUser")
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
		.log(LoggingLevel.INFO, "Exiting UserServiceImplRoute#deleteUserImplRoutes");
		
		
	}

}
