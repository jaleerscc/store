package com.store.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.store.beans.ItemBean;

@SpringBootApplication(scanBasePackages = {"com.store"})
@EnableJpaRepositories("com.store.data")
@EntityScan("com.store.beans")
@RunWith(SpringRunner.class)
public class CrudRepoIntegrationTest {
	
	
	@Autowired
	private StoreItems storeItems;
	
	@org.junit.Test
	public void addToStoreWorks() {
		
		ItemBean item = new ItemBean();
		item.setName("Product X");
		item.setPrice(10.12);
		
		storeItems.save(item);
		
		
		ItemBean addedItem = storeItems.findByName(item.getName());
		
		assertThat(addedItem.getName()).isEqualTo(item.getName());
		
	}

}
