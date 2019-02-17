package com.hcl.poc.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcl.poc.demo.dao.UserRepository;
import com.hcl.poc.demo.entity.UserEntity;
import com.hcl.poc.demo.model.User;
import com.hcl.poc.demo.services.exceptions.NoRecordFoundException;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<User> getUsers(Exchange exchange) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		Iterable<UserEntity> userEntities = userRepository.findAll();
		List<User> users = new ArrayList<>();
		userEntities.forEach(userEntity -> {
			User user = modelMapper.map(userEntity, User.class);
			users.add(user);
		});
		return users;

	}

	public User getUserById(Exchange exchange) throws NoRecordFoundException {

		Map<String, Object> headers = exchange.getIn().getHeaders();
		String userId = (String) headers.get("userId");
		UserEntity userEntity = userRepository.findById(Long.valueOf(userId))
				.orElse(null);
		if(userEntity == null)
			throw new NoRecordFoundException("No records found for userId: "+userId);
		User user = modelMapper.map(userEntity, User.class);
		return user;

	}

	public void createUser(Exchange exchange) {

		Map<String, Object> headers = exchange.getIn().getHeaders();
		User user = exchange.getIn().getBody(User.class);
		UserEntity userEntity = userRepository.save(modelMapper.map(user,
				UserEntity.class));
		exchange.getIn().setBody(modelMapper.map(userEntity, User.class));
	}
	
	public void updateUser(Exchange exchange) {

		Map<String, Object> headers = exchange.getIn().getHeaders();
		String userId = (String) headers.get("userId");
		User user = exchange.getIn().getBody(User.class);
		user.setId(Long.valueOf(userId));
		UserEntity userEntity = userRepository.save(modelMapper.map(user,
				UserEntity.class));
		exchange.getIn().setBody(modelMapper.map(userEntity, User.class));
	}
	
	
	public void deleteUser(Exchange exchange) {

		Map<String, Object> headers = exchange.getIn().getHeaders();
		Long userId = Long.valueOf((String) headers.get("userId"));
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userId);
		userRepository.delete(userEntity);
		
	}
	

}
