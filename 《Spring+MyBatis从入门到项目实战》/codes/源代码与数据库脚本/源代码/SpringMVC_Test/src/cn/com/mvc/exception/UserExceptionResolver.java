package cn.com.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class UserExceptionResolver implements HandlerExceptionResolver{  
    @Override  
    public ModelAndView resolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {
        //1.�������쳣���͡�  
        UserException userException=null;  
        if(ex instanceof UserException){  
        	/*2.������쳣������ϵͳ�Զ�����쳣��
        	ֱ��ȡ���쳣��Ϣ���ڴ���ҳ��չʾ��  */
            userException=(UserException)ex;  
              
        }else{  
            /*3.����� �쳣���Ͳ���ϵͳ�Զ�����쳣��
        	����һ���Զ�����쳣���ͣ���ϢΪ��δ֪���󡱣���*/  
        	userException=new UserException("δ֪����");  
        }  
        //������Ϣ  
        String message=userException.getMessage();  
        ModelAndView modelAndView=new ModelAndView();   
        //��������Ϣ����ҳ��  
        modelAndView.addObject("message",message);  
        //ָ�򵽴������  
        modelAndView.setViewName("/errorPage/userError");  
        return modelAndView;  
    }  
}  
