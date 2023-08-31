package com.callor.shop.service;

import java.util.List;

import com.callor.shop.models.IolistDto;

public interface IolistService {

	public List<IolistDto> selectAll();

	public int insert(IolistDto dto);

	public IolistDto findByDate(String sdate, String edate);

	public IolistDto findByCode(String code);

	public IolistDto findById(String id);

	public IolistDto findByIdAndDate(String code, String date);

}
