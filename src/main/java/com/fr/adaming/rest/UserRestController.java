package com.fr.adaming.rest;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.configuration.LogConfig;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.util.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "api/user")
public class UserRestController {

	//private final IUserService service;
	private Logger log = LogConfig.getLogger(UserRestController.class);
	@Autowired
	private IUserService service;

//	@Autowired
//	public UserRestController(IUserService service) {
//		super();
//		this.service = service;
//	}
//	
	@ApiOperation(value = "desc test blabla")
	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable Long id) {
		User u = service.findById(id);
//		if(!u.isPresent()) {
//			log.error("return null");
//			return null;
//		}
		return u;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public User save(@RequestBody User entity) {

		return service.save(entity);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<User> findAll() {
		return service.findAll();
	}

	@RequestMapping(path = "/teamid/{id}", method = RequestMethod.GET)
	public List<User> findByTeamId(@PathVariable Long id) {
		return service.findByTeamId(id);
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public User update(@RequestBody User user) {
			
		return service.update(user);
	}

	@RequestMapping(path = "/", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
