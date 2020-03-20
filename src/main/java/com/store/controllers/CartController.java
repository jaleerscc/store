/**
 * 
 */
package com.store.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.exceptions.ProductNotFoundException;
import com.store.services.Store;

/**
 * @author sreelaj
 * HTTP Controller to handle requests
 */
@RestController
public class CartController {
	
	private static final String CART_TOTAL_RESPONSE = "cartTotal";
	private static final String RESPONSE = "response";
	private static final String ITEM_ADDED_TO_STORE_RESPONSE = "item added/updated";
	
	
	@Autowired
	private final Store storeService=null;
	
	/**
	 * Provides JSON response after adding an item to cart.
	 * Throws product not fund exception if invoked with invalid product name
	 * @param name Name of Item
	 * @param quantity Quantity to be added to cart
	 * @return JSON response with status
	 */
	@RequestMapping(value = "/addToCart/{name}/{quantity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> addItemToCart(@PathVariable("name") String name, @PathVariable("quantity") int quantity) {
		
		try {
			
			storeService.addItemToCart(name, quantity);
		
		} catch (ProductNotFoundException e) {
		
			return Collections.singletonMap(RESPONSE, e.getMessage());
		
		}
		
		return Collections.singletonMap(RESPONSE, ITEM_ADDED_TO_STORE_RESPONSE);

	}
	
	/**
	 * Additional method to support html forms
	 * @param name Name of Item
	 * @param quantity Quantity to be added to cart
	 * @return JSON response with status
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> pushItemToCart(@RequestParam("name") String name, @RequestParam("quantity") int quantity) {
		
		try {
			storeService.addItemToCart(name, quantity);
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			return Collections.singletonMap(RESPONSE, e.getMessage());
		}
		
		return Collections.singletonMap(RESPONSE, ITEM_ADDED_TO_STORE_RESPONSE);

	}
	
	/**
	 * Provides JSON response after adding an item to store.
	 * Updates the price if item already exists
	 * @param name Name of Item
	 * @param price Price
	 * @return JSON response with status
	 */
	@RequestMapping(value = "/addToStore/{name}/{price}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> addItemToStore(@PathVariable("name") String name, @PathVariable("price") double price) {
		
		storeService.addItemToStore(name, price);
		
		return Collections.singletonMap(RESPONSE, ITEM_ADDED_TO_STORE_RESPONSE);

	}
	
	/**
	 * Additional method to support html forms
	 * Provides JSON response after adding an item to store.
	 * Updates the price if item already exists
	 * @param name Name of Item
	 * @param price Price
	 * @return JSON response with status
	 */
	@RequestMapping(value = "/addToStore", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> pushItemToStore(@RequestParam("name") String name, @RequestParam("price") double price) {
		
		storeService.addItemToStore(name, price);
		
		return Collections.singletonMap(RESPONSE, ITEM_ADDED_TO_STORE_RESPONSE);

	}
	
	/**
	 * Provides JSON response - items in cart
	 * @return JSON response - array of Item objects
	 */
	@RequestMapping(value = "/cart/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getCartItems() {
		
		return Collections.singletonMap(RESPONSE, storeService.getCartItems());

	}
	
	/**
	 * Provides JSON response -total value of items in cart
	 * @return JSON response - total price
	 */
	@RequestMapping(value = "/cart/total", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Double> getCartTotal() {
		
		return Collections.singletonMap(CART_TOTAL_RESPONSE, storeService.getCartTotal());

	}
	
	/**
	 * Provides JSON response - items in store
	 * @return JSON response - array of Item objects
	 */
	@RequestMapping(value = "/store/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getStoreItems() {
		
		return Collections.singletonMap(RESPONSE, storeService.getStoreItems());

	}

}
