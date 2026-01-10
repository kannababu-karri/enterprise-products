package com.enterprise.products.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.Manufacturer;
import com.enterprise.products.entities.OrderDocument;
import com.enterprise.products.entities.OrderQty;
import com.enterprise.products.entities.Product;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.repository.OrderDocumentRepository;

@Service
public class OrderDocumentService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(OrderDocumentService.class);
	
	@Autowired
	private OrderDocumentRepository orderDocumentRepository;
	
    @Autowired
    private ManufacturerService manufacturerService;
    
    @Autowired
    private ProductService productService;
	
	public boolean saveOrderDocument(OrderQty orderQty) throws ServiceException {
		_LOGGER.info(">>> Inside saveOrderDocument.<<<");
		boolean result = false;
		try {
			//Create json object in the mongodb
			//2. Convert to Mongo document
			OrderDocument doc = writeMongoDbOrderDocument(orderQty);
			doc.setDocumentType(orderQty.getDocumentType());
			
			//3. Save to MongoDB
			orderDocumentRepository.save(doc);
			result = true;
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in saveOrderDocument."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in saveOrderDocument."+exp.toString());
		}
		
		return result;
	}
	
	public List<OrderDocument> findAll() throws ServiceException {
		try {
			return orderDocumentRepository.findAll();
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAll."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAll."+exp.toString());
		}
	}

	public List<OrderDocument> findByUserId(Long userId) throws ServiceException {
		try {
			return orderDocumentRepository.findByUserId(userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByUserId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByUserId."+exp.toString());
		}
	}
    
	public List<OrderDocument> findByManufacturerId(Long manufacturerId, Long userId) throws ServiceException {
		try {
			return orderDocumentRepository.findByManufacturerId(manufacturerId, userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturerId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturerId."+exp.toString());
		}
	}
    
	public List<OrderDocument> findByProductId(Long productId, Long userId) throws ServiceException {
		try {
			return orderDocumentRepository.findByProductId(productId, userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByProductId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByProductId."+exp.toString());
		}
	}
    
    // Find by user, mfg and product id
	public List<OrderDocument> findByManufacturerIdAndProductIdAndUserId(Long manufacturerId, Long productId, Long userId) throws ServiceException {
		try {
			return orderDocumentRepository.findByManufacturerIdAndProductIdAndUserId(manufacturerId, productId, userId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturerIdAndProductIdAndUserId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturerIdAndProductIdAndUserId."+exp.toString());
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
   
    
	public List<Product> findAllProducts() throws ServiceException {
		try {
			return productService.findAllProducts();
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllProducts."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllProducts."+exp.toString());
		}
	}
	
	/**
	 * Write the data into mondo db database as order document
	 * @param order
	 * @return
	 */
	private OrderDocument writeMongoDbOrderDocument(OrderQty orderQty) {
		_LOGGER.info(">>> Inside writeMongoDbOrderDocument.<<<");
		OrderDocument doc = new OrderDocument();
		doc.setOrderQtyId(orderQty.getOrderId());
		doc.setUserId(orderQty.getUser().getUserId());
		doc.setUserName(orderQty.getUser().getUserName());
		doc.setRole(orderQty.getUser().getRole());
		doc.setManufacturerId(orderQty.getManufacturer().getManufacturerId());
		doc.setMfgName(orderQty.getManufacturer().getMfgName());
		doc.setProductId(orderQty.getProduct().getProductId());
		doc.setProductName(orderQty.getProduct().getProductName());
		doc.setCasNumber(orderQty.getProduct().getCasNumber());
		doc.setProductDescription(orderQty.getProduct().getProductDescription());
		doc.setQuantity(orderQty.getQuantity());
		doc.setCreatedAt(LocalDateTime.now());
		_LOGGER.info(">>> Inside writeMongoDbOrderDocument doc.<<<"+doc.toString());
		return doc;
	}

}
