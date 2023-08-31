package com.callor.shop.models;

public class ProductDto {

	public String pCode; // 상품코드
	public String pName; // 상품명
	public String pItem; // 품목
	public int pIPrice; // 매입단가
	public int pOPrice; // 매출단가

	public ProductDto() {
		super();
	}

	public ProductDto(String pCode, String pName, String pItem, int pIPrice, int pOPrice) {
		super();
		this.pCode = pCode;
		this.pName = pName;
		this.pItem = pItem;
		this.pIPrice = pIPrice;
		this.pOPrice = pOPrice;
	}

	public String toString() {
		return "ProductDto [pCode=" + pCode + ", pName=" + pName + ", pItem=" + pItem + ", pIPrice=" + pIPrice
				+ ", pOPrice=" + pOPrice + "]";
	}

}
