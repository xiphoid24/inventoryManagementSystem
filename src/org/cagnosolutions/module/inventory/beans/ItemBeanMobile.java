package org.cagnosolutions.module.inventory.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.cagnosolutions.module.inventory.domain.Item;
import org.cagnosolutions.module.inventory.service.ItemManagementServiceLocal;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


@SessionScoped
@ManagedBean(name="itemBeanMobile")
public class ItemBeanMobile {

	@EJB
	private ItemManagementServiceLocal service;

	private Item selectedItem = new Item();
	private int id;
	private String code;
	private String category;
	private String description;
	private int count;
	private String location;
	private float cost;
	private float price;
	private Date date;
	
	
	public String newItem() {
		this.selectedItem = new Item();
		return "pm:edit?transition=slide";
	}
	
	public String save() {
		Item currentItem = this.selectedItem;
		service.updateItem(currentItem);
		return "pm:list?reverse=true";
	}

	public String delete() {
		Item currentItem = this.selectedItem;
		service.removeItem(currentItem);
		return "pm:list?reverse=true";
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

	public void setSelectedItem(Item item) {
		this.selectedItem = item;
	}

	public Item getSelectedItem() {
		return selectedItem;
	}

	public List<Item> getAllItems() {
		return service.findAllItems();
	}
}
