package kr.farmstory2.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.OrderDTO;
import kr.farmstory2.service.OrderService;

@WebServlet("/admin/orderList.do")
public class OrderListController extends HttpServlet{

	private static final long serialVersionUID = 7936707976664594115L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private OrderService service = OrderService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg   = req.getParameter("pg");
		
		
		// 페이지 관련 변수 선언
		int start = 0;
		int currentPage = 1;
		int total = 0;
		int lastPageNum = 0;
		int pageGroupCurrent = 1;
		int pageGroupStart = 1;
		int pageGroupEnd = 0;
		int pageStartNum = 0;
		
		// 현재 페이지 계산
		if(pg != null){
			currentPage = Integer.parseInt(pg);
		}
		
		// Limit 시작값 계산
		start = (currentPage - 1) * 10;
		
		// 전체 상품 갯수
		total = service.selectCountOrders();
		
		// 페이지 번호 계산
		if(total % 10 == 0){
			lastPageNum = (total / 10);
		}else{
			lastPageNum = (total / 10) + 1;
		}
		
		// 페이지 그룹 계산
		pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
		pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
		pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}

		List<OrderDTO> orders = service.selectOrders(start);
		
		req.setAttribute("total", total);
		req.setAttribute("orders", orders);
		
		//페이지
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", pageGroupStart);
		req.setAttribute("pageGroupEnd", pageGroupEnd);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("orderList.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] chks = req.getParameterValues("chk");
		
		for(String orderNo : chks){
			service.deleteOrder(orderNo);
		}

		
		resp.sendRedirect("/Farmstory2/admin/orderList.do");
	}
}
