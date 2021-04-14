package cn.com.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.model.ListQryModel;
import cn.com.mvc.model.MapQryModel;
import cn.com.mvc.model.UserAndProductQryModel;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;
import cn.com.mvc.validator.group.FruitsGroup1;

@Controller
@RequestMapping("query")
public class FindControllerTest {
	
	private FruitsService fruitsService = new FruitsServiceImpl();
	
	@RequestMapping("queryAllFruits")
    public String queryAllFruits(Model model,Fruits fruits,int type){
		List<Fruits> findList=fruitsService.queryFruitsList();
		model.addAttribute("fruitsList", findList);
		return "/fruits/FruitsList"+type;
	}
	
	@RequestMapping("queryFruitsByCondition")
    public String queryFruitsByCondition(Model model,@Validated(value=FruitsGroup1.class) Fruits fruits,
    		BindingResult bindingResult){
		//Fruits fruits=request.getParameter("fruits");
		List<ObjectError> allErrors = null;
		//��ȡУ�������Ϣ  
        if(bindingResult.hasErrors()){  
            allErrors=bindingResult.getAllErrors();  
            for(ObjectError objectError:allErrors){  
                //���������Ϣ  
                System.out.println(objectError.getDefaultMessage());  
            }  
        }  
		List<Fruits> findList=null;
		if(fruits==null||
				(fruits.getName()==null&&fruits.getProducing_area()==null)){
			//���fruits���ѯ����Ϊ�գ�Ĭ�ϲ�ѯ����
			findList=fruitsService.queryFruitsList();
		}else{
			//���fruits��ѯ������Ϊ�գ���������ѯ
			findList=fruitsService.queryFruitsByCondition(fruits);
		}
    	//��model���ݴ���ҳ��
        model.addAttribute("fruitsList", findList);
        //response.sendRedirect("home.action");
        //�����󴫵�ҳ��  
        if(allErrors!=null){
        	model.addAttribute("allErrors",allErrors); 
        }
    	return "/fruits/findFruits";
    }
	
	@RequestMapping("queryUserFruitsByCondition")
    public String queryUserFruitsByCondition(Model model,UserAndProductQryModel ufruits){
		String name=ufruits.getUserFruits().getName();//��ȡ������Ϣ
		System.out.println("name="+name);
		//���洦���߼�ʡ��
		return "/fruits/findFruits";
	}
	
	@RequestMapping("fruitsArrayTest")
    public void FruitsArray(Model model,int[] fids){
		for (int i = 0; i < fids.length; i++) {
			System.out.println("fids["+i+"]="+fids[i]);
		}
	}
	
	@RequestMapping("fruitsListTest")
    public void FruitsList(Model model,ListQryModel listQryModel){
		List<Fruits> fruitList=listQryModel.getFruitList();
		for (int i = 0; i < fruitList.size(); i++) {
			System.out.println("fruitList["+i+"].name="+fruitList.get(i).getName());
		}
	}
	
	@RequestMapping("fruitsMapTest")
    public void FruitsMap(Model model,MapQryModel MapQryModel){
		Map<String,Object> fruitMap=MapQryModel.getFruitMap();
		for(String key:fruitMap.keySet()){
			System.out.println("fruitMap["+key+"]="+fruitMap.get(key));
		}
	}
}
