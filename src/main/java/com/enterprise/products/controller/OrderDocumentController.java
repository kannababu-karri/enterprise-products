package com.enterprise.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enterprise.products.entities.Manufacturer;
import com.enterprise.products.entities.OrderDocument;
import com.enterprise.products.entities.Product;
import com.enterprise.products.entities.User;
import com.enterprise.products.form.OrderDocumentForm;
import com.enterprise.products.service.OrderDocumentService;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orderDocument")
public class OrderDocumentController {
    @Autowired
    private OrderDocumentService orderDocumentService;

    /**
     * Show all all orders from mongo db
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(
    		value = "/showMongoDbDetails",
    		method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String showMongoDbDetails(Model model, HttpServletRequest request) {
    	
    	User user = (User) request.getSession().getAttribute(Utils.getSessionLoginUserIdKey());
    	
		List<OrderDocument> orderDocuments = orderDocumentService.findByUserId(user.getUserId());
		
		if(!orderDocuments.isEmpty() && orderDocuments.size() > 0) {
			System.out.println("orderDocuments: "+orderDocuments.size());
		} else {
			System.out.println("orderDocuments: 0");
		}
		
		OrderDocumentForm form = new OrderDocumentForm();
		
		retrieveForSelections(form);
		
		form.setOrderDocument(new OrderDocument());
    	
    	if(!orderDocuments.isEmpty() && orderDocuments.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultOrderDocuments(orderDocuments);
    	}
    	
    	model.addAttribute("orderDocumentForm", form);
    	
        return "orderDocument/orderDocumentHome";
    }
    
    /**
     * Submit the OrderDocument search page
     * 
     * @param OrderDocument
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/orderDocumentSearch")
    public String orderDocumentSearch(@ModelAttribute("orderDocumentForm") OrderDocumentForm form, 
    										Model model,
    										HttpSession session) {
    	System.out.println("Entered into orderDocumentSearch");
    	
       	Long manufacturerId = form.getOrderDocument().getManufacturerId();
       	Long productId = form.getOrderDocument().getProductId();
       	User user = (User) session.getAttribute(Utils.getSessionLoginUserIdKey());
       	Long userId = user.getUserId();
       	
       	System.out.println("manufacturerId: "+manufacturerId);
       	System.out.println("productId: "+productId);
       	System.out.println("userId: "+userId);
 
        List<OrderDocument> orderDocuments = null;
        
        if (manufacturerId != null && manufacturerId.longValue() > 0 && productId != null && productId.longValue() > 0) {
        	orderDocuments = orderDocumentService.findByManufacturerIdAndProductIdAndUserId(manufacturerId, productId, userId);
        } else if (manufacturerId != null && manufacturerId.longValue() > 0) {
        	orderDocuments = orderDocumentService.findByManufacturerId(manufacturerId, userId);
        } else if (productId != null && productId.longValue() > 0) {
        	orderDocuments = orderDocumentService.findByProductId(productId, userId);
        } else {
        	orderDocuments = orderDocumentService.findByUserId(userId);
        }
        
        //OrderDocumentForm form = new OrderDocumentForm();
   	
		//form.setOrderDocument(orderDocument);
    	
    	if(!orderDocuments.isEmpty() && orderDocuments.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultOrderDocuments(orderDocuments);
    	} else {
    		model.addAttribute("error", "No orders are find for selected criteria.");
    	}
    	
    	retrieveForSelections(form);
    	
    	model.addAttribute("orderDocumentForm", form);
        
        return "orderDocument/orderDocumentHome";
    }
    
    /**
     * Get manufacturers and products
     * @param form
     */
    
	private void retrieveForSelections(OrderDocumentForm form) {
		List<Manufacturer> manufacturers = orderDocumentService.findAllManufacturers();
    	List<Product> products = orderDocumentService.findAllProducts();
    	
    	form.setManufacturers(manufacturers);
    	form.setProducts(products);
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
