package com.store.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.store.models.Item;

/**
 * Represents an item in store
 * @author sreelaj
 *
 */
@Entity
@Table(name = "item")
public class ItemBean implements Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "price", nullable = false)
	private double price;

	private int quantity;

	public Long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public boolean equals(Object anotherItem) {
		if (getClass() != anotherItem.getClass() || anotherItem == null)
			return false;
		ItemBean itemObj = (ItemBean) anotherItem;
		return name.equals(itemObj.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
