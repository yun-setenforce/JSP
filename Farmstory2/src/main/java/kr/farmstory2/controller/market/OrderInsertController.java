package kr.farmstory2.controller.market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.farmstory2.dto.OrderDTO;
import kr.farmstory2.service.OrderService;

@WebServlet("/market/orderInsert.do")
public class OrderInsertController extends HttpServlet{

	private static final long serialVersionUID = 186917085294839323L;

	private OrderService service = OrderService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		String receiver 	= req.getParameter("receiver");
		String hp 		= req.getParameter("hp");
		String zip 		= req.getParameter("zip");
		String addr1 	= req.getParameter("addr1");
		String addr2 	= req.getParameter("addr2");
		String etc 		= req.getParameter("etc");
		
		String uid = req.getParameter("uid");
		String pNo = req.getParameter("pNo");
		String delivery = req.getParameter("delivery");
		String price = req.getParameter("price");
		String count = req.getParameter("count");
		String finalPrice = req.getParameter("finalPrice");
				
		OrderDTO dto = new OrderDTO();
		
		dto.setOrderProduct(pNo);
		dto.setOrderCount(count);
		dto.setOrderDelivery(delivery);
		dto.setOrderPrice(price);
		dto.setOrderTotal(finalPrice);
		dto.setReceiver(receiver);
		dto.setHp(hp);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setOrderEtc(etc);
		dto.setOrderUser(uid);
		
		logger.info("OrderInsert - OrderDTO : " + dto.toString());
		
		service.insertOrder(dto);
		resp.sendRedirect("/Farmstory2/market/list.do?success=100");
	}
}
