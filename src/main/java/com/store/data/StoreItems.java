package com.store.data;

import org.springframework.data.repository.CrudRepository;

import com.store.beans.ItemBean;

/**
 * Represents the persistent repo with H2 DB
 * @author sreelaj
 *
 */
public interface StoreItems extends CrudRepository<ItemBean, String> {
		
	ItemBean findByName(String name);

}
