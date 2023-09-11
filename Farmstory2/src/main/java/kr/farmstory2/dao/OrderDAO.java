package kr.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.db.SQL;
import kr.farmstory2.dto.OrderDTO;
import kr.farmstory2.db.DBHelper;

public class OrderDAO extends DBHelper{
	
	private static OrderDAO instance = new OrderDAO();
	public static OrderDAO getInsetnace() {
		return instance;
	}
	private OrderDAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 기본 CRUD
	public void insertOrder(OrderDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER);
			psmt.setInt(1, dto.getOrderProduct());
			psmt.setInt(2, dto.getOrderCount());
			psmt.setInt(3, dto.getOrderDelivery());
			psmt.setInt(4, dto.getOrderPrice());
			psmt.setInt(5, dto.getOrderTotal());
			psmt.setString(6, dto.getReceiver());
			psmt.setString(7, dto.getHp());
			psmt.setString(8, dto.getZip());
			psmt.setString(9, dto.getAddr1());
			psmt.setString(10, dto.getAddr2());
			psmt.setString(11, dto.getOrderEtc());
			psmt.setString(12, dto.getOrderUser());
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			logger.error("insertOrder() error : " + e.getMessage());
		}
	}
	
	public OrderDTO selectOrder(int orderNo) {
		return null;
	}
	
	public List<OrderDTO> selectOrders(int start) {
		
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDERS);
			psmt.setInt(1, start);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO order = new OrderDTO();
				order.setOrderNo(rs.getInt(1));
				order.setOrderProduct(rs.getInt(2));
				order.setOrderCount(rs.getInt(3));
				order.setOrderDelivery(rs.getInt(4));
				order.setOrderPrice(rs.getInt(5));
				order.setOrderTotal(rs.getInt(6));
				order.setReceiver(rs.getString(7));
				order.setHp(rs.getString(8));
				order.setZip(rs.getString(9));
				order.setAddr1(rs.getString(10));
				order.setAddr2(rs.getString(11));
				order.setOrderEtc(rs.getString(12));
				order.setOrderUser(rs.getString(13));
				order.setOrderDate(rs.getString(14));
				order.setpName(rs.getString(15));
				order.setThumb1(rs.getString(16));
				order.setOrderUserName(rs.getString(17));
				orders.add(order);
			}
			close();
		}catch (Exception e) {
			logger.error("selectOrders() error : " + e.getMessage());
		}
		
		return orders;
	}
	
	public void updateOrder(OrderDTO dto) {
		
	}
	
	public void deleteOrder(String orderNo) {
		try {

			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ORDER);
			psmt.setString(1, orderNo);
			psmt.executeUpdate();
			
			close();
			
		}catch (Exception e) {
			logger.error("deleteOrder() error : " + e.getMessage());
		}
	}
	
	
	public int selectCountOrders() {
		int total = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_ORDERS);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
			
		}catch (Exception e) {
			logger.error("selectCountOrders() error : " + e.getMessage());
		}
		return total;
	}
	
	//admin

	public List<OrderDTO> selectLatestOrders(int size) {
		
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_LATEST_ORDERS);
			psmt.setInt(1, size);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO order = new OrderDTO();
				order.setOrderNo(rs.getInt(1));
				order.setOrderProduct(rs.getInt(2));
				order.setOrderCount(rs.getInt(3));
				order.setOrderDelivery(rs.getInt(4));
				order.setOrderPrice(rs.getInt(5));
				order.setOrderTotal(rs.getInt(6));
				order.setReceiver(rs.getString(7));
				order.setHp(rs.getString(8));
				order.setZip(rs.getString(9));
				order.setAddr1(rs.getString(10));
				order.setAddr2(rs.getString(11));
				order.setOrderEtc(rs.getString(12));
				order.setOrderUser(rs.getString(13));
				order.setOrderDate(rs.getString(14));
				order.setpName(rs.getString(15));
				order.setOrderUserName(rs.getString(16));
				orders.add(order);
			}
			close();
		}catch (Exception e) {
			logger.error("selectLatestOrders() error : " + e.getMessage());
		}
		
		return orders;
	}
}
