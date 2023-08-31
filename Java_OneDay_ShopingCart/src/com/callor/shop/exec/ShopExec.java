package com.callor.shop.exec;

import java.util.Scanner;

import com.callor.shop.service.impl.ShopServiceImplV1;

public class ShopExec {

	public static void main(String[] args) {
		ShopServiceImplV1 shopSer = new ShopServiceImplV1();
		Scanner scan = new Scanner(System.in);
		System.out.println("=".repeat(100));
		System.out.println("빛나라 쇼핑몰 2023");
		System.out.println("=".repeat(100));
		while (true) {
			System.out.println("상품관리");
			System.out.println("-".repeat(100));
			System.out.println("\t1. 상품리스트");
			System.out.println("\t2. 상품등록 및 수정"
					+ "");
			System.out.println("-".repeat(100));
			System.out.println("\t3. 고객리스트");
			System.out.println("\t4. 고객등록 및 수정");
			System.out.println("-".repeat(100));
			System.out.println("\t5. 장바구니 상품 담기");
			System.out.println("\t6. 구매자별 장바구니 리스트 보기");
			System.out.println("\t7. 장바구니 전체 리스트 보기");
			System.out.println("-".repeat(100));
			System.out.println("QUIT. 끝내기");
			System.out.println("=".repeat(100));
			System.out.print("업무선택 >> ");
			String strSelect = scan.nextLine();

			int intSelect = 0;
			try {
				intSelect = Integer.valueOf(strSelect);
			} catch (Exception e) {
				System.out.printf("업무 선택을 잘못 하였습니다(%s).", strSelect);
				continue;
			}
			if (strSelect.equals("QUIT"))
				break;
			else if (intSelect == 1)shopSer.printProductList();
			else if (intSelect == 2)shopSer.insertProduct();
			else if (intSelect == 3)shopSer.printBuyerList();
			else if (intSelect == 4)shopSer.insertBuyer();
			else if (intSelect == 5)shopSer.insertIolist();
			else if (intSelect == 6)shopSer.printByBuyerList();
			else if (intSelect == 7) {
				shopSer.printAllList();
				while (true) {
					System.out.println("1. 기간별 2. 상품별 3. 고객+거래기간");
					String strSel = scan.nextLine();
					int intSel = 0;
					try {
						intSel = Integer.valueOf(strSel);
					} catch (Exception e) {
						System.out.printf("업무 선택을 잘못 하였습니다(%s).", strSel);
						continue;
					}
					if (strSelect.equals("QUIT"))break;
					else if (intSel == 1)shopSer.printByDateList();
					else if (intSel == 2)shopSer.printByPNameList();
					else if (intSel == 3)shopSer.printByNameAndDate();
				}
			}
		}
		System.out.println("업무종료");
	}

}
