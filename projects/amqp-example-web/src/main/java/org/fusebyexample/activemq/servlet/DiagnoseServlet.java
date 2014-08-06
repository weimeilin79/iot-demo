package org.fusebyexample.activemq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fusebyexample.activemq.MSGConsumer;
import org.fusebyexample.activemq.MSGProducer;

/**
 * Servlet implementation class DiagnoseServlet
 */
public class DiagnoseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiagnoseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try{
			MSGProducer msgProducer = new MSGProducer("DiagnoseRequest");
			msgProducer.send("User_One");
			Thread.sleep(1000);				
			MSGConsumer consumer = new MSGConsumer("DiagnoseResult");
			
			//MSGProducer resultProducer = new MSGProducer("DiagnoseResult");			
			String result = null;
				
			while(result == null) {	
				result = consumer.getMessage();
				System.out.println("Going into while with result:["+result+"]");
				Thread.sleep(1000);				
				
			}
			if(result != null)
				response.getWriter().write(result);
			System.out.print("Servlet DONE!! ");
			
		}catch(Exception e){
			e.printStackTrace();
		} 
	}

}
