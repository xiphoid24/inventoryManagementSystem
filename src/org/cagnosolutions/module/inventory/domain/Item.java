package org.cagnosolutions.module.inventory.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String code;
	private String category;
	private String description;
	private int count;
	private String location;
	private float cost;
	private float price;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public Item(){}
	
	public Item(String code, String category, String description, int count, float cost, float price){
		this.code = code;
		this.category = category;
		this.description = description;
		this.count = count;
		this.cost = cost;
		this.price = price;
		this.date = new Date();
	}
	
	public Item(String code, String category, String description, int count, String location, float cost, float price){
		this.code = code;
		this.category = category;
		this.description = description;
		this.count = count;
		this.location = location;
		this.cost = cost;
		this.price = price;
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public int getCount() {
		return count;
	}

	public String getLocation() {
		return location;
	}

	public float getCost() {
		return cost;
	}

	public float getPrice() {
		return price;
	}

	public Date getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		return id + " " + description + " " + price + "(" + count + " left)";
	}
}
