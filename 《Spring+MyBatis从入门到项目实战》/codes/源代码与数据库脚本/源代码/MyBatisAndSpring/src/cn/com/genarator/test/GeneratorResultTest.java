package cn.com.genarator.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.sm.mapper.UserMapper;
import cn.com.sm.po.User;
import cn.com.sm.po.UserExample;

public class GeneratorResultTest {
	private static ApplicationContext applicationContext;  
    private static UserMapper userMapper;  
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws ParseException {
    	applicationContext =
    			new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");  
    	userMapper=(UserMapper)applicationContext.getBean("userMapper");
    	
    	//1.���Բ������
    	User user1 = new User();
    	user1.setUsername("������");
    	user1.setPassword("123qwe");
    	user1.setGender("��");
    	user1.setBirthday(sdf.parse("1992-01-01"));
    	user1.setProvince("����");
    	user1.setCity("����");
    	user1.setEmail("lileilei@126.com");
    	userMapper.insert(user1);
    	System.out.println("1.��������Ϊ��"+user1.getUsername()+"���û�");
    	
    	//2.���Բ�ѯ����(�Զ����ѯ)
    	UserExample userExample=new UserExample();  
        //ͨ��Criteria�����ѯ����  
    	UserExample.Criteria criteria=userExample.createCriteria();  
    	//��ѯ����1��username equal '������'
        criteria.andUsernameEqualTo("������");
        //��ѯ����2��gender <> 'Ů'
        criteria.andGenderNotEqualTo("Ů");
        //��ѯ����3��brithday between '1990-01-01' and '1994-01-01'
        criteria.andBirthdayBetween(sdf.parse("1990-01-01"), sdf.parse("1994-01-01"));
        //��ѯ����4��email is not null
        criteria.andEmailIsNotNull();
        //���ܷ��ض�����¼  
        List<User> list=userMapper.selectByExample(userExample);  
        for (int i = 0; i < list.size(); i++) {  
            User uItem=list.get(i);  
            System.out.println(uItem.getId()+":"+uItem.getUsername());  
        } 
        
        //3.���Բ�ѯ����(����id��ѯ)
        User user2=userMapper.selectByPrimaryKey(1);  
        System.out.println("3.������ѯ��idΪ1���û�����Ϊ"+user2.getUsername());  
        
        //4.�����޸Ĳ���(�������ֶν��и���)
        //�������ֶν��и��£���Ҫ�Ȳ�ѯ�����ٸ���  
        User user3 = userMapper.selectByPrimaryKey(1);      
        user3.setEmail("zhangsan@126.com");      
        userMapper.updateByPrimaryKey(user3);  
        System.out.println("4.����idΪ"+user3.getId()+"���û���������Ϣ");
         
        //5.�����޸Ĳ���(�Ը����ֶν��и���)
        //��������ֶβ���Ϊ�Ÿ��£�������������ʹ�ô˷���������Ҫ�Ȳ�ѯ�ٸ���  
        User user4 = new User();
        //ֻ�޸��û���Email��Ϣ
        user4.setId(1);
        user4.setEmail("zhangsan@126.com");  
        userMapper.updateByPrimaryKeySelective(user4); 
        System.out.println("5.����idΪ"+user4.getId()+"���û�EmailΪ:"
				+user4.getEmail());
        
        //6.����ɾ������
        int deleteId = 5;
        userMapper.deleteByPrimaryKey(deleteId);
        User user5=userMapper.selectByPrimaryKey(deleteId);
        if(user5==null){
        	System.out.println("6.ɾ��idΪ"+deleteId+"���û��ɹ���ɾ���ɹ�");
        }
	}
}
