package com.callor.shop.config;

public class DBContract {
	public static final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER_NAME = "C##myuser";
	public static final String PASSWORD = "12341234";

//	pCode,pName,pItem,pIPrice,pOPrice

	public static class PRODCUT {
		public static final int P_CODE = 1;
		public static final int P_NAME = 2;
		public static final int P_ITEM = 3;
		public static final int P_IPRICE = 4;
		public static final int P_OPRICE = 5;
	}

	public static class BUYER {
		public static final int BU_ID = 1;
		public static final int BU_NAME = 2;
		public static final int BU_TEL = 3;
		public static final int BU_ADDR = 4;
	}

	public static class IOLIST {
		public static final int IO_SEQ = 1;
		public static final int IO_DATE = 2;
		public static final int IO_TIME = 3;
		public static final int IO_BUID = 4;
		public static final int IO_PCODE = 5;
		public static final int IO_QUAN = 6;
		public static final int IO_PRICE = 7;

	}
}
