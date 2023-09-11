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
import kr.farmstory2.dto.ProductDTO;
import kr.farmstory2.dto.UserDTO;
import kr.farmstory2.service.OrderService;
import kr.farmstory2.service.ProductService;
import kr.farmstory2.service.UserService;

@WebServlet(value = {"/admin", "/admin/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 7936707976664594115L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService productService = ProductService.INSTANCE;
	private OrderService orderService = OrderService.INSTANCE;
	private UserService userService = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		List<ProductDTO> latests1 = productService.selectLatestProducts(3);
		List<OrderDTO> latests2 = orderService.selectLatestOrders(3);
		List<UserDTO> latests3 = userService.selectLatestUsers(3);
		
		req.setAttribute("latests1", latests1);
		req.setAttribute("latests2", latests2);
		req.setAttribute("latests3", latests3);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}
}

