package cn.com.mvcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExceptionPropertyUtil {
	
	private Properties prop;// ���Լ��϶���  
	private InputStream fis;// �����ļ�������   
	
	private void init() throws IOException{
		prop = new Properties();// ���Լ��϶���   
	    fis = this.getClass().getResourceAsStream("/exceptionMapping.properties");// �����ļ�������   
	    prop.load(fis);// �������ļ���װ�ص�Properties������   
	    fis.close();// �ر���   
	}
	
	public String getExceptionMsg(String ExceptionCode) throws IOException{
		init();
		String msg = prop.getProperty(ExceptionCode);
		if(msg!=null){
			return msg;
		}else{
			return "δ�����쳣";
		} 
	}
}
