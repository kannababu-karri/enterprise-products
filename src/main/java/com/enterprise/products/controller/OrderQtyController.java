
package com.enterprise.products.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.enterprise.products.entities.Manufacturer;
import com.enterprise.products.entities.OrderQty;
import com.enterprise.products.entities.Product;
import com.enterprise.products.entities.User;
import com.enterprise.products.form.OrderQtyForm;
import com.enterprise.products.service.KafkaProducerService;
import com.enterprise.products.utils.ILConstants;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orderqty")
public class OrderQtyController {
    
    
    private static final Logger _LOGGER = LoggerFactory.getLogger(OrderQtyController.class);
    //@Autowired
    //private OrderQtyService orderQtyService;
    
    @Autowired
    //private OrderDocumentService orderDocumentService; //Mongodb intraction
    private KafkaProducerService kafkaProducerService;
	
	private final RestTemplate restTemplate;

    public OrderQtyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * Show all order qty
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(
    		value = "/showOrderQtyDetails",
    		method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String showOrderQtyDetails(Model model, HttpServletRequest request) {
    	
    	_LOGGER.info(">>> Inside showOrderQtyDetails. <<<");
    	
    	OrderQtyForm form = getAllOrderQtys(request);
    	
    	retrieveForSelections(form);
    	
    	model.addAttribute("orderQtyForm", form);
    	
        return "orderqty/orderQtyHome";
    }
    
    /**
     * Submit the Order Qty search page
     * 
     * @param Manufacturer
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/orderQtySearch")
    public String orderQtySearch(@ModelAttribute("orderqty") OrderQty orderQty, 
    										Model model,
    										HttpSession session) {
    	
    	_LOGGER.info(">>> Inside orderQtySearch. <<<");
    	
       	Long manufacturerId = orderQty.getManufacturer().getManufacturerId();
       	Long productId = orderQty.getProduct().getProductId();
       	User user = (User) session.getAttribute(Utils.getSessionLoginUserIdKey());
       	Long userId = user.getUserId();
       	
       	_LOGGER.info(">>> Inside orderQtySearch. manufacturerId:<<<"+manufacturerId);
       	_LOGGER.info(">>> Inside orderQtySearch. productId:<<<"+productId);
       	_LOGGER.info(">>> Inside orderQtySearch. userId:<<<"+userId);
       	
		//Microservice endpoint
		String url = ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL;
		
		ResponseEntity<OrderQty[]> response = null;	
 
        List<OrderQty> orderQtys = null;
        
        boolean exceptionThrow = false;
        
        try {
	        if (manufacturerId != null && manufacturerId.longValue() > 0 && productId != null && productId.longValue() > 0) {
	        	//orderQtys = orderQtyService.findByManufacturer_ManufacturerIdAndProduct_ProductIdAndUser_UserId(manufacturerId, productId, userId);
	        	url = url+"/search/mfgproductuser?manufacturerId={"+manufacturerId+"}&productId={"+productId+"}&userId={"+userId+"}";
	            _LOGGER.info(">>> Inside orderQtySearch. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    OrderQty[].class,
	                    manufacturerId,
	                    productId,
	                    userId
	            );  
	        } else if (manufacturerId != null && manufacturerId.longValue() > 0) {
	        	//orderQtys = orderQtyService.findByManufacturer_ManufacturerIdAndUser_UserId(manufacturerId, userId);
	        	url = url+"/search/mfguser?manufacturerId={"+manufacturerId+"}&userId={"+userId+"}";
	            _LOGGER.info(">>> Inside orderQtySearch. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    OrderQty[].class,
	                    manufacturerId,
	                    userId
	            );
	        } else if (productId != null && productId.longValue() > 0) {
	        	//orderQtys = orderQtyService.findByProduct_ProductIdAndUser_UserId(productId, userId);
	        	url = url+"/search/productuser?productId={"+productId+"}&userId={"+userId+"}";
	            _LOGGER.info(">>> Inside orderQtySearch. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    OrderQty[].class,
	                    productId,
	                    userId
	            );
	        } else {
	        	//orderQtys = orderQtyService.findByUser_UserId(userId);
	        	// Build URL with query parameters
	            url = url+"/userid/{"+userId+"}";
	            _LOGGER.info(">>> Inside orderQtySearch. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    OrderQty[].class,
	                    userId
	            );
	        }
	    } catch (Exception ex) {
	    	exceptionThrow = true;
	    	model.addAttribute("error", "No orders are find for selected criteria.");
	    }
        
        
        // Convert array to list
        if(response != null) {
        	orderQtys = Arrays.asList(response.getBody());
        } else {
        	if(!exceptionThrow) {
        		orderQtys = getRestAllOrderQtys();
        	}
        }
        
        OrderQtyForm form = new OrderQtyForm();
		
		form.setOrderQty(orderQty);
    	
    	if(orderQtys != null && !orderQtys.isEmpty() && orderQtys.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultOrderQtys(orderQtys);
    	} else {
    		model.addAttribute("error", "No orders are find for selected criteria.");
    	}
    	
    	retrieveForSelections(form);
    	
    	//Reset the selected values
    	form.setManufacturer(orderQty.getManufacturer());
    	form.setProduct(orderQty.getProduct());
    	
    	model.addAttribute("orderQtyForm", form);
        
        return "orderqty/orderQtyHome";
    }
    
    /**
     * Add new order qty
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayNewOrderQty")
    public String displayNewOrderQty(Model model, HttpServletRequest request) {
    	OrderQty orderQty = new OrderQty();

    	model.addAttribute("orderQty", orderQty);
		
    	OrderQtyForm form = new OrderQtyForm();
    	
    	retrieveForSelections(form);
    	
    	model.addAttribute("orderQtyForm", form);
    	
        return "orderqty/addOrderQty";
    }
    
    /**
     * Save OrderQty
     * 
     * @param product
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/saveNewOrderQty")
    @Transactional
    public String saveNewOrderQty(@ModelAttribute("orderqty") OrderQty orderQty, 
							Model model,
							HttpServletRequest request) throws Exception {
    	
    	_LOGGER.info(">>> Inside saveNewOrderQty.<<<");
    	
    	OrderQtyForm form = new OrderQtyForm();
    	form.setOrderQty(orderQty);
    	
    	List<String> errors = checkInput(orderQty);
    	
    	boolean processValidation = false;
    	
       	if (errors.isEmpty()) {
       		
       		User user = (User) request.getSession().getAttribute(Utils.getSessionLoginUserIdKey());
       		
       		//Find out already order qty exists
       		//List<OrderQty> orderQtys = orderQtyService.findByManufacturer_ManufacturerIdAndProduct_ProductIdAndUser_UserId(
       		//		orderQty.getManufacturer().getManufacturerId(), 
       		//		orderQty.getProduct().getProductId(), 
       		//		user.getUserId());
       		//Call microservice
       		String url = ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL+"/search/mfgproductuser?"
       				+ "manufacturerId="+orderQty.getManufacturer().getManufacturerId()+"&"
       						+ "productId="+orderQty.getProduct().getProductId()+"&"
       								+ "userId="+user.getUserId();
       		_LOGGER.info(">>> url.<<<: "+url);
    		ResponseEntity<OrderQty[]> response = restTemplate.getForEntity(
                    url,
                    OrderQty[].class,
                    orderQty.getManufacturer().getManufacturerId(),
                    orderQty.getProduct().getProductId(),
                    user.getUserId()
            ); 
    		
            List<OrderQty> orderQtys = Arrays.asList(response.getBody());
            
            _LOGGER.info(">>> saveNewOrderQty-->orderQtys.<<<: "+orderQtys);
       		
       		if(orderQtys != null && orderQtys.size() > 0) {
       			errors.add("Order already existing in the system..");
       		} else {
       			
       			_LOGGER.info(">>> saveNewOrderQty-->else.<<<: ");
       		
	       		orderQty.setUser(user);
	       		
	       		//Get manufacturer details for input mango db.
	       		//Manufacturer manufacturer = orderQtyService.findByManufacturerId(orderQty.getManufacturer().getManufacturerId());
	       		//Get manufacturer micro service
	       		//Microservice endpoint
				Manufacturer manufacturer = getRestManufacturerById(orderQty.getManufacturer().getManufacturerId());
	            //Get manufacturer
	       		orderQty.setManufacturer(manufacturer);
	       	       		
	       		//Get product details from input mango db.
	       		//Product product = orderQtyService.findByProductId(orderQty.getProduct().getProductId());
	       		//Get product micro service
	       		//Microservice endpoint
	            Product product = getRestProductById(orderQty.getProduct().getProductId());
	            //Get product
	       		orderQty.setProduct(product);
	       				
	       		//OrderQty result = orderQtyService.saveOrUpdate(orderQty);
	       		//Microservice endpoint
	            // Call microservice POST endpoint
	       		OrderQty result = restTemplate.postForObject(ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL, orderQty, OrderQty.class);
		    	
		    	if(result != null && result.getOrderId() > 0) {
			    	
			    	model.addAttribute("orderQtyForm", form);
			    	
			    	model.addAttribute("msg", "Order added successfully.");
			    	
			    	processValidation = true;
			    	
			    	//Execute kafka mongodb and file
			    	try {
			    		//Send the value to kafka producer
			    		result.setDocumentType(ILConstants.MONGODB_OPERATION_SAVE);
			    		_LOGGER.info(">>> saveNewOrderQty-->before sending kafka producer.<<<: "+ILConstants.MONGODB_OPERATION_SAVE);
						kafkaProducerService.send(result);
			    	} catch(Exception ex) {
			       		//DONT throw any exception
			    	}
			    	
			    	//User is save sent to user home
			    	return "forward:/orderqty/showOrderQtyDetails"; 
		    	} else {
		    		model.addAttribute("errors", "Order not added into the system.");
		    	}
       		}
    	} 
       	
       	if(!processValidation) {
    		retrieveForSelections(form);
    	   	//Reset the selected values
        	form.setManufacturer(orderQty.getManufacturer());
        	form.setProduct(orderQty.getProduct());
        	
        	form.getOrderQty().setQuantity(orderQty.getQuantity());
        	
           	model.addAttribute("errors", errors);
    	}
       	model.addAttribute("orderQtyForm", form);
    	//If error display same page
        return "orderqty/addOrderQty";
    }    
    /**
     * display update order qty
     * 
     * @param product id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayUpdateOrderQty")
    public String displayUpdateOrderQty(@RequestParam("orderId") Long orderId, Model model) {
    	
    	_LOGGER.info(">>> Inside displayUpdateOrderQty.<<<");
    	_LOGGER.info(">>> orderId display = <<<"+ orderId);
    	
    	//OrderQty orderQty = orderQtyService.findByOrderId(orderId);
    	OrderQty orderQty = getRestOrderQtyByOrderId(orderId);
    	
    	model.addAttribute("orderqty", orderQty);
    	
    	OrderQtyForm form = new OrderQtyForm();
		
		form.setOrderQty(orderQty);
		
		model.addAttribute("orderQtyForm", form);
    	
        return "orderqty/updateOrderQty";
    }
    
    /**
     * Update order qty
     * 
     * @param orderQty
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/updateOrderQty")
    @Transactional
    public String updateOrderQty(@ModelAttribute("orderQty") OrderQty orderQty, 
							Model model,
							HttpServletRequest request) throws Exception {
    	
    	_LOGGER.info(">>> Inside updateOrderQty.<<<");
    	_LOGGER.info(">>> getOrderId ID =  <<<"+ orderQty.getOrderId());
    	
        OrderQtyForm form = new OrderQtyForm();
        form.setOrderQty(orderQty);
        
       	model.addAttribute("orderQtyForm", form);
   		
       	//OrderQty existingOrderQty = orderQtyService.findByOrderId(orderQty.getOrderId());
       	OrderQty existingOrderQty = getRestOrderQtyByOrderId(orderQty.getOrderId());
   		
   		if(existingOrderQty != null && existingOrderQty.getOrderId() > 0) {
   			
   			existingOrderQty.setQuantity(orderQty.getQuantity());
   			
   			User user = (User) request.getSession().getAttribute(Utils.getSessionLoginUserIdKey());
   			existingOrderQty.setUser(user);
   			
       		//Get manufacturer details for input mango db.
       		//Manufacturer manufacturer = orderQtyService.findByManufacturerId(existingOrderQty.getManufacturer().getManufacturerId());
   			Manufacturer manufacturer = getRestManufacturerById(existingOrderQty.getManufacturer().getManufacturerId());
       		existingOrderQty.setManufacturer(manufacturer);
       	       		
       		//Get product details from input mango db.
       		//Product product = orderQtyService.findByProductId(existingOrderQty.getProduct().getProductId());
       		Product product = getRestProductById(existingOrderQty.getProduct().getProductId());
       		existingOrderQty.setProduct(product);
   		
   			//OrderQty result = orderQtyService.updateOrderQty(existingOrderQty);
       		OrderQty result = restTemplate.postForObject(ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL, existingOrderQty, OrderQty.class);
	    	
	    	if(result != null && result.getOrderId() > 0) {
		    	
		    	model.addAttribute("orderQtyForm", form);
		    	
		    	model.addAttribute("msg", "Order updated successfully.");
		    	
		    	//Execute kafka mongodb and file
		    	try {
		    		//Send the value to kafka producer
		    		result.setDocumentType(ILConstants.MONGODB_OPERATION_UPDATE);
		    		_LOGGER.info(">>> updateOrderQty-->before sending kafka producer.<<<: "+ILConstants.MONGODB_OPERATION_UPDATE);
					kafkaProducerService.send(result);
		    	} catch(Exception ex) {
		       		//DONT throw any exception
		    	}
		    	
		    	//User is save sent to user home
		    	return "forward:/orderqty/showOrderQtyDetails"; 
	    	} else {
	    		model.addAttribute("error", "Order not updated into the system.");
	    	}
   		} else {
   			model.addAttribute("error", "Order not existed into the system.");
   		}
 
    	//If error display same page
        return "orderqty/updateOrderQty";
    }
    
    /**
     * display delete order qty
     * 
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/displayDeleteOrderQty")
    public String displayDeleteProduct(@RequestParam("orderId") Long orderId, Model model) {
    	_LOGGER.info(">>> Inside updateOrderQty.<<<");
    	_LOGGER.info(">>> Order ID display =.<<<");
    	
    	//OrderQty orderQty = orderQtyService.findByOrderId(orderId);
    	OrderQty orderQty = getRestOrderQtyByOrderId(orderId);
    	
    	model.addAttribute("orderqty", orderQty);
    	
    	OrderQtyForm form = new OrderQtyForm();
		
    	form.setOrderQty(orderQty);
    		
    	model.addAttribute("orderQtyForm", form);
    	
        return "orderqty/deleteOrderQty";
    }
    
    /**
     * Delete order qty
     * 
     * @param orderQty
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/deleteOrderQty")
    @Transactional
    public String deleteOrderQty(@ModelAttribute("orderQty") OrderQty orderQty, 
							Model model,
							HttpServletRequest request) {

    	System.out.println("getOrderId ID = " + orderQty.getOrderId());
    	
        OrderQtyForm form = new OrderQtyForm();
        
        form.setOrderQty(orderQty);
         		
        //OrderQty existingOrderQty = orderQtyService.findByOrderId(orderQty.getOrderId());
        OrderQty existingOrderQty = getRestOrderQtyByOrderId(orderQty.getOrderId());
   		
   		if(existingOrderQty != null && existingOrderQty.getOrderId() > 0) {
   			
   			//orderQtyService.deleteByOrderId(existingOrderQty.getOrderId());
      		//Microservice endpoint
   			String msg = null;
   			String url = ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL+"/{id}";
   			try {
	            //Call microservice DELETE endpoint
	   			restTemplate.delete(url, existingOrderQty.getOrderId());
	   			msg = "Order deleted successfully.";
	   			model.addAttribute("msg", msg);
	   			
	   			//Execute kafka mongodb and file
		    	try {
		    		//Send the value to kafka producer
		    		existingOrderQty.setDocumentType(ILConstants.MONGODB_OPERATION_DELETE);
		    		_LOGGER.info(">>> deleteOrderQty-->before sending kafka producer.<<<: "+ILConstants.MONGODB_OPERATION_DELETE);
					kafkaProducerService.send(existingOrderQty);
		    	} catch(Exception ex) {
		       		//DONT throw any exception
		    	}
	   			
   			} catch (HttpClientErrorException.NotFound e) {
   	            model.addAttribute("error", "Order not found with id: " + existingOrderQty.getOrderId());
   	        } catch (HttpServerErrorException e) {
   	            model.addAttribute("error", "Server error occurred: " + e.getResponseBodyAsString());
   	        } catch (Exception e) {
   	            model.addAttribute("error", "Unexpected error: " + e.getMessage());
   	        }
   			
	    	form = getAllOrderQtys(request);
	    	
	    	model.addAttribute("orderQtyForm", form);
	    	
	    	//model.addAttribute("msg", "Product deleted successfully.");
	    	if(msg != null && !msg.isEmpty()) {
		    	//User is save sent to user home
	    		return "forward:/orderqty/showOrderQtyDetails"; 
	    	}
   		} else {
   			model.addAttribute("error", "Order not existed into the system.");
   		}
    	
    	//If error display same page
        return "orderqty/deleteOrderQty";
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
     * Get manufacturers and products
     * @param form
     */
    
	private void retrieveForSelections(OrderQtyForm form) {
		//List<Manufacturer> manufacturers = orderQtyService.findAllManufacturers();
		List<Manufacturer> manufacturers = getRestAllManufacturers();
    	//List<Product> products = orderQtyService.findAllProducts();
		List<Product> products = getRestAllProducts();
    	
    	form.setManufacturers(manufacturers);
    	form.setProducts(products);
	}
    
    /**
     * Retrieving all orderQtys. This method is used in retrieve and save.
     */
	private OrderQtyForm getAllOrderQtys(HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute(Utils.getSessionLoginUserIdKey());
		
		_LOGGER.info("user.getUserId()"+user.getUserId());
		
		//List<OrderQty> orderQtys = orderQtyService.findByUser_UserId(user.getUserId());
		List<OrderQty> orderQtys = null;
		String url = ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL+"/userid/{"+user.getUserId()+"}";
        _LOGGER.info(">>> Inside getAllOrderQtys. url:<<<"+url);
        //Call microservice
        ResponseEntity<OrderQty[]> response = restTemplate.getForEntity(url, OrderQty[].class, user.getUserId());
        
        orderQtys = Arrays.asList(response.getBody());
		
		if(orderQtys != null && orderQtys.size() > 0) {
			_LOGGER.info("orderQtys.size()"+orderQtys.size());
		}
    		
		OrderQtyForm form = new OrderQtyForm();
		
		form.setOrderQty(new OrderQty());
    	
    	if(!orderQtys.isEmpty() && orderQtys.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultOrderQtys(orderQtys);
    	}
		return form;
	}
	
	/**
	 * 
	 * @param manufacturerId
	 * @return
	 */
	private Manufacturer getRestManufacturerById(Long manufacturerId) {
		String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL+"/id/{"+manufacturerId+"}";
		//Call REST API
		ResponseEntity<Manufacturer> responseMfg = restTemplate.getForEntity(url, Manufacturer.class, manufacturerId);
		return responseMfg.getBody();
	}
	
	/**
	 * 
	 * @param productId
	 * @return
	 */
	private Product getRestProductById(Long productId) {
		String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL+"/id/{"+productId+"}";
		//Call REST API
		ResponseEntity<Product> responseProduct = restTemplate.getForEntity(url, Product.class, productId);
		return responseProduct.getBody();
	}
	
	/**
	 * Get product by id.
	 * 
	 * @param productId
	 * @return
	 */
	private OrderQty getRestOrderQtyByOrderId(Long orderId) {
		//Microservice endpoint
        String url = ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL+"/orderid/{"+orderId+"}";
        //Call REST API
        ResponseEntity<OrderQty> response = restTemplate.getForEntity(url, OrderQty.class, orderId);
        //Get product
		return response.getBody();
	}
	
	/**
	 * Get all manufacturer from RESTFUL
	 * @return
	 */
	private List<OrderQty> getRestAllOrderQtys() {
		//Microservice endpoint
		String url = ILConstants.MICROSERVICE_RESTFUL_ORDERQTY_URL;
		
		ResponseEntity<OrderQty[]> response = restTemplate.getForEntity(url, OrderQty[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	/**
	 * Get all manufacturer from RESTFUL
	 * @return
	 */
	private List<Manufacturer> getRestAllManufacturers() {
		//Microservice endpoint
		String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL;
		
		ResponseEntity<Manufacturer[]> response = restTemplate.getForEntity(url, Manufacturer[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	/**
	 * Get all manufacturer from RESTFUL
	 * @return
	 */

	private List<Product> getRestAllProducts() {
		//Microservice endpoint
		String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL;
		
		ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	/**
	 * Check inputs conditions
	 * 
	 * @param order qty
	 * @return
	 */
	private List<String> checkInput(OrderQty orderQty) {
		List<String> errors = new ArrayList<>();
    	
    	//Check the conditions
    	if(orderQty.getManufacturer().getManufacturerId() == null || 
    			(orderQty.getManufacturer().getManufacturerId() != null && orderQty.getManufacturer().getManufacturerId() == 0)) {
    		errors.add("Select manufacturer name.");
    	}
    	
    	//Check the conditions
    	if(orderQty.getProduct().getProductId() == null || 
    			(orderQty.getProduct().getProductId() != null && orderQty.getProduct().getProductId() == 0)) {
    		errors.add("Select product name.");
    	}
    	
    	//Check the conditions
    	if(orderQty.getQuantity() == null || (orderQty.getQuantity() != null && orderQty.getQuantity() == 0)) {
    		errors.add("Enter valid quantity.");
    	}
    	
		return errors;
	}
}
