package kr.farmstory2.service;

import java.util.ArrayList;
import java.util.List;

import kr.farmstory2.dao.ProductDAO;
import kr.farmstory2.db.SQL;
import kr.farmstory2.dto.OrderDTO;
import kr.farmstory2.dto.ProductDTO;

public enum ProductService {
	INSTANCE;
	
	private ProductDAO dao = ProductDAO.getInstance();
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	public ProductDTO selectProduct(String pNo) {
		return dao.selectProduct(pNo);
	}
	public List<ProductDTO> selectProducts(int start) {		
		return dao.selectProducts(start);
	}

	public List<ProductDTO> selectProducts(String type, int start) {
		return dao.selectProducts(type, start);
	}
	public void UpdateProduct(ProductDTO dto) {
		dao.UpdateProduct(dto);
	}
	public void deleteProductWithDraw(String pNo) {
		dao.deleteProductWithDraw(pNo);
	}

	// 추가
	public int selectCountProductsTotal() {
		return dao.selectCountProductsTotal();
	}

	public int selectCountProductsTotal(String type) {
		return dao.selectCountProductsTotal(type);
	}
	public List<ProductDTO> selectLatestProducts(int size) {
		return dao.selectLatestProducts(size);
	}
		
}
