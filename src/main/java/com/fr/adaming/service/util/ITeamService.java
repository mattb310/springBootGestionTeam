package com.fr.adaming.service.util;

import java.util.List;

import com.fr.adaming.entity.Team;

public interface ITeamService {
	
	
	public Team save(Team entity);
	
	public List<Team> findAll();
	
	public Team findById(Long id);
	
	public boolean delete(Long id);
	
	public Team update(Team entity);
	
	

}
