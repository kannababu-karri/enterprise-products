package com.enterprise.products.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.products.entities.OrderDocument;

/**
 * Mongo db intraaction
 */
@Repository
public interface OrderDocumentRepository extends MongoRepository<OrderDocument, String> {
	List<OrderDocument> findAll();

    List<OrderDocument> findByUserId(Long userId);
    
    List<OrderDocument> findByManufacturerId(Long manufacturerId, Long userId);
    
    List<OrderDocument> findByProductId(Long productId, Long userId);
    
    // Find by user, mfg and product id
    List<OrderDocument> findByManufacturerIdAndProductIdAndUserId(Long manufacturerId, Long productId, Long userId);
}
