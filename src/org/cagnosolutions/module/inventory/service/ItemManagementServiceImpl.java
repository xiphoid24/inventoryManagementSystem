package org.cagnosolutions.module.inventory.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.cagnosolutions.module.inventory.data.ItemDAO;
import org.cagnosolutions.module.inventory.domain.Item;
import org.primefaces.model.SortOrder;

@Stateless
public class ItemManagementServiceImpl implements ItemManagementServiceLocal, ItemManagementServiceRemote {

	@EJB
	private ItemDAO dao;
	
	public void addItem(Item item) {
		dao.insert(item);
	}

	public void removeItem(Item item) {
		dao.delete(item);
	}

	public void updateItem(Item item) {
		dao.update(item);
	}

	public Item findItemById(int id) {
		return dao.queryById(id);
	}

	public List<Item> findAllItems() {
		return dao.queryAll();
	}

	public List<Item> findItemsByCode(String code) {
		return dao.queryByCode(code);
	}

	public List<Item> findItemsByCategory(String category) {
		return dao.queryByCategory(category);
	}

	public List<Item> findItemsByDescription(String description) {
		return dao.queryByDescription(description);
	}

	public List<Item> findItemsByCount(int count) {
		return dao.queryByCount(count);
	}

	public List<Item> findItemsByLocation(String location) {
		return dao.queryByLocation(location);
	}

	public List<Item> findItemsByCost(float cost) {
		return dao.queryByCost(cost);
	}

	public List<Item> findItemsByPrice(float price) {
		return dao.queryByPrice(price);
	}

	public List<Item> findItemsByDate(Date date) {
		return dao.queryByDate(date);
	}
	
	public List<Item> lazyLoad(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		return dao.lazyLoad(first, pageSize, sortField, sortOrder, filters);
	}

	public List<Item> getItems(int first, int max) {
		return dao.getItems(first, max);
	}

	public Number getTotalRowCount() {
		return dao.getTotalRowCount();
	}
}
