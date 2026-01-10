package com.enterprise.products.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.enterprise.products.entities.Product;
import com.enterprise.products.entities.User;
import com.enterprise.products.form.ProductForm;
import com.enterprise.products.utils.ILConstants;
import com.enterprise.products.utils.StringUtility;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger _LOGGER = LoggerFactory.getLogger(ProductController.class);
    //@Autowired
    //private ProductService productService;
	
	private final RestTemplate restTemplate;

    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Show all products
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(
    		value = "/showProductDetails",
    		method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String showProductDetails(Model model, HttpServletRequest request) {
    	_LOGGER.info(">>> Inside showProductDetails. <<<");
    	ProductForm form = getAllProducts(request);
    	
    	model.addAttribute("productForm", form);
    	
        return "product/productHome";
    }
    
    /**
     * Submit the product search page
     * 
     * @param Manufacturer
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/productSearch")
    public String productSearch(@ModelAttribute("product") Product product, 
    										Model model,
    										HttpSession session) {
    	
    	_LOGGER.info(">>> Inside showProductDetails. <<<");
     	
       	String productName = product.getProductName();
       	String productDescription = product.getProductDescription();
       	String casNumber = product.getCasNumber();
       	
       	_LOGGER.info(">>> Inside showProductDetails. productName:<<<"+productName);
       	_LOGGER.info(">>> Inside showProductDetails. productDescription:<<<"+productDescription);
       	_LOGGER.info(">>> Inside showProductDetails. casNumber:<<<"+casNumber);
 
        List<Product> products = null;
        
		//Microservice endpoint
		String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL;
		
		ResponseEntity<Product[]> response = null;	
		
		boolean exceptionThrow = false;
        
		try {
	        if (!StringUtility.isEmpty(productName) && !StringUtility.isEmpty(productDescription) && !StringUtility.isEmpty(casNumber)) {    	
	        	//products = productService.findByProductNameDesCanNumber(productName.trim(), productDescription.trim(), casNumber.trim());
	        	// Build URL with query parameters
	            url = url+"/search?name={"+productName+"}&description={"+productDescription+"}&casNumber={"+casNumber+"}";
	            _LOGGER.info(">>> Inside showProductDetails. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    Product[].class,
	                    productName,
	                    productDescription,
	                    casNumber
	            );         
	        } else if (!StringUtility.isEmpty(productName)) {
	        	//products = productService.findByProductNameLike(productName);
	           	// Build URL with query parameters
	            url = url+"/search/productName/{"+productName+"}";
	            _LOGGER.info(">>> Inside showProductDetails. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    Product[].class,
	                    productName
	            );
	        } else if (!StringUtility.isEmpty(productDescription)) {
	        	//products = productService.findByProductDescriptionLike(productDescription);
	           	// Build URL with query parameters
	            url = url+"/search/description/{"+productDescription+"}";
	            _LOGGER.info(">>> Inside showProductDetails. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    Product[].class,
	                    productDescription
	            );
	        } else if (!StringUtility.isEmpty(casNumber)) {
	        	//products = productService.findByCasNumberLike(casNumber);
	           	// Build URL with query parameters
	            url = url+"/search/cas/{"+casNumber+"}";
	            _LOGGER.info(">>> Inside showProductDetails. url:<<<"+url);
	            //Call microservice
	            response = restTemplate.getForEntity(
	                    url,
	                    Product[].class,
	                    casNumber
	            );
	        }
	    } catch (Exception ex) {
	    	exceptionThrow = true;
	    	model.addAttribute("error", "No products are find for selected criteria.");
	    }
        
        // Convert array to list
        if(response != null) {
        	products = Arrays.asList(response.getBody());
        } else {
        	if(!exceptionThrow) {
        		products = getRestAllProducts();
        	}
        }
        
		ProductForm form = new ProductForm();
		
		form.setProduct(product);
    	
    	if(products != null && !products.isEmpty() && products.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultProducts(products);
    	} else {
    		model.addAttribute("error", "No products are find for selected criteria.");
    	}
    	
    	model.addAttribute("productForm", form);
        
        return "product/productHome";
    }
    
    /**
     * Add new product
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayNewProduct")
    public String displayNewManufacturer(Model model, HttpServletRequest request) {
    	
    	_LOGGER.info(">>> Inside displayNewManufacturer. <<<");
    	
    	Product product = new Product();

    	model.addAttribute("product", product);
		
    	ProductForm form = new ProductForm();
    	
    	model.addAttribute("productForm", form);
    	
        return "product/addProduct";
    }
    
    /**
     * Save Product
     * 
     * @param product
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/saveNewProduct") 
    public String saveNewProduct(@ModelAttribute("product") Product product, 
							Model model,
							HttpServletRequest request) {
    	
    	_LOGGER.info(">>> Inside saveNewProduct. <<<");
    	
    	ProductForm form = new ProductForm();
    	form.setProduct(product);
    	
    	List<String> errors = checkInput(product);
    	
       	//Check product name already existing system.
    	//Product productExisting = productService.findByProductName(product.getProductName());
    	Product productExisting = null;
    	try {
	    	//Microservice endpoint
	        String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL+"/name/{"+product.getProductName().trim()+"}";
	        //Call REST API
	        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class, product.getProductName().trim());
	        // Get product object
	        productExisting = response.getBody();
    	} catch (Exception ex) {
    		//Dont throw the exception. Because it valid exception.
    	}
    	if(productExisting != null && productExisting.getProductId() > 0) {
    		errors.add("Product name already existing in the system.");
    	}
       	
       	model.addAttribute("productForm", form);
       	model.addAttribute("errors", errors);
    	
       	if (errors.isEmpty()) {
       		//Product result = productService.saveOrUpdate(product);
       		//Microservice endpoint
       		String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL;
            // Call microservice POST endpoint
       		Product result = restTemplate.postForObject(url, product, Product.class);
	    	
	    	if(result != null && result.getProductId() > 0) {
	    	
		    	form = getAllProducts(request);
		    	
		    	model.addAttribute("productForm", form);
		    	
		    	model.addAttribute("msg", "Product added successfully.");
		    	
		    	//User is save sent to user home
		    	return "forward:/product/showProductDetails"; 
	    	} else {
	    		model.addAttribute("error", "Product not added into the system.");
	    	}
    	}
    	//If error display same page
        return "product/addProduct";
    }
    
    /**
     * display update product
     * 
     * @param product id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayUpdateProduct")
    public String displayUpdateProduct(@RequestParam("productId") Long productId, Model model) {
    	
    	_LOGGER.info(">>> Inside displayUpdateProduct. <<<");
    	
    	_LOGGER.info(">>> Product ID display. <<<"+ productId);
    	
    	//Product product = productService.findByProductId(productId);
    	//Micro service call
    	Product product = getRestProductByProductId(productId);
    	
    	model.addAttribute("product", product);
    	
    	ProductForm form = new ProductForm();
    	
    	product.setProductId(productId);
    	
    	form.setProductId(productId);
		
		form.setProduct(product);
		
    	model.addAttribute("productForm", form);
    	
        return "product/updateProduct";
    }
    
    /**
     * Update product
     * 
     * @param product
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/updateProduct")
    @Transactional
    public String updateProduct(@ModelAttribute("product") Product product, 
							Model model,
							HttpServletRequest request) {
    	
    	_LOGGER.info(">>> Inside displayUpdateProduct. <<<");
    	
    	_LOGGER.info("Product ID = " + product.getProductId());
    	
        ProductForm form = new ProductForm();
        form.setProduct(product);
        
    	//Check the conditions
    	List<String> errors = checkInput(product);
    		
       	model.addAttribute("productForm", form);
       	
       	model.addAttribute("errors", errors);
    	
       	if (errors.isEmpty()) {
       		String modifiedProductName = product.getProductName();
       		Product dbOldProduct = getRestProductByProductId(product.getProductId());
       		
       		_LOGGER.info("modifiedProductName = " + modifiedProductName);
       		_LOGGER.info("dbOldProduct.getProductName() = " + dbOldProduct.getProductName());
       		
       		//boolean isProductExisting = false;
       		Product productNameExists = null;
       		if(!modifiedProductName.equalsIgnoreCase(dbOldProduct.getProductName())) {
	       		//Check modified product name existing in the system
	        	//Product productNameExists = productService.findByProductName(product.getProductName());	       		
	       		ResponseEntity<Product> response = null;
	           	//Microservice endpoint
	       		try {
		            String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL+"/name/{"+product.getProductName()+"}";
		            //Call REST API
		            response = restTemplate.getForEntity(url, Product.class, product.getProductName());
		            //Get manufacturer
		            productNameExists = response.getBody();
	       		} catch (Exception ex) {
	       			_LOGGER.info("Product name already existing in the system."+modifiedProductName);
	       		}
       		}
        	
        	if(productNameExists != null && productNameExists.getProductId() > 0) {        	
        		errors.add("Updated product name already existing in the system.");       	
        	} else {
       		
	       		//Product existingProduct = productService.findByProductId(product.getProductId());
        		Product existingProduct = getRestProductByProductId(product.getProductId());
	       		
	       		if(existingProduct != null && existingProduct.getProductId() > 0) {
	       			
	       			existingProduct.setProductName(product.getProductName());
	       			existingProduct.setProductDescription(product.getProductDescription());
	       			existingProduct.setCasNumber(product.getCasNumber());
	       		
	       			//Product result = productService.saveOrUpdate(existingProduct);
	          		//Microservice endpoint
	       			String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL;
	                // Call microservice POST endpoint
	           		Product result = restTemplate.postForObject(url, existingProduct, Product.class);
			    	if(result != null && result.getProductId() > 0) {
			    	
				    	form = getAllProducts(request);
				    	
				    	model.addAttribute("productForm", form);
				    	
				    	model.addAttribute("msg", "Product updated successfully.");
				    	
				    	//User is save sent to user home
				    	return "forward:/product/showProductDetails"; 
			    	} else {
			    		model.addAttribute("error", "Product not updated into the system.");
			    	}
	       		} else {
	       			model.addAttribute("error", "Product not existed into the system.");
	       		}
        	}
    	}
    	//If error display same page
        return "product/updateProduct";
    }
    
    /**
     * display delete product
     * 
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/displayDeleteProduct")
    public String displayDeleteProduct(@RequestParam("productId") Long productId, Model model) {
    	_LOGGER.info(">>> Inside displayDeleteProduct. <<<");
     	_LOGGER.info(">>> Inside displayDeleteProduct. <<<:"+"productId: "+productId);
    	
    	//Product product = productService.findByProductId(productId);
     	//Microserive call
     	Product product = getRestProductByProductId(productId);
    	
    	model.addAttribute("product", product);
    	
    	ProductForm form = new ProductForm();
    	
    	product.setProductId(productId);
    	
    	form.setProductId(productId);
		
		form.setProduct(product);
		
    	model.addAttribute("productForm", form);
    	
        return "product/deleteProduct";
    }
    
    /**
     * Delete product
     * 
     * @param product
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/deleteProduct")
    @Transactional
    public String deleteProduct(@ModelAttribute("product") Product product, 
							Model model,
							HttpServletRequest request) {
    	
    	_LOGGER.info(">>> Inside deleteProduct. <<<");
     	_LOGGER.info(">>> Inside deleteProduct. <<<:"+"Product ID = "+product.getProductId());

        System.out.println("Product ID = " + product.getProductId());
    	
        ProductForm form = new ProductForm();
        form.setProduct(product);
         		
        //Product existingProduct = productService.findByProductId(product.getProductId());
    	//Micro service call
        Product existingProduct = getRestProductByProductId(product.getProductId());
   		
   		if(existingProduct != null && existingProduct.getProductId() > 0) {
   		
   			//productService.deleteByProductId(product.getProductId());
      		//Microservice endpoint
   			String msg = null;
   			String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL+"/{id}";
   			try {
	            //Call microservice DELETE endpoint
	   			restTemplate.delete(url, product.getProductId());
	   			msg = "Product deleted successfully.";
	   			model.addAttribute("msg", msg);
   			} catch (HttpClientErrorException.NotFound e) {
   	            model.addAttribute("error", "Product not found with id: " + product.getProductId());
   	        } catch (HttpServerErrorException e) {
   	            model.addAttribute("error", "Server error occurred: " + e.getResponseBodyAsString());
   	        } catch (Exception e) {
   	            model.addAttribute("error", "Unexpected error: " + e.getMessage());
   	        }
   			
	    	form = getAllProducts(request);
	    	
	    	model.addAttribute("productForm", form);
	    	
	    	//model.addAttribute("msg", "Product deleted successfully.");
	    	if(msg != null && !msg.isEmpty()) {
		    	//User is save sent to user home
		    	return "forward:/product/showProductDetails"; 
	    	}
   		} else {
   			model.addAttribute("error", "Product not existed into the system.");
   		}
    	
    	//If error display same page
        return "product/deleteProduct";
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
	 * Get product by id.
	 * 
	 * @param productId
	 * @return
	 */
	private Product getRestProductByProductId(Long productId) {
		//Microservice endpoint
        String url = ILConstants.MICROSERVICE_RESTFUL_PRODUCT_URL+"/id/{"+productId+"}";
        //Call REST API
        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class, productId);
        //Get product
		return response.getBody();
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
     * Retrieving all products. This method is used in retrieve and save.
     */
	private ProductForm getAllProducts(HttpServletRequest request) {
		
		List<Product> products = getRestAllProducts();
    		
		ProductForm form = new ProductForm();
		
		form.setProduct(new Product());
    	
    	if(!products.isEmpty() && products.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultProducts(products);
    	}
		return form;
	}
	
	/**
	 * Check inputs conditions
	 * 
	 * @param manufacturer
	 * @return
	 */
	
	private List<String> checkInput(Product product) {
		List<String> errors = new ArrayList<>();
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(product.getProductName())) {
    		errors.add("Enter valid product name.");
    	}
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(product.getProductDescription())) {
    		errors.add("Enter valid product description.");
    	}
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(product.getCasNumber())) {
    		errors.add("Enter valid cas number.");
    	}
    	
		return errors;
	}
}
