package site.zy1.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import site.zy1.bal.ListItemBiz;
import site.zy1.dal.model.ListItem;
import site.zy1.dal.model.Message;
import site.zy1.dal.model.VersionModel;

@RestController
public class AAListController {
	@Autowired
	private ListItemBiz listItemBiz;
	private String version;
	
	@RequestMapping("/list.json")
	public List<ListItem> list(){
		return listItemBiz.list();
	}
	
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	public Message add(@RequestBody ListItem item){
		listItemBiz.add(item);
		Message m = new Message();
		m.setSuccess(true);
		m.setMessage("添加成功");
		return m;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public Message delete(@PathVariable Integer id){
		listItemBiz.delete(id);
		Message m = new Message();
		m.setSuccess(true);
		m.setMessage("删除成功");
		return m;
	}
	
	@RequestMapping(value="/version.json",method=RequestMethod.GET)
	public VersionModel version() throws UnsupportedEncodingException, IOException{
		DefaultResourceLoader loader = new DefaultResourceLoader();
		BufferedReader br = new BufferedReader(new InputStreamReader(loader.getResource("classpath:application.properties").getInputStream(), "utf-8"));
		String str = null;
		String version = null;
		String url = null;
		String message = null;
		while((str = br.readLine()) != null){
			if(str.startsWith("version.number")){
				version = str.split("=")[1];
			}else if(str.startsWith("version.url")){
				url = str.split("=")[1];
			}else if(str.startsWith("version.message")){
				if(str.split("=").length < 2){
					message = "";
				}else{
					message = str.split("=")[1];
				}
			}
		}
		VersionModel versionModel = new VersionModel();
		versionModel.setMessage(message);
		versionModel.setUrl(url);
		versionModel.setVersion(version);
		return versionModel;
	}
}
