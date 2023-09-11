package kr.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.db.DBHelper;
import kr.farmstory2.db.SQL;
import kr.farmstory2.dto.ProductDTO;

public class ProductDAO extends DBHelper{
	//싱글톤 
	private ProductDAO () {}
	private static ProductDAO instance = new ProductDAO();
	public static ProductDAO getInstance() {
		return instance;
	}
	
	//로그 
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//기본 CRUD
	public void insertProduct(ProductDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1, dto.getType());
			psmt.setString(2, dto.getpName());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getDelivery());
			psmt.setInt(5, dto.getStock());
			psmt.setString(6, dto.getThumb1());
			psmt.setString(7, dto.getThumb2());
			psmt.setString(8, dto.getThumb3());
			psmt.setString(9, dto.getSeller());
			psmt.setString(10, dto.getEtc());
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			logger.error("insertProduct() error : " + e.getMessage());
		}
		
	}
	public ProductDTO selectProduct(String pNo) {
		ProductDTO dto = new ProductDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, pNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setpName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb1(rs.getString(8));
				dto.setThumb2(rs.getString(9));
				dto.setThumb3(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
			}
			close();
			
		}catch (Exception e) {
			logger.error("selectProduct() error : " + e.getMessage());
		}
		return dto;
	}
	public List<ProductDTO> selectProducts(int start) {		
		return null;
	}


	public List<ProductDTO> selectProducts(String type, int start) {
		
		List<ProductDTO> products = new ArrayList<>();
		try {
			conn = getConnection();
			
			if(type.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_ALL);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_TYPE);
				psmt.setString(1, type);
				psmt.setInt(2, start);
			}
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setpName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb1(rs.getString(8));
				dto.setThumb2(rs.getString(9));
				dto.setThumb3(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
				products.add(dto);
			}
			close();
		}catch (Exception e) {
			logger.error("selectProducts() error : " + e.getMessage());
		}
		return products;
	}
	
	
	public void UpdateProduct(ProductDTO dto) {
		
	}
	public void deleteProductWithDraw(String pNo) {
		try {

			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_PRODUCT_FOR_WITHDRAW);
			psmt.setString(1, pNo);
			psmt.executeUpdate();
			
			close();
			
		}catch (Exception e) {
			logger.error("deleteProductWithDraw() error : " + e.getMessage());
		}
	}

	// 추가
	public int selectCountProductsTotal() {
		return 0;
	}

	public int selectCountProductsTotal(String type) {int total = 0;
	
		try {
			conn = getConnection();
			if(type.equals("0")) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_ALL);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_TYPE);
				psmt.setString(1, type);
			}
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
			
		}catch (Exception e) {
			logger.error("selectCountProductsTotal() error : " + e.getMessage());
		}
		
		return total;
	}
	
	//admin

	public List<ProductDTO> selectLatestProducts(int size) {
		
		List<ProductDTO> products = new ArrayList<>();
		try {
			conn = getConnection();
			
			
			psmt = conn.prepareStatement(SQL.SELECT_LATEST_PRODUCTS);
			psmt.setInt(1, size);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setpName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb1(rs.getString(8));
				dto.setThumb2(rs.getString(9));
				dto.setThumb3(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
				products.add(dto);
			}
			close();
		}catch (Exception e) {
			logger.error("selectLatestProducts() error : " + e.getMessage());
		}
		return products;
	}
	
}
