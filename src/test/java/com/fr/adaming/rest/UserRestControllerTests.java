package com.fr.adaming.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fr.adaming.rest.UserRestController;


//@RunWith(SpringRunner.class)
//@WebMvcTest(UserRestController.class)
//@ComponentScan(basePackages = {"com.fr.adaming", "com.fr.adaming.dao", "com.fr.adaming.service", "com.fr.adaming.rest"})
public class UserRestControllerTests {
	
//	@Autowired
//	private MockMvc mvc;
	
//	@Test
//	public void getAllUser() throws Exception{
//		mvc.perform( MockMvcRequestBuilders
//			      .get("/")
//			      .accept(MediaType.APPLICATION_JSON))
//			      .andDo(print())
//			      .andExpect(status().isOk())
//			      .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
//			      .andExpect(MockMvcResultMatchers.jsonPath("$.user[*].getId").isNotEmpty());
//	}

}
