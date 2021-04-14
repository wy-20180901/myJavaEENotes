package cn.com.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    String uri=request.getRequestURI();  
	    //�жϵ�ǰ�����ַ�Ƿ��ǵ�¼��ַ  
	    if(!(uri.contains("Login")||uri.contains("login"))){  
	        //�ǵ�¼����  
	        if(request.getSession().getAttribute("user")!=null){  
	            //˵���Ѿ���¼�������� 
	            return true;
	        }else{  
	            //û�е�¼,��ת����¼����  
	            response.sendRedirect(request.getContextPath()+"/user/toLogin.action");  
	        }  
	    }else{  
	        //��¼����ֱ�ӷ���  
	    	return true; 
	    }  
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}
}
