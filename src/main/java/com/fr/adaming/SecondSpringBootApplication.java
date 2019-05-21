package com.fr.adaming;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fr.adaming.configuration.LogConfig;
import com.fr.adaming.entity.Team;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.util.ITeamService;
import com.fr.adaming.service.util.IUserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SecondSpringBootApplication {
	


	public static void main(String[] args) {
		ApplicationContext ctxt =  SpringApplication.run(SecondSpringBootApplication.class, args);
		
		IUserService serv = ctxt.getBean(IUserService.class);
		ITeamService serv1 = ctxt.getBean(ITeamService.class);

		Team t = new Team(0L, "team1", null);
		Team t1 = new Team(0L, "team2", null);
		serv1.save(t);
		serv1.save(t1);
		
		User u = new User(null, "admin", "admin", "admin", "a", null);
		User u1 = new User(null, "name1", "fname1", "1", "a", serv1.findById(1L));
		User u2 = new User(null, "name2", "fname2", "2", "a", serv1.findById(1L));
		User u3 = new User(null, "name3", "fname3", "3", "a", serv1.findById(1L));
		User u4 = new User(null, "name4", "fname4", "4", "a", serv1.findById(2L));
		
		serv.save(u);
		serv.save(u1);
		serv.save(u2);
		serv.save(u3);
		serv.save(u4);
		
//		Logger logger = Logger.getLogger(Logger.class);
//		Logger logger = Logger.getLogger(SecondSpringBootApplication.class);
//		logger.debug("test");
//		List<User> lu1 = new ArrayList<User>();
//		lu1.add(serv.findById(1L).get());
//		lu1.add(serv.findById(2L).get());
//		lu1.add(serv.findById(3L).get());
		
		LogConfig.getLogger(SecondSpringBootApplication.class).fatal("qsdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
	
		
		
		
	}

}
