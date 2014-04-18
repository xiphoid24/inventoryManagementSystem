package coqui.controller;

import coqui.model.Comuni;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.LazyDataModel;

/**
*
* @author diego.cimarosa
*/
@ManagedBean (name="comuniController")
@ViewScoped <<< IMPORTANT!!!
public class ComuniController implements Serializable {
@PersistenceContext(unitName="CoquiPU")
EntityManager em;

private LazyDataModel<Comuni> lazyModel;
private Comuni selectedComune;

private int rowCount = 0;
private String theQuery = "";
private String stemma = "";

public String getStemma() {
String[] tab = selectedComune.getDenominazione().split(" ");
stemma = "stemmaComune";

for( int i = 0; i < tab.length; i++ )
stemma += tab[i].substring(0, 1).toUpperCase()+tab[i].substring(1).toLowerCase();

return stemma;
}

public void setStemma(String stemma) {
this.stemma = stemma;
}

public ComuniController() {
lazyModel = new LazyDataModel<Comuni>() {
@Override
public List<Comuni> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {
List<Comuni> theList = null;

// I use two queries: one to count filtered result and the second to get paged result

StringBuilder queryCount = new StringBuilder("SELECT count(c.idComune) FROM Comuni c");
StringBuilder query = new StringBuilder("SELECT c FROM Comuni c");
StringBuilder where = null;
StringBuilder orderBy = null;

if ( !filters.isEmpty() ) { // typed something to filter?
boolean and_clause = false;
where = new StringBuilder(" WHERE ");
Iterator it = filters.entrySet().iterator();
while (it.hasNext()) {
Map.Entry pairs = (Map.Entry)it.next();
if( and_clause )
where.append(" AND ");

where.append("c." + pairs.getKey() + " LIKE '%"); // a more general approach should get type of column and build a smarter filter
where.append(pairs.getValue() + "%'"); // client side filter only text fields

and_clause = true;
}
}

if(sortField != null) { // need sort on a column?
orderBy = new StringBuilder(" ORDER BY c." + sortField);
orderBy.append( (sortOrder ? " ASC " : " DESC ") );
}

// complete the two queries

queryCount.append( (where == null ? "": where) ) ;
queryCount.append( (orderBy == null ? "": orderBy) );

query.append( (where == null ? "": where) ) ;
query.append( (orderBy == null ? "": orderBy) );



rowCount = 0;

try {
theQuery = queryCount.toString();
Query q = em.createQuery(theQuery);
rowCount = ((Long)q.getSingleResult()).intValue(); // getSingleResult() return Long but setRowCount needs an integer!
lazyModel.setRowCount(rowCount);

theQuery = query.toString();
q = em.createQuery(theQuery);
q.setFirstResult(first);
q.setMaxResults(pageSize);
theList = q.getResultList(); // only the "pageSize" records ... please!
q = null;
System.out.println("ComuniController: theQuery: " + theQuery + ", rowCount: " + rowCount);
} catch(Exception ex) {
System.out.println("ComuniController:load: " + ex.getLocalizedMessage());
lazyModel.setRowCount(0);
}
return theList;
}
};
}

public LazyDataModel<Comuni> getLazyModel() {
return lazyModel;
}

public void setLazyModel(LazyDataModel<Comuni> lazyModel) {
this.lazyModel = lazyModel;
}

public Comuni getSelectedComune() {
return selectedComune;
}

public void setSelectedComune(Comuni selectedComune) {
this.selectedComune = selectedComune;
}

public void addComune() {

}

}