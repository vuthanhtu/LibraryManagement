package bookstoreonline.web.customercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bookstoreonline.model.Cart;
/**
 * Servlet implementation class RemoveFromCartController
 */
@WebServlet("/remove-from-cart")
public class RemoveFromCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String bid = request.getParameter("bid");
			if(bid!=null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cart_list!=null) {
					for(Cart c:cart_list) {
						if(c.getBook_id()==Integer.parseInt(bid)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
					response.sendRedirect("cart");
				}
			}else {
				response.sendRedirect("cart");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
