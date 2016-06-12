package site.zy1.dal.dal;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import site.zy1.dal.model.ListItem;

@Repository
public class ListItemDAO extends HibernateDaoSupport{
	
	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<ListItem> list(){
		List<ListItem> list = (List<ListItem>) getHibernateTemplate().find("from ListItem order by date desc"); 
		return list;
	}
	
	public void add(ListItem listItem){
		getHibernateTemplate().save(listItem);
	}
	
	public void delete(int id){
		ListItem listItem = new ListItem();
		listItem.setId(id);
		getHibernateTemplate().delete(listItem);
	}

}
