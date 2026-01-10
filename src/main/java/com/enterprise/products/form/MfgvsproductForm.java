package com.enterprise.products.form;

import java.util.List;

import com.enterprise.products.entities.MfgVsProduct;

public class MfgvsproductForm {
	private List<MfgVsProduct> resultMfgvsproducts;
    private boolean showDetails;
	private MfgVsProduct mfgvsproduct;
	private Long mfgvsproductId;
	public List<MfgVsProduct> getResultMfgvsproducts() {
		return resultMfgvsproducts;
	}
	public void setResultMfgvsproducts(List<MfgVsProduct> resultMfgvsproducts) {
		this.resultMfgvsproducts = resultMfgvsproducts;
	}
	public boolean isShowDetails() {
		return showDetails;
	}
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
	public MfgVsProduct getMfgvsproduct() {
		return mfgvsproduct;
	}
	public void setMfgvsproduct(MfgVsProduct mfgvsproduct) {
		this.mfgvsproduct = mfgvsproduct;
	}
	public Long getMfgvsproductId() {
		return mfgvsproductId;
	}
	public void setMfgvsproductId(Long mfgvsproductId) {
		this.mfgvsproductId = mfgvsproductId;
	}
}
