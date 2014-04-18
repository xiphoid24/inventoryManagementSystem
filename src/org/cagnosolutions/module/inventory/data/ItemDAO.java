package org.cagnosolutions.module.inventory.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.cagnosolutions.module.inventory.domain.Item;
import org.primefaces.model.SortOrder;

@Local
public interface ItemDAO {
	public void insert(Item item);
	public void delete(Item item);
	public void update(Item item);
	public Item queryById(int id);
	public List<Item> queryAll();	
	public List<Item> queryByCode(String code);
	public List<Item> queryByCategory(String category);
	public List<Item> queryByDescription(String description);
	public List<Item> queryByCount(int count);
	public List<Item> queryByLocation(String location);
	public List<Item> queryByCost(float cost);
	public List<Item> queryByPrice(float price);
	public List<Item> queryByDate(Date date);
	
	public List<Item> lazyLoad(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters);
	public List<Item> getItems(int first, int max);
	public Number getTotalRowCount();
}
