package fpoly.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest req;
	
	public String getString(String name, String defaultValue) {
		String value = req.getParameter(name);
		return value != null ? value : defaultValue;
	}
	
	public int getInt(String name, int defaultValue) {
		String value = req.getParameter(name);
		return value != null ? Integer.parseInt(value) : defaultValue; 
	}
	
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = req.getParameter(name);
		return value != null ? Boolean.parseBoolean(value) : defaultValue;
	}
	
	public double getDouble(String name, double defaultVale) {
		String value = req.getParameter(name);
		return value != null ? Double.parseDouble(value): defaultVale;
	}
	
	public Date getDate(String name, String pattern) {
		String value = req.getParameter(name);
		
		if(value != null) {
			try {
				SimpleDateFormat s = new SimpleDateFormat(pattern);
				return s.parse(value);
			} catch (Exception e) {
				throw new RuntimeException("Sai dinh dang");
			}
		}
		
		return null;
	}
	
	public File save(MultipartFile file) {
		if(file == null || file.isEmpty()) {
			return null;
		}
		String webPath = req.getServletContext().getRealPath("/");
		String savePath = webPath + "/uploads";
		
		try {
			File fileSave = new File(savePath);
			if(!fileSave.exists()) {
				fileSave.mkdirs();
			}
			String fileName = file.getOriginalFilename();
			File saveFile = new File(savePath, fileName);
			file.transferTo(saveFile);
			return saveFile;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error"+e.getMessage(),e);
		}
	}
}
