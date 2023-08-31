package com.callor.shop.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.callor.shop.models.BuyerDto;
import com.callor.shop.models.IolistDto;
import com.callor.shop.models.ProductDto;
import com.callor.shop.service.BuyerService;
import com.callor.shop.service.IolistService;
import com.callor.shop.service.ProductService;

public class ShopServiceImplV1 {

	protected List<ProductDto> pdList;
	protected List<BuyerDto> byList;
	protected List<IolistDto> ioList;
	protected final Scanner scan;
	protected final BuyerService bySer;
	protected final ProductService pdSer;
	protected final IolistService ioSer;
	public ShopServiceImplV1() {
		bySer = new BuyerServiceImplV1();
		pdSer = new ProductServiceImplV1();
		ioSer = new IolistServiceImplV1();
		scan = new Scanner(System.in);
	}
	public void printProductList() {
		pdList = pdSer.selectAll();
		System.out.println("=".repeat(100));
		System.out.println("상품 리스트");
		System.out.println("=".repeat(100));
		System.out.println("상품코드\t상품명\t품목\t매입단가\t매출단가");
		System.out.println("-".repeat(100));
		for (ProductDto pdDto : pdList) {
			System.out.printf("%s\t", pdDto.pCode);
			System.out.printf("%s\t", pdDto.pName);
			System.out.printf("%s\t", pdDto.pItem);
			System.out.printf("%s\t", pdDto.pIPrice);
			System.out.printf("%s\n", pdDto.pOPrice);
		}
	}
	public void insertProduct() {
		System.out.println("=".repeat(100));
		System.out.println("상품정보 등록 및 수정[동일 상품코드 입력시 자동으로 상품정보를 수정합니다]");
		System.out.println("=".repeat(100));
		String strCode = "";
		ProductDto pdDto = new ProductDto();
		while (true) {
			System.out.println("상품코드[10자 이내] >> ");
			strCode = scan.nextLine();
			if (strCode.length() > 10) continue;
			else if (strCode.length() > 0 && strCode.length() <= 10) break;
		}
		int dtoNull = 0;
		if (!(pdSer.findByCode(strCode) == null)) {
			System.out.println("동일 상품코드 존재 상품정보를 수정합니다.");
			System.out.println(pdDto.toString());
			dtoNull = 1;
		}
		pdDto.pCode = strCode;
		System.out.println("상품명 >> ");
		String strName = scan.nextLine();
		pdDto.pName = strName;
		System.out.println("품목 >> ");
		String strItem = scan.nextLine();
		pdDto.pItem = strItem;
		System.out.println("매입단가 >> ");
		String strIPrice = scan.nextLine();
		pdDto.pIPrice = Integer.valueOf(strIPrice);
		pdDto.pOPrice = pdDto.pIPrice + (int) Math.round((pdDto.pIPrice * 0.22 / 10.0) * 10);
		if (dtoNull == 0) pdSer.insert(pdDto);
		else if (dtoNull == 1) pdSer.update(pdDto);
		System.out.println("완료");
	}

	public void printBuyerList() {
		byList = bySer.selectAll();
		System.out.println("=".repeat(100));
		System.out.println("고객 리스트");
		System.out.println("=".repeat(100));
		System.out.println("고객ID\t고객이름\t전화번호\t주소");
		System.out.println("-".repeat(100));
		for (BuyerDto byDto : byList) {
			System.out.printf("%s\t", byDto.buID);
			System.out.printf("%s\t", byDto.buName);
			System.out.printf("%s\t", byDto.buTel);
			System.out.printf("%s\n", byDto.buAddr);
		}
	}

	public void insertBuyer() {
		System.out.println("=".repeat(100));
		System.out.println("고객정보 등록 및 수정");
		System.out.println("=".repeat(100));
		BuyerDto byDto = new BuyerDto();

		System.out.print("1. 등록 2. 수정");
		String strSel = scan.nextLine();
		String strID = "";
		int dtoNull = 0;
		if (strSel.equals("1")) {
			strID = String.format("%010d", (Integer.valueOf(bySer.maxNum()) + 1));
			System.out.println("신규 ID : " + strID);
		} else if (strSel.equals("2")) {
			System.out.print("수정할 ID >> ");
			strID = scan.nextLine();
			byDto = bySer.findById(strID);
			if (!(byDto == null)) {
				System.out.println("동일 ID 존재 고객정보를 수정합니다.");
				System.out.println(byDto.toString());
				dtoNull = 1;
			}
		}
		byDto.buID = strID;
		System.out.println("고객이름 >> ");
		String strName = scan.nextLine();
		byDto.buName = strName;
		System.out.println("전화번호 >> ");
		String strTel = scan.nextLine();
		byDto.buTel = strTel;
		System.out.println("주소 >> ");
		String strAddr = scan.nextLine();
		byDto.buAddr = strAddr;

		if (dtoNull == 0)
			bySer.insert(byDto);
		else if (dtoNull == 1)
			bySer.update(byDto);
	}

	public void insertIolist() {
		IolistDto ioDto = new IolistDto();

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat today = new SimpleDateFormat("YYYY-MM-dd");
		ioDto.ioDate = today.format(date);
		SimpleDateFormat nowTime = new SimpleDateFormat("HH:mm:ss");
		ioDto.ioTime = nowTime.format(date);

		System.out.print("고객 ID 입력 >> ");
		String strId = scan.nextLine();
		BuyerDto byDto = bySer.findById(strId);
		if (byDto == null)
			System.out.println("고객이 존재하지 않음");
		else if (!(bySer.findById(strId) == null)) {
			ioDto.ioBuid = strId;
			System.out.print("상품 CODE 입력 >> ");
			String strCode = scan.nextLine();
			ProductDto pdDto = pdSer.findByCode(strCode);
			if (pdDto == null)
				System.out.println("상품이 존재하지 않음");
			else if (!(pdDto == null)) {
				ioDto.ioPCode = strCode;
				System.out.print("상품 갯수 입력 >> ");
				String strQuan = scan.nextLine();
				ioDto.ioQuan = Integer.valueOf(strQuan);
				ioDto.ioPrice = pdDto.pOPrice;
				ioSer.insert(ioDto);
			}
		}
	}

	public void printAllList() {
		ioList = ioSer.selectAll();
		System.out.println("=".repeat(100));
		System.out.println("전체거래 리스트");
		System.out.println("=".repeat(100));
		System.out.println("거래일자\t거래시각\t고객ID\t고객이름\t전화번호\t상품코드\t상품명\t판매단가\t수량\t판매합계");
		System.out.println("-".repeat(100));
		for (IolistDto ioDto : ioList) {
			System.out.printf("%s\t", ioDto.ioDate);
			System.out.printf("%s\t", ioDto.ioTime);
			System.out.printf("%s\t", ioDto.ioBuid);
			BuyerDto byDto = bySer.findById(ioDto.ioBuid);
			System.out.printf("%s\t", byDto.buName);
			System.out.printf("%s\t", byDto.buTel);
			System.out.printf("%s\t", ioDto.ioPCode);
			ProductDto pdDto = pdSer.findByCode(ioDto.ioPCode);
			System.out.printf("%s\t", pdDto.pName);
			System.out.printf("%s\t", ioDto.ioPrice);
			System.out.printf("%s\t", ioDto.ioQuan);
			System.out.println(ioDto.ioPrice * ioDto.ioQuan);
		}
	}

	public void printByBuyerList() {
		ioList = ioSer.selectAll();
		System.out.print("고객이름 입력 >> ");
		String strName = scan.nextLine();
		BuyerDto byDto = bySer.findByName(strName);
		IolistDto ioDto = ioSer.findById(byDto.buID);
		ProductDto pdDto = pdSer.findByCode(ioDto.ioPCode);
		System.out.println("=".repeat(100));
		System.out.println(strName + "님 거래 리스트");
		System.out.println("=".repeat(100));
		System.out.println("거래일자\t거래시각\t고객ID\t고객이름\t전화번호\t상품코드\t상품명\t판매단가\t수량\t판매합계");
		System.out.println("-".repeat(100));
		for (IolistDto dto : ioList) {
			System.out.printf("%s\t", dto.ioDate);
			System.out.printf("%s\t", dto.ioTime);
			System.out.printf("%s\t", dto.ioBuid);
			System.out.printf("%s\t", byDto.buName);
			System.out.printf("%s\t", byDto.buTel);
			System.out.printf("%s\t", dto.ioPCode);
			System.out.printf("%s\t", pdDto.pName);
			System.out.printf("%s\t", dto.ioPrice);
			System.out.printf("%s\t", dto.ioQuan);
			System.out.println(ioDto.ioPrice * ioDto.ioQuan);
		}
	}

	public void printByDateList() {
		ioList = ioSer.selectAll();
		System.out.println("시작날짜 입력 >> ");
		String strSDate = scan.nextLine();
		System.out.println("마지막날짜 입력 >> ");
		String strEDate = scan.nextLine();
		IolistDto ioDto = ioSer.findByDate(strSDate, strEDate);
		System.out.println("=".repeat(100));
		System.out.println(strSDate + " - " + strEDate + " 거래 리스트");
		System.out.println("=".repeat(100));
		System.out.println("거래일자\t거래시각\t고객ID\t고객이름\t전화번호\t상품코드\t상품명\t판매단가\t수량\t판매합계");
		System.out.println("-".repeat(100));
		for (IolistDto dto : ioList) {
			System.out.printf("%s\t", dto.ioDate);
			System.out.printf("%s\t", dto.ioTime);
			System.out.printf("%s\t", dto.ioBuid);
			BuyerDto byDto = bySer.findById(ioDto.ioBuid);
			System.out.printf("%s\t", byDto.buName);
			System.out.printf("%s\t", byDto.buTel);
			System.out.printf("%s\t", dto.ioPCode);
			ProductDto pdDto = pdSer.findByCode(ioDto.ioPCode);
			System.out.printf("%s\t", pdDto.pName);
			System.out.printf("%s\t", dto.ioPrice);
			System.out.printf("%s\t", dto.ioQuan);
			System.out.println(ioDto.ioPrice * dto.ioQuan);
		}
	}

	public void printByPNameList() {
		ioList = ioSer.selectAll();
		System.out.print("상품이름 입력 >> ");
		String strPName = scan.nextLine();
		ProductDto pdDto = pdSer.findByName(strPName);
		IolistDto ioDto = ioSer.findByCode(pdDto.pCode);
		System.out.println("=".repeat(100));
		System.out.println(strPName + " 물품 거래 리스트");
		System.out.println("=".repeat(100));

		for (IolistDto dto : ioList) {
			System.out.printf("%s\t", dto.ioDate);
			System.out.printf("%s\t", dto.ioTime);
			System.out.printf("%s\t", dto.ioBuid);
			BuyerDto byDto = bySer.findById(ioDto.ioBuid);
			System.out.printf("%s\t", byDto.buName);
			System.out.printf("%s\t", byDto.buTel);
			System.out.printf("%s\t", dto.ioPCode);
			System.out.printf("%s\t", pdDto.pName);
			System.out.printf("%s\t", dto.ioPrice);
			System.out.printf("%s\t", dto.ioQuan);
			System.out.println(ioDto.ioPrice * dto.ioQuan);
		}
	}

	public void printByNameAndDate() {
		ioList = ioSer.selectAll();
		System.out.println("고객ID 입력 >> ");
		String strID = scan.nextLine();
		System.out.println("날짜 입력 >> ");
		String strDate = scan.nextLine();
		IolistDto ioDto = ioSer.findByIdAndDate(strID, strDate);
		BuyerDto byDto = bySer.findById(ioDto.ioBuid);
		System.out.println("=".repeat(100));
		System.out.println(byDto.buName + "님" + ioDto.ioDate + "날짜 구매 리스트");
		System.out.println("=".repeat(100));
		System.out.println("거래일자\t거래시각\t고객ID\t고객이름\t전화번호\t상품코드\t상품명\t판매단가\t수량\t판매합계");
		System.out.println("-".repeat(100));
		for (IolistDto dto : ioList) {
			System.out.printf("%s\t", dto.ioDate);
			System.out.printf("%s\t", dto.ioTime);
			System.out.printf("%s\t", dto.ioBuid);
			System.out.printf("%s\t", byDto.buName);
			System.out.printf("%s\t", byDto.buTel);
			System.out.printf("%s\t", ioDto.ioPCode);
			ProductDto pdDto = pdSer.findByCode(ioDto.ioPCode);
			System.out.printf("%s\t", pdDto.pName);
			System.out.printf("%s\t", dto.ioPrice);
			System.out.printf("%s\t", dto.ioQuan);
			System.out.println(dto.ioPrice * dto.ioQuan);
		}

	}

}
