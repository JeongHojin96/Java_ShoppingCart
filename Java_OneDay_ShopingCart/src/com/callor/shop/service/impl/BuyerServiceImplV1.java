package com.callor.shop.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.shop.config.DBConnection;
import com.callor.shop.config.DBContract;
import com.callor.shop.models.BuyerDto;
import com.callor.shop.service.BuyerService;

public class BuyerServiceImplV1 implements BuyerService {

	protected List<BuyerDto> byList;
	protected Connection dbConn;

	public BuyerServiceImplV1() {
		byList = new ArrayList<>();
		dbConn = DBConnection.getDBConn();
	}

//	buID, buName, buTel, buAddr
	protected BuyerDto result2Dto(ResultSet result) {
		BuyerDto byDto = new BuyerDto();
		try {
			byDto.buID = result.getString(DBContract.BUYER.BU_ID);
			byDto.buName = result.getString(DBContract.BUYER.BU_NAME);
			byDto.buTel = result.getString(DBContract.BUYER.BU_TEL);
			byDto.buAddr = result.getString(DBContract.BUYER.BU_ADDR);
			return byDto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<BuyerDto> selectAll() {
		String sql = " SELECT buID, buName, buTel, buAddr " + " FROM TBL_buyer " + " ORDER BY buName ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			ResultSet result = pStr.executeQuery();
			while (result.next()) {
				BuyerDto byDto = result2Dto(result);
				byList.add(byDto);
			}
			return byList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public BuyerDto findById(String id) {
		String sql = " SELECT buID, buName, buTel, buAddr " + " FROM tbl_buyer " + " WHERE buID = ? ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, id);
			ResultSet result = pStr.executeQuery();
			if (result.next())
				return result2Dto(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public BuyerDto findByName(String name) {
		String sql = " SELECT buID, buName, buTel, buAddr " + " FROM tbl_buyer "
				+ " WHERE buName LIKE '%' || ? || '%' ";
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
	public int insert(BuyerDto dto) {
		String sql = " INSERT INTO TBL_buyer( " + " buID, buName, buTel, buAddr) " + " VALUES( " + " ?, ?, ?, ?) ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, dto.buID);
			pStr.setString(2, dto.buName);
			pStr.setString(3, dto.buTel);
			pStr.setString(4, dto.buAddr);
			int result = pStr.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int update(BuyerDto dto) {
		String sql = " UPDATE TBL_buyer " + " SET " + " buName = ?," + " buTel = ?," + " buAddr = ? "
				+ " WHERE buID = ?";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, dto.buName);
			pStr.setString(2, dto.buTel);
			pStr.setString(3, dto.buAddr);
			pStr.setString(4, dto.buID);
			int result = pStr.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String maxNum() {
		String sql = " SELECT buid " + " FROM tbl_buyer ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			ResultSet result = pStr.executeQuery();
			if (result.next()) {
				String maxNum = result.getString(1);
				if (maxNum == null) return "0";
				else return maxNum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "0";
	}
}
