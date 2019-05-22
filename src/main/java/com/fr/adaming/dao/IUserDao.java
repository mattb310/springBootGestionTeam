package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.User;

/**
 * @author Matthieu Billaud
 *
 */
@Repository
public interface IUserDao extends JpaRepository<User, Long>{
	
	/**find User with login and password
	 * @param login String
	 * @param pwd String
	 * @return User or null
	 */
	User findByLoginAndPwd(String login, String pwd);
	
	List<User> findByTeamId(Long id);

}
