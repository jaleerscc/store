package com.store.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.store.beans.ItemBean;
import com.store.data.StoreItems;
import com.store.exceptions.ProductNotFoundException;
import com.store.services.Store;

@SpringBootApplication(scanBasePackages = {"com.store"})
@EnableJpaRepositories("com.store.data")
@EntityScan("com.store.beans")
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ServiceImplTest {
	
	@MockBean
	private StoreItems storeItems;
	
	@TestConfiguration
	static class StoreImplConfig {
		
		@Bean
		public Store storeImpl() {
			return new StoreImpl();
		}
	}
	
	@Autowired
	private Store storeImpl;
	
	@Before
	public void setUp() {
		
		ItemBean item = new ItemBean();
		item.setName("Product X");
		item.setPrice(10.12);
		
		Mockito.when(storeItems.findByName(item.getName())).thenReturn(item);
	}
	
	
	@Test
	public void addToCartWorks() throws ProductNotFoundException {
		
		storeImpl.addItemToCart("Product X", 10);	
		
		double cartValue = storeImpl.getCartTotal();
		
		assertThat(cartValue).isEqualTo(101.2);
		

		
	}
	
	

}
