package com.enterprise.products.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.enterprise.products.entities.User;
import com.enterprise.products.form.ManufacturerForm;
import com.enterprise.products.utils.ILConstants;
import com.enterprise.products.utils.StringUtility;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {
	
	private static final Logger _LOGGER = LoggerFactory.getLogger(ManufacturerController.class);
	
	private final RestTemplate restTemplate;

    public ManufacturerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
    //@Autowired
    //private ManufacturerService manufacturerService;

    /**
     * Show all Manufacturers
     * 
     * @param model
     * @param request
     * @return
     */
    //@GetMapping("/showManufacturerDetails")
    @RequestMapping(
    		value = "/showManufacturerDetails",
    		method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String showManufacturerDetails(Model model, HttpServletRequest request) {
    	
    	_LOGGER.info(">>> Inside showManufacturerDetails. <<<");
    	
    	ManufacturerForm form = getAllManufacturers(request);
    	
    	model.addAttribute("manufacturerForm", form);
    	
        return "manufacturer/manufacturerHome";
    }
    
    /**
     * Submit the Manufacturer search page
     * 
     * @param Manufacturer
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/manufacturerSearch")
    public String manufacturerSearch(@ModelAttribute("manufacturer") Manufacturer manufacturer, 
    										Model model,
    										HttpSession session) {
    	
    	_LOGGER.info(">>> Inside manufacturerSearch. <<<");
       	String manufacturerName = manufacturer.getMfgName();
       	
       	_LOGGER.info(">>> Inside manufacturerSearch. <<<:"+"manufacturerName: "+manufacturerName);
 
        List<Manufacturer> manufacturers = null;
        
        if (!StringUtility.isEmpty(manufacturerName)) {
        	//manufacturers = manufacturerService.findByManufacturerNameLike(manufacturerName.trim());
        	//Microservice endpoint
            String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL+"/search/{"+manufacturerName.trim()+"}";

            //Call REST API
            ResponseEntity<Manufacturer[]> response = restTemplate.getForEntity(url, Manufacturer[].class, manufacturerName.trim());

            // Convert array to list
            manufacturers = Arrays.asList(response.getBody());
        } else {
        	//manufacturers = manufacturerService.findAllManufacturers();
        	
    		//Call restful web service
    		//public ResponseEntity<List<Manufacturer>> getAll() {
    		manufacturers = getRestAllManufacturers();
        }
        
		ManufacturerForm form = new ManufacturerForm();
		
		form.setManufacturer(manufacturer);
    	
    	if(!manufacturers.isEmpty() && manufacturers.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultManufacturers(manufacturers);
    	} else {
    		model.addAttribute("error", "No manufacturers are find for selected criteria.");
    	}
    	
    	model.addAttribute("manufacturerForm", form);
        
        return "manufacturer/manufacturerHome";
    }
    
    /**
     * Add new manufacturer
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayNewManufacturer")
    public String displayNewManufacturer(Model model, HttpServletRequest request) {
    	_LOGGER.info(">>> Inside displayNewManufacturer. <<<");
    	
    	Manufacturer manufacturer = new Manufacturer();

    	model.addAttribute("manufacturer", manufacturer);
		
    	ManufacturerForm form = new ManufacturerForm();
    	
    	model.addAttribute("manufacturerForm", form);
    	
        return "manufacturer/addManufacturer";
    }
    
    /**
     * Save manufacturer
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/saveNewManufacturer") 
    public String saveNewManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer, 
							Model model,
							HttpServletRequest request) {
    	
    	_LOGGER.info(">>> Inside saveNewManufacturer. <<<");
    	
    	ManufacturerForm form = new ManufacturerForm();
    	form.setManufacturer(manufacturer);
    	
    	List<String> errors = checkInput(manufacturer);
    	
       	//Check mfg name already existing system.
    	//Manufacturer manufacturerExisting = manufacturerService.findByMfgName(manufacturer.getMfgName());
    	Manufacturer manufacturerExisting = null;
    	try {
	    	//Microservice endpoint
	        String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL+"/name/{"+manufacturer.getMfgName().trim()+"}";
	        //Call REST API
	        ResponseEntity<Manufacturer> response = restTemplate.getForEntity(url, Manufacturer.class, manufacturer.getMfgName().trim());
	        // Get manufacturer object
	        manufacturerExisting = response.getBody();
    	} catch (Exception ex) {
    		//Dont throw the exception. Because it valid exception.
    	}
    	
    	if(manufacturerExisting != null && manufacturerExisting.getManufacturerId() > 0) {
    		errors.add("Manufacturer name already existing in the system.");
    	}
       	
       	model.addAttribute("manufacturerForm", form);
       	model.addAttribute("errors", errors);
    	
       	if (errors.isEmpty()) {
       		
       		//Manufacturer result = manufacturerService.saveOrUpdate(manufacturer);
       		
       		//Microservice endpoint
       		String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL;
            // Call microservice POST endpoint
            Manufacturer result = restTemplate.postForObject(url, manufacturer, Manufacturer.class);
	    	
	    	if(result != null && result.getManufacturerId() > 0) {
	    	
		    	form = getAllManufacturers(request);
		    	
		    	model.addAttribute("manufacturerForm", form);
		    	
		    	model.addAttribute("msg", "Manufacturer added successfully.");
		    	
		    	//User is save sent to user home
		    	return "forward:/manufacturer/showManufacturerDetails"; 
	    	} else {
	    		model.addAttribute("error", "Manufacturer not added into the system.");
	    	}
    	}
    	//If error display same page
        return "manufacturer/addManufacturer";
    }
    
    /**
     * display update manufacturer
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayUpdateManufacturer")
    public String displayUpdateManufacturer(@RequestParam("manufacturerId") Long manufacturerId, Model model) {
    	
    	_LOGGER.info(">>> Inside displayUpdateManufacturer. <<<");
    	_LOGGER.info(">>> Inside displayUpdateManufacturer. <<<:"+"manufacturerId: "+manufacturerId);
    	
    	//Manufacturer manufacturer = manufacturerService.findByManufacturerId(manufacturerId);
    	
    	//Micro service call
    	Manufacturer manufacturer = getRestManufacturerByManufacturerId(manufacturerId);
    	
    	model.addAttribute("manufacturer", manufacturer);
    	
    	ManufacturerForm form = new ManufacturerForm();
    	
    	manufacturer.setManufacturerId(manufacturerId);
    	
    	form.setManufacturerId(manufacturerId);
		
		form.setManufacturer(manufacturer);
		
    	model.addAttribute("manufacturerForm", form);
    	
        return "manufacturer/updateManufacturer";
    }
    
    /**
     * Update manufacturer
     * 
     * @param manufacturer
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/updateManufacturer")
    public String updateManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer, 
							Model model,
							HttpServletRequest request) {

        _LOGGER.info(">>> Inside updateManufacturer. <<<");
    	_LOGGER.info(">>> Inside updateManufacturer. <<<:"+"manufacturerId: "+manufacturer.getManufacturerId());
    	
        ManufacturerForm form = new ManufacturerForm();
        form.setManufacturer(manufacturer);
        
    	//Check the conditions
    	List<String> errors = checkInput(manufacturer);
       	
       	model.addAttribute("manufacturerForm", form);
       	
       	model.addAttribute("errors", errors);
    	
       	if (errors.isEmpty()) {
       		
       		//Manufacturer existingMfg = manufacturerService.findByManufacturerId(manufacturer.getManufacturerId());
       		
        	//Microservice endpoint
            String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL+"/id/{"+manufacturer.getManufacturerId()+"}";
            //Call REST API
            ResponseEntity<Manufacturer> response = restTemplate.getForEntity(url, Manufacturer.class, manufacturer.getManufacturerId());
            //Get manufacturer
            Manufacturer existingMfg = response.getBody();
       		
       		if(existingMfg != null && existingMfg.getManufacturerId() > 0) {
       			
       			existingMfg.setMfgName(manufacturer.getMfgName());
       			existingMfg.setAddress1(manufacturer.getAddress1());
       			existingMfg.setAddress2(manufacturer.getAddress2());
       			existingMfg.setCity(manufacturer.getCity());
       			existingMfg.setState(manufacturer.getState());
       			existingMfg.setZip(manufacturer.getZip());
       			existingMfg.setZipExt(manufacturer.getZipExt());
       		
       			//Manufacturer result = manufacturerService.saveOrUpdate(existingMfg);
           		//Microservice endpoint
           		url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL;
                // Call microservice POST endpoint
                Manufacturer result = restTemplate.postForObject(url, existingMfg, Manufacturer.class);
		    	
		    	if(result != null && result.getManufacturerId() > 0) {
		    	
			    	form = getAllManufacturers(request);
			    	
			    	model.addAttribute("manufacturerForm", form);
			    	
			    	model.addAttribute("msg", "Manufacturer updated successfully.");
			    	
			    	//User is save sent to user home
			    	return "forward:/manufacturer/showManufacturerDetails"; 
		    	} else {
		    		model.addAttribute("error", "Manufacturer not updated into the system.");
		    	}
       		} else {
       			model.addAttribute("error", "Manufacturer not existed into the system.");
       		}
    	}
    	//If error display same page
        return "manufacturer/updateManufacturer";
    }
    
    /**
     * display delete manufacturer
     * 
     * @param manufacturer
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayDeleteManufacturer")
    public String displayDeleteManufacturer(@RequestParam("manufacturerId") Long manufacturerId, Model model) {
    	
        _LOGGER.info(">>> Inside displayDeleteManufacturer. <<<");
     	_LOGGER.info(">>> Inside displayDeleteManufacturer. <<<:"+"manufacturerId: "+manufacturerId);
    	
    	//Manufacturer manufacturer = manufacturerService.findByManufacturerId(manufacturerId);
     	
     	//Microserive call
       	Manufacturer manufacturer = getRestManufacturerByManufacturerId(manufacturerId);
    	
    	model.addAttribute("manufacturer", manufacturer);
    	
    	ManufacturerForm form = new ManufacturerForm();
    	
    	manufacturer.setManufacturerId(manufacturerId);
    	
    	form.setManufacturerId(manufacturerId);
		
		form.setManufacturer(manufacturer);
		
    	model.addAttribute("manufacturerForm", form);
    	
        return "manufacturer/deleteManufacturer";
    }


    
    /**
     * Delete manufacturer
     * 
     * @param manufacturer
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/deleteManufacturer")
    public String deleteManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer, 
							Model model,
							HttpServletRequest request) {

    	_LOGGER.info(">>> Inside deleteManufacturer. <<<");
     	_LOGGER.info(">>> Inside deleteManufacturer. <<<:"+"manufacturerId: "+manufacturer.getManufacturerId());
    	
        ManufacturerForm form = new ManufacturerForm();
        form.setManufacturer(manufacturer);
         		
        //Manufacturer existingMfg = manufacturerService.findByManufacturerId(manufacturer.getManufacturerId());
        
    	//Micro service call
    	Manufacturer existingMfg = getRestManufacturerByManufacturerId(manufacturer.getManufacturerId());
   		
   		if(existingMfg != null && existingMfg.getManufacturerId() > 0) {
   		
   			//manufacturerService.deleteByManufacturerId(manufacturer.getManufacturerId());
   			
       		//Microservice endpoint
   			String msg = null;
   			String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL+"/{id}";
   			try {
	            //Call microservice DELETE endpoint
	   			restTemplate.delete(url, manufacturer.getManufacturerId());
	   			msg = "Manufacturer deleted successfully.";
	   			model.addAttribute("msg", msg);
   			} catch (HttpClientErrorException.NotFound e) {
   	            model.addAttribute("error", "Manufacturer not found with id: " + manufacturer.getManufacturerId());
   	        } catch (HttpServerErrorException e) {
   	            model.addAttribute("error", "Server error occurred: " + e.getResponseBodyAsString());
   	        } catch (Exception e) {
   	            model.addAttribute("error", "Unexpected error: " + e.getMessage());
   	        }
   			
	    	form = getAllManufacturers(request);
	    	
	    	model.addAttribute("manufacturerForm", form);
	    	
	    	if(msg != null && !msg.isEmpty()) {
		    	//User is save sent to user home
		    	return "forward:/manufacturer/showManufacturerDetails"; 
	    	}
   		} else {
   			model.addAttribute("error", "Manufacturer not existed into the system.");
   		}
    	
    	//If error display same page
        return "manufacturer/deleteManufacturer";
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
     * Retrieving all Manufacturers. This method is used in retrieve and save.
     */
	private ManufacturerForm getAllManufacturers(HttpServletRequest request) {
		
		_LOGGER.info(">>> Inside getAllManufacturers. <<<");
		
		//Below method is for monolithic approach
		//List<Manufacturer> manufacturers = manufacturerService.findAllManufacturers();
		
		//Call restful web service
		//public ResponseEntity<List<Manufacturer>> getAll() {
		List<Manufacturer> manufacturers = getRestAllManufacturers();
    		
		ManufacturerForm form = new ManufacturerForm();
		
		form.setManufacturer(new Manufacturer());
    	
    	if(!manufacturers.isEmpty() && manufacturers.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultManufacturers(manufacturers);
    	}
		return form;
	}
	
	/**
	 * Get manufacturer by id.
	 * 
	 * @param manufacturerId
	 * @return
	 */
	private Manufacturer getRestManufacturerByManufacturerId(Long manufacturerId) {
		//Microservice endpoint
        String url = ILConstants.MICROSERVICE_RESTFUL_MANUFACTURER_URL+"/id/{"+manufacturerId+"}";
        //Call REST API
        ResponseEntity<Manufacturer> response = restTemplate.getForEntity(url, Manufacturer.class, manufacturerId);
        //Get manufacturer
		return response.getBody();
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
	 * Check inputs conditions
	 * 
	 * @param manufacturer
	 * @return
	 */
	
	private List<String> checkInput(Manufacturer manufacturer) {
		List<String> errors = new ArrayList<>();
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(manufacturer.getMfgName())) {
    		errors.add("Enter valid manufacturer name.");
    	}
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(manufacturer.getAddress1())) {
    		errors.add("Enter valid address1.");
    	}
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(manufacturer.getCity())) {
    		errors.add("Enter valid city.");
    	}
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(manufacturer.getState())) {
    		errors.add("Enter valid state.");
    	}
    	
      	//Check the conditions
    	if(StringUtility.isEmpty(manufacturer.getZip())) {
    		errors.add("Enter valid zip.");
    	}
		return errors;
	}
}
