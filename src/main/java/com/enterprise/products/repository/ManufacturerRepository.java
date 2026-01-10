package com.enterprise.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enterprise.products.entities.Manufacturer;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
	// Find by manufacturer name
	Optional<Manufacturer> findByMfgName(String mfgName);
    
	Optional<Manufacturer> findByManufacturerId(Long id);
	
	void deleteByManufacturerId(Long id);
    
    @Query("""
    	    SELECT m FROM Manufacturer m
    	    WHERE LOWER(m.mfgName) LIKE LOWER(CONCAT('%', :mfgName, '%'))
    	""")
    	List<Manufacturer> findByManufacturerNameLike(@Param("mfgName") String mfgName);
}
