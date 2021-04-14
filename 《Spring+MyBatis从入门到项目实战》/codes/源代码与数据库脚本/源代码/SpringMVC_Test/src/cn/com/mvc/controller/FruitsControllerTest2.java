package cn.com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;

public class FruitsControllerTest2 implements HttpRequestHandler{

	private FruitsService fruitsService = new FruitsServiceImpl();
	
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ģ��Service��ȡˮ����Ʒ�б�  
        List<Fruits> fruitsList = fruitsService.queryFruitsList();  
        //��fruitsListת��Ϊjson��
        String jsonInfo=convertListToJson(fruitsList);
        //���÷��ظ�ʽ
        response.setCharacterEncoding("utf-8");  
        response.setContentType("application/json;charset=utf-8");  
        //д��json��
        response.getWriter().write(jsonInfo);  
        //����ģ������  
        //request.setAttribute("fruitsList",fruitsList);  
        //����ת����ͼ  
        //request.getRequestDispatcher("/WEB-INF/jsp/fruits/fruitsList.jsp").forward(request, response);  
	}

	private String convertListToJson(List<Fruits> fruitsList) {
 	    StringBuilder builder=new StringBuilder();
 	    builder.append('[');
 	    for(Fruits fruits:fruitsList){
 		   builder.append('{');
 		   builder.append("\"name\":\"").append(fruits.getName()).append("\",");
 		   builder.append("\"price\":\"").append(fruits.getPrice()).append("\",");
 		   builder.append("\"producing_area\":\"").append(fruits.getProducing_area()).append("\"");
 		   builder.append("},");
 	    }
 	    builder.deleteCharAt(builder.length()-1);
 	    builder.append(']');
		return builder.toString();
	}
}