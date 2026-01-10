package com.enterprise.products.form;

import java.util.List;

import com.enterprise.products.entities.Manufacturer;
import com.enterprise.products.entities.OrderDocument;
import com.enterprise.products.entities.Product;

public class OrderDocumentForm {
	
	private List<OrderDocument> resultOrderDocuments;
    private boolean showDetails;
	private OrderDocument orderDocument;
	private Long orderDocumentId;
	
	private List<Manufacturer> manufacturers;
	private List<Product> products;
	
	public List<OrderDocument> getResultOrderDocuments() {
		return resultOrderDocuments;
	}
	public void setResultOrderDocuments(List<OrderDocument> resultOrderDocuments) {
		this.resultOrderDocuments = resultOrderDocuments;
	}
	public boolean isShowDetails() {
		return showDetails;
	}
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
	public OrderDocument getOrderDocument() {
		return orderDocument;
	}
	public void setOrderDocument(OrderDocument orderDocument) {
		this.orderDocument = orderDocument;
	}
	public Long getOrderDocumentId() {
		return orderDocumentId;
	}
	public void setOrderDocumentId(Long orderDocumentId) {
		this.orderDocumentId = orderDocumentId;
	}
	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}
	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
