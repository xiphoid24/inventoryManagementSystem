package org.cagnosolutions.module.inventory.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import org.cagnosolutions.module.inventory.domain.Item;
import org.primefaces.model.SortOrder;

@Remote
public interface ItemManagementServiceRemote {
	public void addItem(Item item);
	public void removeItem(Item item);
	public void updateItem(Item item);
	public Item findItemById(int id);
	public List<Item> findAllItems();	
	public List<Item> findItemsByCode(String code);
	public List<Item> findItemsByCategory(String category);
	public List<Item> findItemsByDescription(String description);
	public List<Item> findItemsByCount(int count);
	public List<Item> findItemsByLocation(String location);
	public List<Item> findItemsByCost(float cost);
	public List<Item> findItemsByPrice(float price);
	public List<Item> findItemsByDate(Date date);
	
	public List<Item> lazyLoad(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);
	public List<Item> getItems(int first, int max);
	public Number getTotalRowCount();
}