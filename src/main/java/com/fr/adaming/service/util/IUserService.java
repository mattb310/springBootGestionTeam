package com.fr.adaming.service.util;

import java.util.List;
import java.util.Optional;

import com.fr.adaming.entity.User;

public interface IUserService {
	
	public User login(String login, String pwd);
	
	public User save(User entity);
	
	public User update(User user);
	
	public boolean delete(Long id);
	
	public List<User> findAll();
	
	public User findById(Long id);
	
	public List<User> findByTeamId(Long id);

}
