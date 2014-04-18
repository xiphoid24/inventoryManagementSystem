package org.cagnosolutions.module.inventory.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.cagnosolutions.module.inventory.domain.Item;
import org.primefaces.model.SortOrder;



@Stateless
public class ItemDAOImpl implements ItemDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Item item) {
		em.persist(item);
	}

	public void delete(Item item) {
		item = em.merge(item);
		em.remove(item);
	}

	public void update(Item item) {
		em.merge(item);
	}

	public Item queryById(int id) {
		return (Item) em.createQuery("SELECT item FROM Item item WHERE item.id=:id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public List<Item> queryAll() {
		return em.createQuery("SELECT item FROM Item item").getResultList();
	}

	public List<Item> queryByCode(String code) {
		return em.createQuery("SELECT item FROM Item item WHERE item.code=:code")
				.setParameter("code", code)
				.getResultList();
	}

	public List<Item> queryByCategory(String category) {
		return em.createQuery("SELECT item FROM Item item WHERE item.category=:category")
				.setParameter("category", category)
				.getResultList();
	}

	public List<Item> queryByDescription(String description) {
		return em.createQuery("SELECT item FROM Item item WHERE item.description=:description")
				.setParameter("description", description)
				.getResultList();
	}

	public List<Item> queryByCount(int count) {
		return em.createQuery("SELECT item FROM Item item WHERE item.count=:count")
				.setParameter("count", count)
				.getResultList();
	}

	public List<Item> queryByLocation(String location) {
		return em.createQuery("SELECT item FROM Item item WHERE item.location=:location")
				.setParameter("location", location)
				.getResultList();
	}

	public List<Item> queryByCost(float cost) {
		return em.createQuery("SELECT item FROM Item item WHERE item.cost=:cost")
				.setParameter("cost", cost)
				.getResultList();
	}

	public List<Item> queryByPrice(float price) {
		return em.createQuery("SELECT item FROM Item item WHERE item.price=:price")
				.setParameter("id", price)
				.getResultList();
	}

	public List<Item> queryByDate(Date date) {
		return em.createQuery("SELECT item FROM Item item WHERE item.date=:date")
				.setParameter("date", date)
				.getResultList();
	}

	public List<Item> lazyLoad(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Item> q = cb.createQuery(Item.class);
		Root<Item> item = q.from(Item.class);
		q.select(item);
		
		// filtering
		Predicate filterSet = cb.conjunction();
		for (Map.Entry<String, String> filter : filters.entrySet()) {
			Path<String> key = item.get(filter.getKey());
			if (!filter.getValue().equals("")) {
				filterSet = cb.and(filterSet, cb.like(key, filter.getValue()+"%"));
			}
		}
		q.where(filterSet);
		
		// sorting
		if (sortField != null) {
			if (sortOrder.equals(SortOrder.ASCENDING)) {
				q.orderBy(cb.asc(item.get(sortField)));
			} else if (sortOrder.equals(SortOrder.DESCENDING)) {
				q.orderBy(cb.desc(item.get(sortField)));
			}
		}
		return em.createQuery(q).setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public List<Item> getItems(int first, int max) {
		return em.createQuery("SELECT item FROM Item item").setFirstResult(first).setMaxResults(max).getResultList();
	}

	public Number getTotalRowCount() {
		Number totalRowCount = (Number) em.createQuery("SELECT COUNT(item.id) FROM Item item").getSingleResult();
		return totalRowCount.intValue();
	}

}
