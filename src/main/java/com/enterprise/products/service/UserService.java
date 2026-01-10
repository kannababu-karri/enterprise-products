package com.enterprise.products.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.User;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.repository.UserRepository;

@Service
public class UserService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User saveOrUpdate(User user) throws ServiceException {
		try {
			return userRepository.save(user);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in userRepository."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in userRepository."+exp.toString());
		}
	}
	
	/**
	 * 
	 */
	public void deleteByUserId(Long id) throws ServiceException {
		try {
			userRepository.deleteById(id);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in deleteById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in deleteById."+exp.toString());
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<User> findAllUsers() throws ServiceException {
		try {
			return userRepository.findAll();
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllUsers."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllUsers."+exp.toString());
		}
	}
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User findById(Long id) throws ServiceException {
        try {
        	return userRepository.findById(id).orElse(null);
        } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findById."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findById."+exp.toString());
		}
    }
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User findByUserNameAndPassword(String userName, String password) throws ServiceException {
        try {
        	return userRepository.findByUserNameAndPassword(userName, password).orElse(null);
        } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByUserNameAndPassword."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByUserNameAndPassword."+exp.toString());
		}
    }
	
	/**
	 * 
	 */
	public List<User> findByUserName(String userName) throws ServiceException {
		try {
			return userRepository.findByUserName(userName);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByUserName."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByUserName."+exp.toString());
		}
	}

	/**
	 * 
	 * @param role
	 * @return
	 */
	public List<User> findByRole(String role) throws ServiceException {
		try {
			return userRepository.findByRole(role);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByRole."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByRole."+exp.toString());
		}
	}

    /**
     * 
     * @param userName
     * @param role
     * @return
     */
	public List<User> findByUserNameAndRole(String userName, String role) throws ServiceException {
		try {
			return userRepository.findByUserNameAndRole(userName, role);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByUserNameAndRole."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByUserNameAndRole."+exp.toString());
		}
	}
			

    /**
     * 
     * @param userName
     * @param role
     * @return
     */
    public List<User> findByUserNameOrRole(String userName, String role) throws ServiceException {
    	try {
    		return userRepository.findByUserNameOrRole(userName, role);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByUserNameOrRole."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByUserNameOrRole."+exp.toString());
		}
    }
	
	
}
