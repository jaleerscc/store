/**
 * 
 */
package com.store.services.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import com.store.beans.ItemBean;
import com.store.data.StoreItems;
import com.store.exceptions.ProductNotFoundException;
import com.store.models.Item;
import com.store.services.Store;

/**
 * All operations that can be performed on the store
 * @author sreelaj
 *
 */
@Service
@SessionScope
@Transactional
public class StoreImpl implements Store {
	
	@Autowired
	private StoreItems storeItems;
	
	private Map<ItemBean, Integer> shoppingCart = new HashMap<ItemBean,Integer>();
	
	private static DecimalFormat priceFormat = new DecimalFormat("0.00");

	/**
	 *	Add item with name (unique in the store) and quantity into the cart. Names are case-sensitive.
	 *	Validates the item before adding to cart.
	 *	TODO check stock before adding? probably not
	 */
	@Override
	public void addItemToCart(String name, int quantity) throws ProductNotFoundException {
		
		ItemBean newItem = new ItemBean();
		newItem.setName(name);
		
		ItemBean storeItem = storeItems.findByName(newItem.getName());
		
		if(storeItem==null) {
			throw new ProductNotFoundException(newItem);
		}
		
		if(shoppingCart.containsKey(newItem)) {
			int newQuantity = shoppingCart.get(newItem)+quantity;
			newItem.setQuantity(newQuantity);
			shoppingCart.replace(newItem, newQuantity);
		}
		else {
			newItem.setQuantity(quantity);
			shoppingCart.put(newItem, quantity);
		}

	}

	/**
	 *	Add item with name (unique in the store) and price into the store inventory. Names are case-sensitive. 
	 *  can be called repeatedly for the same item (to change the price)
	 *  If item exists, update the repository with modified price.
	 */
	@Override
	public void addItemToStore(String name, double price) {
		
		ItemBean storeItem = storeItems.findByName(name);
		if(storeItem==null) {
			storeItem = new ItemBean();
			storeItem.setName(name);
		}
		storeItem.setPrice(price);
		storeItems.save(storeItem);

	}
	
	/**
	 * Internal method to get the list of items in cart.
	 * @return list of Item objects
	 */
	private List<ItemBean> getCartList() {
		List<ItemBean> cartItems = shoppingCart.keySet().stream().collect(Collectors.toList());
		cartItems.sort(Comparator.comparing(ItemBean::getName));
		return cartItems;
	}
	
	/**
	 * Get items in the cart, consolidating all duplicate items, in ascending order by item name
	 * @return Array of Item objects
	 */
	@Override
	public Item[] getCartItems() {
		
		List<ItemBean> currentCartItems = getCartList();		
		Item[] cartItemsArray=currentCartItems.toArray(new ItemBean[currentCartItems.size()]);
		return cartItemsArray;
	
	}

	/**
	 * Calculates the total value of items in the cart
	 * Always checks the price from db - to get the latest price
	 * @return Total value of items in the cart
	 */
	@Override
	public double getCartTotal() {

		double cartTotal = 0.00;
		List<ItemBean> cartItems = shoppingCart.keySet().stream().collect(Collectors.toList());
		
		for (ItemBean cartItem : cartItems) {
			
			ItemBean storeItem = storeItems.findByName(cartItem.getName());
			cartTotal+= storeItem.getPrice() * shoppingCart.get(cartItem);
		}
		
		return new Double(priceFormat.format(cartTotal));
	}
	
	/**
	 * Provides a list of items in the store
	 * @return Array of Item objects in the store
	 */
	@Override
	public Item[] getStoreItems() {
		
		Iterable<ItemBean> sItems = storeItems.findAll();
		List<ItemBean> storeItemsList = new ArrayList<ItemBean>();
		sItems.forEach(storeItemsList::add);

		Item[]storeItemsArray=storeItemsList.toArray(new ItemBean[storeItemsList.size()]);
		return storeItemsArray;
		
	}
	

}
