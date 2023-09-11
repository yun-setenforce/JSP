package kr.farmstory2.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.farmstory2.dto.ProductDTO;
import kr.farmstory2.service.ProductService;

@WebServlet("/admin/productRegister.do")
public class ProductRegisterController extends HttpServlet{

	private static final long serialVersionUID = 7936707976664594115L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService service = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("productRegister.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 업로드 경로 구하기 
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/thumb");
		
		int size = 1024 * 1024 * 10;
		MultipartRequest mr = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());

		String productName = mr.getParameter("productName");
		String type        = mr.getParameter("type");
		String price       = mr.getParameter("price");
		String delivery    = mr.getParameter("delivery");
		String stock       = mr.getParameter("stock");
		String thumb1      = mr.getOriginalFileName("thumb1");
		String thumb2      = mr.getOriginalFileName("thumb2");
		String thumb3      = mr.getOriginalFileName("thumb3");
		String seller      = mr.getParameter("seller");
		String etc         = mr.getParameter("etc");

		ProductDTO dto = new ProductDTO(path);
		dto.setpName(productName);
		dto.setType(type);
		dto.setPrice(price);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		dto.setThumb1ForRename(thumb1);
		dto.setThumb2ForRename(thumb2);
		dto.setThumb3ForRename(thumb3);
		dto.setSeller(seller);
		dto.setEtc(etc);
		
		service.insertProduct(dto);
		
		resp.sendRedirect("/Farmstory2/admin/productList.do");
	}
}
