package com.enterprise.products.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.enterprise.products.entities.User;
import com.enterprise.products.form.UserForm;
import com.enterprise.products.service.UserService;
import com.enterprise.products.utils.StringUtility;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    private UserService userService;

    /**
     * Show all users
     * 
     * @param model
     * @param request
     * @return
     */
    //@GetMapping("/showUserDetails")
    @RequestMapping(
    		value = "/showUserDetails",
    		method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String showUserDetails(Model model, HttpServletRequest request) {
    	UserForm form = getAllUsers(request);
    	
    	model.addAttribute("userForm", form);
    	
        return "user/userHome";
    }
    
    /**
     * Submit the user search page
     * 
     * @param user
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/userSearch")
    public String userSearch(@ModelAttribute("user") User user, 
    										Model model,
    										HttpSession session) {
    	
    	
       	String userName = user.getUserName(); 	
       	String role = user.getRole();
       	
       	System.out.println("userName: "+userName);
       	System.out.println("role: "+role);
 
        List<User> users = null;
        
        if (!StringUtility.isEmpty(userName) && !StringUtility.isEmpty(role)) {
            users = userService.findByUserNameAndRole(userName.trim(), role.trim());
        } else if (!StringUtility.isEmpty(userName)) {
            users = userService.findByUserName(userName.trim());
        } else if (!StringUtility.isEmpty(role)) {
            users = userService.findByRole(role.trim());
        } else {
            users = userService.findAllUsers();
        }
        
		UserForm form = new UserForm();
		
		form.setUser(user);
    	//form.setRoles(Utils.Role.getAllRoles());
    	
    	if(!users.isEmpty() && users.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultUsers(users);
    	} else {
    		model.addAttribute("error", "No users for selected criteria.");
    	}
    	
    	model.addAttribute("userForm", form);
        
        return "user/userHome";
    }
    
    /**
     * Add new user
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayNewUser")
    public String displayNewUser(Model model, HttpServletRequest request) {
    	User user = new User();

    	model.addAttribute("user", user);
		
    	UserForm form = new UserForm();
    	
    	model.addAttribute("userForm", form);
    	
        return "user/addUser";
    }
    
    /**
     * Add new user
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/saveNewUser") 
    public String saveNewUser(@ModelAttribute("user") User user, 
							Model model,
							HttpServletRequest request) {
    	
    	UserForm form = new UserForm();
    	form.setUser(user);
    	
    	System.out.println("user.getPassword():"+user.getPassword()+":user.getPassword()");
    	
    	List<String> errors = new ArrayList<>();
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(user.getUserName())) {
    		errors.add("Enter valid user name.");
    	}
    	
    	if(StringUtility.isEmpty(user.getPassword()) || StringUtility.isEmpty(user.getPassword().trim())) {
    		errors.add("Enter valid password.");
    	}
    	
       	if(StringUtility.isEmpty(user.getRole())) {
       		errors.add("Select valid role.");
    	}
       	
       	model.addAttribute("userForm", form);
       	model.addAttribute("errors", errors);
    	
       	if (errors.isEmpty()) {
	    	User result = userService.saveOrUpdate(user);
	    	
	    	if(result != null && result.getUserId() > 0) {
	    	
		    	form = getAllUsers(request);
		    	
		    	model.addAttribute("userForm", form);
		    	
		    	model.addAttribute("msg", "User added successfully.");
		    	
		    	//User is save sent to user home
		    	return "forward:/user/showUserDetails"; 
	    	} else {
	    		model.addAttribute("error", "User not added into the system.");
	    	}
    	}
    	//If error display same page
        return "user/addUser";
    }
    
    /**
     * display update user
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayUpdateUser")
    public String displayUpdateUser(@RequestParam("userId") Long userId, Model model) {
    	System.out.println("User ID display = " + userId);
    	User user = userService.findById(userId);
    	model.addAttribute("user", user);
    	
    	UserForm form = new UserForm();
    	
    	user.setUserId(userId);
    	
    	form.setUserId(userId);
		
		form.setUser(user);
		
    	model.addAttribute("userForm", form);
    	
        return "user/updateUser";
    }
    
    /**
     * Update user
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/updateUser")
    @Transactional
    public String updateUser(@ModelAttribute("user") User user, 
							Model model,
							HttpServletRequest request) {

        System.out.println("User ID = " + user.getUserId());
    	
    	UserForm form = new UserForm();
    	form.setUser(user);
    	
    	List<String> errors = new ArrayList<>();
    	
    	//Check the conditions
    	if(StringUtility.isEmpty(user.getPassword()) || StringUtility.isEmpty(user.getPassword().trim())) {
    		errors.add("Enter valid password.");
    	}
    	
       	if(StringUtility.isEmpty(user.getRole())) {
       		errors.add("Select valid role.");
    	}
       	
       	model.addAttribute("userForm", form);
       	model.addAttribute("errors", errors);
    	
       	if (errors.isEmpty()) {
       		
       		User existingUser = userService.findById(user.getUserId());
       		
       		if(existingUser != null && existingUser.getUserId() > 0) {
       			
       			existingUser.setPassword(user.getPassword());
       			existingUser.setRole(user.getRole());
       			existingUser.setQuestion1(user.getQuestion1());
       			existingUser.setAnswer1(user.getAnswer1());
       			existingUser.setQuestion2(user.getQuestion2());
       			existingUser.setAnswer2(user.getAnswer2());
       		
		    	User result = userService.saveOrUpdate(existingUser);
		    	
		    	if(result != null && result.getUserId() > 0) {
		    	
			    	form = getAllUsers(request);
			    	
			    	model.addAttribute("userForm", form);
			    	
			    	model.addAttribute("msg", "User updated successfully.");
			    	
			    	//User is save sent to user home
			    	return "forward:/user/showUserDetails"; 
		    	} else {
		    		model.addAttribute("error", "User not updated into the system.");
		    	}
       		} else {
       			model.addAttribute("error", "User not existed into the system.");
       		}
    	}
    	//If error display same page
        return "user/updateUser";
    }
    
    /**
     * display delete user
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/displayDeleteUser")
    public String displayDeleteUser(@RequestParam("userId") Long userId, Model model) {
    	System.out.println("User ID display = " + userId);
    	User user = userService.findById(userId);
    	model.addAttribute("user", user);
    	
    	UserForm form = new UserForm();
    	
    	user.setUserId(userId);
    	
    	form.setUserId(userId);
		
		form.setUser(user);
		
    	model.addAttribute("userForm", form);
    	
        return "user/deleteUser";
    }
    
    /**
     * Delete user
     * 
     * @param user
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/deleteUser")
    @Transactional
    public String deleteUser(@ModelAttribute("user") User user, 
							Model model,
							HttpServletRequest request) {

        System.out.println("User ID = " + user.getUserId());
    	
    	UserForm form = new UserForm();
    	form.setUser(user);
         		
   		User existingUser = userService.findById(user.getUserId());
   		
   		if(existingUser != null && existingUser.getUserId() > 0) {
   		
	    	userService.deleteByUserId(user.getUserId());
	    	form = getAllUsers(request);
		    	
	    	model.addAttribute("userForm", form);
	    	
	    	model.addAttribute("msg", "User deleted successfully.");
	    	
	    	//User is save sent to user home
	    	return "forward:/user/showUserDetails"; 
   		} else {
   			model.addAttribute("error", "User not existed into the system.");
   		}
    	
    	//If error display same page
        return "user/deleteUser";
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
     * Retrieving all users. This method is used in retrieve and save.
     */
	private UserForm getAllUsers(HttpServletRequest request) {
		
		List<User> users = userService.findAllUsers();   	
    		
		UserForm form = new UserForm();
		
		form.setUser(new User());
    	
    	form.getUser().setRole(Utils.getUserRole(request));
    	//form.setRoles(Utils.Role.getAllRoles());
    	
    	if(!users.isEmpty() && users.size() > 0) {
    		form.setShowDetails(true);
    		form.setResultUsers(users);
    	}
		return form;
	}
}
