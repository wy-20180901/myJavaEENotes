package cn.com.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import cn.com.mvc.model.Fruits;

public class FruitsServiceImpl implements FruitsService{
	
	public List<Fruits> fruitsList=null;
	
	public List<Fruits> init(){
		if(fruitsList==null){
			//��ʼ������
			fruitsList = new ArrayList<Fruits>();  

	        Fruits apple = new Fruits();
	        apple.setId(1);
	        apple.setName("�츻ʿƻ��");  
	        apple.setPrice(2.3);  
	        apple.setProducing_area("ɽ��"); 
	          
	        Fruits Banana = new Fruits();  
	        Banana.setId(2);
	        Banana.setName("�㽶");  
	        Banana.setPrice(1.5);  
	        Banana.setProducing_area("�Ϻ�");  
	          
	        fruitsList.add(apple);  
	        fruitsList.add(Banana); 
	        return fruitsList;
		}else{
			return fruitsList;
		}
	}
	
	public List<Fruits> queryFruitsList(){
		init();
        return fruitsList;
	}

	public Fruits queryFruitById(Integer id) {
		init();
		Fruits fruits;
		for (int i = 0; i < fruitsList.size(); i++) {
			fruits=fruitsList.get(i);
			if(fruits.getId()==id){
				return fruits;
			}
		}
		return null;
	}
	
	public List<Fruits> queryFruitsByCondition(Fruits fruits){
		init();
		String name=fruits.getName();
		String area=fruits.getProducing_area();
		List<Fruits> queryList=new ArrayList<Fruits>();
		Fruits f;
		for (int i = 0; i < fruitsList.size(); i++) {
			f=fruitsList.get(i);
			//��һ����������ͷ���
			if((!name.equals("")&&f.getName().contains(name))||
			   (!area.equals("")&&f.getProducing_area().contains(area))){
				queryList.add(f);
			}
		}
		return queryList.size()>0?queryList:null;
	}
}
