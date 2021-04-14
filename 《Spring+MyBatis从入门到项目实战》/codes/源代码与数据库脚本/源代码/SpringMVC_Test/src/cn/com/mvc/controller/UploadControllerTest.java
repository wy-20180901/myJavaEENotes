package cn.com.mvc.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadControllerTest {
	
	@RequestMapping("toUploadPage")
    public String toUploadPage(Model model)throws Exception{
		return "/ImgUploadTest";
	}
	
	@RequestMapping("uploadImg")
    public String uploadImg(Model model,MultipartFile file)throws Exception{
		//�ϴ���ͼƬ��ԭʼ����
		String originalFilename=file.getOriginalFilename();
		String newFileName = null;
		//�ϴ�ͼƬ
		if(file!=null&&originalFilename!=null&&originalFilename.length()>0){
			//�洢ͼƬ������·��
			String pic_path="G:\\upload\\";
			//�µ�ͼƬ���ƣ�UUID��������ƣ�
			newFileName=UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile=new File(pic_path+newFileName);
			//���ڴ��е�����д�����
			file.transferTo(newFile);
		}
		
		//���Ըղ��ϴ��õ�ͼƬ����
		model.addAttribute("image", newFileName); 
		//�ػص��ϴ�ҳ��
		return "/ImgUploadTest";
	}
}
