package com.fr.adaming.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Team;
import com.fr.adaming.service.util.ITeamService;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamServiceTests {

	@Autowired
	private ITeamService serv;
	private static Team res;

	@After
	public void afterMethod() {
		if (res != null)
			serv.delete(res.getId());
	}

	@Test
	public void aa_createValidTeam() {
		Team t = new Team(null, "name1", null);
		res = serv.save(t);

		assertNotNull(res.getId());
	}

	@Test
	public void ab_createValidTeamSpecifiedIdNotExisting() {
		Team t1 = new Team(null, "name1", null);
		Team temp = serv.save(t1);
		Team t = new Team(12L, "", null);
		res = serv.save(t);
		serv.delete(temp.getId());
		assertNotNull(res.getId());
	}

	@Test
	public void ac_createInvalidTeamIdAlrdE() {
		Team t1 = new Team(null, "name1", null);
		Team temp = serv.save(t1);
		Team t = new Team(temp.getId(), "", null);
		res = serv.save(t);
		serv.delete(temp.getId());

		assertNull(res);
	}

	@Test
	public void ad_createInvalidTeamNull() {
		Team t = null;
		res = serv.save(t);

		assertNull(res);
	}

	@Test
	public void ba_updateTeam() {
		Team t = null;
		res = serv.update(t);
		assertNull(res);
	}

	@Test
	public void bb_updateInvalidTeamIdNotE() {
		Team t = new Team(52L, "aaa", null);
		res = serv.update(t);

		assertNull(res);
	}

	@Test
	public void bd_updateValidTeam() {
		Team t = new Team(null, "aa", null);
		Team temp = serv.save(t);
		Team t2 = new Team(temp.getId(), "bb", null);
		res = serv.update(t2);
		String[] a = { "bb" };
		String[] b = { res.getName() };
		assertArrayEquals(a,b);

	}
	
	
	@Test
	public void ca_deleteInvalidTeamNull() {
		Team t = new Team(null, "aa", null);
		boolean res = serv.delete(t.getId());
		
		assertFalse(res);
		
	}
	
	
	@Test
	public void cb_deleteInvalidTeamIdNotE() {
		Team t = new Team(52L, "name", null);
		boolean res = serv.delete(t.getId());
		
		assertFalse(res);
		
	}
	  
	 
	@Test
	public void cc_deleteValidTeam() {
		Team t = new Team(null, "name", null);
		t = serv.save(t);
		boolean res = serv.delete(t.getId());
		
		assertTrue(res);
		
	}
	
	@Test
	public void da_findInvalidTeamNull() {
		Long id = null;
		res = serv.findById(id);
		assertNull(res);
	}
	
	
	@Test
	public void db_findInvalidTeamIdNotE() {
		Long id = 12L;
		res = serv.findById(id);
		
		assertNull(res);
	}
	
	@Test
	public void dc_findValidTeam() {
		Team t = new Team(null, "name", null);
		t = serv.save(t);
		res = serv.findById(t.getId());
		assertNotNull(res.getId());
	}

	
	
	
	

}
