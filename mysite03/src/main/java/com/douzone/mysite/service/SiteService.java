package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryServiceException;
import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {
	private static String SAVE_PATH = "/upload-mysite/admin";
	private static String URL_BASE = "/admin/images";
	
	@Autowired
	SiteRepository siteRepository;
	
	public String saveImg(MultipartFile file) throws GalleryServiceException{
		try {
			File uploadDirectory = new File(SAVE_PATH);
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdir();
			}

			if(file.isEmpty()) {
				throw new GalleryServiceException("file upload error: image empty");
			}

			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
			String saveFilename = generateSaveFilename(extName);

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();

			return URL_BASE + "/" + saveFilename;

			
		} catch(IOException ex) {
			throw new GalleryServiceException("file upload error:" + ex);
		}
		
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
	
	
	public SiteVo getSite() {
		return siteRepository.find();
	}

	public void update(SiteVo siteVo) {
		if(siteRepository.findAdminMain() == true){
			siteRepository.update(siteVo);	
		} else {
			siteRepository.insert(siteVo);
		}
		
	}
	
}
