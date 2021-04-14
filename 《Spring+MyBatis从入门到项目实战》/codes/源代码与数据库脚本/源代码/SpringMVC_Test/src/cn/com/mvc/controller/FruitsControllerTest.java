package cn.com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;

public class FruitsControllerTest implements Controller{

	private FruitsService fruitsService = new FruitsServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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

}