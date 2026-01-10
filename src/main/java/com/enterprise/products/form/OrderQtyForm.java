package com.enterprise.products.form;

import java.util.List;

import com.enterprise.products.entities.Manufacturer;
import com.enterprise.products.entities.OrderQty;
import com.enterprise.products.entities.Product;

public class OrderQtyForm {

	private List<OrderQty> resultOrderQtys;
    private boolean showDetails;
	private OrderQty orderQty;
	private Long orderId;
	
	private List<Manufacturer> manufacturers;
	private List<Product> products;
	
	private Manufacturer manufacturer;
	private Product product;
	
	public List<OrderQty> getResultOrderQtys() {
		return resultOrderQtys;
	}
	public void setResultOrderQtys(List<OrderQty> resultOrderQtys) {
		this.resultOrderQtys = resultOrderQtys;
	}
	public boolean isShowDetails() {
		return showDetails;
	}
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
	public OrderQty getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(OrderQty orderQty) {
		this.orderQty = orderQty;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
