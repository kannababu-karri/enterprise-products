package com.enterprise.products.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.OrderQty;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.utils.ILConstants;

@Service
public class KafkaConsumerService {
	
	private static final Logger _LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
	
	@Autowired
    private OrderDocumentService orderDocumentService; //Mongodb intraction
	
	@Autowired
	private FileWriterService fileWriterService;

    @KafkaListener(
    		topics = ILConstants.INNOVARE_LABS_TOPIC, 
    		groupId = ILConstants.INNOVARE_LABS_GROUP_ID,
    		containerFactory = "kafkaListenerContainerFactory")
    public void consume(OrderQty orderQty) throws ServiceException {
    	try {
	        //Save to MongoDB
	    	orderDocumentService.saveOrderDocument(orderQty);
	
	        // Write to file
	        fileWriterService.writeToFile(orderQty);
	
	        _LOGGER.info("Consumed message: " + orderQty.getOrderId());
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured while consume."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured while consume.."+exp.toString());
		}
    }
}
