package controller;

import java.io.IOException;

import javax.naming.ldap.Rdn;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookCRUD;
import dto.Book;
@WebServlet(value="/insert")
public class InsertBook extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book b=new Book();
		b.setBook_id(Integer.parseInt(req.getParameter("id")));
		b.setBook_name(req.getParameter("bookname"));
		b.setAuthor_name(req.getParameter("authorname"));
		b.setnoof_Pages(Integer.parseInt(req.getParameter("pages")));
		b.setPrice(Double.parseDouble(req.getParameter("price")));
		BookCRUD bc=new BookCRUD();
		int result=bc.insertBook(b);
		if(result>0) {
			RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
			req.setAttribute("msg","INSERTED SUCCESSFULLY");
			rd.forward(req, resp);
			
		}else {
			RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
			req.setAttribute("msg", "FAILED TO INSERT");
			rd.forward(req, resp);
		}
	}

}