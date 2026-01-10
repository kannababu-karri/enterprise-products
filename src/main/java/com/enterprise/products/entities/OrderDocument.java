package com.enterprise.products.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB Document
 */
@Document(collection = "order_documents")
@CompoundIndex(def = "{'orderDocumentId': 1}")
public class OrderDocument {
	@Id
    private String orderDocumentId;

	private String documentType;
	private Long orderQtyId;
	private Long userId;
	private String userName;
	private String role;
    private Long manufacturerId;
    private String mfgName;
    private Long productId;
    private String productName;
    private String productDescription;
    private String casNumber;
    private Long quantity;
    private LocalDateTime createdAt;
    
	public String getOrderDocumentId() {
		return orderDocumentId;
	}
	public void setOrderDocumentId(String orderDocumentId) {
		this.orderDocumentId = orderDocumentId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getOrderQtyId() {
		return orderQtyId;
	}
	public void setOrderQtyId(Long orderQtyId) {
		this.orderQtyId = orderQtyId;
	}
	public Long getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getMfgName() {
		return mfgName;
	}
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getCasNumber() {
		return casNumber;
	}
	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
