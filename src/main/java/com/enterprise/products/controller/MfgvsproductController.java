package com.enterprise.products.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enterprise.products.entities.MfgVsProduct;
import com.enterprise.products.entities.User;
import com.enterprise.products.form.MfgvsproductForm;
import com.enterprise.products.service.MfgvsproductService;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mfgvsproduct")
public class MfgvsproductController {
	@Autowired
	private MfgvsproductService mfgvsproductService;
	
    /**
     * Show all Mfg vs Products
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(
    		value = "/showMfgvsproductDetails",
    		method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String showMfgvsproductDetails(Model model, HttpServletRequest request) {
    	MfgvsproductForm form = getAllMfgvsproducts(request);
    	
    	model.addAttribute("mfgvsproductForm", form);
    	
        return "mfgvsproduct/mfgvsproductHome";
    }
    
    /**
     * Submit the product search page
     * 
     * @param Manufacturer
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/mfgvsproductSearch")
    public String mfgvsproductSearch(@ModelAttribute("mfgvsproduct") MfgVsProduct mfgvsproduct, 
    										Model model,
    										HttpSession session) {
     	
       	Long manufacturerId = mfgvsproduct.getManufacturerId();
 
        List<MfgVsProduct> mfgvsproducts = null;
        
        if(manufacturerId > 0) {
        	MfgVsProduct mfgvsproductResult = mfgvsproductService.findByManufacturerId(manufacturerId);
        	mfgvsproducts = new ArrayList<MfgVsProduct>();
        	mfgvsproducts.add(mfgvsproductResult);
        } else {
        	mfgvsproducts = mfgvsproductService.findAllMfgVsProducts();
        }
        
        MfgvsproductForm form = new MfgvsproductForm();
		
		form.setMfgvsproduct(mfgvsproduct);
    	
    	if(mfgvsproducts != null && !mfgvsproducts.isEmpty() && mfgvsproducts.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultMfgvsproducts(mfgvsproducts);
    	} else {
    		model.addAttribute("error", "No manufacturer are find for selected criteria.");
    	}
    	
    	model.addAttribute("mfgvsproductForm", form);
        
    	return "mfgvsproduct/mfgvsproductHome";
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

    /**
     * Retrieving all manufacturer. This method is used in retrieve and save.
     */
	private MfgvsproductForm getAllMfgvsproducts(HttpServletRequest request) {
		
		List<MfgVsProduct> mfgvsproducts = mfgvsproductService.findAllMfgVsProducts();
    		
		MfgvsproductForm form = new MfgvsproductForm();
		
		form.setMfgvsproduct(new MfgVsProduct());
    	
    	if(!mfgvsproducts.isEmpty() && mfgvsproducts.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultMfgvsproducts(mfgvsproducts);
    	}
		return form;
	}
}



