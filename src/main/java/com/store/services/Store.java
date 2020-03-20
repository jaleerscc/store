/**
 * 
 */
package com.store.services;

import com.store.exceptions.ProductNotFoundException;
import com.store.models.Item;

/**
 * @author sreelaj
 *
 */
public interface Store {

	/* Add item with name (unique in the store) and quantity */
	/* into the cart. Names are case-sensitive. */
	void addItemToCart(String name, int quantity) throws ProductNotFoundException;

	/* Add item with name (unique in the store) and price */
	/* into the store inventory. Names are case-sensitive. */
	void addItemToStore(String name, double price);

	/* Get items in the cart, consolidating all duplicate */
	/* items, in ascending order by item name */
	Item[] getCartItems();
	//List<Item> getCartItems();

	/* Get total cost of items currently in the cart */
	double getCartTotal();
	
	
	// additional method
	Item[] getStoreItems();
	
	

}
