package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Book;

public class BookCRUD {
	final static String path="com.mysql.cj.jdbc.Driver";
	final static String address ="jdbc:mysql://localhost:3306/book_store?user=root&password=17shalu03";
public int insertBook(Book b) {
	Connection c=null;
	try {
		Class.forName(path);
		 c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("insert into Book values(?,?,?,?,?)");
		ps.setInt(1, b.getBook_id());
		ps.setString(2, b.getBook_name());
		ps.setString(3, b.getAuthor_name());
		ps.setInt(4, b.getnoof_Pages());
		ps.setDouble(5, b.getPrice());
		return ps.executeUpdate();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return 0;
}
public int deleteBookById(int id) {
	Connection c=null;
	try {
		Class.forName(path);
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("delete from book where book_id=? ");
		ps.setInt(1,id);
		return ps.executeUpdate();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally
	{
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return 0;
	
	
}
public int UpdateBookById(int id,Book b) {
	Connection c=null;
	try {
		Class.forName(path);
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("update book set book_name=?,author_name=?,no of_pages=?,price=? where book_id=?");
		ps.setString(1, b.getBook_name());
		ps.setString(2, b.getAuthor_name());
ps.setInt(3, b.getnoof_Pages());		
ps.setDouble(4,b.getPrice());
ps.setInt(5, id);
		return ps.executeUpdate();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return 0;
	
	
}
public Book getBookById(int id) {
	Connection c=null;
	Book b=null;
	try {
		Class.forName(path);
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("select * from Book where book_id=?");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			b=new Book();
			b.setBook_id(rs.getInt(1));
			b.setBook_name(rs.getNString(2));
			b.setAuthor_name(rs.getString(3));
			b.setnoof_Pages(rs.getInt(4));
			b.setPrice(rs.getDouble(5));
			return b;
			
		}else {
			return b;
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return b;
}
public ArrayList<Book> getAllBooks(){
	Connection c=null;
	ArrayList<Book> sp=new ArrayList<>();
	try {
		Class.forName(path);
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("select * from book");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Book b=new Book();
			b.setBook_id(rs.getInt(1));
			b.setBook_name(rs.getString(2));
			b.setAuthor_name(rs.getString(3));
			b.setnoof_Pages(rs.getInt(4));
			b.setPrice(rs.getDouble(5));
			sp.add(b);
		}
		return sp;
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return null;
}
public boolean batchExecution(ArrayList<Book>books) {
	Connection c=null;
	try {
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("insert into book values(?,?,?,?,?)");
		for (Book b : books) {
			ps.setInt(1, b.getBook_id());
			ps.setString(2, b.getBook_name());
			ps.setString(3, b.getAuthor_name());
			ps.setInt(4, b.getnoof_Pages());
			ps.setDouble(5, b.getPrice());
			ps.addBatch();
		}
		ps.executeBatch();
		return true;
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		Class.forName(path);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return false;
}
}
