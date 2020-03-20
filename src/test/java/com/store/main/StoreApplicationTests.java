package com.store.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.store.controllers.CartController;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class StoreApplicationTests {
	
	@Autowired
	private CartController cartController;

	@Test
	public void contextLoads() {
		
		assertThat(cartController).isNotNull();
		
	}

}
