package com.enterprise.products.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "keyvaluepair")
public class KeyValuePair implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "keyValuePairId")
	private Integer keyValuePairId;

	@Column(name = "cacheKey", unique = true, nullable = false)
	private String cacheKey;
	
	@Column(name = "cacheValue", nullable = false)
	private String cacheValue;

    // constructors
    public KeyValuePair() {}
    public KeyValuePair(String cacheKey, String cacheValue) {
        this.cacheKey = cacheKey;
        this.cacheValue = cacheValue;
    }

    public Integer getKeyValuePairId() {
        return keyValuePairId;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public String getCacheValue() {
        return cacheValue;
    }   
    public void setKeyValuePairId(Integer keyValuePairId) {
        this.keyValuePairId = keyValuePairId;
    }       
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }   
    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue;
    }   
}   