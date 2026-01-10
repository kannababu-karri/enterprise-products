package com.enterprise.products.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.enterprise.products.entities.User;

import jakarta.servlet.http.HttpServletRequest;

public final class Utils {
	
	private static final String SESSION_LOGIN_USER = "SESSION_LOGIN_USER";
	
	/**
	 * Get current timestamp
	 * @return
	 */
	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());		 
	}
	
	/**
	 * Get windowns login user name.
	 * 
	 * @return
	 */
	public static String getLoginUserName() {
		return System.getProperty("user.name");
	}
	
	/**
	 * Get login user name.
	 * 
	 * @return
	 */
	public static String getLoginUserName(HttpServletRequest request) {
		String userName = "";		
		if(request != null && request.getSession() != null) {
			User user = getUser(request);
			if(user != null) {
				userName = user.getUserName();
			}
		}
		return userName;
	}
	
	/**
	 * Get login user role.
	 * 
	 * @return
	 */
	public static String getUserRole(HttpServletRequest request) {
		String userRole = "";		
		if(request != null && request.getSession() != null) {
			User user = getUser(request);
			if(user != null) {
				userRole = user.getRole();
			}
		}
		return userRole;
	}
	
	/**
	 * Get Session user
	 */
	public static User getUser(HttpServletRequest request) {
		User user = null;		
		if(request != null && request.getSession() != null) {
			//System.out.println("Kanna:"+request.getSession().getAttribute(getSessionLoginUserIdKey()));
			user = (User) request.getSession().getAttribute(getSessionLoginUserIdKey());
		}
		return user;
	}
	
	/**
	 * Used for login user id credentials.
	 * 
	 * @return
	 */
	public static String getSessionLoginUserIdKey() {
		return SESSION_LOGIN_USER;
	}
	
	/**
     * User Roles Enum
     */
    public enum Role {
        ADMIN(ILConstants.ADMIN_ROLE),
        USER(ILConstants.USER_ROLE),
        VIEW(ILConstants.VIEW_ROLE);

        private final String value;

        Role(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        /** Return all roles as List<String> */
        public static List<String> getAllRoles() {
            return Arrays.stream(Role.values())
                         .map(Role::getValue)
                         .collect(Collectors.toList());
        }
    }

}
