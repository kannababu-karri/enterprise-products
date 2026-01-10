package com.enterprise.products.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.Product;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.repository.ProductRepository;

@Service
public class ProductService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;

	public Product saveOrUpdate(Product product) throws ServiceException {
		try {
			return productRepository.save(product);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());
		}
	}

	public List<Product> findAllProducts() throws ServiceException {
		try {
			return productRepository.findAll();
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllProducts."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllProducts."+exp.toString());
		}
	}
	
	public Product findByProductName(String productName) throws ServiceException {
		try {
	    	return productRepository.findByProductName(productName).orElse(null);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductName."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductName."+exp.toString());
		}
    }
	
    public List<Product> findByProductNameLike(String productName) throws ServiceException {
    	try {
    		return productRepository.findByProductNameLike(productName);
	    } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductNameLike."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductNameLike."+exp.toString());
		}
    }
    
    public List<Product> findByProductDescriptionLike(String productDescription) throws ServiceException {
    	try {
	    	return productRepository.findByProductDescriptionLike(productDescription);
	    } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductDescriptionLike."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductDescriptionLike."+exp.toString());
		}
    }
    
    public List<Product> findByCasNumberLike(String casNumber) throws ServiceException {
    	try {
	    	return productRepository.findByCasNumberLike(casNumber);
	    } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByCasNumberLike."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByCasNumberLike."+exp.toString());
		}
    }
    
    public List<Product> findByProductNameDesCanNumber(String productName, 
    													String productDescription, 
    													String casNumber) throws ServiceException {
    	try {
	    	return productRepository.findByProductNameDesCanNumber(productName, productDescription, casNumber);
	    } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductNameDesCanNumber."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductNameDesCanNumber."+exp.toString());
		}
    }
    
	public Product findByProductId(Long productId) throws ServiceException {
		try {
	        return productRepository
	                .findByProductId(productId)
	                .orElse(null);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductId."+exp.toString());
		}
    }
	
	public void deleteByProductId(Long productId) throws ServiceException {
		try {
			productRepository.deleteByProductId(productId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in deleteByProductId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in deleteByProductId."+exp.toString());
		}
	}
}
