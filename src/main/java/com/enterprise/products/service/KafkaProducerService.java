package com.enterprise.products.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.OrderQty;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.utils.ILConstants;

@Service
public class KafkaProducerService {
	
	private static final Logger _LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
	
	private final KafkaTemplate<String, OrderQty> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderQty> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void send(OrderQty orderQty) throws ServiceException {
        try {
	        kafkaTemplate.send(
	                ILConstants.INNOVARE_LABS_TOPIC,
	                Integer.valueOf(ILConstants.INNOVARE_LABS_PARTITION),
	                orderQty.getOrderId().toString(),
	                orderQty
	            );
        } catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured while send."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured while send.."+exp.toString());
		}
    }
}
