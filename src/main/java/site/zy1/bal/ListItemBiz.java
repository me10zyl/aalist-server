package site.zy1.bal;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import site.zy1.dal.dal.ListItemDAO;
import site.zy1.dal.model.ListItem;

@Service
public class ListItemBiz {
	@Autowired
	ListItemDAO listItemDAO;
	
	public List<ListItem> list(){
		return listItemDAO.list();
	}
	
	@Transactional(readOnly=false)
	public void add(ListItem item){
		listItemDAO.add(item);
	}
	
	@Transactional(readOnly=false)
	public void delete(int id){
		listItemDAO.delete(id);
	}
}
