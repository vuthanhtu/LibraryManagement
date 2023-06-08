package bookstoreonline.web.customercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookstoreonline.model.Cart;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
//        	out.print("add to cart servlet");

            ArrayList<Cart> cartList = new ArrayList<>();
            int book_id = Integer.parseInt(request.getParameter("bid"));
            Cart cm = new Cart();
            cm.setBook_id(book_id);
            cm.setQuantity(1);
            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            if (cart_list == null) {
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("home");
            } else {
                cartList = cart_list;

                boolean exist = false;
                for (Cart c : cart_list) {
                    if(c.getBook_id()==book_id) {
                    	exist = true;
                    	out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart'>GO to Cart Page</a></h3>");
                    }
                    
                    
                }
                if(!exist) {
                	cartList.add(cm);
                	response.sendRedirect("home");
                }
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
