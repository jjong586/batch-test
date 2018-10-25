package com.secl.test;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		
		Image image = Image.getInstance("C:/Users/User/Downloads/SO2455-contractorlogo.jpg");
		image.setAlignment(Image.ALIGN_RIGHT);
		image.scaleAbsolute(80, 40);

		String fileName="";
		String dir="D:/www/kipes/kipes/report/report";
		fileName = "simple_table.pdf";

		File directory = new File(dir);
		if(!directory.exists()) directory.mkdirs(); //파일경로 없으면 생성

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(dir+"/"+fileName));

		document.open();
		PdfPTable table = new PdfPTable(4);

		for(int i = 0; i < 16; i++){
		table.addCell("cell-가-Number:" + i);
		}
		
		document.add(image);
		document.add(table);
		document.close();
	        
	        
	        
		
		
		return "home";
	}
	
}
