package com.fr.adaming.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Team;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.util.ITeamService;
import com.fr.adaming.service.util.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTests {
	
	@Autowired
	private IUserService serv;
	@Autowired
	private ITeamService serv1;
	private static User res;
	
	@After
	public void afterMethod() {
		if(res!=null)
			serv.delete(res.getId());
	}
	
	@Test
	public void aa_createValidUser() {
		User u = new User(null, "name", "fName", "login", "pwd", null);
		res = serv.save(u);
		
		assertNotNull(res.getId());
	}
	
	@Test
	public void ab_createValidUserSpecifiedIdNotExisting() {
		User u = new User(12L, "name", "fName", "login2", "pwd", null);
		res = serv.save(u);
		
		assertNotNull(res.getId());
	}
	
	@Test
	public void ac_createInvalidUserIdAlrdE() {
		User u = new User(1L, "name", "fName", "login3", "pwd", null);
		User temp = serv.save(u);
		res = serv.save(u);
		serv.delete(temp.getId());
		assertNull(res);
	}
	
	@Test
	public void ad_createInvalidUserWrongLogin() {
		User u = new User(null, "name", "fName", "login", "pwd", null);
		User temp = serv.save(u);
		res = serv.save(u);
		serv.delete(temp.getId());
		
		assertNull(res);
	}
	
	@Test
	public void ae_createInvalidUserNull() {
		User u = null;
		res = serv.save(u);
		
		assertNull(res);
	}
	
	@Test
	public void ba_updateInvalidUserNull() {
		User u = null;
		res = serv.update(u);
		
		assertNull(res);
	}
	
	@Test
	public void bb_updateInvalidUserIdNotE() {
		User u = new User(52L, "name", "fName", "log", "pwd", null);
		res = serv.update(u);
		
		assertNull(res);
	}
	
	@Test
	public void bc_updateInvalidUserWrongLogin() {
		User u = new User(1L, "name", "fName", "login2", "pwd", null);
		res = serv.update(u);
		
		assertNull(res);
	}
	
	@Test
	public void bd_updateValidUser() {
		User u = new User(null, "name", "fName", "login", "pwd", null);
		User temp = serv.save(u);
		User u2 = new User(temp.getId(), "name", "fName", "log", "pwd", null);
		res = serv.update(u2);
		String[] a = {"log"};
		String[] b = {res.getLogin()};
		assertArrayEquals(a,b);
	}
	
	@Test
	public void ca_deleteInvalidUserNull() {
		User u = new User(null, "name", "fName", "log", "pwd", null);
		boolean res = serv.delete(u.getId());
		
		assertFalse(res);
		
	}
	
	@Test
	public void cb_deleteInvalidUserIdNotE() {
		User u = new User(52L, "name", "fName", "log", "pwd", null);
		boolean res = serv.delete(u.getId());
		
		assertFalse(res);
		
	}
	
	@Test
	public void cc_deleteValidUser() {
		User u = new User(null, "name", "fName", "log", "pwd", null);
		u = serv.save(u);
		boolean res = serv.delete(u.getId());
		
		assertTrue(res);
		
	}
	
	@Test
	public void da_findInvalidUserNull() {
		Long id = null;
		res = serv.findById(id);
		assertNull(res);
	}
	
	
	@Test
	public void db_findInvalidUserIdNotE() {
		Long id = 12L;
		res = serv.findById(id);
		
		assertNull(res);
	}
	
	@Test
	public void dc_findValidUser() {
		User u = new User(null, "name", "fName", "login", "pwd", null);
		u = serv.save(u);
		res = serv.findById(u.getId());
		
		assertNotNull(res.getId());
	}
	
	@Test()
	public void ea_findTeamInvalidUserNull() {
		Long id = null;
		List<User> res = serv.findByTeamId(id);
		assertNull(res);
	}
	
	
	@Test
	public void eb_findTeamInvalidUserIdNotE() {
		Long id = 12L;
		List<User> res = serv.findByTeamId(id);
		System.out.println(res);
		List<User> expected = new ArrayList<User>();
		assertEquals(expected, res);
	}
	
	@Test
	public void ec_findTeamValidUser() {
		
		Team t = new Team(0L, "team1", null);
		t = serv1.save(t);
		System.out.println(t);
		User u = new User(null, "name", "fName", "login", "pwd", t);
		u = serv.save(u);
		
		List<User> res = serv.findByTeamId(t.getId());
		serv.delete(u.getId());
		serv1.delete(t.getId());
		assertNotNull(res.get(0).getId());
	}
	
	@Test
	public void fa_loginValidUser() {
		User u = new User(null, "","","login2","pwd",null);
		u = serv.save(u);
		res = serv.login("login2", "pwd");
		
		assertNotNull(res.getId());
		
	}
	
	@Test
	public void fb_loginInvalidUser() {
		User u = new User(null, "","","login2","pwd",null);
		u = serv.save(u);
		res = serv.login("login", "pwd");
		serv.delete(u.getId());
		assertNull(res);
	}
	
	@Test
	public void fc_loginInvalidUserNull() {
		User u = new User(null, "","","login2","pwd",null);
		u = serv.save(u);
		res = serv.login(null, null);
		serv.delete(u.getId());
		
		assertNull(res);
	}
	
	
	
	

}
