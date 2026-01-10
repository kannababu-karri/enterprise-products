package com.enterprise.products.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enterprise.products.entities.OrderDocument;
import com.enterprise.products.entities.User;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.form.FileReadForm;
import com.enterprise.products.service.FileWriterService;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/fileRead")
public class FileReadController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(FileReadController.class);
    @Autowired
    private FileWriterService fileWriterService;

    /**
     * Show all order from file.
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(
    		value = "/showFileDetails",
    		method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String showFileDetails(Model model, HttpServletRequest request) throws Exception {
    	_LOGGER.info("Entered showFileDetails");
    	try {
	    	List<OrderDocument> orderDocuments = fileWriterService.readFromFile();
	    	System.out.println("Entered orderDocuments:"+orderDocuments.size());
	    	
	    	FileReadForm form = new FileReadForm();
	    	
	    	if(!orderDocuments.isEmpty() && orderDocuments.size() > 0) {
	    		form.setShowDetails(true);
	    		form.setResultOrderDocuments(orderDocuments);
	    	}
	    	
	    	model.addAttribute("fileReadForm", form);
	    	
	        
    	} catch (ServiceException exp) {
			_LOGGER.error("ERROR: Service Exception occured while readFromFile."+exp.toString());	
			throw new Exception("ERROR: Service Exception occured while readFromFile.."+exp.toString());
		}
    	return "fileRead/fileReadHome";
    }
    
    /**
     * Return to IL home
     * 
     * @param session
     * @return
     */
    @GetMapping("/returnILHome")
    public String returnILHome(HttpSession session) {
    	User user = (User) session.getAttribute(Utils.getSessionLoginUserIdKey());
        if (user == null) {
            return "forward:/login"; // forward if not logged in
        }
        return "ilHome";
    }

}
