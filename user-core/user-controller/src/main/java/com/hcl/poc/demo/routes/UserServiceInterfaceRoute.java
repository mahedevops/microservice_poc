package com.hcl.poc.demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.hcl.poc.demo.model.User;


@Component
public class UserServiceInterfaceRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		restConfiguration()
		.component("{{server.component}}")
		.host("{{server.host}}").port("{{server.port}}")
		.bindingMode(RestBindingMode.json)
		.dataFormatProperty("prettyPrint", "true")
		.contextPath("/{{service.name}}/{{service.version}}")
		.apiContextPath("/")
		.apiProperty("api.title", "{{api.title}}")
		.apiProperty("api.version", "{{api.version}}")
		.apiProperty("api.description", "{{api.description}}");
		
		
		
		rest()
		.get("/users/")
		.id("getUsers")
        .description("get all user")
        .outType(User[].class)
        .responseMessage()
        	.code(200)
        .endResponseMessage()
        .to("direct:getUsers")
		
		.get("/users/{userId}")
		.id("getUserById")
        .description("get user")
       		.param()
       			.name("userId")
       			.type(RestParamType.query)
       			.dataType("Long")
       			.description("get user details by Id")
       		.endParam()
        .outType(User.class)
        .responseMessage()
        	.code(200)
        .endResponseMessage()
        .to("direct:getUserById")
		
		.post("/users")
		.id("createUser")
        .description("create user")
        .type(User.class)
        .outType(User.class)
        .responseMessage()
        	.code(201)
        	.message("User successfully created")
        .endResponseMessage()
        .to("direct:createUser")
		
		.put("/users/{userId}")
		.id("updateUser")
        .description("update an user")
        .param()
       			.name("userId")
       			.type(RestParamType.query)
       			.description("get user details by Id")
       	.endParam()
        .type(User.class)
        .outType(User.class)
        .responseMessage()
        	.code(200)
        .endResponseMessage()
        .to("direct:updateUser")
		
		.delete("/users/{userId}")
		.id("deleteuser")
        .description("delete an user")
        .param()
       			.name("userId")
       			.type(RestParamType.query)
       			.description("delete user by Id")
       	.endParam()
       	.responseMessage()
        	.code(200)
        .endResponseMessage()
        .to("direct:deleteUser");
	}

}
