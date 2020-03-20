/**
 * 
 */
package com.store.models;

/**
 * Store item template
 * @author sreelaj
 *
 */
public interface Item {

	/* Get item name (which is unique in the store) */
	String getName();

	/* Get quantity */
	int getQuantity();

}
