package com.enterprise.products.service;

import org.springframework.cache.annotation.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.enterprise.products.entities.KeyValuePair;
import com.enterprise.products.repository.KeyValuePairRepository;

import java.util.Optional;

@Service
public class KeyValuePairService {
    private static final Logger _LOGGER = LoggerFactory.getLogger(KeyValuePairService.class);

    private final KeyValuePairRepository repository;

    public KeyValuePairService(KeyValuePairRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "keyValueCache", key = "#key")
    public Optional<KeyValuePair> getByKey(String key) {
        _LOGGER.info("Service KeyValuePair key: {}", key);
        return repository.findByCacheKey(key); // logs added
    }
}

