package collection;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Cash_Collection")
public class Cash_Collection extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw= response.getWriter();
		response.setContentType("text/html");
		
		try {
			
			
			String retailer_id=request.getParameter("retailer_id");
			String retailer_name =request.getParameter("retailer_name");
			String retailer_area =request.getParameter("retailer_area");
			String retailer_add = request.getParameter("retailer_add");
			String product_name = request.getParameter("product_name");
			String product_quantity = request.getParameter("product_quantity");
			
			String bill_amount=request.getParameter("bill_amount");
			String paid_amount=request.getParameter("paid_amount");
			String pend_amount=request.getParameter("pend_amount");
			String status=request.getParameter("status");
		
			String collection_person = request.getParameter("collection_person");
	        String date =request.getParameter("date");
	        
					
			
  Class.forName("com.mysql.jdbc.Driver");
	        
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine","root","root");
			
               PreparedStatement ps=con.prepareStatement("insert into cash_collection values(?,?,?,?,?,?,?,?,?,?,?,?)");
			
            ps.setString(1,retailer_id);   
			ps.setString(2,retailer_name);
			ps.setString(3,retailer_area);
			ps.setString(4,retailer_add);
			ps.setString(5,product_name);
            ps.setString(6,product_quantity);
            
            
            ps.setString(7,bill_amount);
            ps.setString(8,paid_amount);
            ps.setString(9,pend_amount);
            ps.setString(10,status);
            
            
            ps.setString(11,collection_person);
            ps.setString(12,date);
		
            
            
			int i=ps.executeUpdate();
			
			
			if(i!=0){
				
				
				//System.out.println("databse connected");
				//pw.println("insert sucess.........");
				
				//response.sendRedirect("accept_order.html");
				 
				 pw.println("<script type=\"text/javascript\">");
			       pw.println("alert('Your Cash is accepted successfully');");
			       pw.println("</script>");
				
				
				
	              RequestDispatcher rd = request.getRequestDispatcher("cash_collection.html");		  
			        rd.include(request,response);
			
			
			}
			else
			{
				
				System.out.println("database not connected");
				pw.println("failed");
			}
			
			
			

	        
	        
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		
		
	}

}
