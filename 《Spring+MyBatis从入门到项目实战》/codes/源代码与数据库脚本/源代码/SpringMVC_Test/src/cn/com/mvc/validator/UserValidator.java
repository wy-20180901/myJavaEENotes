package cn.com.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cn.com.mvc.model.User;

public class UserValidator implements Validator {  
   
    public boolean supports(Class<?> clazz) {
       return User.class.equals(clazz);  
    }  
   
    public void validate(Object obj, Errors errors) {
    	ValidationUtils.rejectIfEmpty(errors, "username", "Username.is.empty", "�û�������Ϊ��");  
        User user = (User) obj;  
        if (null == user.getPassword() || "".equals(user.getPassword())){
     	   //ָ����֤ʧ�ܵ��ֶ��� �������룬Ĭ�ϴ�����Ϣ
     	   errors.rejectValue("password", "Password.is.empty","���벻��Ϊ��");  
        }else if(user.getPassword().length()<6){
     	   //ָ����֤ʧ�ܵ��ֶ��� �������룬Ĭ�ϴ�����Ϣ
     	   errors.rejectValue("password", "length.too.short", "���볤�Ȳ���С��6λ."); 
        }  
    }  
}  