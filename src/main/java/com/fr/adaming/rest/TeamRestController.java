package com.fr.adaming.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Team;
import com.fr.adaming.service.util.ITeamService;

@RestController
@RequestMapping(path="api/team")
public class TeamRestController {
	
	@Autowired
	private ITeamService service;
	
	@RequestMapping(path="/create", method = RequestMethod.POST)
	public Team save(Team entity) {
		return service.save(entity);
	}
	
	@RequestMapping(path="/all", method = RequestMethod.GET)
	public List<Team> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(path="/id/{id}", method = RequestMethod.GET)
	public Team findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	

}
