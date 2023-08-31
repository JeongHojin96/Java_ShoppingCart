package com.callor.shop.service;

import java.util.List;

import com.callor.shop.models.ProductDto;

public interface ProductService {

	public List<ProductDto> selectAll();

	public ProductDto findByName(String name);

	public ProductDto findByCode(String code);

	public int insert(ProductDto dto);

	public int update(ProductDto dto);

}
