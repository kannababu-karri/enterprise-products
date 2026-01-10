package com.enterprise.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enterprise.products.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByProductId(Long id);
	
	Optional<Product> findByProductName(String productName);
	
	void deleteByProductId(Long id);
	

    
    @Query("""
    	    SELECT p FROM Product p
    	    WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))
    	""")
    	List<Product> findByProductNameLike(@Param("productName") String productName);
    
    @Query("""
    	    SELECT p FROM Product p
    	    WHERE LOWER(p.productDescription) LIKE LOWER(CONCAT('%', :productDescription, '%'))
    	""")
    	List<Product> findByProductDescriptionLike(@Param("productDescription") String productDescription);
    
    @Query("""
    	    SELECT p FROM Product p
    	    WHERE LOWER(p.casNumber) LIKE LOWER(CONCAT('%', :casNumber, '%'))
    	""")
    	List<Product> findByCasNumberLike(@Param("casNumber") String casNumber);
	
    
    @Query("""
    	    SELECT p FROM Product p
    	    WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))
    	    AND LOWER(p.productDescription) LIKE LOWER(CONCAT('%', :productDescription, '%'))
    	    AND LOWER(p.casNumber) LIKE LOWER(CONCAT('%', :casNumber, '%'))
    	""")
    	List<Product> findByProductNameDesCanNumber(@Param("productName") String productName,
    	                       @Param("productDescription") String productDescription,
    	                       @Param("casNumber") String casNumber);
}
