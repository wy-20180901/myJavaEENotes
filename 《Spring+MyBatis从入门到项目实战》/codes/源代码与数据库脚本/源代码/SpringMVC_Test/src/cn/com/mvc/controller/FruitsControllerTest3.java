package cn.com.mvc.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;

//ע���Handler��  
//ʹ��@Controller����ʶ����һ��������  
@Controller
public class FruitsControllerTest3{

	private FruitsService fruitsService = new FruitsServiceImpl();
	
	//��Ʒ��ѯ�б�  
    //@RequestMappingʵ�� ��queryFruitsList������url����ӳ�䣬һ��������Ӧһ��url  
    //һ�㽨�齫url�ͷ���д��һ��  
    @RequestMapping(value="/queryFruitsList")
	public ModelAndView  queryFruitsList() throws Exception {
		//ģ��Service��ȡˮ����Ʒ�б�  
        List<Fruits> fruitsList = fruitsService.queryFruitsList();  
        //����ModelAndView  
        ModelAndView modelAndView =  new ModelAndView();  
        //�൱ ��request��setAttribut����jspҳ����ͨ��fruitsListȡ����  
        modelAndView.addObject("fruitsList", fruitsList);  
        //ָ����ͼ  
        modelAndView.setViewName("/WEB-INF/jsp/fruits/fruitsList.jsp"); 
        return modelAndView;  
	}
    
    //���滹���Զ�����ɾ�ĵ�URLӳ�䷽��
    @RequestMapping(value="/queryFruit",method={RequestMethod.GET})  
    public String queryFruitById(Model model,@RequestParam(value="fruit_id") Integer fruitId)throws Exception{  
              
        //����service��ȡˮ����Ʒ�б� 
    	Fruits fruit=fruitsService.queryFruitById(fruitId);   
              
        //ͨ���β��е�model��model���ݴ���ҳ��  
        //�൱��modelAndView.addObject����  
        model.addAttribute("fruit", fruit);  
              
        return "/fruits/fruitsDetail";  
    }  
    
    @RequestMapping(value="/queryFruit/{id}",method={RequestMethod.GET})  
    public @ResponseBody Fruits getFruitById(Model model,@PathVariable("id")Integer fruitId)
    		throws Exception{  
        //����service��ȡˮ����Ʒ�б� 
    	Fruits fruit=fruitsService.queryFruitById(fruitId);   
        return fruit;  
    }  
    
    @RequestMapping(value="/addFruit",method={RequestMethod.POST})  
    public String addFruit(Model model,Fruits fruit)
    		throws Exception{   
         //��������߼�  
    	return "...";
    } 
    
    @RequestMapping(value="/deleteFruit/{id}",method={RequestMethod.DELETE})  
    public String deleteFruitById(Model model,@PathVariable("id")Integer fruitId)
    		throws Exception{  
        //����ɾ���߼�
    	return "...";
    } 
    
    @RequestMapping(value="/editFruit",method={RequestMethod.PUT})  
    public String editFruitById(Model model,Fruits fruit)
    		throws Exception{  
        //�����޸��߼� 
        return "...";  
    } 
}