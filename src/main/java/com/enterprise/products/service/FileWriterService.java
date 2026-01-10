package com.enterprise.products.service;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.enterprise.products.entities.OrderDocument;
import com.enterprise.products.entities.OrderQty;
import com.enterprise.products.exception.ServiceException;
import com.enterprise.products.utils.ILConstants;

@Component
public class FileWriterService {

	private static final Logger _LOGGER = LoggerFactory.getLogger(FileWriterService.class);

    public void writeToFile(OrderQty orderQty) throws ServiceException {
    	
    	try {
    		_LOGGER.info("FILE_PATH:"+ILConstants.ORDER_FILE_PATH);
    		_LOGGER.info("Entered into writeToFile with orderId:"+orderQty.getOrderId());
	        String line = orderQty.getOrderId() + "," +
	        				orderQty.getDocumentType() + "," +
	        				orderQty.getUser().getUserId() + "," +
	        				orderQty.getUser().getUserName() + "," +
	        				orderQty.getUser().getRole() + "," +
	        				orderQty.getManufacturer().getManufacturerId() + "," +
	        				orderQty.getManufacturer().getMfgName() + "," +
	        				orderQty.getProduct().getProductId() + "," +
	        				orderQty.getProduct().getProductName() + "," +
	        				orderQty.getProduct().getCasNumber() + "," +
	        				orderQty.getProduct().getProductDescription() + "," +
	        				orderQty.getQuantity()+ "," +
	        				LocalDateTime.now();
	
			/*
			 * Files.write( Paths.get(ILConstants.ORDER_FILE_PATH), (line +
			 * System.lineSeparator()).getBytes(), StandardOpenOption.CREATE,
			 * StandardOpenOption.APPEND );
			 */
	        BufferedWriter writer = null;
	        try {
		        writer = Files.newBufferedWriter(
		                Paths.get(ILConstants.ORDER_FILE_PATH),
		                StandardOpenOption.CREATE,
		                StandardOpenOption.APPEND);
	
		            writer.write(line);
		            writer.newLine();
		        
	        } catch (Exception exp) {
		    	throw exp;
		    } finally {
		    	if (writer != null) {
		            try {
		                writer.close();
		            } catch (Exception exp) {
		            	throw exp;
		            }
		        }
		    }
		} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured while writeToFile."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured while writeToFile.."+exp.toString());
		}
    }
    
    public List<OrderDocument> readFromFile() throws ServiceException {
    	System.out.println("Entered readFromFile");
    	List<OrderDocument> orderDocuments = new ArrayList<>();
    	
    	try (Stream<String> lines = Files.lines(Paths.get(ILConstants.ORDER_FILE_PATH))) {
    	    lines.forEach(line -> {
    	    	String[] fields = line.split(",");
                OrderDocument orderDocument = new OrderDocument();
                orderDocument.setOrderQtyId(Long.valueOf(fields[0]));
				orderDocument.setDocumentType(fields[1]);				
				orderDocument.setUserId(Long.valueOf(fields[2]));
				orderDocument.setUserName(fields[3]);
				orderDocument.setRole(fields[4]);
				orderDocument.setManufacturerId(Long.valueOf(fields[5]));
				orderDocument.setMfgName(fields[6]);
				orderDocument.setProductId(Long.valueOf(fields[7]));
				orderDocument.setProductName(fields[8]);
				orderDocument.setCasNumber(fields[9]);
				orderDocument.setProductDescription(fields[10]);
				orderDocument.setQuantity(Long.valueOf(fields[11]));
				orderDocument.setCreatedAt(LocalDateTime.parse(fields[12]));

                orderDocuments.add(orderDocument);
    	    });
    	} catch (Exception exp) {
			_LOGGER.error("ERROR: Service Exception occured while readFromFile."+exp.toString());	
			throw new ServiceException("ERROR: Service Exception occured while readFromFile.."+exp.toString());
		}
    	return orderDocuments;
    }
}
