-- 2023-05-30 장바구니

CREATE TABLE tbl_product (
    pCode	VARCHAR2(13)	NOT NULL	PRIMARY KEY,
    pName	nVARCHAR2(50),
    pItem	VARCHAR2(10),		
    pIPrice	NUMBER,
    pOPrice	NUMBER		
);
DROP TABLE tbl_buyer;
CREATE TABLE tbl_buyer (
    buID	VARCHAR2(10)	NOT NULL	PRIMARY KEY,
    buName	nVARCHAR2(20),
    buTel	VARCHAR2(13),	
    buAddr	nVARCHAR2(125)		
);
DROP TABLE tbl_iolist;
CREATE TABLE tbl_iolist (
    ioSEQ	NUMBER		PRIMARY KEY,
    ioDate	VARCHAR2(10),
    ioTime	VARCHAR2(10),		
    ioBuid	VARCHAR2(10)	NOT NULL,
    ioPCode	VARCHAR2(13)	NOT NULL,	
    ioQuan	NUMBER	DEFAULT 0,	
    ioPrice	NUMBER		
);
DROP SEQUENCE tbl_io;

CREATE SEQUENCE SEQ_iolist
START WITH 1 INCREMENT BY 1;

COMMIT;

SELECT * FROM tbl_iolist;

||
