package com.enterprise.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enterprise.products.entities.User;
import com.enterprise.products.service.UserService;
import com.enterprise.products.utils.StringUtility;
import com.enterprise.products.utils.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Get login page
     * 
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String loginPage(Model model) {
    	model.addAttribute("user", new User());
        return "loginHome";
    }

    /**
     * Submit the login page
     * 
     * @param user
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String validateLoginCredentials(@ModelAttribute("user") User user, 
    										Model model,
    										HttpSession session) {
    	
    	String userName = user.getUserName();
    	String password = user.getPassword();
    	
    	//System.out.println("Username: " + userName);
        //System.out.println("Password: " + password);
        
        if(StringUtility.isEmpty(userName) && StringUtility.isEmpty(password)) {
        	model.addAttribute("error", "Enter valid User Id and Password.");
        } else if(StringUtility.isEmpty(userName)) {
    		model.addAttribute("error", "Enter valid User Id.");
    	} else if(StringUtility.isEmpty(password)) {
    		model.addAttribute("error", "Enter valid Password.");
    	} else {
	        User authenticateUser = userService.findByUserNameAndPassword(userName, password);
        	if(authenticateUser != null && authenticateUser.getUserId() > 0) {
	            session.setAttribute(Utils.getSessionLoginUserIdKey(), authenticateUser);
	            return "redirect:ilHome";
        	} else {
        		model.addAttribute("error", "User not exists in the system. Enter valid User Id and Password.");
        	}
    	}
        return "loginHome"; // go back to login page
    }

    /**
     * Display IL home page.
     * 
     * @param session
     * @return
     */
    @GetMapping("/ilHome")
    public String ilHomePage(HttpSession session) {
    	User user = (User) session.getAttribute(Utils.getSessionLoginUserIdKey());
        if (user == null) {
            return "redirect:/login"; // redirect if not logged in
        }
        return "ilHome";
    }
    
    /**
     * Display the logout
     * 
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // remove all session attributes
        return "redirect:/login"; // go back to login page
    }
    
    /**
     * Reset the form 
     * 
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/login/reset")
    public String resetLoginForm(HttpSession session, Model model) {
    	session.invalidate(); // remove all session attributes
    	return "redirect:/login"; // go back to login page
    }
}
