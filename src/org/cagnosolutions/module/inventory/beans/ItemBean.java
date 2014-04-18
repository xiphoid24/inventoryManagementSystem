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
@ManagedBean(name="itemBean")
public class ItemBean {

	@EJB
	private ItemManagementServiceLocal service;
	private LazyDataModel<Item> lazyModel;

	private Item selectedItem;
	private int id;
	private String code;
	private String category;
	private String description;
	private int count;
	private String location;
	private float cost;
	private float price;
	private Date date;

	public ItemBean() {
		lazyModel = new LazyDataModel<Item>() {
			public List<Item> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
				this.setRowCount(service.getTotalRowCount().intValue());
				return service.lazyLoad(first, pageSize, sortField, sortOrder, filters);
			}
		};
	}
	
	public void newItem() {
		this.selectedItem = new Item();
	}
	
	public void add(ActionEvent event) {
		Item newItem = new Item(code, category, description, count, location, cost, price);
		service.addItem(newItem);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Added successfully"));
		this.code = null;
		this.category = null;
		this.description = null;
		this.count = 0;
		this.location = null;
		this.cost =  0;
		this.price = 0;
	}
	
	public String save() {
		Item currentItem = this.selectedItem;
		service.updateItem(currentItem);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated successfully"));
		return "detail?facesRedirect=true";
	}

	public String delete() {
		Item currentItem = this.selectedItem;
		service.removeItem(currentItem);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item " + selectedItem.getId() + " removed successfully"));
		return "list?facesRedirect=true";
	}

	public LazyDataModel<Item> getLazyModel() {
		return lazyModel;
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
