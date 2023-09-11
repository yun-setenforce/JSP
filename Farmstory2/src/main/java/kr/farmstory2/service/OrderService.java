package kr.farmstory2.service;

import kr.farmstory2.dto.OrderDTO;
import kr.farmstory2.dto.ProductDTO;

import java.util.List;

import kr.farmstory2.dao.OrderDAO;

public enum OrderService {
	INSTANCE;
	
	OrderDAO dao = OrderDAO.getInsetnace();

	public void insertOrder(OrderDTO dto) {
		dao.insertOrder(dto);
	}
	
	public OrderDTO selectOrder(int orderNo) {
		return dao.selectOrder(orderNo);
	}

	public List<OrderDTO> selectOrders(int start) {
		return dao.selectOrders(start);
	}

	public void updateOrder(OrderDTO dto) {
		dao.updateOrder(dto);
	}
	
	public void deleteOrder(String orderNo) {
		dao.deleteOrder(orderNo);
	}
	

	public int selectCountOrders() {
		return dao.selectCountOrders();
	}
	public List<OrderDTO> selectLatestOrders(int size) {
		return dao.selectLatestOrders(size);
	}
}
