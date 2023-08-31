package com.callor.shop.models;

public class IolistDto {
	public int ioSEQ;
	public String ioDate;
	public String ioTime;
	public String ioBuid;
	public String ioPCode;
	public int ioQuan;
	public int ioPrice;

	public IolistDto() {
		super();
	}

	public IolistDto(int ioSEQ, String ioDate, String ioTime, String ioBuid, String ioPCode, int ioQuan, int ioPrice) {
		super();
		this.ioSEQ = ioSEQ;
		this.ioDate = ioDate;
		this.ioTime = ioTime;
		this.ioBuid = ioBuid;
		this.ioPCode = ioPCode;
		this.ioQuan = ioQuan;
		this.ioPrice = ioPrice;
	}

	public String toString() {
		return "IolistDto [ioSEQ=" + ioSEQ + ", ioDate=" + ioDate + ", ioTime=" + ioTime + ", ioBuid=" + ioBuid
				+ ", ioPCode=" + ioPCode + ", ioQuan=" + ioQuan + ", ioPrice=" + ioPrice + "]";
	}

}
