package com.store.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.store.beans.ItemBean;
import com.store.data.StoreItems;

/**
 * Main class - app entry point
 * @author sreelaj
 *
 */
@SpringBootApplication(scanBasePackages = {"com.store"})
@EnableJpaRepositories("com.store.data")
@EntityScan("com.store.beans")
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
		
	}
	
	/**
	 * Initial Item objects to prepopulate the store
	 */
	@Bean
	public CommandLineRunner initStore(StoreItems storeItems) {
		return (args) -> {
			
			ItemBean item1 = new ItemBean();
			item1.setName("Lyric");
			item1.setPrice(5.14);
			storeItems.save(item1);
			ItemBean item2 = new ItemBean();
			item2.setName("BTE");
			item2.setPrice(5.09);
			storeItems.save(item2);
		
		};
	}

}
