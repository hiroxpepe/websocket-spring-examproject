/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hiroxpepe.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author hiroxpepe
 */
public class SpringWebSocketServlet extends WebSocketServlet {
    
    private static final long serialVersionUID = -8712872385957386182L;
   
    private ApplicationContext context;
    
    @Override
    public void init() throws ServletException {
        try {
            // trying to load the Spring context file.
            // it's declared in Servlet web.xml.
            ServletConfig sc = getServletConfig();
            String configLocation = sc.getInitParameter("contextConfigLocation");
            context = new FileSystemXmlApplicationContext(
                getServletContext().getRealPath("/")+ "/" + configLocation
            );
            // must call the superclass init method.
            super.init();
        }
        catch (ServletException se) {
            throw se;
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest httpServletRequest, String s) {
        // a WebSocket objects that are declared in Spring context file will be returned.
        return context.getBean(WebSocket.class);
    }
    
}
