/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author christina
 * */
package org.fusebyexample.activemq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProducerServlet
 */
public class ProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProducerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DO Nothing
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("willStart");
		String targetName = request.getParameter("targetName");
		System.out.println("willStart:["+action+"]");
		System.out.println("targetName:["+targetName+"]");
		
		SensorSignalHandler handler = (SensorSignalHandler)request.getServletContext().getAttribute(targetName);
		
		
		if(handler == null){
			handler = new SensorSignalHandler();
		}
		
		if(!handler.isStarted() && "Start".equals(action)){
			handler.setStarted(true);
			handler.start(targetName);			
		}else{
			handler.setStarted(false);
			handler.stop();
		}
		
		request.getServletContext().setAttribute(targetName, handler);
	}

}
