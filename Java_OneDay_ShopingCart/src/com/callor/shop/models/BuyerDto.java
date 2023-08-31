package com.callor.shop.models;

public class BuyerDto {

	public String buID;
	public String buName;
	public String buTel;
	public String buAddr;

	public BuyerDto() {
		super();
	}

	public BuyerDto(String buID, String buName, String buTel, String buAddr) {
		super();
		this.buID = buID;
		this.buName = buName;
		this.buTel = buTel;
		this.buAddr = buAddr;
	}

	public String toString() {
		return "BuyerDto [buID=" + buID + ", buName=" + buName + ", buTel=" + buTel + ", buAddr=" + buAddr + "]";
	}

}
