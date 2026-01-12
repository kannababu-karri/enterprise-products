package com.enterprise.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.products.entities.KeyValuePair;

public interface KeyValuePairRepository extends JpaRepository<KeyValuePair, Integer> {
    Optional<KeyValuePair> findByCacheKey(String key); // search by cachekey
}
