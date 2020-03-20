package com.store.exceptions;

import com.store.models.Item;

/**
 * 
 * @author sreelaj
 * Class for custom exception - if try to add a product that is not in the repository
 *
 */
public class ProductNotFoundException extends Exception {
	
	private static final long serialVersionUID = -9174379016171541903L;
	private static final String DEFAULT_MESSAGE = "Product does not exist";
	
	public ProductNotFoundException()
	{
		super(DEFAULT_MESSAGE);
	}
	
	public ProductNotFoundException(Item item)
	{
		super(String.format("Product with name %s does not exist in the store", item.getName()));
	}

}
