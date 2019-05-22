package com.fr.adaming.service.util;

import java.util.List;
import java.util.Optional;

import com.fr.adaming.entity.User;

/**
 * @author Matthieu Billaud
 *
 */
public interface IUserService {
	
	/**
	 * @param login string
	 * @param pwd string
	 * @return User if found, null if not (from DB)
	 */
	public User login(String login, String pwd);
	
	/**
	 * @param entity User  - object to save 
	 * @return User updated from DB or null if constraint violation
	 */
	public User save(User entity);
	
	/**
	 * @param user User - object to update
	 * @return User updated from DB or null if constraint violation(or ID mismatch)
	 */
	public User update(User user);
	
	/**
	 * @param id - user id to be deleted
	 * @return true if success, false otherwise
	 */
	public boolean delete(Long id);
	
	/**
	 * @return list containing all User or empty List
	 */
	public List<User> findAll();
	
	/**
	 * @param id - user id to be found
	 * @return User if exists, or null otherwise
	 */
	public User findById(Long id);
	
	/**
	 * @param id - team id
	 * @return list containing all users in the team or empty list otherwise 
	 */
	public List<User> findByTeamId(Long id);

}
