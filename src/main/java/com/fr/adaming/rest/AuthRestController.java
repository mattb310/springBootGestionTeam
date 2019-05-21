package com.fr.adaming.rest;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.configuration.LogConfig;
import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.util.IUserService;

@RestController
@RequestMapping(path="api")
public class AuthRestController {
	
	@Autowired
	private IUserService service;
	private Logger log = LogConfig.getLogger(AuthRestController.class);
	
	@RequestMapping(path="/login", method = RequestMethod.POST)
	public String login(@Valid @RequestBody LoginDto dto) {
		//log.debug("*****\n"+dto.getLogin()+"|"+dto.getEmail()+"|"+dto.getPwd());
		User u=null;
		if(dto.getEmail()==null && dto.getLogin()==null) {
			log.error("invalid input args");
			return "invalid args";
		}else if(dto.getEmail()==null) {
			log.debug("email null");
			u = service.login(dto.getLogin(), dto.getPwd());
		}else if(dto.getLogin()==null) {
			log.debug("login null");
			u = service.login(dto.getEmail(), dto.getPwd());			
		}
		else {
			log.error("invalid input args");
			return "invalid args";
		}
		if(u==null) {
			log.debug("login failed, user not existing");
			return "fail";
		}
		else {
			log.debug("login succes, user found");
			return "Welcome "+u.getName();			
		}
	}
	
	@RequestMapping(path="/register", method = RequestMethod.POST)
	public User register(@RequestBody RegisterDto dto) {
		User u = new User(0L,dto.getName(),dto.getFName(),dto.getLogin(),dto.getPwd(),null);
		return service.save(u);
	}

}
