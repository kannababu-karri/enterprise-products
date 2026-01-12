package com.enterprise.products.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.enterprise.products.utils.ILConstants;

@Service
public class StateCityService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(StateCityService.class);

    // Simulate database: states -> cities
    private static final Map<String, List<String>> stateCityMap = new HashMap<>();

    static {
    	stateCityMap.put("GA", Arrays.asList("Augusta", "Columbus", "Macon", "Athens", "Alpharetta", "Roswell", "Dalton"));
        stateCityMap.put("CA", Arrays.asList("Los Angeles", "San Francisco", "San Diego"));
        stateCityMap.put("TX", Arrays.asList("Houston", "Dallas", "Austin"));
        stateCityMap.put("FL", Arrays.asList("Miami", "Orlando", "Tampa"));
    }

    @Cacheable(value = "stateCityCache", key = "#state")
    public List<String> getCitiesByState(String state) {
        _LOGGER.info("Fetching cities from DB for state:"+state);
        // simulate slow DB access
        try { 
        	Thread.sleep(1000); 
        } catch (InterruptedException ignored) {
        	
        }
        return stateCityMap.getOrDefault(state, Collections.emptyList());
    }
}
