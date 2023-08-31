package com.callor.shop.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.shop.config.DBConnection;
import com.callor.shop.config.DBContract;
import com.callor.shop.models.IolistDto;
import com.callor.shop.service.IolistService;

public class IolistServiceImplV1 implements IolistService {
	protected Connection dbConn;
	protected List<IolistDto> ioList;
	public IolistServiceImplV1() {
		dbConn = DBConnection.getDBConn();
		ioList = new ArrayList<>();
	}
	protected IolistDto result2Dto(ResultSet result) {
		IolistDto ioDto = new IolistDto();
		try {
			ioDto.ioSEQ = result.getInt(DBContract.IOLIST.IO_SEQ);
			ioDto.ioDate = result.getString(DBContract.IOLIST.IO_DATE);
			ioDto.ioTime = result.getString(DBContract.IOLIST.IO_TIME);
			ioDto.ioBuid = result.getString(DBContract.IOLIST.IO_BUID);
			ioDto.ioPCode = result.getString(DBContract.IOLIST.IO_PCODE);
			ioDto.ioQuan = result.getInt(DBContract.IOLIST.IO_QUAN);
			ioDto.ioPrice = result.getInt(DBContract.IOLIST.IO_PRICE);
			return ioDto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//	ioSEQ, ioDate, ioTime, ioBuid, ioPCode, ioQuan, ioPrice
	public List<IolistDto> selectAll() {
		String sql = " SELECT ioSEQ, ioDate, ioTime, ioBuid, ioPCode, ioQuan, ioPrice " + " FROM TBL_iolist "
				+ " ORDER BY ioSEQ ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			ResultSet result = pStr.executeQuery();
			while (result.next()) {
				IolistDto ioDto = result2Dto(result);
				ioList.add(ioDto);
			}
			return ioList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int insert(IolistDto dto) {
		String sql = " INSERT INTO TBL_iolist( " + " ioSEQ, ioDate, ioTime, ioBuid, ioPCode, ioQuan, ioPrice) "
				+ " VALUES( " + " seq_ioList.NextVAL," + "?, ?, ?, ?, ?, ?) ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, dto.ioDate);
			pStr.setString(2, dto.ioTime);
			pStr.setString(3, dto.ioBuid);
			pStr.setString(4, dto.ioPCode);
			pStr.setInt(5, dto.ioQuan);
			pStr.setString(6, dto.ioPCode);
			int result = pStr.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public IolistDto findByDate(String sdate, String edate) {
		String sql = " SELECT ioSEQ, ioDate, ioTime, ioBuid, ioPCode, ioQuan, ioPrice " + " FROM tbl_iolist "
				+ " WHERE ioDate BETWEEN ? AND ? " + " ORDER BY ioDate ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, sdate);
			pStr.setString(2, edate);
			ResultSet result = pStr.executeQuery();
			if (result.next())
				return result2Dto(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public IolistDto findByCode(String code) {
		String sql = " SELECT ioSEQ, ioDate, ioTime, ioBuid, ioPCode, ioQuan, ioPrice " + " FROM tbl_iolist "
				+ " WHERE ioCode = ? " + " ORDER BY ioCode ";
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
	public IolistDto findById(String id) {
		String sql = " SELECT ioSEQ, ioDate, ioTime, ioBuid, ioPCode, ioQuan, ioPrice " + " FROM tbl_iolist "
				+ " WHERE ioBuid = ? " + " ORDER BY ioBuid ";
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
	public IolistDto findByIdAndDate(String code, String date) {
		String sql = " SELECT ioSEQ, ioDate, ioTime, ioBuid, ioPCode, ioQuan, ioPrice " + " FROM tbl_iolist "
				+ " WHERE ioPCode = ? " + " ioDate = ? " + " ORDER BY ioSEQ ";
		try {
			PreparedStatement pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, code);
			pStr.setString(2, date);
			ResultSet result = pStr.executeQuery();
			if (result.next())
				return result2Dto(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
