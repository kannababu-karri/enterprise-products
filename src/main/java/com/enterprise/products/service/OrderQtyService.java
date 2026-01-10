package com.enterprise.products.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.products.entities.Manufacturer;
import com.enterprise.products.entities.OrderQty;
import com.enterprise.products.entities.Product;
import com.enterprise.products.entities.User;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.repository.OrderQtyRepository;

@Service
public class OrderQtyService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(OrderQtyService.class);
	
	@Autowired
	private OrderQtyRepository orderQtyRepository;
	
    @Autowired
    private ManufacturerService manufacturerService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    //private OrderDocumentService orderDocumentService; //Mongodb intraction
    private KafkaProducerService kafkaProducerService;

    @Transactional
	public OrderQty saveOrUpdate(OrderQty orderQty) throws ServiceException {
    	OrderQty saveOrderQty = null;
    	try {
			// 1️. Save to MySQL
			OrderQty orderResult = orderQtyRepository.findByManufacturerAndProductAndUser(orderQty.getManufacturer(), orderQty.getProduct(), orderQty.getUser());
			if (orderResult == null) {
				orderResult = new OrderQty();
				orderResult.setManufacturer(orderQty.getManufacturer());
				orderResult.setProduct(orderQty.getProduct());
				orderResult.setUser(orderQty.getUser());
			}
			orderResult.setQuantity(orderQty.getQuantity());
				
			//Save into mongodb intrasactions
			//orderDocumentService.saveOrderDocument(orderResult);
			saveOrderQty = orderQtyRepository.save(orderResult);
			//saveOrderQty = orderResult;
			//if(saveOrderQty != null && saveOrderQty.getOrderId() > 0) {
				//Send the value to kafka producer
				//orderResult.setDocumentType(ILConstants.MONGODB_OPERATION_SAVE);
				//kafkaProducerService.send(orderResult);
			//}
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());
		}
		return saveOrderQty;
	}
	
    @Transactional
	public OrderQty updateOrderQty(OrderQty orderQty) throws ServiceException {
    	OrderQty updateOrderQty = null;
    	try {	
			//Save into mongodb intrasactions
			//orderDocumentService.updateOrderDocument(orderQty);
	    	updateOrderQty = orderQtyRepository.save(orderQty);
	    	//if(updateOrderQty != null && updateOrderQty.getOrderId() > 0) {
	    		//Send the value to kafka producer
	    		//updateOrderQty.setDocumentType(ILConstants.MONGODB_OPERATION_UPDATE);
	    		//kafkaProducerService.send(updateOrderQty);
			//}
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in updateOrderQty."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in updateOrderQty."+exp.toString());
		}
		return updateOrderQty;
	}
    
    @Transactional
	public void deleteByOrderId(Long orderId) throws ServiceException {
    	
		try {
			//Get the orderQty details before delete from database.
			OrderQty resultOrderQty = findByOrderId(orderId);
			//Delete record from MySql database
			orderQtyRepository.deleteByOrderId(orderId);
			//Send the value to kafka producer
			//if(resultOrderQty != null && resultOrderQty.getOrderId() > 0) {
				//resultOrderQty.setDocumentType(ILConstants.MONGODB_OPERATION_DELETE);
	    		//kafkaProducerService.send(resultOrderQty);
			//}
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in deleteByOrderId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in deleteByOrderId."+exp.toString());
		}
	}

	public List<OrderQty> findAllOrderQtys() throws ServiceException {
		try {
			return orderQtyRepository.findAll();
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllOrderQtys."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllOrderQtys."+exp.toString());
		}
	}
	
	public OrderQty findByManufacturerAndProductAndUser(Manufacturer manufacturer, Product product, User user, Long qty) throws ServiceException {
		try {
			return orderQtyRepository.findByManufacturerAndProductAndUser(manufacturer, product, user);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturerAndProductAndUser."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturerAndProductAndUser."+exp.toString());
		}
	}
	
	// Find by userid
	public List<OrderQty> findByUser_UserId(Long userId) throws ServiceException {
		try {
			return orderQtyRepository.findByUser_UserId(userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByUser_UserId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByUser_UserId."+exp.toString());
		}
	}

    // Find by mfg id
	public List<OrderQty> findByManufacturer_ManufacturerIdAndUser_UserId(Long manufacturerId, Long userId) throws ServiceException {
		try {
			return orderQtyRepository.findByManufacturer_ManufacturerIdAndUser_UserId(manufacturerId, userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturer_ManufacturerIdAndUser_UserId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturer_ManufacturerIdAndUser_UserId."+exp.toString());
		}
	}
    
    // Find by product id
	public List<OrderQty> findByProduct_ProductIdAndUser_UserId(Long productId, Long userId) throws ServiceException {
		try {
			return orderQtyRepository.findByProduct_ProductIdAndUser_UserId(productId, userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProduct_ProductIdAndUser_UserId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProduct_ProductIdAndUser_UserId."+exp.toString());
		}
	}

    // Find by user, mfg and product id
	public List<OrderQty> findByManufacturer_ManufacturerIdAndProduct_ProductIdAndUser_UserId(Long manufacturerId, Long productId, Long userId) throws ServiceException {
		try {
			return orderQtyRepository.findByManufacturer_ManufacturerIdAndProduct_ProductIdAndUser_UserId(manufacturerId, productId, userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturer_ManufacturerIdAndProduct_ProductIdAndUser_UserId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturer_ManufacturerIdAndProduct_ProductIdAndUser_UserId."+exp.toString());
		}
	}
	
    public Manufacturer findByManufacturerId(Long manufacturerId) throws ServiceException {
    	try {
    		return manufacturerService.findByManufacturerId(manufacturerId);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturerId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturerId."+exp.toString());
		}
    }
	
    public List<Manufacturer> findAllManufacturers() throws ServiceException {
    	try {
    		return manufacturerService.findAllManufacturers();
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllManufacturers."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllManufacturers."+exp.toString());
		}
    }
       
    public Product findByProductId(Long productId) throws ServiceException {
        try {
        	return productService.findByProductId(productId);
        } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductId."+exp.toString());
		}
    }
    
	public List<Product> findAllProducts() throws ServiceException {
		try {
			return productService.findAllProducts();
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllProducts."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllProducts."+exp.toString());
		}
	}
	
	public OrderQty findByOrderId(Long orderId) throws ServiceException {
        try {
			return orderQtyRepository
	                .findByOrderId(orderId)
	                .orElse(null);
        } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByOrderId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByOrderId."+exp.toString());
		}
    }
	
}
