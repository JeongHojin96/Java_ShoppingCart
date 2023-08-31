package com.callor.shop.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.shop.config.DBConnection;
import com.callor.shop.config.DBContract;
import com.callor.shop.models.ProductDto;
import com.callor.shop.service.ProductService;

public class ProductServiceImplV1 implements ProductService {

	protected List<ProductDto> pdList;
	protected Connection dbConn;

	public ProductServiceImplV1() {
		pdList = new ArrayList<>();
		dbConn = DBConnection.getDBConn();
	}
	protected ProductDto result2Dto(ResultSet result) {
		ProductDto pdDto = new ProductDto();
		try {
			pdDto.pCode = result.getString(DBContract.PRODCUT.P_CODE);
			pdDto.pName = result.getString(DBContract.PRODCUT.P_NAME);
			pdDto.pItem = result.getString(DBContract.PRODCUT.P_ITEM);
			pdDto.pIPrice = result.getInt(DBContract.PRODCUT.P_IPRICE);
			pdDto.pOPrice = result.getInt(DBContract.PRODCUT.P_OPRICE);
			return pdDto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// pCode, pName, pItem, pIPrice, pOPrice
	public List<ProductDto> selectAll() {
		String sql = " SELECT pCode, pName, pItem, pIPrice, pOPrice " + " FROM TBL_PRODUCT " + " ORDER BY PCODE ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			ResultSet result = pStr.executeQuery();
			while (result.next()) {
				ProductDto pdDto = result2Dto(result);
				pdList.add(pdDto);
			}
			return pdList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ProductDto findByCode(String code) {
		String sql = " SELECT pCode, pName, pItem, pIPrice, pOPrice " + " FROM tbl_product" + " WHERE pCode = ? ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, code);
			ResultSet result = pStr.executeQuery();
			if (result.next())
				return result2Dto(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ProductDto findByName(String name) {
		String sql = " SELECT pCode, pName, pItem, pIPrice, pOPrice " + " FROM tbl_product"
				+ " WHERE pName LIKE '%' || ? || '%' ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet result = pStr.executeQuery();
			if (result.next())
				return result2Dto(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int insert(ProductDto dto) {
		String sql = " INSERT INTO tbl_product( " + " pCode, pName, pItem, pIPrice, pOPrice) " + " VALUES( "
				+ " ?, ?, ?, ?, ?) ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, dto.pCode);
			pStr.setString(2, dto.pName);
			pStr.setString(3, dto.pItem);
			pStr.setInt(4, dto.pIPrice);
			pStr.setInt(5, dto.pOPrice);
			int result = pStr.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int update(ProductDto dto) {
		String sql = " UPDATE tbl_product " + " SET " + " pName = ?," + " pItem = ?," + " pIPrice = ?,"
				+ " pOPrice = ? " + " WHERE pCode = ?";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, dto.pName);
			pStr.setString(2, dto.pItem);
			pStr.setInt(3, dto.pIPrice);
			pStr.setInt(4, dto.pOPrice);
			pStr.setString(5, dto.pCode);
			int result = pStr.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
