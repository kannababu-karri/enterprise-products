package com.enterprise.products.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.Manufacturer;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.repository.ManufacturerRepository;

@Service
public class ManufacturerService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ManufacturerService.class);
	@Autowired
    private ManufacturerRepository manufacturerRepository;
	/**
	 * save and update
	 */
    public Manufacturer saveOrUpdate(Manufacturer manufacturer) throws ServiceException {
    	try {
    		return manufacturerRepository.save(manufacturer);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in saveOrUpdate."+exp.toString());
		}
    }

    public List<Manufacturer> findAllManufacturers() throws ServiceException {
    	try {
    		return manufacturerRepository.findAll();
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findAllManufacturers."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findAllManufacturers."+exp.toString());
		}
    }
    
    public List<Manufacturer> findByManufacturerNameLike(String mfgName) throws ServiceException {
    	try {
    		return manufacturerRepository.findByManufacturerNameLike(mfgName);
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturerNameLike."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturerNameLike."+exp.toString());
		}
    }
    
	/**
	 * Retrieve by manufacturer id
	 * @param manufacturerId
	 * @return
	 */
	public Manufacturer findByManufacturerId(Long manufacturerId) throws ServiceException {
		try {
	        return manufacturerRepository
	                .findByManufacturerId(manufacturerId)
	                .orElse(null);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByManufacturerId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByManufacturerId."+exp.toString());
		}
    }
	
	public void deleteByManufacturerId(Long manufacturerId) throws ServiceException {
		try {
			manufacturerRepository.deleteByManufacturerId(manufacturerId);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in deleteByManufacturerId."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in deleteByManufacturerId."+exp.toString());
		}
	}

	public Manufacturer findByMfgName(String mfgName) throws ServiceException {
		try {
			return manufacturerRepository
					.findByMfgName(mfgName)
					.orElse(null);
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured in findByMfgName."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured in findByMfgName."+exp.toString());
		}
	}
}
