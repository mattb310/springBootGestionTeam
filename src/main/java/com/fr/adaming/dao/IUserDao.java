package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Long>{
	
	User findByLoginAndPwd(String login, String pwd);
	
	List<User> findByTeamId(Long id);

}
