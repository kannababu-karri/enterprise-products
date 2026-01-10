package com.enterprise.products.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.MfgVsProduct;
import com.enterprise.products.repository.MfgvsproductRepository;

@Service
public class MfgvsproductService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(MfgvsproductService.class);
	@Autowired
    private MfgvsproductRepository mfgvsproductRepository;

    public MfgVsProduct saveOrUpdate(MfgVsProduct mfgVsProduct) {
    	_LOGGER.info("inside");
        return mfgvsproductRepository.save(mfgVsProduct);
    }

    public List<MfgVsProduct> findAllMfgVsProducts() {
        return mfgvsproductRepository.findAll();
    }
    
    public MfgVsProduct findByMfgvsproductId(Long mfgvsproductId) {
        return mfgvsproductRepository
                .findByMfgvsproductId(mfgvsproductId)
                .orElse(null);
    }	
    
	/**
	 * Retrieve by manufacturer id
	 * @param manufacturerId
	 * @return
	 */
	public MfgVsProduct findByManufacturerId(Long manufacturerId) {
        return mfgvsproductRepository
                .findByManufacturerId(manufacturerId)
                .orElse(null);
    }
}
