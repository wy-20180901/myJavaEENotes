package cn.com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.mvc.exception.UserException;
import cn.com.mvc.model.User;

@Controller
@RequestMapping("user")
public class UserControllerTest {

   /* @InitBinder
    public void initBinder(DataBinder binder) {
       binder.setValidator(new UserValidator());
    }*/
    
    @RequestMapping("toLogin")
    public String toLoginPage() {
       //��ת����¼����
       return "/user/login";
    }
 
    @RequestMapping("login")
    public String login(Model model,HttpServletRequest request, 
    		@Valid User user, BindingResult result) throws UserException, IOException {
    	
       //��ѯ�û���Ϊ�������û�
       boolean isBlackUser = checkBlackList(user);
       //�ж���Ʒ�Ƿ�Ϊ��,����idû�в鵽��Ʒ����ʾ�û���Ʒ��Ϣ��������  
       if(isBlackUser){  
           throw new UserException("user.not.have.power");  
       }  
    	
       //��¼���
       List<ObjectError> allErrors = null;
       if (result.hasErrors()){
    	   allErrors=result.getAllErrors();  
           for(ObjectError objectError:allErrors){  
               //���������Ϣ  
               System.out.println("code="+objectError.getCode()+
            		   "  DefaultMessage="+objectError.getDefaultMessage());  
               //�򽫴��󴫵�ҳ��  
               model.addAttribute("allErrors",allErrors); 
           } 
    	   return "/user/login";
       }else{
    	   //����˺����룬�ɹ�����¼�ɹ�
    	   boolean flag = checkUser(user);
    	   if(flag){
    		   //���û���Ϣ����session
    		   request.getSession().setAttribute("user", user);
    	   }else{
    		   model.addAttribute("errorMsg","�˺Ż��������"); 
    		   return "/user/login";
    	   }
       }
       return "/user/loginSuccess";
    }

	private boolean checkUser(User user) {
		if(user.getUsername().equals("zhangsan")
				&&user.getPassword().equals("qwe123")){
			return true;
		}
		return false;
	}

	private boolean checkBlackList(User user) {
		String blackArray [] = {"jack","tom","jean"};
		for (int i = 0; i < blackArray.length; i++) {
			if(user.getUsername().equals(blackArray[i])){
				return true;
			}
		}
		return false;
	}
   
}
